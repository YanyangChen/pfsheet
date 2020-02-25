package websams.component.handsontable;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import websams.component.handsontable.config.ColumnConfiguration;
import websams.component.handsontable.config.SorterConfiguration;
import websams.component.handsontable.util.TableColumnBuilder.TableColumn;

@FacesComponent(value = "handsonTable")
public class TableComponent extends UINamingContainer {
	
	private enum Keys {
		columns,
		colHeaders,
		rowHeaders,
		stretchH,
		width,
		height,
		autoWrapRow,
		contextMenu,
		dropdownMenu,
		filters,
		columnSorting,
		multiColumnSorting,
		licenseKey,
	}

	private ObjectMapper mapper = new ObjectMapper();
	
	public String getTableSettingsAsJson() {
		return (String) getStateHelper().get("tableSettingsAsJson");
	}

	public void setTableSettingsAsJson(String tableSettingsAsJson) {
		getStateHelper().put("tableSettingsAsJson", tableSettingsAsJson);
	}
	
	public Object applyAttribute(Object attribute) {
		return attribute;
	}
	
	@Override
	public void encodeBegin(FacesContext context) throws IOException {

		Map<String, Object> attributes = getAttributes();
		Object tableSettings;
		
		tableSettings = exportTableSettingsFromAttributes(attributes);
		
		setTableSettingsAsJson(convertObjectAsJsonString(tableSettings, Object.class));

		super.encodeBegin(context);

	}

	private Object exportTableSettingsFromAttributes(Map<String, Object> attributes) {
		
		Object tableSettings = null;
		
		tableSettings = extractValidAttributes(attributes)
			.entrySet().stream()
			.map(entry -> convertAttributeValue(entry))
			.filter(entry -> entry != null)
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
		return tableSettings;
	}

	private Entry<String, Object> convertAttributeValue(Entry<String, Object> attribute) {
		String attributeName = attribute.getKey();
		
		if (attributeName.equals(Keys.columns.name())) {
			attribute = applyColumnsConfiguration(attribute);
		}
		
		if (attributeName.equals(Keys.columnSorting.name()) || attributeName.equals(Keys.multiColumnSorting.name())) {
			attribute = applySortingConfiguration(attribute);
		} 
		
		return attribute;	
	}
	
	private Entry<String, Object> applySortingConfiguration(Entry<String, Object> attribute) {
		
		Boolean isColumnSorting = (Boolean) attribute.getValue();

		if (!isColumnSorting) {
			return null;
		}
		
		SorterConfiguration sorterConfiguration = new SorterConfiguration();
		sorterConfiguration.setConfig(isColumnSorting);

		return new AbstractMap.SimpleEntry<String, Object>(attribute.getKey(), sorterConfiguration);
		
	}

	private Entry<String, Object> applyColumnsConfiguration(Entry<String, Object> attribute) {
		
		List<TableColumnComponent> columnComponents = getColumnsComponents();
		List<ColumnConfiguration> columnConfiguration = getColumnConfiguration(attribute.getValue());
		
		List<Object> result = new ArrayList<>(); 

		for (TableColumnComponent columnComponent : columnComponents) {

			for (ColumnConfiguration columnConfig : columnConfiguration) {
				columnComponent.mergeColumnConfiguration(columnConfig);
			}
		}
		
		result = columnConfiguration.stream()
					.map(config -> config.getAsMap())
					.collect(Collectors.toList());
		
		return  new AbstractMap.SimpleEntry<String, Object>(attribute.getKey(), result.toArray());

	}

	private List<ColumnConfiguration> getColumnConfiguration(Object tableColumns) {

		TableColumn[] columns = (TableColumn[]) tableColumns;

		return Stream.of(columns).map(ColumnConfiguration::new).collect(Collectors.toList());

	}

	private List<TableColumnComponent> getColumnsComponents() {

		List<TableColumnComponent> columnComponents = new ArrayList<>();

		int childCount = this.getChildCount();

		if (childCount > 0) {
			for (int i = 0; i < childCount; i++) {
				UIComponent child = this.getChildren().get(i);

				if (child instanceof TableColumnComponent) {
					TableColumnComponent columnCompnent = (TableColumnComponent) child;

					if (columnCompnent.getId() != null) {
						columnComponents.add(columnCompnent);
					}
				}
			}
		}

		return columnComponents;

	}

	private <T> String convertObjectAsJsonString(Object data, Class<T> dataType) throws IOException {

		if (data == null || !dataType.isInstance(data)) {
			return null;
		}
		T typedData = dataType.cast(data);

		mapper.setSerializationInclusion(Include.NON_NULL);

		return mapper.writeValueAsString(typedData);

	}
	
	private Map<String, Object> extractValidAttributes(Map<String, Object> attributes) {

		Map<String, Object> result = new HashMap<>();
		
		for (Keys key: Keys.values()) {
			
			Object attributeValue = attributes.get(key.name());
			if (attributeValue != null) {
				result.put(key.name(), attributeValue);
			}
		}
		
		return result;
		
	}

	@Override
	public String getFamily() {
		return UINamingContainer.COMPONENT_FAMILY;
	}
}

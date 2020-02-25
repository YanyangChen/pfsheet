package websams.component.handsontable;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;

import websams.component.handsontable.config.ColumnConfiguration;
import websams.component.handsontable.config.SorterConfiguration;

@FacesComponent(value = "handsonTableColumn")
public class TableColumnComponent extends UINamingContainer {
	
	private enum Keys {
		columnId,
		sortable;
	}

	public String getId() {
		return (String) getAttributes().get(Keys.columnId.name());
	}
	
	public void mergeColumnConfiguration(ColumnConfiguration columnConfig) {
		
		if (columnConfig.getId().equals(this.getId())) {
			addSortingConfig(columnConfig);
		}
		
	}

	private void addSortingConfig(ColumnConfiguration config) {

		Boolean isSortable = (Boolean) getAttributes().get(Keys.sortable.name());

		if (isSortable != null) {
			SorterConfiguration columnSorting = new SorterConfiguration();
			columnSorting.setHeaderAction(isSortable);
			columnSorting.setIndicator(isSortable);

			config.setColumnSorting(columnSorting);
		}

	}

}

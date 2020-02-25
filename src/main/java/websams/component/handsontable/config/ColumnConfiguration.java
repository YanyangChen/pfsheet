package websams.component.handsontable.config;

import java.util.Map;

import lombok.ToString;
import websams.component.handsontable.util.TableColumnBuilder.TableColumn;

@ToString
public class ColumnConfiguration {

	private TableColumn tableColumn;
	private SorterConfiguration columnSorting;

	public ColumnConfiguration(TableColumn column) {
		this.tableColumn = column;
	}
	
	public String getId() {
		if (this.tableColumn == null) {
			return null;
		}
		
		return this.tableColumn.getData();
	}

	public void setColumnSorting(SorterConfiguration columnSorting) {
		this.columnSorting = columnSorting;
	}

	public SorterConfiguration getColumnSorting() {
		return columnSorting;
	}
	
	public Map<String, Object> getAsMap() {
		Map<String, Object> result = this.tableColumn.getAsMap();
		
		if (result == null) {
			return null;
		}
		
		result.put("columnSorting", this.columnSorting);
		
		return result;
	}
}

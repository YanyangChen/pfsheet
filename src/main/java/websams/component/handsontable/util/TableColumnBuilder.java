package websams.component.handsontable.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableColumnBuilder {

	private List<TableColumn> tableColumns = new ArrayList<>();
	private TableColumn tableColumn;
	
	public TableColumnBuilder createTableColumn() {
		tableColumn = new TableColumn();
		return this;
	}
	
	public TableColumnBuilder data(String data) {
		this.tableColumn.setData(data);
		return this;
	}
	
	public TableColumnBuilder type(String type) {
		this.tableColumn.setType(type);
		return this;
	}
	
	public TableColumnBuilder width(int width) {
		this.tableColumn.setWidth(width);
		return this;
	}
	
	public TableColumnBuilder numericFormat(String pattern) {
		this.tableColumn.setNumericFormat(pattern);
		return this;
	}
	
	public TableColumnBuilder dateFormat(String pattern) {
		this.tableColumn.setDateFormat(pattern);
		return this;
	}
	
	public TableColumnBuilder next() {
		this.tableColumns.add(this.tableColumn);
		this.tableColumn = null;
		return this;
	}
	
	public TableColumn[] build() {
		return this.tableColumns.stream().toArray(TableColumn[]::new);
	}

	public class TableColumn {
		private String data;
		private String type;
		private Integer width;
		private NumericFormat numericFormat;
		private String dateFormat;
		
		public void setData(String data) {
			this.data = data;
		}

		public void setType(String type) {
			this.type = type;
		}

		public void setWidth(Integer width) {
			this.width = width;
		}

		public void setNumericFormat(String pattern) {
			this.numericFormat = new NumericFormat(pattern);
		}

		public void setDateFormat(String pattern) {
			this.dateFormat = pattern;
		}

		public String getData() {
			return data;
		}

		public String getType() {
			return type;
		}

		public Integer getWidth() {
			return width;
		}

		public NumericFormat getNumericFormat() {
			return numericFormat;
		}
		
		public String getDateFormat() {
			return dateFormat;
		}
		
		public Map<String, Object> getAsMap() {
			
			Map<String, Object> tableColumnMap = new HashMap<>();
			
			tableColumnMap.put("data", this.getData());
			tableColumnMap.put("type", this.getType());
			tableColumnMap.put("width", this.getWidth());
			tableColumnMap.put("numericFormat", this.getNumericFormat());
			tableColumnMap.put("dateFormat", this.getDateFormat());
			
			return tableColumnMap;
			
		}
	}

	public class NumericFormat {
		private String pattern;

		public NumericFormat(String pattern) {
			this.pattern = pattern;
		}

		public String getPattern() {
			return pattern;
		}
	}
}

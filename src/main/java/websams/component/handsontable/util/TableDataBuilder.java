package websams.component.handsontable.util;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;

public class TableDataBuilder<T> {

	private TableData tableData;
	
	private final Class<T> tableDataType;
	
	public TableDataBuilder(Class<T> tableDataType) {
		this.tableDataType = tableDataType;
	}
	
	private Class<T> getTableDataType() {
		return tableDataType;
	}

	public TableDataBuilder<T> createTableData() {
		this.tableData = new TableData();
		return this;
	}
	
	public TableDataBuilder<T> data(T[] data) {
		this.tableData.setData(data);
		return this;
	}
	
	public TableDataBuilder<T> datePattern(String pattern) {
		this.tableData.setDateFormat(pattern);
		return this;
	}
	
	public TableData build() {
		return this.tableData;
	}
	
	public class TableData {
		private T[] data;
		private String dataInJson;
		private SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
		private ObjectMapper mapper = new ObjectMapper(); 
		
		public T[] getData() {
			return data;
		}
		
		public void setData(T[] data) {
			this.data = data;
		}
		
		public String getDataInJson() {
			
			if (this.data != null) {
				this.dataInJson = convertToJson(this.data);
			}
			
			return dataInJson;
		}

		public void setDataInJson(String dataInJson) {
			this.dataInJson = dataInJson;
			
			if (this.dataInJson != null) {
				this.data = convertToDataObject(this.dataInJson);
			}
		}
		
		public void setDateFormat(String pattern) {
			formatter = new SimpleDateFormat(pattern);
		}
		
		private T[] convertToDataObject(String json) {
			
			mapper.setDateFormat(this.formatter);
			
			T[] object = null;
			ArrayType arrayType = mapper.getTypeFactory().constructArrayType(TableDataBuilder.this.getTableDataType());
			
			try {
				object = mapper.readValue(json, arrayType);
			} catch (IOException e) {
				throw new RuntimeException(String.format("Failed to convert JSON to %s", arrayType.getClass().toString()));
			}
			
			return object;
			
		}
		
		private String convertToJson(T[] data) {
			
			mapper.setDateFormat(this.formatter);
			
			if (data == null) {
				return null;
			}
			
			try {
				return mapper.writeValueAsString(data);
			} catch (JsonProcessingException e) {
				throw new RuntimeException(String.format("Failed to convert %s to JSON", data.getClass().toString()));
			}
		}
	}
	
}

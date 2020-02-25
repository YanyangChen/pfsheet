package websams.jsf.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import websams.component.handsontable.util.TableColumnBuilder;
import websams.component.handsontable.util.TableColumnBuilder.TableColumn;
import websams.component.handsontable.util.TableDataBuilder;
import websams.component.handsontable.util.TableDataBuilder.TableData;

@Named("tableBean")
@ViewScoped
public class TableBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private TableData rows;
	private TableColumn[] columns;
	private String[] colHeaders;
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
	
	@PostConstruct
	public void init() throws ParseException {
		
		TestData[] testData = Stream.of(
				new TestData(1, "EUR", "Euro", 0.9033, "EUR / USD", formatter.parse("2019-08-19"), 0.0026, true),
				new TestData(2, "JPY", "Japanese Yen", 124.3870, "JPY / USD", formatter.parse("2019-08-20"), 0.0001, false),
				new TestData(3, "GBP", "Pound Sterling", 0.6396, "GBP / USD", formatter.parse("2019-08-16"), 0.00, false),
				new TestData(4, "JPY", "Japanese Yen", 124.3870, "JPY / USD", formatter.parse("2019-08-20"), 0.0001, false),
				new TestData(5, "JPY", "Japanese Yen", 124.3870, "JPY / USD", formatter.parse("2019-08-20"), 0.0001, true),
				new TestData(6, "JPY", "Japanese Yen", 124.3870, "JPY / USD", formatter.parse("2019-08-20"), 0.0001, true)
			).toArray(TestData[]::new);
		
		rows = new TableDataBuilder<>(TestData.class)
					.createTableData()
					.data(testData)
					.datePattern("yyyy-mm-dd")
					.build();
		
		columns = new TableColumnBuilder()
							.createTableColumn().data("id").type("numeric").width(40).next()
							.createTableColumn().data("currencyCode").type("text").next()
							.createTableColumn().data("currency").type("text").next()
							.createTableColumn().data("level").type("numeric").numericFormat("0.0000").next()
							.createTableColumn().data("units").type("text").next()
							.createTableColumn().data("asOf").type("date").dateFormat("YYYY-MM-DD").next()
							.createTableColumn().data("changeRate").type("numeric").numericFormat("0.00%").next()
							.createTableColumn().data("isInterested").type("checkbox").next()
							.build();
		
		colHeaders = Stream.of("ID", "Code", "Currency", "Level", "Units", "Date", "Change", "Interested?").toArray(String[]::new);
		
	}

	public TableData getRows() {
		return rows;
	}

	public void setRows(TableData rows) {
		this.rows = rows;
	}

	public TableColumn[] getColumns() {
		return columns;
	}

	public void setColumns(TableColumn[] columns) {
		this.columns = columns;
	}

	public String[] getColHeaders() {
		return colHeaders;
	}

	public void setColHeaders(String[] colHeaders) {
		this.colHeaders = colHeaders;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void udpateTable() {
		
		Random randomGen = new Random();
		int randomIdx = randomGen.nextInt(this.rows.getData().length);
		double randomLevel = randomGen.nextDouble() * 200;
		
		TestData data = (TestData)this.rows.getData()[randomIdx];
		
		data.setLevel(randomLevel);
		
	}
	
}

package websams.component.handsontable.config;

public class SorterConfiguration {

	private boolean indicator;
	private boolean headerAction;
	
	public void setIndicator(boolean indicator) {
		this.indicator = indicator;
	}
	public void setHeaderAction(boolean headerAction) {
		this.headerAction = headerAction;
	}
	
	public boolean isIndicator() {
		return indicator;
	}
	
	public boolean isHeaderAction() {
		return headerAction;
	}
	
	public void setConfig(boolean isSortable) {
		this.indicator = isSortable;
		this.headerAction = isSortable;
	}
}

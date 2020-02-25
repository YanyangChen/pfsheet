package websams.jsf.bean;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestData implements Serializable {

	public TestData(int i, String string, String string2, double d, String string3, Date parse, double e, boolean b) {
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L;

	private long id;
	private String currencyCode;
	private String currency;
	private double level;
	private String units;
	private Date asOf;
	private double changeRate;
	private boolean IsInterested;
	public void setLevel(double randomLevel) {
		// TODO Auto-generated method stub
		
	}
}

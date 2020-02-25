package websams.component.handsontable.model;

import java.io.Serializable;

public class Subjects implements Serializable {

    private static final long serialVersionUID = 1L;
    private String chin;
    private String eng;
    private String gens;
    
    public Subjects(String Chinese) {
    	
		super();
		this.chin = Chinese;
	}
    
	public Subjects() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String lile;
    private String pe;
    
	public String getChin() {
		return chin;
	}
	public void setChin(String chin) {
		this.chin = chin;
	}
	public String getEng() {
		return eng;
	}
	public void setEng(String eng) {
		this.eng = eng;
	}
	public String getGens() {
		return gens;
	}
	public void setGens(String gens) {
		this.gens = gens;
	}
	public String getLile() {
		return lile;
	}
	public void setLile(String lile) {
		this.lile = lile;
	}
	public String getPe() {
		return pe;
	}
	public void setPe(String pe) {
		this.pe = pe;
	}
	
	public boolean isEqual (Subjects o) {
		if(this.getChin() == o.getChin() 
				&& this.getEng() == o.getEng()
				&& this.getGens() == o.getGens()
				&& this.getLile() == o.getLile()
				&& this.getPe() == o.getPe()) {
			return true;
		}
		else {
		return false;}
		
	}

}

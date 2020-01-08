package bll;

public class Gericht {
	private int id;
	private String bezeichnung;
	private double preis;
	public Gericht(int id, String bezeichnung, double preis) {
		super();
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.preis = preis;
	}
	//TESTCHANGE
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public double getPreis() {
		return preis;
	}
	public void setPreis(double preis) {
		this.preis = preis;
	}
	
}

package bll;

public class Produkt {
	private int id;
	private String bezeichnung;
	private double preis;
	private ProduktTyp typ;
	
	public Produkt(int id, String bezeichnung, double preis, ProduktTyp typ) {
		super();
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.preis = preis;
		this.typ = typ;
	}
	
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
	public ProduktTyp getTyp() {
		return typ;
	}
	public void setTyp(ProduktTyp typ) {
		this.typ = typ;
	}
	
}

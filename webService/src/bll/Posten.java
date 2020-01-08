package bll;

public class Posten {
	private int idPosten;
	private int idProdukt;
	private String produktbezeichnung;
	private double preis;
	private ProduktTyp typ;
	
	public Posten(int idPosten, int idProdukt, String produktbezeichnung, double preis, ProduktTyp typ) {
		super();
		this.idPosten = idPosten;
		this.idProdukt = idProdukt;
		this.produktbezeichnung = produktbezeichnung;
		this.preis = preis;
		this.typ = typ;
	}
	
	public int getIdPosten() {
		return idPosten;
	}
	public void setIdPosten(int idPosten) {
		this.idPosten = idPosten;
	}
	public int getIdProdukt() {
		return idProdukt;
	}
	public void setIdProdukt(int idProdukt) {
		this.idProdukt = idProdukt;
	}
	public String getProduktbezeichnung() {
		return produktbezeichnung;
	}
	public void setProduktbezeichnung(String produktbezeichnung) {
		this.produktbezeichnung = produktbezeichnung;
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

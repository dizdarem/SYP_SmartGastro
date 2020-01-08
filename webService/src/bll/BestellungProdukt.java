package bll;

public class BestellungProdukt {
	private int idBestellung;
	private int idTisch;
	private int idPosten;
	private int idProdukt;
	private String produktbezeichnung;
	private double preis;
	private String typ;
	
	public BestellungProdukt(int idBestellung, int idTisch, int idPosten, int idProdukt, String produktbezeichnung,
			double preis, String typ) {
		super();
		this.idBestellung = idBestellung;
		this.idTisch = idTisch;
		this.idPosten = idPosten;
		this.idProdukt = idProdukt;
		this.produktbezeichnung = produktbezeichnung;
		this.preis = preis;
		this.typ = typ;
	}
	public int getIdBestellung() {
		return idBestellung;
	}
	public void setIdBestellung(int idBestellung) {
		this.idBestellung = idBestellung;
	}
	public int getIdTisch() {
		return idTisch;
	}
	public void setIdTisch(int idTisch) {
		this.idTisch = idTisch;
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
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	
}

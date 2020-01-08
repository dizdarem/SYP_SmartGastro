package bll;
import java.util.ArrayList;
import java.sql.Timestamp;

public class BestellungGruppiert {
	private int bestellungId;
	private int tischId;
	private Timestamp bestellZeit;
	private ArrayList<Posten> arrPosten;
	
	public BestellungGruppiert(int bestellungId, int tischId, Timestamp zeit, ArrayList<Posten> arrPosten) {
		super();
		this.bestellungId = bestellungId;
		this.tischId = tischId;
		this.bestellZeit = zeit;
		this.arrPosten = arrPosten;
	}
	
	public int getTischId() {
		return tischId;
	}

	public void setTischId(int tischId) {
		this.tischId = tischId;
	}

	public BestellungGruppiert() {
		super();
	}
	
	public Timestamp getBestellZeit() {
		return bestellZeit;
	}

	public void setBestellZeit(Timestamp bestellZeit) {
		this.bestellZeit = bestellZeit;
	}

	public int getBestellungId() {
		return bestellungId;
	}
	public void setBestellungId(int bestellungId) {
		this.bestellungId = bestellungId;
	}
	public ArrayList<Posten> getArrPosten() {
		return arrPosten;
	}
	public void setArrPosten(ArrayList<Posten> arrPosten) {
		this.arrPosten = arrPosten;
	}
	
}

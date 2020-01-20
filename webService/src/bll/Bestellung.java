package bll;

import java.util.ArrayList;
import java.util.Date;

public class Bestellung {
	private int id;
    private Date zeitstempel;
    private int idTisch;
    private int idTablet;
    private double gesamtpreis;
    private boolean bezahlt;
    private ArrayList<Produkt> produkte;

    public Bestellung(int id, Date zeitstempel, int idTisch, int idTablet, double gesamtpreis, boolean gebracht, boolean bezahlt) {
        this.id = id;
        this.zeitstempel = zeitstempel;
        this.idTisch = idTisch;
        this.idTablet = idTablet;
        this.gesamtpreis = gesamtpreis;
        this.bezahlt = bezahlt;
        produkte = new ArrayList<Produkt>();
    }

    public ArrayList<Produkt> getProdukte() {
		return produkte;
	}

	public void setProdukte(ArrayList<Produkt> produkte) {
		this.produkte = produkte;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getZeitstempel() {
        return zeitstempel;
    }

    public void setZeitstempel(Date zeitstempel) {
        this.zeitstempel = zeitstempel;
    }

    public int getIdTisch() {
        return idTisch;
    }

    public void setIdTisch(int idTisch) {
        this.idTisch = idTisch;
    }

    public int getIdTablet() {
        return idTablet;
    }

    public void setIdTablet(int idTablet) {
        this.idTablet = idTablet;
    }

    public double getGesamtpreis() {
        return gesamtpreis;
    }

    public void setGesamtpreis(double gesamtpreis) {
        this.gesamtpreis = gesamtpreis;
    }

    public boolean isBezahlt() {
        return bezahlt;
    }

    public void setBezahlt(boolean bezahlt) {
        this.bezahlt = bezahlt;
    }
}

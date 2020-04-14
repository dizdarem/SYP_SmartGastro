package at.htl_villach.android_app.bll;

import java.util.ArrayList;

public class Bestellung {
    private int id;
    private String zeitstempel;
    private int idTisch;
    private int idTablet;
    private double gesamtpreis;
    private boolean bezahlt;
    private ArrayList<Produkt> produkte;

    public Bestellung(int id, String zeitstempel, int idTisch, int idTablet, double gesamtpreis, boolean bezahlt, ArrayList<Produkt> produkte) {
        this.id = id;
        this.zeitstempel = zeitstempel;
        this.idTisch = idTisch;
        this.idTablet = idTablet;
        this.gesamtpreis = gesamtpreis;
        this.bezahlt = bezahlt;
        this.produkte = produkte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZeitstempel() {
        return zeitstempel;
    }

    public void setZeitstempel(String zeitstempel) {
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

    @Override
    public String toString() {
        return "Bestellung{" +
                "id=" + id +
                ", zeitstempel=" + zeitstempel +
                ", idTisch=" + idTisch +
                ", idTablet=" + idTablet +
                ", gesamtpreis=" + gesamtpreis +
                ", bezahlt=" + bezahlt +
                ", produkte=" + produkte +
                '}';
    }
}

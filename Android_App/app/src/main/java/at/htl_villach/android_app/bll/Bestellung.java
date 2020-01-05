package at.htl_villach.android_app.bll;

import java.util.ArrayList;
import java.util.Date;

public class Bestellung {
    private int id;
    private Date zeitstempel;
    private int idTisch;
    private int idTablet;
    private double gesamtpreis;
    private boolean gebracht;
    private boolean bezahlt;
    private ArrayList<Produkt> produkte;

    public Bestellung(int id, Date zeitstempel, int idTisch, int idTablet, double gesamtpreis, boolean gebracht, boolean bezahlt) {
        this.id = id;
        this.zeitstempel = zeitstempel;
        this.idTisch = idTisch;
        this.idTablet = idTablet;
        this.gesamtpreis = gesamtpreis;
        this.gebracht = gebracht;
        this.bezahlt = bezahlt;
        produkte = new ArrayList<Produkt>();
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

    public boolean isGebracht() {
        return gebracht;
    }

    public void setGebracht(boolean gebracht) {
        this.gebracht = gebracht;
    }

    public boolean isBezahlt() {
        return bezahlt;
    }

    public void setBezahlt(boolean bezahlt) {
        this.bezahlt = bezahlt;
    }
}

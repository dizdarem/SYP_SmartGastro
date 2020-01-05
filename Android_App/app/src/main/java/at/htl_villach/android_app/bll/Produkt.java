package at.htl_villach.android_app.bll;

public class Produkt {
    private int id;
    private String bezeichnung;
    private double preis;
    private typ typ_produkt;

    public Produkt(int id, String bezeichnung, double preis, typ typ_produkt) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.preis = preis;
        this.typ_produkt = typ_produkt;
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

    public typ getTyp_produkt() {
        return typ_produkt;
    }

    public void setTyp_produkt(typ typ_produkt) {
        this.typ_produkt = typ_produkt;
    }

    @Override
    public String toString() {
        return bezeichnung + "    " +  preis;
    }
}

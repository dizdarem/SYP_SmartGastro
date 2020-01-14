package at.htl_villach.android_app.dal;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import at.htl_villach.android_app.bll.Bestellung;
import at.htl_villach.android_app.bll.Produkt;
import at.htl_villach.android_app.bll.typ;
import at.htl_villach.android_app.services.ServiceBestellungPost;
import at.htl_villach.android_app.services.ServiceProduktGetBeilagen;
import at.htl_villach.android_app.services.ServiceProduktGetDesserts;
import at.htl_villach.android_app.services.ServiceProduktGetSpeisen;

public class DatabaseManager {
    private static DatabaseManager db = null;
    private static String ipHost = "http://192.168.196.36:8080";

    private DatabaseManager() {
    }

    public static DatabaseManager newInstance() {
        if (db == null) {
            db = new DatabaseManager();
        }
        return db;
    }

    public static DatabaseManager newInstance(String ip) {
        if (db == null) {
            db = new DatabaseManager();
        }
        ipHost = ip;
        return db;
    }

    public ArrayList<Produkt> getAllGetraenke() throws Exception {
        Gson gson = new Gson();
        ArrayList<Produkt> list = new ArrayList<>();

        //each call needs an new instance of async !!
        /*ServiceProduktGetGetraenke controller = new ServiceProduktGetGetraenke();
        ServiceProduktGetGetraenke.setIpHost(ipHost);

        controller.execute();
        String strFromWebService = controller.get();
        try {
            Type colltype = new TypeToken<ArrayList<Produkt>>(){}.getType();
            result = gson.fromJson(strFromWebService,colltype);
        } catch (Exception ex) {
            throw new Exception(strFromWebService);
        }*/
        list.add(new Produkt(1,"Coca Cola", 2.50, typ.GETRAENK));
        list.add(new Produkt(2,"Cola Zero", 2.50, typ.GETRAENK));
        list.add(new Produkt(3,"Red Bull Energy", 3.90, typ.GETRAENK));
        list.add(new Produkt(4,"Latte Macchiato", 3.20, typ.GETRAENK));
        list.add(new Produkt(5,"Tee", 2.20, typ.GETRAENK));
        return list;
    }

    public ArrayList<Produkt> getAllBeilagen() throws Exception {
        Gson gson = new Gson();
        ArrayList<Produkt> result;

        //each call needs an new instance of async !!
        ServiceProduktGetBeilagen controller = new ServiceProduktGetBeilagen();
        ServiceProduktGetBeilagen.setIpHost(ipHost);

        controller.execute();
        String strFromWebService = controller.get();
        try {
            Type colltype = new TypeToken<ArrayList<Produkt>>(){}.getType();
            result = gson.fromJson(strFromWebService,colltype);
        } catch (Exception ex) {
            throw new Exception(strFromWebService);
        }

        return result;
    }

    public ArrayList<Produkt> getAllSpeisen() throws Exception {
        Gson gson = new Gson();
        ArrayList<Produkt> result;

        //each call needs an new instance of async !!
        ServiceProduktGetSpeisen controller = new ServiceProduktGetSpeisen();
        ServiceProduktGetSpeisen.setIpHost(ipHost);

        controller.execute();
        String strFromWebService = controller.get();
        try {
            Type colltype = new TypeToken<ArrayList<Produkt>>(){}.getType();
            result = gson.fromJson(strFromWebService,colltype);
        } catch (Exception ex) {
            throw new Exception(strFromWebService);
        }

        return result;
    }

    public ArrayList<Produkt> getAllDesserts() throws Exception {
        Gson gson = new Gson();
        ArrayList<Produkt> result;

        //each call needs an new instance of async !!
        ServiceProduktGetDesserts controller = new ServiceProduktGetDesserts();
        ServiceProduktGetDesserts.setIpHost(ipHost);

        controller.execute();
        String strFromWebService = controller.get();
        try {
            Type colltype = new TypeToken<ArrayList<Produkt>>(){}.getType();
            result = gson.fromJson(strFromWebService,colltype);
        } catch (Exception ex) {
            throw new Exception(strFromWebService);
        }

        return result;
    }

    public int getCurrentIdBestellung() throws Exception {
        Gson gson = new Gson();
        int result;

        //each call needs an new instance of async !!
        ServiceProduktGetDesserts controller = new ServiceProduktGetDesserts();
        ServiceProduktGetDesserts.setIpHost(ipHost);

        controller.execute();
        String strFromWebService = controller.get();
        try {
            Type colltype = new TypeToken<ArrayList<Produkt>>(){}.getType();
            result = gson.fromJson(strFromWebService,colltype);
        } catch (Exception ex) {
            throw new Exception(strFromWebService);
        }

        return result;
    }



    public String addBestellung(Bestellung bestellung) throws Exception {
        Gson gson = new Gson();

        ServiceBestellungPost controller = new ServiceBestellungPost();
        ServiceBestellungPost.setIpHost(ipHost);
        controller.setBestellung(bestellung);
        controller.execute();

        return controller.get();
    }
}

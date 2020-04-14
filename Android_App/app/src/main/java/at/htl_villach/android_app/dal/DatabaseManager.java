package at.htl_villach.android_app.dal;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import at.htl_villach.android_app.bll.Bestellung;
import at.htl_villach.android_app.bll.Produkt;
import at.htl_villach.android_app.services.ServiceBestellungPost;
import at.htl_villach.android_app.services.ServiceProduktGetBeilagen;
import at.htl_villach.android_app.services.ServiceProduktGetDesserts;
import at.htl_villach.android_app.services.ServiceProduktGetGetraenke;
import at.htl_villach.android_app.services.ServiceProduktGetSpeisen;
import at.htl_villach.android_app.services.ServiceTablet;
import at.htl_villach.android_app.services.ServiceTisch;

public class DatabaseManager {
    private static DatabaseManager db = null;
    private static String ipHost = "http://192.168.192.27:8080";

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

    public ArrayList<Integer> getTablets() throws Exception {
        Gson gson = new Gson();
        ArrayList<Integer> result;

        //each call needs an new instance of async !!
        ServiceTablet controller = new ServiceTablet();
        ServiceTablet.setIpHost(ipHost);

        controller.execute();
        String strFromWebService = controller.get();
        try {
            Type colltype = new TypeToken<ArrayList<Integer>>(){}.getType();
            result = gson.fromJson(strFromWebService,colltype);
        } catch (Exception ex) {
            throw new Exception(strFromWebService);
        }
        return result;
    }

    public ArrayList<Integer> getTische() throws Exception {
        Gson gson = new Gson();
        ArrayList<Integer> result;

        //each call needs an new instance of async !!
        ServiceTisch controller = new ServiceTisch();
        ServiceTisch.setIpHost(ipHost);

        controller.execute();
        String strFromWebService = controller.get();
        try {
            Type colltype = new TypeToken<ArrayList<Integer>>(){}.getType();
            result = gson.fromJson(strFromWebService,colltype);
        } catch (Exception ex) {
            throw new Exception(strFromWebService);
        }
        return result;
    }

    public ArrayList<Produkt> getAllGetraenke() throws Exception {
        Gson gson = new Gson();
        ArrayList<Produkt> result;

        //each call needs an new instance of async !!
        ServiceProduktGetGetraenke controller = new ServiceProduktGetGetraenke();
        ServiceProduktGetGetraenke.setIpHost(ipHost);

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
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++ post bestellung databasemanager");
        System.out.println(bestellung.toString());
        return controller.get();
    }
}

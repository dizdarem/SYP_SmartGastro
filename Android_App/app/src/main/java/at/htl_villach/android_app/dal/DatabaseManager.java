package at.htl_villach.android_app.dal;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import at.htl_villach.android_app.bll.Produkt;
import at.htl_villach.android_app.services.ServiceProduktGetGetraenke;

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
}

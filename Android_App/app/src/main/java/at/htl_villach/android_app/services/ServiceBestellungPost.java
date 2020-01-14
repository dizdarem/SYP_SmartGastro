package at.htl_villach.android_app.services;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import at.htl_villach.android_app.bll.Bestellung;

public class ServiceBestellungPost extends AsyncTask<String, Void, String> {
    private static final String URL = "/RestaurantVerwaltung_WebService/SmartGastro/produkte/get/beilage";
    private static String ipHost = null;
    private Bestellung bestellung = null;
    private int id = 0;

    public void setBestellung(Bestellung bestellung ){
        this.bestellung = bestellung;
        this.id = bestellung.getId();
    }


    public static void setIpHost(String ipHost) {
        ServiceBestellungPost.ipHost = ipHost;
    }

    @Override
    protected String doInBackground(String... artikelinfo) {
        java.net.URL url = null;
        HttpURLConnection conn = null;
        BufferedWriter writer = null;
        BufferedReader reader = null;
        String content = null;
        Gson gson = new Gson();

        try {
            url = new java.net.URL(this.ipHost + URL + "/" + id);
            // Öffnen der Connection
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            // setzen des Content-Types auf das JSON Format
            conn.setRequestProperty("Content-Type", "application/json");
            writer = new BufferedWriter( new OutputStreamWriter(( conn.getOutputStream())));
            writer.write(gson.toJson(bestellung));
            writer.flush();
            writer.close();

            // Überprüfen, ob ein Fehler aufgetreten ist, lesen der Fehlermeldung
            if( conn.getResponseCode() != 200){
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while((line = reader.readLine())!= null){
                    sb.append(line);
                }
                content = conn.getResponseCode() + " " + sb.toString();
            }
            else{
                content = "ResponseCode: "+conn.getResponseCode();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try{
                if( reader != null){
                    reader.close();

                }
                writer.close();
                conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;

    }
}

package at.htl_villach.android_app.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServiceProduktGetBeilagen {
    private static final String URL = "/RestaurantVerwaltung_WebService/SmartGastro/produkte/get/beilage";
    private static String ipHost = null;

    public static void setIpHost(String ipHost) {
        ServiceProduktGetBeilagen.ipHost = ipHost;
    }

    @Override
    protected String doInBackground(String... artikelinfo) {
        boolean isError = false;
        java.net.URL url = null;
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String content = null;

        try {
            url = new URL(ipHost + URL);
            conn = (HttpURLConnection) url.openConnection();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            // get data from server
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            content = sb.toString();
            if (isError) {
                throw new Exception(conn.getResponseCode() + "; " + content);
            }
        } catch (Exception ex) {
            content = ex.getMessage();
        } finally {
            try {
                reader.close();
                conn.disconnect();
            } catch (Exception e) {
            }
        }
        return content;

    }
}

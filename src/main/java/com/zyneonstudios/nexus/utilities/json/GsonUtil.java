package com.zyneonstudios.nexus.utilities.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class GsonUtil {

    public static JsonObject getObject(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URI(url).toURL().openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return JsonParser.parseString(response.toString()).getAsJsonObject();
            }
            connection.disconnect();
        } catch (Exception ignore) {}
        return null;
    }

    public static String getFromURL(String urlString) {
        try {
            URL url = new URI(urlString).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();
            return response.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getFromFile(File file) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            return gson.fromJson(reader, JsonObject.class).toString();
        } catch (Exception e) {
            return null;
        }
    }
}

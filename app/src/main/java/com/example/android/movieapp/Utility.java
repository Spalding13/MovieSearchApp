package com.example.android.movieapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Utility {

    private Utility(){}

    private static String LOG_TAG = Utility.class.getName();

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        if (url == null) {
            return jsonResponse;
        }

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error in HTTP request");
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        Log.i(LOG_TAG,jsonResponse);
        return jsonResponse;
    }

    private static String readFromStream(InputStream stream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (stream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(stream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static ArrayList<Movie> jsonParser(String response) {
        ArrayList<Movie> list = new ArrayList<Movie>();
        try {
            Log.i(LOG_TAG,"response: "+response);
            JSONObject root = new JSONObject(response);
            JSONArray respArray = root.getJSONArray("Search");

            for (int i = 0; i < respArray.length(); i++) {
                JSONObject obj = respArray.getJSONObject(i);

                String Title = obj.getString("Title");
                String Year = obj.getString("Year");
                String imageUrl = obj.getString("Poster");

                list.add(new Movie(Title, Year, imageUrl));
            }
            } catch (JSONException e) {
            Log.e(LOG_TAG, "JSON ERROR");
        }
        return list;
    }

    private static URL createURL(String urlStr) throws MalformedURLException {
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Bad URL");
        }
        return url;
    }

    public static  ArrayList<Movie> getMovieData(String urlStr) {
        String jsonResponse = "";
        ArrayList<Movie> list = null;
        URL url = null;
        try {
            url = createURL(urlStr);
            jsonResponse = makeHttpRequest(url);
            list = jsonParser(jsonResponse);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Bad URL");
        }
        return list;
    }


}

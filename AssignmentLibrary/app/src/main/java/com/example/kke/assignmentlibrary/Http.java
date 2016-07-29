package com.example.kke.assignmentlibrary;

import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kke on 2016-07-29.
 */
public class Http {

    String TAG = "AssignmentLibrary";

    public Http() {
    }

    public String get(String params) {
        InputStream inputStream = null;

        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(params);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestMethod("GET");

            int statusCode = urlConnection.getResponseCode();

            if (statusCode ==  200) {

                inputStream = new BufferedInputStream(urlConnection.getInputStream());

                String response = convertInputStreamToString(inputStream);

                return response;

            }else{
                Log.d(TAG, "Result : " + statusCode);
            }

        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage());
        }

        return null;
    }

    private String convertInputStreamToString(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));

        String line = "";
        String result = "";

        while((line = bufferedReader.readLine()) != null){
            result += line;
        }

        if(null!=inputStream){
            inputStream.close();
        }

        return result;
    }
}

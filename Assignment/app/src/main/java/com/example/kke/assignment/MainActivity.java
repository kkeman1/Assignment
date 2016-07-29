package com.example.kke.assignment;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.kke.assignmentlibrary.Http;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Http http = new Http();
        new JsonLoadingTask().execute();

    }

    private class JsonLoadingTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String URL = "https://apis.daum.net/search/book?apikey=f9a60ca01f987d4e9d830a61b654c0df&q=%EC%9C%84%EC%9D%B8&output=json&pageno=1&result=20";
            Http http = new Http();
            http.post(URL);

            parseResult(http.post(URL));

            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
        }


        private void parseResult(String result) {

            try{
                JSONObject response = new JSONObject(result);
                JSONObject response2 = new JSONObject(response.getString("channel"));
                JSONArray Array = new JSONArray(response2.getString("item"));

                String[] blogTitles = new String[Array.length()];

                for(int i=0; i< Array.length();i++ ){
                    JSONObject post = Array.optJSONObject(i);
                    String title = post.optString("title");
                    Log.e("111111111111", title);
                    blogTitles[i] = title;
                }

            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }
}

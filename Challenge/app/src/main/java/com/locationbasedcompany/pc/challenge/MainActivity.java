package com.locationbasedcompany.pc.challenge;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button searchButton;
    private EditText email_user_text;
    private String baseurl = "https://haveibeenpwned.com/api/v2/breachedaccount/";

    private String json_string_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchData();
            }
        });

        email_user_text = findViewById(R.id.email_user_text);
    }

    private void searchData() {
       String toSearch = email_user_text.getText().toString();

       // no data to search
       if(toSearch == null || toSearch == "") {
           return;
       }

       String url = baseurl + toSearch;

       HttpTask httpTask = new HttpTask(url);
       httpTask.execute();
    }

    public void parseJsonData() {
        if(json_string_result == null) {
            return;
        }

        Intent intent = new Intent(this, DisplayListView.class);
        intent.putExtra("json_data", json_string_result);
        startActivity(intent);
    }

    public class HttpTask extends AsyncTask<Void,Void,String> {
        String urlString;
        String JSON_STRING;

        public HttpTask(String urlString) {
            this.urlString = urlString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            json_string_result = s;
            parseJsonData();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void[] objects) {

            try{
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.connect();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuilder stringBuilder = new StringBuilder();

                while((JSON_STRING = reader.readLine())!=null) {
                    stringBuilder.append(JSON_STRING + "\n");
                }

                reader.close();
                connection.disconnect();

                return stringBuilder.toString().trim();

            }catch (MalformedURLException e) {
              System.out.println("error= " + e.getLocalizedMessage());
            } catch (IOException e){
                System.out.println("error " + e.getLocalizedMessage());
            }

            return null;
        }
    }
}

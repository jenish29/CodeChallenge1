package com.locationbasedcompany.pc.challenge;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {

    private String json_string_data;

    private JSONArray jsonArray;
    private ListViewRowAdapter listViewRowAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_list);

        listViewRowAdapter = new ListViewRowAdapter(this, R.layout.list_view_row);
        listView = findViewById(R.id.listView);
        listView.setAdapter(listViewRowAdapter);

        json_string_data = getIntent().getStringExtra("json_data");

        try{
            jsonArray = new JSONArray(json_string_data);

            int count = 0;
            String name, domain, breachDate, description, dataClasses;

            while(count < jsonArray.length()) {
                JSONObject obj = jsonArray.getJSONObject(count);
                name = obj.getString("Title");
                domain = obj.getString("Domain");
                breachDate = obj.getString("BreachDate");
                description = obj.getString("Description");
                dataClasses = obj.getString("DataClasses");

                ListViewRow row = new ListViewRow(name,domain,breachDate,description,dataClasses);
                listViewRowAdapter.add(row);
                count++;
            }


        }catch (JSONException e) {

            Log.d("error = ", e.getLocalizedMessage());
            e.printStackTrace();
        }


        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}

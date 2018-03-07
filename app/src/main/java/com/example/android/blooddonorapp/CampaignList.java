package com.example.android.blooddonorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by user on 10-12-2017.
 */

public class CampaignList extends AppCompatActivity {
    ListView lv;
    Database_Handler_Class db;
    Bundle bundle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_list);
        db = new Database_Handler_Class(getApplicationContext());
        Intent intent = getIntent();
        bundle = intent.getExtras();
        db.getAllCampaignRecord(bundle.getString("State123"));
        ArrayList<String> listadd = new ArrayList<String>();
        ArrayAdapter<String> adapter;
        lv = (ListView) findViewById(R.id.list_campaign);
        adapter = new ArrayAdapter<String>(this, R.layout.activity_textview, listadd);
        lv.setAdapter(adapter);

        for (int i = 0; i < db.count; i++) {
            listadd.add(" Campaign Name: " + db.c_data[i][0] + "\n Venue: " + db.c_data[i][1] + "\n State: " + db.c_data[i][2] + "\n start date: " + db.c_data[i][3]
                    + "\n End date: " + db.c_data[i][4] + "\n EmailId: " + db.c_data[i][5] + "\n Strength: " + db.c_data[i][6] + "\n Description: " + db.c_data[i][7] + "\n Contact: " + db.c_data[i][8]);
            adapter.notifyDataSetChanged();
        }
        Toast.makeText(this, "Search completed", Toast.LENGTH_SHORT).show();


    }
}

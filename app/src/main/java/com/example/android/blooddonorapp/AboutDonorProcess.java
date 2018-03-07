package com.example.android.blooddonorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by user on 02-12-2017.
 */

public class AboutDonorProcess extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_donor_process);
        Button about = (Button) findViewById(R.id.btnproceed);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AboutDonorProcess.this, Donor.class);
                startActivity(i);
            }
        });

    }
}

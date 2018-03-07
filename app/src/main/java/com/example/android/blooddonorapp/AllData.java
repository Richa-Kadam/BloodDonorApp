package com.example.android.blooddonorapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by user on 12-12-2017.
 */

public class AllData extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donors_data);
        android.support.design.widget.TabLayout tabLayout = (TabLayout) findViewById(R.id.tl1);
        android.support.v4.view.ViewPager viewPager = (ViewPager) findViewById(R.id.vp1);
        ViewPagerAdapt v = new ViewPagerAdapt(getSupportFragmentManager());
        v.addFrag(new DonorBloodRecord(), "BloodRecord");
        v.addFrag(new DonorRecord(), "DonorRecord");
        v.addFrag(new CampaignRecord(), "CampRecord");
        viewPager.setAdapter(v);
        tabLayout.setupWithViewPager(viewPager);


    }
}
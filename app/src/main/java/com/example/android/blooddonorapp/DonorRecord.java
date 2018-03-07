package com.example.android.blooddonorapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by user on 28-12-2017.
 */

public class DonorRecord extends Fragment {
    ListView l;
    Database_Handler_Class handlerClass;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_donor_record_list, container, false);
        handlerClass = new Database_Handler_Class(getActivity());
        handlerClass.getAllDonorRecords();
        ArrayList<String> listadd = new ArrayList<String>();
        ArrayAdapter<String> adapter;
        l = (ListView) v.findViewById(R.id.ListDonorData);
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.activity_textview, listadd);
        l.setAdapter(adapter);

        for (int i = 0; i < handlerClass.count; i++) {
            listadd.add("Name : " + handlerClass.d_data[i][0].toString() + "\nAge: " + handlerClass.d_data[i][1].toString() + "\nCity : " + handlerClass.d_data[i][2].toString() + "\nContact : " + handlerClass.d_data[i][3].toString()
                    + "\nEmail: " + handlerClass.d_data[i][4].toString() + "\nRecent Donation Date : " + handlerClass.d_data[i][5].toString() + "\nBloodgroup : " + handlerClass.d_data[i][6].toString() + "\nState : " + handlerClass.d_data[i][7]+"\nDonation Count : " + handlerClass.d_data[i][8]);
            adapter.notifyDataSetChanged();
        }

        return v;
    }

}

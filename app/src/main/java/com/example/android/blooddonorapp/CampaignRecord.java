package com.example.android.blooddonorapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by user on 28-12-2017.
 */

public class CampaignRecord extends Fragment {
    ListView lv;
    Database_Handler_Class db;
    ArrayAdapter<String> adapter;
    ArrayList<String> listadd;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_camp_record, container, false);
        db = new Database_Handler_Class(getActivity());
        db.getAllCampaignRecords();
        listadd = new ArrayList<String>();

        lv = (ListView) v.findViewById(R.id.cam_rec_list);
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.activity_textview, listadd);
        lv.setAdapter(adapter);

        for (int i = 0; i < db.count; i++) {
            listadd.add(" Campaign Name: " + db.b[i][0] + "\n Venue: " + db.b[i][1] + "\n State: " + db.b[i][2] + "\n start date: " + db.b[i][3]
                    + "\n End date: " + db.b[i][4] + "\n EmailId: " + db.b[i][5] + "\n Strength: " + db.b[i][6] + "\n Description: " + db.b[i][7] + "\n Contact: " + db.b[i][8]);
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String selectedFromList = (String) (lv.getItemAtPosition(pos));
                selectedItem(selectedFromList, pos);
            }
        });

        return v;
    }

    private void selectedItem(String selectedFromList, int pos) {
        final int p = pos;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("Delete");
        alertDialogBuilder
                .setMessage("Delete this Campaign?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        listadd.remove(p);
                        deleteCampaign(db.b[p][9]);
                        adapter.notifyDataSetChanged();
                        adapter.notifyDataSetInvalidated();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    private void deleteCampaign(String s) {
        db.deleteCamp(s);
        Toast.makeText(getActivity(), "Camp Deleted successfully", Toast.LENGTH_SHORT).show();
    }

}

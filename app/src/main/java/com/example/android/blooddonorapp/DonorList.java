package com.example.android.blooddonorapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by user on 10-12-2017.
 */

public class DonorList extends AppCompatActivity {
    ListView ls;
    Database_Handler_Class db;
    Bundle bundle;
    Intent intent;
    private static final int REQUEST_PHONE_CALL = 1;
    private static final int REQUEST_PHONE_MESSAGE = 11;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_list);
        db = new Database_Handler_Class(getApplicationContext());
        intent = getIntent();
        bundle = intent.getExtras();
        db.getAllRecords(bundle.getString("State"), bundle.getString("bloodgroup"));
        ArrayList<String> listadd = new ArrayList<String>();
        ArrayAdapter<String> adapter;
        ls = (ListView) findViewById(R.id.ListDonors);
        adapter = new ArrayAdapter<String>(this, R.layout.activity_textview, listadd);
        ls.setAdapter(adapter);

        for (int i = 0; i < db.count; i++) {
            listadd.add("Name : " + db.a[i][0].toString() + "\nAge: " + db.a[i][1].toString() + "\nCity : " + db.a[i][2].toString() + "\nContact : " + db.a[i][3].toString()
                    + "\nEmail: " + db.a[i][4].toString() + "\nRecent Donation Date : " + db.a[i][5].toString()+ "\nState : " + db.a[i][6].toString());
            adapter.notifyDataSetChanged();
        }
        Toast.makeText(this, "Search completed", Toast.LENGTH_SHORT).show();
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String selectedFromList = (String) (ls.getItemAtPosition(pos));
                selectedItem(selectedFromList, pos);
            }
        });
    }


    public void selectedItem(String selectedFromList, final int pos) {
        final int p = pos;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Send Notification");
        alertDialogBuilder
                .setIcon(R.drawable.question)
                .setMessage("How you want to contact to Donor?")
                .setPositiveButton("Send Message", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        openmessagepage(db.a[p][3]);
                    }
                })
                .setNegativeButton("Call", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        call(db.a[p][3]);
                    }
                })
                .setNeutralButton("SendEmail", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        sendEmail(db.a[p][4]);
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    private void sendEmail(String s) {
        Intent intent = new Intent(this, SendEmail.class);
        Bundle b = new Bundle();
        b.putString("To_mail", s);
        b.putString("from_mail", bundle.getString("mail"));
        intent.putExtras(b);
        startActivity(intent);

    }

    public void openmessagepage(String tosend) {
        Intent intent = new Intent(this, SendSmsPage.class);
        Bundle b = new Bundle();
        b.putString("To_Send", tosend);
        b.putString("from_send", bundle.getString("contact"));
        intent.putExtras(b);
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_PHONE_MESSAGE);
        } else {
            startActivity(intent);
        }
    }

    private void call(String tocall) {
        Intent in = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tocall));
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
        } else {
            startActivity(in);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.mapmenu:
                Bundle b = new Bundle();
                b.putString("Statem", bundle.getString("State"));
                b.putString("bloodgroupm", bundle.getString("bloodgroup"));
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtras(b);
               // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
        return true;
    }

}

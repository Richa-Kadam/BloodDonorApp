package com.example.android.blooddonorapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by user on 12-12-2017.
 */

public class AddCampaign extends AppCompatActivity implements View.OnClickListener {
    EditText startdate, enddate;
    private int year, month, day;
    EditText ecamp, evenue, eemail, econtact, epeople, estart, eend, edesc;
    Spinner sState;
    Button save;
    private int mYear,mMonth,mDay;
    Database_Handler_Class handlerClass;
    String scamp, svenue, semail, scontact, speople, sstart, send, sdesc, sstate;

    String[] state = {"Select State", "ANDHRA PRADESH", "ARUNACHAL PRADESH", "ASSAM", "BIHAR", "CHHATTISGARH", "DELHI", "GUJARAT", "HIMACHAL PRADESH"
            , "JAMMU AND KASHMIR", "JHARKHAND", "KARNATAKA", "KERALA", "MADHYA PRADESH", "MAHARASHTRA", "MANIPUR", "TELANGANA", "TRIPURA", "UTTAR PRADESH"
            , "MEGHALAYA", "MIZORAM", "NAGALAND", "ODISHA (ORISSA)", "PUNJAB", "RAJASTHAN", "SIKKIM", "TAMIL NADU", "UTTARAKHAND", "WEST BENGAL"};


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_camp);
        handlerClass = new Database_Handler_Class(getApplicationContext());

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        sState = (Spinner) findViewById(R.id.spinnerstate);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, state);
        sState.setAdapter(dataAdapter);

        save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(this);

        startdate = (EditText) findViewById(R.id.editStartDate);
        startdate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                showDialog(999);
                return false;
            }
        });

        enddate = (EditText) findViewById(R.id.editEndtDate);
        enddate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                showDialog(99);
                return false;
            }
        });
    }


    private void onPrepareDialog(Dialog d, int dialogId) {
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 999:
                return new DatePickerDialog(this,R.style.DialogTheme2, from_dateListener, mYear, mMonth, mDay);
            case 99:
                return new DatePickerDialog(this,R.style.DialogTheme3, to_dateListener, mYear, mMonth, mDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener from_dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    private DatePickerDialog.OnDateSetListener to_dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            showDate1(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        startdate.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    private void showDate1(int year, int month, int day) {
        enddate.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }


    @Override
    public void onClick(View v) {
        ecamp = (EditText) findViewById(R.id.editTextName);
        evenue = (EditText) findViewById(R.id.editVenu);
        eemail = (EditText) findViewById(R.id.editEmail);
        econtact = (EditText) findViewById(R.id.editContactNo);
        epeople = (EditText) findViewById(R.id.editStrength);
        estart = (EditText) findViewById(R.id.editStartDate);
        eend = (EditText) findViewById(R.id.editEndtDate);
        edesc = (EditText) findViewById(R.id.editDescription);
        sState = (Spinner) findViewById(R.id.spinnerstate);

        String MobilePattern = "^[+]?[0-9]{10,13}$";
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String citypattern = "^[a-zA-Z\\s]*$";


        if (ecamp.getText().toString().matches("") || evenue.getText().toString().matches("") ||
                sState.getSelectedItem().toString().equals("Select State") || eemail.getText().toString().matches("")
                || econtact.getText().toString().matches("") || epeople.getText().toString().matches("") || estart.getText().equals("")
                || eend.getText().toString().matches("") || edesc.getText().toString().matches("")) {
            Toast.makeText(getApplicationContext(), "Please fill all the field.", Toast.LENGTH_SHORT).show();
        } else if (!evenue.getText().toString().matches(citypattern)) {
            evenue.setError("Not a valid city name");
        } else if (!econtact.getText().toString().matches(MobilePattern)) {
            econtact.setError("phone number is not valid");
        } else if (!(eemail.getText().toString()).matches(emailPattern)) {
            eemail.setError("Invalid Email");
        } else {
            svenue = evenue.getText().toString();
            sstart = estart.getText().toString();
            send = eend.getText().toString();
            scontact = econtact.getText().toString();
            semail = eemail.getText().toString();
            scamp = ecamp.getText().toString();
            sdesc = edesc.getText().toString();
            speople=epeople.getText().toString();
            sstate = sState.getSelectedItem().toString();


            Contacts c = new Contacts();
            c.setCname(scamp);
            c.setCstate(sstate);
            c.setVenue(svenue);
            c.setC_contact(scontact);
            c.setStart(sstart);
            c.setEnd(send);
            c.setPeople(speople);
            c.setEmail_id(semail);
            c.setDescription(sdesc);

            long res = handlerClass.insertRecord_Camp(c);
            if (res != -1) {
                Toast.makeText(getApplicationContext(), "Campaign added Successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "wrong", Toast.LENGTH_SHORT).show();
            }
            ecamp.setText("");
            evenue.setText("");
            sState.setSelection(0);
            econtact.setText("");
            eemail.setSelection(0);
            estart.setText("");
            eend.setText("");
            edesc.setText("");
            epeople.setText("");
            eemail.setText("");


        }
    }
}

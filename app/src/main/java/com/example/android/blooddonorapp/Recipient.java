package com.example.android.blooddonorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by user on 30-11-2017.
 */

public class Recipient extends AppCompatActivity implements View.OnClickListener {
    Button search_donor, search_camp;
    public EditText editCity, edittextName, editPincode, editAge, editContact, editMail;
    Spinner SpinBloodGroup, SpinState;
    Database_Handler_Class db;
    public RadioGroup genderRadioGroup;
    public RadioButton genderMaleRadioButton, genderFemaleRadioButton, rb;
    private int radioid;
    String sbloodgroup, sState;
    public String[] values;
    String[] state = {"Select State", "ANDHRA PRADESH", "ARUNACHAL PRADESH", "ASSAM", "BIHAR", "CHHATTISGARH", "DELHI", "GUJARAT", "HIMACHAL PRADESH"
            , "JAMMU AND KASHMIR", "JHARKHAND", "KARNATAKA", "KERALA", "MADHYA PRADESH", "MAHARASHTRA", "MANIPUR", "TELANGANA", "TRIPURA", "UTTAR PRADESH"
            , "MEGHALAYA", "MIZORAM", "NAGALAND", "ODISHA (ORISSA)", "PUNJAB", "RAJASTHAN", "SIKKIM", "TAMIL NADU", "UTTARAKHAND", "WEST BENGAL"};
    String[] bloodgroup = {"Select Required Blood group", "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipient);
        db = new Database_Handler_Class(getApplicationContext());
        edittextName = (EditText) findViewById(R.id.editTextName);
        editContact = (EditText) findViewById(R.id.editTextContactNo);
        editAge = (EditText) findViewById(R.id.editTextAge);
        editCity = (EditText) findViewById(R.id.editTextCity);
        editPincode = (EditText) findViewById(R.id.editTextPinCode);
        editMail = (EditText) findViewById(R.id.editTextEmail);
        genderRadioGroup = (RadioGroup) findViewById(R.id.radio_gender_group);
        genderMaleRadioButton = (RadioButton) findViewById(R.id.r_male);
        genderFemaleRadioButton = (RadioButton) findViewById(R.id.r_female);

        SpinState = (Spinner) findViewById(R.id.spinner_recep);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, state);
        SpinState.setAdapter(dataAdapter);


        SpinBloodGroup = (Spinner) findViewById(R.id.spinnerbloodrecep);
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, bloodgroup);
        SpinBloodGroup.setAdapter(dataAdapter1);


        search_donor = (Button) findViewById(R.id.btnSearch);
        search_donor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioid = genderRadioGroup.getCheckedRadioButtonId();
                rb = (RadioButton) findViewById(radioid);
                String pinpattern = "^[1-9][0-9]{5}$";
                String MobilePattern = "^[+]?[0-9]{10,13}$";
                String citypattern = "^[a-zA-Z\\s]*$";
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (edittextName.getText().toString().matches("") || editAge.getText().toString().matches("") || SpinState.getSelectedItem().toString().equals("Select State")
                        || editCity.getText().toString().matches("") || editPincode.getText().equals("") || radioid == -1
                        || SpinBloodGroup.getSelectedItem().toString().equals("Select Required Blood group")
                        || editContact.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all the field.", Toast.LENGTH_SHORT).show();
                } else if (!(editMail.getText().toString()).matches(emailPattern)) {
                    editMail.setError("Invalid Email");
                } else if (!editContact.getText().toString().matches(MobilePattern)) {
                    editContact.setError("phone number is not valid");
                } else if (!editCity.getText().toString().matches(citypattern)) {
                    editCity.setError("Not a valid city name");
                } else if (!(editPincode.getText().toString()).matches(pinpattern)) {
                    editPincode.setError("Invalid Pin Code");
                } else {
                    sbloodgroup = SpinBloodGroup.getSelectedItem().toString();
                    sState = SpinState.getSelectedItem().toString();
                    db.getAllRecords(sState.toUpperCase(), sbloodgroup);
                    values = new String[db.count];
                    if (db.count > 0) {
                        Bundle b = new Bundle();
                        b.putString("State", sState);
                        b.putString("bloodgroup", sbloodgroup);
                        b.putString("contact", editContact.getText().toString());
                        b.putString("mail", editMail.getText().toString());
                        Intent intent = new Intent(getApplicationContext(), DonorList.class);
                        intent.putExtras(b);
                        getApplicationContext().startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Sorry, No match found.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        search_camp = (Button) findViewById(R.id.btnCampaign);
        search_camp.setOnClickListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.back_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.homemenu:
                Intent intent = new Intent(getApplicationContext(), MainPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }


    @Override
    public void onClick(View v) {
        radioid = genderRadioGroup.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(radioid);
        String pinpattern = "^[1-9][0-9]{5}$";
        String MobilePattern = "^[+]?[0-9]{10,13}$";
        String citypattern = "^[a-zA-Z\\s]*$";
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (edittextName.getText().toString().matches("") || editAge.getText().toString().matches("") || SpinState.getSelectedItem().toString().equals("Select State")
                || editCity.getText().toString().matches("") || editPincode.getText().equals("") || radioid == -1
                || SpinBloodGroup.getSelectedItem().toString().equals("Select Required Blood group")
                || editContact.getText().toString().matches("")) {
            Toast.makeText(getApplicationContext(), "Please fill all the field.", Toast.LENGTH_SHORT).show();
        } else if (!(editMail.getText().toString()).matches(emailPattern)) {
            editMail.setError("Invalid Email");
        } else if (!editContact.getText().toString().matches(MobilePattern)) {
            editContact.setError("phone number is not valid");
        } else if (!editCity.getText().toString().matches(citypattern)) {
            editCity.setError("Not a valid city name");
        } else if (!(editPincode.getText().toString()).matches(pinpattern)) {
            editPincode.setError("Invalid Pin Code");
        } else {
            sState = SpinState.getSelectedItem().toString();
            db.getAllCampaignRecord(sState);
            if (db.count > 0) {
                Bundle b = new Bundle();
                b.putString("State123", sState);
                Intent i = new Intent(getApplicationContext(), CampaignList.class);
                i.putExtras(b);
                getApplicationContext().startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), "Sorry, No match found.", Toast.LENGTH_SHORT).show();
            }


        }
    }
}

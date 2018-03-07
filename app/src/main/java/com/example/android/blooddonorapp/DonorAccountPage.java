package com.example.android.blooddonorapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by user on 03-12-2017.
 */

public class DonorAccountPage extends AppCompatActivity {

    public EditText editcity, editpass, editDate, editFirst, editAge, editContact, editEmail, editPinCode, editUsername;
    TextView editID, donorcnttxt;
    Button search_camp, update, cntincr;
    EditText Name, Password;
    String city;
    String password;
    String RecentDate;
    String Fullname;
    String Age;
    String Contact;
    String Email;
    String PinCode;
    String Username;
    String State;
    String id;
    String dncnt;
    String currentdate;
    public Spinner editState;
    private int year, month, day;
    private int mYear, mMonth, mDay;
    Database_Handler_Class db;
    public int donationCount = 0;
    String[] state = {"Select State", "ANDHRA PRADESH", "ARUNACHAL PRADESH", "ASSAM", "BIHAR", "CHHATTISGARH", "DELHI", "GUJARAT", "HIMACHAL PRADESH"
            , "JAMMU AND KASHMIR", "JHARKHAND", "KARNATAKA", "KERALA", "MADHYA PRADESH", "MAHARASHTRA", "MANIPUR", "TELANGANA", "TRIPURA", "UTTAR PRADESH"
            , "MEGHALAYA", "MIZORAM", "NAGALAND", "ODISHA (ORISSA)", "PUNJAB", "RAJASTHAN", "SIKKIM", "TAMIL NADU", "UTTARAKHAND", "WEST BENGAL"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_account_page);
        db = new Database_Handler_Class(getApplicationContext());
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        currentdate = mDay + "/" + mMonth + 1 + "/" + mYear;


        Name = (EditText) findViewById(R.id.Username);
        Password = (EditText) findViewById(R.id.password1);

        editState = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, state);
        editState.setAdapter(dataAdapter);

        editFirst = (EditText) findViewById(R.id.editTextFirstName);
        editAge = (EditText) findViewById(R.id.editTextAge);
        editState = (Spinner) findViewById(R.id.spinner);
        editcity = (EditText) findViewById(R.id.editTextCity);
        editContact = (EditText) findViewById(R.id.editTextContactNo);
        editEmail = (EditText) findViewById(R.id.editTextEmailId);
        editPinCode = (EditText) findViewById(R.id.editTextPinCode);
        editDate = (EditText) findViewById(R.id.editTextDate);
        editUsername = (EditText) findViewById(R.id.editTextUsername);
        editpass = (EditText) findViewById(R.id.editTextPassword);
        editID = (TextView) findViewById(R.id.editID);
        donorcnttxt = (TextView) findViewById(R.id.donorcounttv);

        editcity.setEnabled(false);
        editpass.setEnabled(false);
        editDate.setEnabled(false);
        editFirst.setEnabled(false);
        editAge.setEnabled(false);
        editContact.setEnabled(false);
        editEmail.setEnabled(false);
        editPinCode.setEnabled(false);
        editUsername.setEnabled(false);



        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        editFirst.setText(bundle.getString("name"));
        editAge.setText(bundle.getString("Age"));
        editcity.setText(bundle.getString("City"));
        editContact.setText(bundle.getString("Contact"));
        editEmail.setText(bundle.getString("Email"));
        editDate.setText(bundle.getString("recentDonation"));
        editPinCode.setText(bundle.getString("Pincode"));
        editUsername.setText(bundle.getString("Username"));
        editpass.setText(bundle.getString("Password"));
        editID.setText(bundle.getString("id"));
        donorcnttxt.setText(bundle.getString("dncnt"));

        int indexS = 0;
        for (int i = 0; i < editState.getCount(); i++) {
            if (editState.getItemAtPosition(i).equals(bundle.getString("State"))) {
                indexS = i;
            }
        }
        editState.setSelection(indexS);

        update = (Button) findViewById(R.id.btnEdit);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //callupdate();
                editcity.setEnabled(true);
                editpass.setEnabled(true);
                editDate.setEnabled(true);
                editFirst.setEnabled(true);
                editAge.setEnabled(true);
                editContact.setEnabled(true);
                editEmail.setEnabled(true);
                editPinCode.setEnabled(true);
                editUsername.setEnabled(true);

            }
        });


        search_camp = (Button) findViewById(R.id.btnSearchCampaign);
        search_camp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sState = editState.getSelectedItem().toString();
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
        });
        editDate = (EditText) findViewById(R.id.editTextDate);
        editDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                showDialog(9999);
                return false;
            }
        });

        cntincr = (Button) findViewById(R.id.donorcountbtn);
        cntincr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int months = monthsBetweenDates();
                if (months > 6) {
                    donationCount = Integer.parseInt(String.valueOf(donorcnttxt.getText())) + 1;
                    display(donationCount);
                    updateDate();

                } else {
                    Toast.makeText(getApplicationContext(), "You have recently donated", Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    public void display(int val) {
        donorcnttxt = (TextView) findViewById(R.id.donorcounttv);
        donorcnttxt.setText(String.valueOf(val));
    }

    public void updateDate() {
        editDate.setText(new StringBuilder().append(mDay).append("/").append(mMonth + 1).append("/").append(mYear));

    }

    private void onPrepareDialog(Dialog d, int dialogId) {
    }

    protected Dialog onCreateDialog(int id) {
        if (id == 9999) {
            return new DatePickerDialog(this, R.style.DialogTheme1, from_dateListener, mYear, mMonth, mDay);

        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener from_dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            showDate(arg1, arg2 + 1, arg3);
        }
    };


    private void showDate(int year, int month, int day) {
        editDate.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }


    public void callupdate() {
        Fullname = editFirst.getText().toString();
        Age = editAge.getText().toString();
        city = editcity.getText().toString();
        Contact = editContact.getText().toString();
        Email = editEmail.getText().toString();
        RecentDate = editDate.getText().toString();
        PinCode = editPinCode.getText().toString();
        Username = editUsername.getText().toString();
        password = editpass.getText().toString();
        State = editState.getSelectedItem().toString();
        id = editID.getText().toString();
        dncnt = donorcnttxt.getText().toString();

        Contact c = new Contact();
        c.setId(id);
        c.setFirst(Fullname);
        c.setCity(city);
        c.setPassword(password);
        c.setRecentDate(RecentDate);
        c.setAge(Age);
        c.setContact(Contact);
        c.setEmail(Email);
        c.setPinCode(PinCode);
        c.setUsername(Username);
        c.setState(State);
        c.setDonorDonationCount(dncnt);
        db.updateRecords(c);
        Toast.makeText(this, "Data updated successfully....", Toast.LENGTH_SHORT).show();
    }

    public void calldelete() {
        id = editID.getText().toString();
        db.deleterecords(id);
        Toast.makeText(this, "Account Deleted successfully.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), Donor.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }

    public void callDelete() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Warning");
        alertDialogBuilder
                .setMessage("Are you sure you want to delete your account?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        calldelete();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.Logoutmenu:
                Toast.makeText(getApplicationContext(),"Logged out successfully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Donor.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
            case R.id.Updatemenu:
                callupdate();
                break;
            case R.id.Deletemenu:
                callDelete();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Donor.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    public int monthsBetweenDates() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date2 = null;
        try {
            date2 = simpleDateFormat.parse(editDate.getText().toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar c1 = Calendar.getInstance();
        c1.setTime(date2);
        Calendar dob = Calendar.getInstance();
        dob.set(Integer.parseInt(String.valueOf(c1.get(Calendar.YEAR))), c1.get(Calendar.MONTH), Integer.parseInt(String.valueOf(c1.get(Calendar.DAY_OF_MONTH))));

        Calendar today = Calendar.getInstance();

        int monthsBetween = 0;
        int dateDiff = today.get(Calendar.DAY_OF_MONTH) - dob.get(Calendar.DAY_OF_MONTH);

        if (dateDiff < 0) {
            int borrrow = today.getActualMaximum(Calendar.DAY_OF_MONTH);
            dateDiff = (today.get(Calendar.DAY_OF_MONTH) + borrrow) - dob.get(Calendar.DAY_OF_MONTH);
            monthsBetween--;
            if (dateDiff > 0) {
                monthsBetween++;
            }
        } else {
            monthsBetween++;
        }
        monthsBetween += today.get(Calendar.MONTH) - dob.get(Calendar.MONTH);
        monthsBetween += (today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)) * 12;
        return monthsBetween;


    }

}


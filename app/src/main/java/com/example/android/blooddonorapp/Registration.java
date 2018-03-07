package com.example.android.blooddonorapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by user on 01-12-2017.
 */

public class Registration extends Fragment {
    Database_Handler_Class handlerClass;
    String pass, conpass;
    EditText editcity, editpass, editconpass, editDate, editFirst, editAge, editContact, editEmail, editPinCode, editUsername;
    Spinner editState, editBloodGroup;
    String city, password, RecentDate, First, Age, Contact, Email, PinCode, Username, Gender, State, BloodGroup;
    private int year, month, day, radioid;
    private int mYear, mMonth, mDay;
    private RadioGroup genderRadioGroup;
    private RadioButton genderMaleRadioButton, genderFemaleRadioButton, rb;
    CheckBox cb;
    String[] state = {"Select State", "ANDHRA PRADESH", "ARUNACHAL PRADESH", "ASSAM", "BIHAR", "CHHATTISGARH", "DELHI", "GUJARAT", "HIMACHAL PRADESH"
            , "JAMMU AND KASHMIR", "JHARKHAND", "KARNATAKA", "KERALA", "MADHYA PRADESH", "MAHARASHTRA", "MANIPUR", "TELANGANA", "TRIPURA", "UTTAR PRADESH"
            , "MEGHALAYA", "MIZORAM", "NAGALAND", "ODISHA (ORISSA)", "PUNJAB", "RAJASTHAN", "SIKKIM", "TAMIL NADU", "UTTARAKHAND", "WEST BENGAL"};
    String[] bloodgroup = {"Select Blood group", "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_registration, container, false);

        handlerClass = new Database_Handler_Class(getContext());
        editState = (Spinner) v.findViewById(R.id.spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, state);
        editState.setAdapter(dataAdapter);

        editBloodGroup = (Spinner) v.findViewById(R.id.spinnerblood);
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, bloodgroup);
        editBloodGroup.setAdapter(dataAdapter1);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        Button btnregister = (Button) v.findViewById(R.id.btnregister);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editFirst = (EditText) v.findViewById(R.id.editTextFirstName);
                editAge = (EditText) v.findViewById(R.id.editTextAge);
                editState = (Spinner) v.findViewById(R.id.spinner);
                editcity = (EditText) v.findViewById(R.id.editTextCity);
                editBloodGroup = (Spinner) v.findViewById(R.id.spinnerblood);
                editContact = (EditText) v.findViewById(R.id.editTextContactNo);
                editEmail = (EditText) v.findViewById(R.id.editTextEmailId);
                editPinCode = (EditText) v.findViewById(R.id.editTextPincode);
                editDate = (EditText) v.findViewById(R.id.editTextDate);
                editUsername = (EditText) v.findViewById(R.id.editTextUsername);
                editpass = (EditText) v.findViewById(R.id.editTextPassword);
                editconpass = (EditText) v.findViewById(R.id.editTextConfirmPassword);
                genderRadioGroup = (RadioGroup) v.findViewById(R.id.radio_gender_group);
                genderMaleRadioButton = (RadioButton) v.findViewById(R.id.radio_male);
                genderFemaleRadioButton = (RadioButton) v.findViewById(R.id.radio_female);
                cb= (CheckBox) v.findViewById(R.id.checkBox);


                radioid = genderRadioGroup.getCheckedRadioButtonId();
                rb = (RadioButton) v.findViewById(radioid);
                pass = editpass.getText().toString();
                conpass = editconpass.getText().toString();

                String MobilePattern = "^[+]?[0-9]{10,13}$";
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String pinpattern = "^[1-9][0-9]{5}$";
                String citypattern = "^[a-zA-Z\\s]*$";

                boolean res = checkPassWordAndConfirmPassword(pass, conpass);
                if (!res) {
                    Toast.makeText(getContext(), "Please enter same password in both field", Toast.LENGTH_SHORT).show();
                    editpass.setText("");
                    editconpass.setText("");
                }
                if (editFirst.getText().toString().matches("") || editAge.getText().toString().matches("") || radioid == -1 ||
                        editState.getSelectedItem().toString().equals("Select State") || editcity.getText().toString().matches("") || editBloodGroup.getSelectedItem().toString().equals("Select Blood group")
                        || editContact.getText().toString().matches("") || editEmail.getText().toString().matches("") || editPinCode.getText().equals("") || editDate.getText().equals("") || editUsername.getText().equals("")
                        || editpass.getText().toString().matches("") || editconpass.getText().toString().matches("")) {
                    Toast.makeText(getContext(), "Please fill all the field.", Toast.LENGTH_SHORT).show();
                } else if (Integer.parseInt(editAge.getText().toString()) < 17 || Integer.parseInt(editAge.getText().toString())>65) {
                    editAge.setError("Sorry, You are not allow to donate blood");
                } else if (!editcity.getText().toString().matches(citypattern)) {
                    editcity.setError("Not a valid city name");
                } else if (!editContact.getText().toString().matches(MobilePattern)) {
                    editContact.setError("phone number is not valid");
                } else if (!(editEmail.getText().toString()).matches(emailPattern)) {
                    editEmail.setError("Invalid Email");
                } else if (!(editPinCode.getText().toString()).matches(pinpattern)) {
                    editPinCode.setError("Invalid Pin Code");
                } else if (radioid == -1) {
                    Toast.makeText(getActivity(), "Select gender", Toast.LENGTH_SHORT).show();
                } else {
                    city = editcity.getText().toString();
                    password = editpass.getText().toString();
                    RecentDate = editDate.getText().toString();
                    First = editFirst.getText().toString();
                    Age = String.valueOf(editAge.getText());
                    Contact = editContact.getText().toString();
                    Email = editEmail.getText().toString();
                    PinCode = editPinCode.getText().toString();
                    Username = editUsername.getText().toString();
                    State = editState.getSelectedItem().toString();
                    BloodGroup = editBloodGroup.getSelectedItem().toString();
                    Gender = rb.getText().toString();



                    Contact c = new Contact();
                    c.setCity(city);
                    c.setPassword(password);
                    c.setRecentDate(RecentDate);
                    c.setFirst(First);
                    c.setAge(Age);
                    c.setContact(Contact);
                    c.setEmail(Email);
                    c.setPinCode(PinCode);
                    c.setUsername(Username);
                    c.setState(State);
                    c.setBloodGroup(BloodGroup);
                    c.setGender(Gender);

                    handlerClass.insertRecord_Donor(c);
                    Toast.makeText(getContext(), "Registration Successfull", Toast.LENGTH_SHORT).show();
                    editFirst.setText("");
                    cb.setChecked(false);
                    rb.setChecked(false);
                    editAge.setText("");
                    editState.setSelection(0);
                    editcity.setText("");
                    editBloodGroup.setSelection(0);
                    editContact.setText("");
                    editEmail.setText("");
                    editPinCode.setText("");
                    editDate.setText("");
                    editUsername.setText("");
                    editpass.setText("");
                    editconpass.setText("");
                }
            }
        });

        editDate = (EditText) v.findViewById(R.id.editTextDate);
        editDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                showDialog(999);
                return false;
            }
        });

        cb= (CheckBox) v.findViewById(R.id.checkBox);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb.isChecked())
                    editDate.setText("00/00/0000");
            }
        });

        return v;
    }

    HashMap<Integer, Dialog> mDialogs = new HashMap<Integer, Dialog>();

    public void showDialog(int dialogId) {

        Dialog d = mDialogs.get(dialogId);
        if (d == null) {

            d = onCreateDialog(dialogId);
            mDialogs.put(dialogId, d);
        }
        if (d != null) {
            onPrepareDialog(d, dialogId);
            d.show();
        }
    }

    private void onPrepareDialog(Dialog d, int dialogId) {
    }

    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(getActivity(),R.style.DialogTheme ,myDateListener, mYear, mMonth, mDay);
        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        editDate.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }

    public boolean checkPassWordAndConfirmPassword(String password, String confirmPassword) {
        boolean pstatus = false;
        if (confirmPassword != null && password != null) {
            if (password.equals(confirmPassword)) {
                pstatus = true;
            }
        }
        return pstatus;
    }


}





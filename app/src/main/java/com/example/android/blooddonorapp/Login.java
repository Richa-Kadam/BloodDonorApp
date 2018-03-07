package com.example.android.blooddonorapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by user on 01-12-2017.
 */

public class Login extends Fragment implements View.OnClickListener {
    Button LoginBtn;
    Database_Handler_Class db;
    private EditText edituname, editpass;

    String uname, pass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmet_login, container, false);
        db = new Database_Handler_Class(getContext());
        LoginBtn = (Button) v.findViewById(R.id.LoginBtn);
        LoginBtn.setOnClickListener(this);
        edituname = (EditText) v.findViewById(R.id.Username);
        editpass = (EditText) v.findViewById(R.id.password1);
        uname = edituname.getText().toString();
        pass = editpass.getText().toString();
        return v;
    }


    @Override
    public void onClick(View v) {
        db.getLoginRecords(edituname.getText().toString(), editpass.getText().toString());
        if (db.count > 0) {
            Toast.makeText(getActivity(), "Logged in Successfully,welcome " + db.name.toString(), Toast.LENGTH_LONG).show();
            Bundle b = new Bundle();
            b.putString("name", db.name.toString());
            b.putString("Age", db.age.toString());
            b.putString("City", db.City.toString());
            b.putString("Contact", db.Contact.toString());
            b.putString("Email", db.Email.toString());
            b.putString("recentDonation", db.recentDonation.toString());
            b.putString("State", db.State.toString());
            b.putString("Pincode", db.Pincode.toString());
            b.putString("Username", db.Username.toString());
            b.putString("Password", db.Password.toString());
            b.putString("id", db.id.toString());
            b.putString("dncnt", db.doncnt.toString());
            Intent intent = new Intent(getContext(), DonorAccountPage.class);
            intent.putExtras(b);
            getContext().startActivity(intent);
        } else if (edituname.getText().toString().equals("") && editpass.getText().toString().equals("")) {
            Toast.makeText(getContext(), "Enter both username and password", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Register First", Toast.LENGTH_SHORT).show();
        }


    }

}


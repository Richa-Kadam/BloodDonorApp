package com.example.android.blooddonorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by user on 30-11-2017.
 */

public class AdminLogin extends AppCompatActivity implements View.OnClickListener {
    Button login;
    EditText adminName, adminPassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        login = (Button) findViewById(R.id.LoginBtn);
        login.setOnClickListener(this);

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
        adminName = (EditText) findViewById(R.id.Username);
        adminPassword = (EditText) findViewById(R.id.password1);
        if (adminName.getText().toString().matches("") && (adminPassword.getText().toString().matches(""))) {
            Toast.makeText(getApplicationContext(), "Please Enter Username and password", Toast.LENGTH_SHORT).show();
        } else if (!adminName.getText().toString().matches("admin")) {
            adminName.setError("Incorrect Username");
        } else if (!adminPassword.getText().toString().matches("qwerty")) {
            adminPassword.setError("Incorrect Password");
        } else {
            if (adminName.getText().toString().matches("admin") && adminPassword.getText().toString().matches("qwerty")) {
                Intent i = new Intent(getApplicationContext(), AdminAccountPage.class);
                getApplicationContext().startActivity(i);
                finish();
                Toast.makeText(getApplicationContext(), "Logged in Successfully,welcome Admin", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Please Enter Username and password", Toast.LENGTH_SHORT).show();
            }
        }

    }
}

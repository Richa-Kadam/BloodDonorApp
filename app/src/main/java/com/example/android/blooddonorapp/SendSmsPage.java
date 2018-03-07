package com.example.android.blooddonorapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by user on 23-12-2017.
 */

public class SendSmsPage extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    Bundle bundle;
    EditText editPhoneNo,editMessage;
    Button btnSendSMS;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms_page);
        Intent intent = getIntent();
        bundle = intent.getExtras();

        editPhoneNo=(EditText)findViewById(R.id.txtPhoneNo);
        editMessage=(EditText)findViewById(R.id.txtMessage);

        editPhoneNo.setText(bundle.getString("To_Send"));
        editMessage.setText("");
        btnSendSMS=(Button)findViewById(R.id.btnSendSMS);
        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSms();
            }
        });


    }

    public void sendSms()
    {
        String phoneNo=editPhoneNo.getText().toString();
        String message = editMessage.getText().toString();
        if (phoneNo.length()>0 && message.length()>0)
            sendSMS(phoneNo, message);
        else
            Toast.makeText(getBaseContext(),
                    "Please enter both phone number and message.",
                    Toast.LENGTH_SHORT).show();


    }
    String p,m;
    private void sendSMS(String phoneNumber, String message)
    {
         p=phoneNumber;
         m=message;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {}
            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }


    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(p, null, m, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent", Toast.LENGTH_LONG).show();
                    editPhoneNo.setText("");
                    editMessage.setText("");
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }

}
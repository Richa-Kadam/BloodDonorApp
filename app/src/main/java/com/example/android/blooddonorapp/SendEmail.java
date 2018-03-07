package com.example.android.blooddonorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by user on 23-12-2017.
 */

public class SendEmail extends AppCompatActivity {
    EditText editTextTo, editTextSubject, editTextMessage, editTextFrom;
    Button send;
    Bundle bundle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        editTextTo = (EditText) findViewById(R.id.editText1);
        editTextFrom = (EditText) findViewById(R.id.editText);
        editTextSubject = (EditText) findViewById(R.id.editText2);
        editTextMessage = (EditText) findViewById(R.id.editText3);

        Intent intent = getIntent();
        bundle = intent.getExtras();
        editTextTo.setText(bundle.getString("To_mail"));
        editTextFrom.setText(bundle.getString("from_mail"));


        send = (Button) findViewById(R.id.button1);
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                sendMail();
            }

        });

    }

    private void sendMail() {
        String to = editTextTo.getText().toString();
        String subject = editTextSubject.getText().toString();
        String message = editTextMessage.getText().toString();
        String cc = editTextFrom.getText().toString();


        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_CC, cc);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);


        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }
}

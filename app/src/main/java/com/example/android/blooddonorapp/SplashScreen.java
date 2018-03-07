package com.example.android.blooddonorapp;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SplashScreen extends AppCompatActivity {
    @InjectView(R.id.loading_image)
    protected ImageView animationImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.inject(this);
        AnimationDrawable animationDrawable = (AnimationDrawable) animationImage.getDrawable();
        animationDrawable.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, MainPage.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }
    @Override
    public void onBackPressed() {
        System.exit(0);
        finish();
    }
}

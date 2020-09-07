package com.josamuna.toplearners;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        Handler handler = new Handler(Looper.getMainLooper());
//        handler.postDelayed(() ->{
//        }, 2000);

        try {
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(SplashActivity.this, SubmitActivity.class);
        startActivity(intent);
    }
}
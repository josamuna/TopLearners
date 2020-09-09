package com.josamuna.toplearners;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        Handler handler = new Handler(Looper.getMainLooper());
//        handler.postDelayed(() ->{
//        }, 2000);
        new CountDownTimer(0, 1000) {
//        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
//                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(intent);
            }
        }.start();
    }
}
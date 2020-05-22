package com.arc.agni.tictactoe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.arc.agni.tictactoe.R;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(() -> {
            Intent homeScreenIntent = new Intent(SplashScreenActivity.this, HomeScreenActivity.class);
            startActivity(homeScreenIntent);
            finish();
        }, 1200);

    }
}

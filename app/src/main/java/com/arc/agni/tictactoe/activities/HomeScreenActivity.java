package com.arc.agni.tictactoe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.arc.agni.tictactoe.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import androidx.appcompat.app.AppCompatActivity;

import static com.arc.agni.tictactoe.constants.TicTocToeConstants.DOUBLE_PLAYER_GAME;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.PLAY_MODE;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.SINGLE_PLAYER_GAME;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // Full screen setting
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Initialize MobileAds & Request for ads
        //MobileAds.initialize(this, ADMOB_APP_ID);
        AdView mAdView = findViewById(R.id.hs_adView);
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice(TEST_DEVICE_ID).build();
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void goToConfigurationPageForSinglePlayerGame(View view) {
        Intent configScreenIntent = new Intent(HomeScreenActivity.this, ConfigScreenActivity.class);
        configScreenIntent.putExtra(PLAY_MODE, SINGLE_PLAYER_GAME);
        startActivity(configScreenIntent);
    }


    public void goToConfigurationPageForDoublePlayerGame(View view) {
        Intent configScreenIntent = new Intent(HomeScreenActivity.this, ConfigScreenActivity.class);
        configScreenIntent.putExtra(PLAY_MODE, DOUBLE_PLAYER_GAME);
        startActivity(configScreenIntent);
    }
}

package com.arc.agni.tictactoe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arc.agni.tictactoe.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import static com.arc.agni.tictactoe.constants.TicTocToeConstants.DIFFICULTY_MODE;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.DOUBLE_PLAYER_GAME;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.NAMES_REQUIRED;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.PATTERN;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.PLAYER_ONE_NAME;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.PLAYER_TWO_NAME;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.PLAY_MODE;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.ROUNDS;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.SINGLE_PLAYER_GAME;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.TITLE_CONFIG_SCREEN;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.TONY;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.YOU;

public class ConfigScreenActivity extends AppCompatActivity {

    TextView namesLabel;
    EditText playerOneNameLayout;
    EditText playerTwoNameLayout;
    String playMode = DOUBLE_PLAYER_GAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_screen);
        setTitle(TITLE_CONFIG_SCREEN);

        // Full screen setting
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        playerOneNameLayout = findViewById(R.id.player_1_name_input);
        playerTwoNameLayout = findViewById(R.id.player_2_name_input);

        if (SINGLE_PLAYER_GAME.equalsIgnoreCase(getIntent().getStringExtra(PLAY_MODE))) {
            namesLabel = findViewById(R.id.names);
            configurationForSinglePlayerGame();
        }

        // Request for ad
        AdView mAdView = findViewById(R.id.cs_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice(TEST_DEVICE_ID).build();
        mAdView.loadAd(adRequest);
    }

    public void configurationForSinglePlayerGame() {
        playerOneNameLayout.setText(YOU);
        playerTwoNameLayout.setText(TONY);
        playMode = SINGLE_PLAYER_GAME;

        namesLabel.setVisibility(View.GONE);
        playerOneNameLayout.setVisibility(View.GONE);
        playerTwoNameLayout.setVisibility(View.GONE);

        findViewById(R.id.mode_layout).setVisibility(View.VISIBLE);
    }

    public void startGame(View view) {

        String playerOneName = playerOneNameLayout.getText().toString();
        String playerTwoName = playerTwoNameLayout.getText().toString();
        Integer rounds = Integer.valueOf(((RadioButton) findViewById(((RadioGroup) findViewById(R.id.rounds_input)).getCheckedRadioButtonId())).getText().toString());
        String pattern = ((RadioButton) findViewById(((RadioGroup) findViewById(R.id.pattern_input)).getCheckedRadioButtonId())).getText().toString();
        String difficultyMode = ((RadioButton) findViewById(((RadioGroup) findViewById(R.id.mode_input)).getCheckedRadioButtonId())).getText().toString();

        if (null != playerOneName && !playerOneName.trim().isEmpty() && null != playerTwoName && !playerTwoName.trim().isEmpty()) {
            Intent gamePlayIntent = new Intent(ConfigScreenActivity.this, PlayScreenActivity.class);
            gamePlayIntent.putExtra(PLAYER_ONE_NAME, playerOneName);
            gamePlayIntent.putExtra(PLAYER_TWO_NAME, playerTwoName);
            gamePlayIntent.putExtra(ROUNDS, rounds);
            gamePlayIntent.putExtra(PATTERN, pattern);
            gamePlayIntent.putExtra(PLAY_MODE, playMode);
            gamePlayIntent.putExtra(DIFFICULTY_MODE, difficultyMode);
            startActivity(gamePlayIntent);
        } else {
            Toast.makeText(this, NAMES_REQUIRED, Toast.LENGTH_SHORT).show();
        }

    }

}

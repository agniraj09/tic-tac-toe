package com.arc.agni.tictactoe.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arc.agni.tictactoe.R;
import com.arc.agni.tictactoe.helper.AnimationHelper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import static com.arc.agni.tictactoe.constants.TicTocToeConstants.ADMOB_INTERSTIT_ID;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.BOTH;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.COMBO_1;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.COMBO_2;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.COMBO_3;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.COMBO_4;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.COMBO_5;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.COMBO_6;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.COMBO_7;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.COMBO_8;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.DIFFICULT;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.DIFFICULTY_MODE;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.FIVE_TURNS;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.FOUR_TURNS;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.GAME_OVER;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.MEDIUM;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.NEGATIVE_ONE;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.OF;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.ONE;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.PATTERN;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.PATTERN_COLOR;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.PLAYER_ONE_NAME;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.PLAYER_TWO_NAME;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.PLAY_MODE;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.ROUND;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.ROUNDS;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.ROUND_TIED;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.SINGLE_PLAYER_GAME;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.START_WITH;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.THREE_TURNS;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.TIE;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.TURN;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.TWO;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.WON_THE_GAME;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.WON_THE_ROUND;
import static com.arc.agni.tictactoe.constants.TicTocToeConstants.YOUR;

public class PlayScreenActivity extends AppCompatActivity {

    // Mode of play
    boolean isSinglePlayerGame;                         // State denotes the mode of play, SINGLE_PLAYER | DOUBLE_PLAYER
    boolean isItUserTurn = true;                        // State controls the repetitive click by user in SINGLE_PLAYER mode
    String difficulty;

    // Player Related Variables
    String playerOneName;                               // Name of the player 1
    String playerTwoName;                               // Name of the player 2
    int playerOneTurnCount;                             // Counter for player 1 turn
    int playerTwoTurnCount;                             // Counter for player 2 turn
    int playerOneWinCount;                              // Counter for number of rounds won by player 1
    int playerTwoWinCount;                              // Counter for number of rounds won by player 2
    TextView playerOneWinCountText;                     // Textview which shows the 'playerOneWinCount'
    TextView playerTwoWinCountText;                     // Textview which shows the 'playerTwoWinCount'
    boolean playerOneTurn = true;                       // State variable denotes who holds the current turn

    // Round Related Variables
    int totalRounds;                                    // No.of Rounds chosen by user | Default value is 5
    int currentRound = 1;                               // Counter for rounds | Pointer for current round
    TextView roundsTextView;                            // Textview which shows 'currentRound'
    TextView roundWinMessage;                           // Textview which shows who won the round once the round is over.
    LinearLayout playerCard;                            // This textview is hidden when 'roundWinMessage' is being displayed.
    TextView gameWinMessage;                            // Textview which shows who won the game once all rounds are over
    boolean isGameGoingOn = true;                       // State value which denotes if the game is finished or not

    // Other Variables
    boolean isColorPatternSelected;                     // State variable denotes the pattern chosen by user. TRUE - COLOR | FALSE - COIN
    boolean isCurrentRoundOver;                         // State variable denotes if the current round is over.
    int[] selectedValues = new int[9];                  // Bucket which holds the KEY values for PLAYER1 and PLAYER2
    List<Integer> clickedIndices = new ArrayList<>();   // Bucket which contains the index value of cells chosen by players
    List<LinearLayout> cells = new ArrayList<>();       // Bucket which holds the layout values for each cell.
    boolean isSoundMuted;
    TextView musicButton;
    MediaPlayer tickSound;
    MediaPlayer roundWinSound;
    boolean isSettingsOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);

        // Full screen setting
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Read user inputted data from INTENT
        playerOneName = getIntent().getStringExtra(PLAYER_ONE_NAME);
        playerTwoName = getIntent().getStringExtra(PLAYER_TWO_NAME);
        totalRounds = getIntent().getIntExtra(ROUNDS, 5);
        isColorPatternSelected = PATTERN_COLOR.equalsIgnoreCase(getIntent().getStringExtra(PATTERN));   // Determine game play pattern(color/coin) based on Pattern selection
        isSinglePlayerGame = SINGLE_PLAYER_GAME.equalsIgnoreCase(getIntent().getStringExtra(PLAY_MODE));
        difficulty = getIntent().getStringExtra(DIFFICULTY_MODE);

        // Initialize cell layouts
        cells = Arrays.asList(findViewById(R.id.index0),
                findViewById(R.id.index1), findViewById(R.id.index2),
                findViewById(R.id.index3), findViewById(R.id.index4),
                findViewById(R.id.index5), findViewById(R.id.index6),
                findViewById(R.id.index7), findViewById(R.id.index8));

        // Initialize the other layouts
        roundsTextView = findViewById(R.id.round_text);
        playerOneWinCountText = findViewById(R.id.player_1_score);
        playerTwoWinCountText = findViewById(R.id.player_2_score);
        roundWinMessage = findViewById(R.id.round_win_message);
        gameWinMessage = findViewById(R.id.game_win_message);
        playerCard = findViewById(R.id.player_tiles);
        musicButton = findViewById(R.id.music);

        // Set layout values
        ((TextView) findViewById(R.id.player_1_name)).setText(playerOneName);
        ((TextView) findViewById(R.id.player_2_name)).setText(playerTwoName);
        String roundText = ROUND + currentRound + OF + totalRounds;
        roundsTextView.setText(roundText);

        tickSound = MediaPlayer.create(PlayScreenActivity.this, R.raw.tick);
        roundWinSound = MediaPlayer.create(PlayScreenActivity.this, R.raw.result);

        // Show animations
        showGameOpeningAnimations();

        showCustomToast(playerOneName);

        // Request for ad
        AdView mAdView = findViewById(R.id.ps_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice(TEST_DEVICE_ID).build();
        mAdView.loadAd(adRequest);
    }

    public void showGameOpeningAnimations() {
        AnimationHelper.showTopToBottomAnimation(findViewById(R.id.round_card), this);
        AnimationHelper.showBottomToTopAnimation(findViewById(R.id.player_card), this);
        AnimationHelper.showZoomInAnimation(findViewById(R.id.play_card_cardview), this);
    }

    public void showCustomToast(String playerName) {
        String message = START_WITH + (isSinglePlayerGame ? YOUR : (playerName + "'s")) + TURN;
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        View view = toast.getView();
        view.getBackground().setColorFilter(getResources().getColor(R.color.radio_button_color), PorterDuff.Mode.SRC_IN);
        ((TextView) view.findViewById(android.R.id.message)).setTextColor(getResources().getColor(R.color.white));
        toast.setGravity(Gravity.CENTER, 0, 1);
        toast.show();
    }

    public void onClickIndex0(View view) {
        fillCellAndDecideWinner(0);
    }

    public void onClickIndex1(View view) {
        fillCellAndDecideWinner(1);
    }

    public void onClickIndex2(View view) {
        fillCellAndDecideWinner(2);
    }

    public void onClickIndex3(View view) {
        fillCellAndDecideWinner(3);
    }

    public void onClickIndex4(View view) {
        fillCellAndDecideWinner(4);
    }

    public void onClickIndex5(View view) {
        fillCellAndDecideWinner(5);
    }

    public void onClickIndex6(View view) {
        fillCellAndDecideWinner(6);
    }

    public void onClickIndex7(View view) {
        fillCellAndDecideWinner(7);
    }

    public void onClickIndex8(View view) {
        fillCellAndDecideWinner(8);
    }

    public void fillCellAndDecideWinner(int index) {

        boolean isUserTurn = !isSinglePlayerGame || isItUserTurn;
        if (isUserTurn && !isCurrentRoundOver && !clickedIndices.contains(index)) {
            // Play tick sound
            if (!isSoundMuted) {
                tickSound.start();
            }

            if (playerOneTurn) {
                selectedValues[index] = ONE;
                cells.get(index).setBackgroundResource(isColorPatternSelected ? R.drawable.ic_player1_fill : R.drawable.ic_player1_coin);
                playerOneTurnCount++;
                playerOneTurn = false;
            } else {
                selectedValues[index] = TWO;
                cells.get(index).setBackgroundResource(isColorPatternSelected ? R.drawable.ic_player2_fill : R.drawable.ic_player2_coin);
                playerTwoTurnCount++;
                playerOneTurn = true;
            }

            if (isSinglePlayerGame) {
                isItUserTurn = false;
            }
            clickedIndices.add(index);

            // If Player 1 Won the round
            if (playerOneTurnCount == THREE_TURNS || playerOneTurnCount == FOUR_TURNS || playerOneTurnCount == FIVE_TURNS) {
                if (doesThisPlayerWonTheRound(ONE)) {
                    doPostRoundOverSteps(ONE);
                }
            }

            // If Player 2 Won the round
            if (playerTwoTurnCount == THREE_TURNS || playerTwoTurnCount == FOUR_TURNS) {
                if (doesThisPlayerWonTheRound(TWO)) {
                    doPostRoundOverSteps(TWO);
                }
            }

            // If no one won the round - TIE
            if (clickedIndices.size() == 9 && !isCurrentRoundOver) {
                doPostRoundOverSteps(TIE);
            }

            // If it is a single player game
            if (isSinglePlayerGame && !playerOneTurn && !isCurrentRoundOver) {
                playTonyTurn();
            }
        }
    }

    public void doPostRoundOverSteps(final int winner) {

        isCurrentRoundOver = true;
        switch (winner) {
            case ONE: {
                playerOneWinCount++;
                highlightCellsPostRound(winner, R.drawable.ic_player1_win);
                playerOneWinCountText.setText(String.valueOf(playerOneWinCount));
                showRoundResult(ONE, playerOneName + WON_THE_ROUND);
                break;
            }
            case TWO: {
                playerTwoWinCount++;
                highlightCellsPostRound(winner, R.drawable.ic_player2_win);
                playerTwoWinCountText.setText(String.valueOf(playerTwoWinCount));
                showRoundResult(TWO, playerTwoName + WON_THE_ROUND);
                break;
            }
            case TIE: {
                showRoundResult(TIE, ROUND_TIED);
                break;
            }
        }
    }

    public void highlightCellsPostRound(int winner, int cellToBeHighlightedWith) {
        if (!isSoundMuted) {
            roundWinSound.start();
        }

        for (int fillIndex = 0; fillIndex <= 8; fillIndex++) {
            if (winner == selectedValues[fillIndex]) {
                cells.get(fillIndex).setBackgroundResource(cellToBeHighlightedWith);
            }
        }
    }

    private void showRoundResult(int winner, String roundWinResult) {
        animate(winner, roundWinResult);
        new CountDownTimer(1600, 1600) {
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                roundWinMessage.setVisibility(View.GONE);
                playerCard.setVisibility(View.VISIBLE);
                goToNextRound();
            }
        }.start();
    }

    public void animate(int winner, String roundWinResult) {
        playerCard.setVisibility(View.GONE);
        int color = ONE == winner ? getResources().getColor(R.color.blue) : (TWO == winner ? getResources().getColor(R.color.green) : getResources().getColor(R.color.tie));
        roundWinMessage.setBackgroundColor(color);
        (PlayScreenActivity.this.findViewById(R.id.round_win_message_outline)).setBackgroundColor(color);
        AnimationHelper.showZoomInAnimation(roundWinMessage, PlayScreenActivity.this);
        roundWinMessage.setText(roundWinResult);
        roundWinMessage.setVisibility(View.VISIBLE);
    }

    private void goToNextRound() {
        if (currentRound < totalRounds) {
            if (currentRound % 5 == 0) {
                trigggerInterstitalAd();
            }

            resetAllValues();
            currentRound++;
            String roundText = ROUND + currentRound + OF + totalRounds;
            roundsTextView.setText(roundText);
        }
        // If game is over
        else if (currentRound == totalRounds) {
            isGameGoingOn = false;
            findViewById(R.id.settings_layout).setVisibility(View.INVISIBLE);
            showGameWinnerMessage();
        }
    }

    private void playTonyTurn() {
        List<Integer> remainingIndices = new ArrayList<>();
        for (int i = 0; i < selectedValues.length; i++) {
            if (selectedValues[i] == 0) {
                remainingIndices.add(i);
            }
        }
        final int nextIndexToBeClicked;

        switch (difficulty) {

            // Medium difficulty mode algorithm
            case MEDIUM: {
                if (playerOneTurnCount % 2 == 0 && (singlePlayerAlgorithm(ONE, remainingIndices) != NEGATIVE_ONE)) {
                    nextIndexToBeClicked = singlePlayerAlgorithm(ONE, remainingIndices);
                } else {
                    nextIndexToBeClicked = (singlePlayerAlgorithm(TWO, remainingIndices) == NEGATIVE_ONE ? (remainingIndices.get(new Random().nextInt(remainingIndices.size()))) :
                            (singlePlayerAlgorithm(TWO, remainingIndices)));
                }
                break;
            }

            // Difficult difficulty mode algorithm
            case DIFFICULT: {
                nextIndexToBeClicked = getNextIndexToBeClicked(remainingIndices);
                break;
            }

            // Default algorithm - EASY mode
            default: {
                nextIndexToBeClicked = remainingIndices.get(new Random().nextInt(remainingIndices.size()));
                break;
            }
        }

        new CountDownTimer(300, 300) {
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                isItUserTurn = true;
                fillCellAndDecideWinner(nextIndexToBeClicked);
                isItUserTurn = true;
            }
        }.start();
    }

    private void resetAllValues() {
        isCurrentRoundOver = false;
        playerOneTurn = true;
        isItUserTurn = true;
        playerOneTurnCount = 0;
        playerTwoTurnCount = 0;
        for (int index = 0; index <= 8; index++) {
            selectedValues[index] = 0;
            cells.get(index).setBackgroundResource(R.drawable.ic_plain_layout);
        }
        clickedIndices.clear();
    }

    private void showGameWinnerMessage() {
        roundsTextView.setText(GAME_OVER);
        int winner = playerOneWinCount == playerTwoWinCount ? TIE : (playerOneWinCount > playerTwoWinCount ? ONE : TWO);
        fillTilesOneByOne(winner);
    }

    private void fillTilesOneByOne(int winner) {

        final int resource;
        if (winner == ONE) {
            resource = R.drawable.ic_player1_fill;
        } else if (winner == TWO) {
            resource = R.drawable.ic_player2_fill;
        } else {
            resource = R.drawable.ic_game_tie;
        }

        new CountDownTimer(2000, 200) {
            int cell = 0;

            public void onTick(long millisUntilFinished) {
                cells.get(cell).setBackgroundResource(resource);
                cell++;
            }

            @Override
            public void onFinish() {
                SpannableString winMessage = formatResult((playerOneWinCount == playerTwoWinCount ? BOTH : (playerOneWinCount > playerTwoWinCount ? playerOneName : playerTwoName)));
                gameWinMessage.setText(winMessage);
                findViewById(R.id.game_win_layout).setBackgroundResource(resource);
                findViewById(R.id.game_win_layout).setVisibility(View.VISIBLE);
                trigggerInterstitalAd();
            }
        }.start();
    }

    public SpannableString formatResult(String winnerName) {
        String resultText = winnerName + WON_THE_GAME;
        SpannableString finalResultText = new SpannableString(resultText);
        final int startIndex = resultText.indexOf(winnerName);
        final int endIndex = resultText.indexOf(winnerName) + winnerName.length();
        //finalResultText.setSpan(new ForegroundColorSpan(Color.RED), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        finalResultText.setSpan(new RelativeSizeSpan(1.8f), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        finalResultText.setSpan(new StyleSpan(Typeface.BOLD), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return finalResultText;
    }

    public boolean doesThisPlayerWonTheRound(int playerKey) {
        return (playerKey == selectedValues[0] && playerKey == selectedValues[1] && playerKey == selectedValues[2]
                || (playerKey == selectedValues[3] && playerKey == selectedValues[4] && playerKey == selectedValues[5])
                || (playerKey == selectedValues[6] && playerKey == selectedValues[7] && playerKey == selectedValues[8])
                || (playerKey == selectedValues[0] && playerKey == selectedValues[3] && playerKey == selectedValues[6])
                || (playerKey == selectedValues[1] && playerKey == selectedValues[4] && playerKey == selectedValues[7])
                || (playerKey == selectedValues[2] && playerKey == selectedValues[5] && playerKey == selectedValues[8])
                || (playerKey == selectedValues[0] && playerKey == selectedValues[4] && playerKey == selectedValues[8])
                || (playerKey == selectedValues[2] && playerKey == selectedValues[4] && playerKey == selectedValues[6]));
    }

    public int getNextIndexToBeClicked(List<Integer> remainingIndices) {
        int nextIndex = NEGATIVE_ONE;

        // If Center index is available, occupy it first. If not, occupy the first corner
        if (playerTwoTurnCount == 0) {
            if (remainingIndices.contains(4)) {
                nextIndex = 4;
            } else {
                nextIndex = 0;
            }
        }

        // Occupy favorable indexes
        if (NEGATIVE_ONE == nextIndex) {
            nextIndex = singlePlayerAlgorithm(TWO, remainingIndices);
        }

        // Block User Win by occupying 3rd same index
        if (NEGATIVE_ONE == nextIndex) {
            nextIndex = singlePlayerAlgorithm(ONE, remainingIndices);
        }

        // If no match found, click random index
        if (NEGATIVE_ONE == nextIndex) {
            nextIndex = remainingIndices.get(new Random().nextInt(remainingIndices.size()));
        }

        return nextIndex;
    }

    public int singlePlayerAlgorithm(int key, List<Integer> remainingIndices) {
        int index;

        if ((NEGATIVE_ONE != (index = CheckAllPossibilities(key, COMBO_1))) && remainingIndices.contains(index)) {
            return index;
        } else if ((NEGATIVE_ONE != (index = CheckAllPossibilities(key, COMBO_2))) && remainingIndices.contains(index)) {
            return index;
        } else if ((NEGATIVE_ONE != (index = CheckAllPossibilities(key, COMBO_3))) && remainingIndices.contains(index)) {
            return index;
        } else if ((NEGATIVE_ONE != (index = CheckAllPossibilities(key, COMBO_4))) && remainingIndices.contains(index)) {
            return index;
        } else if ((NEGATIVE_ONE != (index = CheckAllPossibilities(key, COMBO_5))) && remainingIndices.contains(index)) {
            return index;
        } else if ((NEGATIVE_ONE != (index = CheckAllPossibilities(key, COMBO_6))) && remainingIndices.contains(index)) {
            return index;
        } else if ((NEGATIVE_ONE != (index = CheckAllPossibilities(key, COMBO_7))) && remainingIndices.contains(index)) {
            return index;
        } else if ((NEGATIVE_ONE != (index = CheckAllPossibilities(key, COMBO_8))) && remainingIndices.contains(index)) {
            return index;
        }

        // If no match found
        return NEGATIVE_ONE;
    }

    public int CheckAllPossibilities(int key, List<Integer> combo) {
        if (key == selectedValues[combo.get(0)] && key == selectedValues[combo.get(1)]) {
            return combo.get(2);
        } else if (key == selectedValues[combo.get(1)] && key == selectedValues[combo.get(2)]) {
            return combo.get(0);
        } else if (key == selectedValues[combo.get(0)] && key == selectedValues[combo.get(2)]) {
            return combo.get(1);
        }

        // If no match found
        return NEGATIVE_ONE;
    }

    public void goToHome(View view) {
        Intent homeIntent = new Intent(this, HomeScreenActivity.class);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeIntent);
    }

    public void trigggerInterstitalAd() {
        // Initialize the Mobile Ads SDK
        //MobileAds.initialize(this, ADMOB_APP_ID);
        AdRequest adIRequest = new AdRequest.Builder().build();
        //AdRequest adIRequest = new AdRequest.Builder().addTestDevice(TEST_DEVICE_ID).build();

        // Prepare the Interstitial Ad Activity
        InterstitialAd interstitial = new InterstitialAd(this);

        // Insert the Ad Unit ID
        interstitial.setAdUnitId(ADMOB_INTERSTIT_ID);

        // Interstitial Ad load Request
        interstitial.loadAd(adIRequest);

        // Prepare an Interstitial Ad Listener
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                // Call displayInterstitial() function when the Ad loads
                if (interstitial.isLoaded()) {
                    interstitial.show();
                }
            }
        });
    }

    public void openSettings(View view) {
        isSettingsOpen = !isSettingsOpen;
        if (isSettingsOpen) {
            findViewById(R.id.music).setVisibility(View.VISIBLE);
            findViewById(R.id.restart).setVisibility(View.VISIBLE);
            AnimationHelper.showLeftToRightIconAnimation(findViewById(R.id.music), this);
            AnimationHelper.showLeftToRightIconAnimation(findViewById(R.id.restart), this);
        } else {
            AnimationHelper.showRightToLeftIconAnimation(findViewById(R.id.music), this);
            AnimationHelper.showRightToLeftIconAnimation(findViewById(R.id.restart), this);
            findViewById(R.id.music).setVisibility(View.GONE);
            findViewById(R.id.restart).setVisibility(View.GONE);
        }
    }

    public void muteAndUnmuteSound(View view) {
        isSoundMuted = !isSoundMuted;
        if (isSoundMuted) {
            musicButton.setBackgroundResource(R.drawable.ic_music_off);
        } else {
            musicButton.setBackgroundResource(R.drawable.ic_music_on);
        }
    }

    public void restartGame(View view) {
        resetAllValues();
        playerOneWinCount = 0;
        playerTwoWinCount = 0;
        currentRound = 1;
        String roundText = ROUND + currentRound + OF + totalRounds;
        roundsTextView.setText(roundText);
        playerOneWinCountText.setText(String.valueOf(playerOneWinCount));
        playerTwoWinCountText.setText(String.valueOf(playerTwoWinCount));
        showGameOpeningAnimations();
        showCustomToast(playerOneName);
    }

    /**
     * This method is triggered when back button is pressed
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeScreenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        if (isGameGoingOn) {
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setContentView(R.layout.dialog_exit_game);

            // If 'No' is clicked
            Button noButton = dialog.findViewById(R.id.no_button);
            noButton.setOnClickListener(v -> dialog.dismiss());

            // If 'Yes' is clicked
            Button yesButton = dialog.findViewById(R.id.yes_button);
            yesButton.setOnClickListener(v -> startActivity(intent));

            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            dialog.show();
        } else {
            startActivity(intent);
        }
    }

}

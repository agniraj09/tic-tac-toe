package com.arc.agni.tictactoe.constants;

import java.util.Arrays;
import java.util.List;

public class TicTocToeConstants {

    // Titles
    public static final String TITLE_CONFIG_SCREEN = "Settings";

    // Intent Constants
    public static final String PLAYER_ONE_NAME = "player_one_name";
    public static final String PLAYER_TWO_NAME = "player_two_name";
    public static final String ROUNDS = "rounds";
    public static final String PATTERN = "pattern";
    public static final String PLAY_MODE = "play_mode";
    public static final String SINGLE_PLAYER_GAME = "single_player_game";
    public static final String DOUBLE_PLAYER_GAME = "double_player_game";
    public static final String DIFFICULTY_MODE = "difficulty_mode";
    public static final String EASY = "Easy";
    public static final String MEDIUM = "Medium";
    public static final String DIFFICULT = "Difficult";

    // Error Messages
    public static final String NAMES_REQUIRED = "Please enter player names";

    // Pattern Values
    public static final String PATTERN_COLOR = "Color";

    // Winner Constants & Cell Values
    public static final int TIE = 0;
    public static final int TWO = 2;
    public static final int ONE = 1;
    public static final int NEGATIVE_ONE = -1;

    // Round Constants
    public static final int THREE_TURNS = 3;
    public static final int FOUR_TURNS = 4;
    public static final int FIVE_TURNS = 5;

    public static final String OF = " of ";
    public static final String ROUND = "Round ";
    public static final String GAME_OVER = "Game Over";
    public static final String ROUND_TIED = "Round Tied";
    public static final String WON_THE_ROUND = " won this round";
    public static final String WON_THE_GAME = "\nwon the game";
    public static final String BOTH = "Both of you";
    public static final String YOU = "You";
    public static final String TONY = "Tony";

    public static final List<Integer> COMBO_1 = Arrays.asList(0, 1, 2);
    public static final List<Integer> COMBO_2 = Arrays.asList(3, 4, 5);
    public static final List<Integer> COMBO_3 = Arrays.asList(6, 7, 8);
    public static final List<Integer> COMBO_4 = Arrays.asList(0, 3, 6);
    public static final List<Integer> COMBO_5 = Arrays.asList(1, 4, 7);
    public static final List<Integer> COMBO_6 = Arrays.asList(2, 5, 8);
    public static final List<Integer> COMBO_7 = Arrays.asList(0, 4, 8);
    public static final List<Integer> COMBO_8 = Arrays.asList(2, 4, 6);

    public static final String ADMOB_APP_ID = "ca-app-pub-4587610802196055~7759646032";
    public static final String ADMOB_INTERSTIT_ID = "ca-app-pub-4587610802196055/2292363219";
    //public static final String ADMOB_INTERSTIT_ID = "ca-app-pub-3940256099942544/1033173712";
    public static final String TEST_DEVICE_ID = "0EC56B91253E874AAF286CEDC3945F6A";

    public static final String START_WITH = "Start with ";
    public static final String YOUR = "Your";
    public static final String TURN = " Turn";
}

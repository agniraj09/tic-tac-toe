package com.arc.agni.tictactoe.helper;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.arc.agni.tictactoe.R;

public class AnimationHelper {

    public static void showTopToBottomAnimation(View view, Context context) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.top_to_bottom_anim);
        view.startAnimation(animation);
    }

    public static void showBottomToTopAnimation(View view, Context context) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.bottom_to_top_anim);
        view.startAnimation(animation);
    }

    public static void showZoomInAnimation(View view, Context context) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.zoom_in_anim);
        view.startAnimation(animation);
    }

    public static void showLeftToRightIconAnimation(View view, Context context){
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.left_to_right_icons);
        view.startAnimation(animation);
    }

    public static void showRightToLeftIconAnimation(View view, Context context){
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.right_to_left_icons);
        view.startAnimation(animation);
    }
}

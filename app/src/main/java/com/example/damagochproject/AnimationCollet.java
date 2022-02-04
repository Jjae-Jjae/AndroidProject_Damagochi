package com.example.damagochproject;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AnimationCollet {
    public static void FlipAni(ImageView img, Context context){
        Animation SelectAni = AnimationUtils.loadAnimation(context, R.anim.fadeout);

        ObjectAnimator flipani = ObjectAnimator.ofFloat(img, "rotationY", 0, 180).setDuration(2000);
        img.startAnimation(SelectAni);
        flipani.start();
    }

    public static void FadeInAni(ImageView img, Context context){
        Animation StartAni = AnimationUtils.loadAnimation(context, R.anim.fadein);
        img.startAnimation(StartAni);
    }

    public static void FadeOutAni(ImageView img, Context context){
        Animation StartAni = AnimationUtils.loadAnimation(context, R.anim.fadeout);
        img.startAnimation(StartAni);
    }
}

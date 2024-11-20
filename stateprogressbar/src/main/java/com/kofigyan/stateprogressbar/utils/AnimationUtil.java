package com.kofigyan.stateprogressbar.components;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.OvershootInterpolator;

public class AnimationUtil {

    public static void animateStateTransition(View view, long duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", -10f, 0f);
        animator.setDuration(duration);
        animator.setInterpolator(new OvershootInterpolator());
        animator.start();
    }
}

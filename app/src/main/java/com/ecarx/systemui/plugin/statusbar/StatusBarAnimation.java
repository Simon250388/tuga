package com.ecarx.systemui.plugin.statusbar;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import com.ecarx.systemui.plugin.utils.Lg;

/* loaded from: classes.dex */
public class StatusBarAnimation {
    private View mRootView;
    private volatile float xStart = 0.0f;
    private volatile float xEnd = 0.0f;

    public StatusBarAnimation(View view) {
        this.mRootView = view;
    }

    private void statusBarViewAnimation(boolean z) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(z ? 0.0f : 1.0f, z ? 1.0f : 0.0f);
        alphaAnimation.setDuration(100L);
        alphaAnimation.setFillAfter(true);
        this.mRootView.startAnimation(alphaAnimation);
    }

    public void iconAnimation(View view, boolean z, Animator.AnimatorListener animatorListener) {
        this.xEnd = ((this.mRootView.getX() + this.mRootView.getWidth()) - view.getX()) - view.getWidth();
        Lg.d("xStart:" + this.xStart + " xEnd:" + this.xEnd + " reverse:" + z);
        float[] fArr = new float[2];
        fArr[0] = z ? 1.0f : 0.0f;
        fArr[1] = z ? 0.0f : 1.0f;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "alpha", fArr);
        float[] fArr2 = new float[2];
        fArr2[0] = z ? 1.5f : 1.0f;
        fArr2[1] = z ? 1.0f : 1.5f;
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "scaleX", fArr2);
        float[] fArr3 = new float[2];
        fArr3[0] = z ? 1.5f : 1.0f;
        fArr3[1] = z ? 1.0f : 1.5f;
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, "scaleY", fArr3);
        float[] fArr4 = new float[2];
        fArr4[0] = z ? this.xEnd : 0.0f;
        fArr4[1] = z ? 0.0f : this.xEnd;
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view, "translationX", fArr4);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2, ofFloat3, ofFloat4);
        animatorSet.setDuration(500L);
        if (animatorListener != null) {
            animatorSet.addListener(animatorListener);
        }
        animatorSet.start();
    }

    public void twinkleAnimation(View view, Animator.AnimatorListener animatorListener) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
        ofFloat.setDuration(500L);
        ofFloat.setRepeatMode(ValueAnimator.REVERSE);
        ofFloat.setRepeatCount(3);
        if (animatorListener != null) {
            ofFloat.addListener(animatorListener);
        }
        ofFloat.start();
    }
}

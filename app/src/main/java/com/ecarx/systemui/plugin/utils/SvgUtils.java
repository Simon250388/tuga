package com.ecarx.systemui.plugin.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;


/* loaded from: classes.dex */
public class SvgUtils {
    private static final String TAG = SvgUtils.class.getSimpleName();

    private SvgUtils() {
    }

    public static Drawable getDrawable(Context context, int i) {
        return AppCompatResources.getDrawable(context, i);
    }

    public static Drawable getTintDrawable(Context context, int i, int i2) {
        if (i == -1) {
            return null;
        }
        Drawable drawable = getDrawable(context, i);
        if (i2 == -1) {
            return drawable;
        }
        if (drawable == null) {
            Lg.eTag(TAG, "decode svg drawable error");
            return null;
        }
        Drawable mutate = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTint(mutate, context.getResources().getColor(i2));
        return mutate;
    }

    public static Drawable getTintListDrawable(Context context, int i, int i2) {
        if (i == -1) {
            return null;
        }
        Drawable drawable = getDrawable(context, i);
        if (i2 == -1) {
            return drawable;
        }
        if (drawable == null) {
            Lg.eTag(TAG, "decode svg drawable error");
            return null;
        }
        ColorStateList colorStateList = AppCompatResources.getColorStateList(context, i2);
        Drawable mutate = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTintList(mutate, colorStateList);
        return mutate;
    }

    public static Drawable generateShape(int i, int i2) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i);
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(i2);
        return gradientDrawable;
    }

    public static Drawable generateSelector(Context context, int i, int i2, int i3) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        if (i != -1) {
            stateListDrawable.addState(new int[]{16842913}, getDrawable(context, i));
        }
        if (i2 != -1) {
            stateListDrawable.addState(new int[]{16842919}, getDrawable(context, i2));
        }
        if (i3 != -1) {
            stateListDrawable.addState(new int[0], getDrawable(context, i3));
        }
        return stateListDrawable;
    }
}

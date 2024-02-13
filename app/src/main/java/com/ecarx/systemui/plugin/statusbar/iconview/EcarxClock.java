package com.ecarx.systemui.plugin.statusbar.iconview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextClock;
import android.widget.TextView;

/* loaded from: classes.dex */
public class EcarxClock extends TextClock {
    private static final String AM_CHINAESE = "上午";
    private static final String AM_ENGLISH = "AM";
    private static final String PM_CHINAESE = "下午";
    private static final String PM_ENGLISH = "PM";
    private static final String TAG = EcarxClock.class.getSimpleName();

    public EcarxClock(Context context) {
        super(context);
    }

    public EcarxClock(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public EcarxClock(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        String str;
        if (charSequence == null || charSequence.toString() == null) {
            str = null;
        } else {
            str = charSequence.toString();
            if (str.endsWith(AM_CHINAESE)) {
                str = str.replace(AM_CHINAESE, "AM");
            } else if (str.endsWith(PM_CHINAESE)) {
                str = str.replace(PM_CHINAESE, PM_ENGLISH);
            }
        }
        String str2 = TAG;
        Log.i(str2, "[JRSYS_StatusBar][setText]--1, text: " + ((Object) charSequence) + ", targetText: " + str);
        super.setText(str, bufferType);
    }
}

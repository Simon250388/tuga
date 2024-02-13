package com.ecarx.systemui.plugin.utils;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/* loaded from: classes.dex */
public class AlphaTextView extends AppCompatTextView {
    public AlphaTextView(Context context) {
        super(context);
    }

    public AlphaTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AlphaTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        super.setPressed(z);
        setAlpha(z ? 0.5f : 1.0f);
    }
}

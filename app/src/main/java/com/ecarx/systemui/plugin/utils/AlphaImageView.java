package com.ecarx.systemui.plugin.utils;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/* loaded from: classes.dex */
public class AlphaImageView extends AppCompatImageView {
    public AlphaImageView(Context context) {
        super(context);
    }

    public AlphaImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AlphaImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        super.setPressed(z);
        setAlpha(z ? 0.5f : 1.0f);
    }
}

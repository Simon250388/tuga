package com.ecarx.systemui.plugin.psd;

import android.content.Context;
import android.util.AttributeSet;
import com.ecarx.systemui.plugin.statusbar.SystemIconContainer;

/* loaded from: classes.dex */
public class PsdSystemIconContainer extends SystemIconContainer {
    private static final String TAG = PsdSystemIconContainer.class.getSimpleName();

    public PsdSystemIconContainer(Context context) {
        this(context, null);
    }

    public PsdSystemIconContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PsdSystemIconContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}

package com.ecarx.xui.adaptapi.peripherals;

import android.content.Context;
import com.ecarx.xui.adaptapi.AdaptAPI;
import com.ecarx.xui.adaptapi.peripherals.wear.IIntelligentKey;

/* loaded from: classes.dex */
public abstract class Peripherals extends AdaptAPI {
    public static Peripherals create(Context context) {
        return null;
    }

    public abstract IIntelligentKey getIntelligentKey();

    public abstract boolean isIntelligentKeySupport();
}

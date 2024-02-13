package com.ecarx.xui.adaptapi.dvr;

import android.content.Context;
import com.ecarx.xui.adaptapi.AdaptAPI;
import com.ecarx.xui.adaptapi.dvr.ota.IOta;

/* loaded from: classes.dex */
public abstract class Dvr extends AdaptAPI {
    public static Dvr create(Context context) {
        return null;
    }

    public abstract IOperation getOperation();

    public abstract IOta getOta();

    public abstract ISettings getSettings();
}

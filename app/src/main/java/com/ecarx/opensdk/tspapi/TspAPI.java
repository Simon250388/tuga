package com.ecarx.opensdk.tspapi;

import android.content.Context;
import com.ecarx.opensdk.tspapi.impl.TspAPIImpl;

/* loaded from: classes.dex */
public abstract class TspAPI {
    private static final String TAG = "TspAPI";

    public abstract IEnvType getEnvType();

    public static TspAPI create(Context context) {
        return new TspAPIImpl(context);
    }
}

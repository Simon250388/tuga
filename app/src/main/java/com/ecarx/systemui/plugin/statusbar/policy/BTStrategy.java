package com.ecarx.systemui.plugin.statusbar.policy;

import android.content.Context;
import android.util.Log;

/* loaded from: classes.dex */
public abstract class BTStrategy {
    static final String TAG = "BTStrategy";
    private static BTStrategy mStrategy;

    /* loaded from: classes.dex */
    public interface BTStrategyCallback {
        void onBTStateChange();
    }

    public abstract void destroy();

    public abstract boolean isA2dpConnected();

    public abstract boolean isAvrcpConnected();

    public abstract boolean isEnabled();

    public abstract boolean isHfpConnected();

    public abstract boolean isSupport();

    public abstract void setCallBack(BTStrategyCallback bTStrategyCallback);

    public static BTStrategy getStrategy(Context context) {
        try {
            if (mStrategy == null) {
                synchronized (BTStrategy.class) {
                    if (mStrategy == null) {
                        mStrategy = new GoogleBTStrategy(context);
                    }
                }
            }
            if (mStrategy.isSupport()) {
                Log.i(TAG, "[JRSYS_BT][getStrategy] use GoogleBTStrategy");
                return mStrategy;
            }
            Log.i(TAG, "[JRSYS_BT][getStrategy] BTStrategy not support");
            mStrategy = null;
            return null;
        } catch (Throwable th) {
            Log.e(TAG, "[JRSYS_BT][getStrategy] use GoogleBTStrategy error " + th);
            return null;
        }
    }
}

package com.ecarx.systemui.plugin.policy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes.dex */
public abstract class BaseController extends BroadcastReceiver {
    protected Context mContext;

    public void destroy() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
    }

    public BaseController(Context context) {
        this.mContext = context.getApplicationContext();
    }
}

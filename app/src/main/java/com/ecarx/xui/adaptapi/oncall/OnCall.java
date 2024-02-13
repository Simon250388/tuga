package com.ecarx.xui.adaptapi.oncall;

import android.content.Context;
import com.ecarx.xui.adaptapi.AdaptAPI;
import com.ecarx.xui.adaptapi.FunctionStatus;

/* loaded from: classes.dex */
public abstract class OnCall extends AdaptAPI {
    public static final String ACTION_ONCALL_STARTED = "ecarx.xui.intent.action.ONCALL_STARTED";
    public static final String ACTION_ONCALL_UNSUBSCRIBED = "ecarx.xui.intent.action.ONCALL_UNSUBSCRIBED";
    public static final String EXTRA_ONCALL_TYPE = "ecarx.xui.intent.extra.ONCALL_TYPE";

    /* loaded from: classes.dex */
    public interface ICallListener {
        void onCallCreate(Call call);

        void onCallStatusChanged(int i, int i2);
    }

    public static OnCall create(Context context) {
        return null;
    }

    public abstract Call getCurrentCall();

    public abstract int getECallCallbackRemainedTime();

    public abstract int getETARescue();

    public abstract FunctionStatus isOnCallSupported(int i);

    public abstract void registerCallListener(ICallListener iCallListener);

    public abstract void startCall(int i);

    public abstract void unregisterCallListener(ICallListener iCallListener);
}

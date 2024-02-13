package com.ecarx.sdk.openapi;

import android.os.Bundle;

/* loaded from: classes.dex */
public abstract class ECarXApiClient {
    @Deprecated
    public static final int CAUSE_NETWORK_LOST = 2;
    @Deprecated
    public static final int CAUSE_SERVICE_DISCONNECTED = 1;

    /* loaded from: classes.dex */
    public interface Callback {
        void onAPIReady(boolean z);
    }

    @Deprecated
    /* loaded from: classes.dex */
    public interface ConnectionCallbacks {
        void onConnected(Bundle bundle);

        void onConnectionSuspended(int i);
    }
}

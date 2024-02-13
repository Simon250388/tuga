package com.ecarx.systemui.plugin.statusbar.policy;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.ecarx.systemui.plugin.statusbar.policy.BTStrategy;
import com.ecarx.systemui.plugin.utils.Lg;

/* loaded from: classes.dex */
public class GoogleBTStrategy extends BTStrategy {
    private static final int A2DP_SINK = 11;
    private static final String ACTION_CONNECTION_STATE_CHANGED_A2DP_SINK = "android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED";
    private static final String ACTION_CONNECTION_STATE_CHANGED_HFP_CLIENT = "android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED";
    private static final int AVRCP_STATUS_CHANGE = 12;
    private static final int HFP_STATUS_CHANGE = 16;
    private static final String TAG = GoogleBTStrategy.class.getSimpleName();
    private final BluetoothAdapter mBTAdapter;
    private BroadcastReceiver mBTReceiver = new BroadcastReceiver() { // from class: com.ecarx.systemui.plugin.statusbar.policy.GoogleBTStrategy.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) {
                return;
            }
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(action) || "android.bluetooth.device.action.ACL_CONNECTED".equals(action) || "android.bluetooth.device.action.ACL_DISCONNECTED".equals(action) || GoogleBTStrategy.ACTION_CONNECTION_STATE_CHANGED_A2DP_SINK.equals(action) || GoogleBTStrategy.ACTION_CONNECTION_STATE_CHANGED_HFP_CLIENT.equals(action)) {
                if (!"android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
                    GoogleBTStrategy.this.handleBTStateChange();
                    return;
                }
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
                if (GoogleBTStrategy.this.mBTState != intExtra) {
                    GoogleBTStrategy.this.mBTState = intExtra;
                    GoogleBTStrategy.this.handleBTStateChange();
                }
            }
        }
    };
    private int mBTState;
    private BTStrategy.BTStrategyCallback mCallback;
    private Context mContext;

    private boolean stateToIsConnect(int i) {
        return i == 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GoogleBTStrategy(Context context) {
        this.mBTState = 0;
        this.mContext = context;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBTAdapter = defaultAdapter;
        if (defaultAdapter == null) {
            Lg.e(TAG, "BluetoothAdapter.getDefaultAdapter() error");
            return;
        }
        String str = TAG;
        Lg.i(str, "mBTAdapter = " + this.mBTAdapter);
        this.mBTState = this.mBTAdapter.getState();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        intentFilter.addAction(ACTION_CONNECTION_STATE_CHANGED_A2DP_SINK);
        intentFilter.addAction(ACTION_CONNECTION_STATE_CHANGED_HFP_CLIENT);
        this.mContext.registerReceiver(this.mBTReceiver, intentFilter);
    }

    @Override // com.ecarx.systemui.plugin.statusbar.policy.BTStrategy
    public boolean isSupport() {
        return this.mBTAdapter != null;
    }

    @Override // com.ecarx.systemui.plugin.statusbar.policy.BTStrategy
    public void destroy() {
        this.mCallback = null;
    }

    @Override // com.ecarx.systemui.plugin.statusbar.policy.BTStrategy
    public void setCallBack(BTStrategy.BTStrategyCallback bTStrategyCallback) {
        this.mCallback = bTStrategyCallback;
        handleBTStateChange();
    }

    @Override // com.ecarx.systemui.plugin.statusbar.policy.BTStrategy
    public boolean isEnabled() {
        return this.mBTAdapter.getState() == BluetoothAdapter.STATE_ON;
    }

    @SuppressLint("MissingPermission")
    @Override // com.ecarx.systemui.plugin.statusbar.policy.BTStrategy
    public boolean isA2dpConnected() {
        BluetoothAdapter bluetoothAdapter = this.mBTAdapter;
        if (bluetoothAdapter == null) {
            return false;
        }
        return stateToIsConnect(bluetoothAdapter.getProfileConnectionState(11));
    }

    @SuppressLint("MissingPermission")
    @Override // com.ecarx.systemui.plugin.statusbar.policy.BTStrategy
    public boolean isHfpConnected() {
        BluetoothAdapter bluetoothAdapter = this.mBTAdapter;
        if (bluetoothAdapter == null) {
            return false;
        }
        return stateToIsConnect(bluetoothAdapter.getProfileConnectionState(16));
    }

    @SuppressLint("MissingPermission")
    @Override // com.ecarx.systemui.plugin.statusbar.policy.BTStrategy
    public boolean isAvrcpConnected() {
        BluetoothAdapter bluetoothAdapter = this.mBTAdapter;
        if (bluetoothAdapter == null) {
            return false;
        }
        return stateToIsConnect(bluetoothAdapter.getProfileConnectionState(12));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleBTStateChange() {
        BTStrategy.BTStrategyCallback bTStrategyCallback = this.mCallback;
        if (bTStrategyCallback != null) {
            bTStrategyCallback.onBTStateChange();
        }
    }
}

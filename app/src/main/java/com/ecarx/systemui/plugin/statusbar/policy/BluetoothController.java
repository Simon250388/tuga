package com.ecarx.systemui.plugin.statusbar.policy;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.text.TextUtils;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.policy.BaseController;
import com.ecarx.systemui.plugin.statusbar.StatusBarManager;
import com.ecarx.systemui.plugin.statusbar.entity.StatusBarIconEntity;
import com.ecarx.systemui.plugin.statusbar.iconview.EStatefulObject;
import com.ecarx.systemui.plugin.statusbar.policy.BTStrategy;
import com.ecarx.systemui.plugin.utils.Lg;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class BluetoothController extends BaseController implements BTStrategy.BTStrategyCallback {
    private static final String A2DP_CONNECTION_STATE_CHANGED = "android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED";
    private static final String ECARX_STATE = "android.bluetooth.profile.extra.STATE";
    private static final String HFP_CONNECTION_STATE_CHANGED = "android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED";
    private static final String TAG = "BluetoothController";
    private static volatile BluetoothController bluetoothController;
    private Handler handler;
    private BTStrategy mBTStrategy;
    private ArrayList<BluetoothStateChangeCallback> mChangeCallbacks;
    private boolean mConnectedA2DP;
    private boolean mConnectedAvrcp;
    private boolean mConnectedHFP;
    private boolean mEnabled;
    private Runnable mInitRunnable;
    private int mLastIconId;

    /* loaded from: classes.dex */
    public interface BluetoothStateChangeCallback {
        void onBluetoothStateChange(boolean z, boolean z2, boolean z3);
    }

    private boolean stateToIsConnect(int i) {
        return i == 2;
    }

    @Override // com.ecarx.systemui.plugin.statusbar.policy.BTStrategy.BTStrategyCallback
    public void onBTStateChange() {
    }

    private BluetoothController(Context context) {
        super(context);
        this.mEnabled = false;
        this.mConnectedHFP = false;
        this.mConnectedA2DP = false;
        this.mConnectedAvrcp = false;
        this.mInitRunnable = new Runnable() { // from class: com.ecarx.systemui.plugin.statusbar.policy.BluetoothController.1
            @Override // java.lang.Runnable
            public void run() {
                Lg.w(BluetoothController.TAG, "[JRSYS_BT][init] runnable");
                BluetoothController bluetoothController2 = BluetoothController.this;
                bluetoothController2.mBTStrategy = BTStrategy.getStrategy(bluetoothController2.mContext);
                if (BluetoothController.this.mBTStrategy != null) {
                    BluetoothController.this.mBTStrategy.setCallBack(BluetoothController.this);
                    BluetoothController bluetoothController3 = BluetoothController.this;
                    bluetoothController3.mEnabled = bluetoothController3.mBTStrategy.isEnabled();
                    BluetoothController bluetoothController4 = BluetoothController.this;
                    bluetoothController4.mConnectedA2DP = bluetoothController4.mBTStrategy.isA2dpConnected();
                    BluetoothController bluetoothController5 = BluetoothController.this;
                    bluetoothController5.mConnectedHFP = bluetoothController5.mBTStrategy.isHfpConnected();
                    BluetoothController bluetoothController6 = BluetoothController.this;
                    bluetoothController6.mConnectedAvrcp = bluetoothController6.mBTStrategy.isAvrcpConnected();
                    BluetoothController.this.updateStateChange();
                    return;
                }
                BluetoothController.this.handler.postDelayed(this, 200L);
            }
        };
        this.mChangeCallbacks = new ArrayList<>();
        this.handler = new Handler();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(A2DP_CONNECTION_STATE_CHANGED);
        intentFilter.addAction(HFP_CONNECTION_STATE_CHANGED);
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        this.mContext.registerReceiver(this, intentFilter);
    }

    public static BluetoothController getInstance(Context context) {
        if (bluetoothController == null) {
            synchronized (BluetoothController.class) {
                if (bluetoothController == null) {
                    bluetoothController = new BluetoothController(context);
                }
            }
        }
        bluetoothController.init();
        return bluetoothController;
    }

    public void init() {
        this.handler.post(this.mInitRunnable);
    }

    public void addStateChangedCallback(BluetoothStateChangeCallback bluetoothStateChangeCallback) {
        this.mChangeCallbacks.add(bluetoothStateChangeCallback);
    }

    @Override // com.ecarx.systemui.plugin.policy.BaseController
    public void destroy() {
        super.destroy();
        BTStrategy bTStrategy = this.mBTStrategy;
        if (bTStrategy != null) {
            try {
                bTStrategy.destroy();
                this.mBTStrategy = null;
            } catch (Exception e) {
                Lg.e(TAG, e);
            }
        }
        this.mLastIconId = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateStateChange() {
        int i;
        Lg.w(TAG, "[JRSYS_BT][updateStateChange] BT on:" + this.mEnabled + " | A2DP:" + this.mConnectedA2DP + " | HFP:" + this.mConnectedHFP + " mConnectedAvrcp:" + this.mConnectedAvrcp);
        if (this.mConnectedA2DP && this.mConnectedHFP) {
            i = R.drawable.ic_status_bt_connected;
        } else if (this.mConnectedA2DP) {
            i = R.drawable.ic_status_bt_media_audio_connected;
        } else if (this.mConnectedHFP) {
            i = R.drawable.ic_status_bt_phone_audio_connected;
        } else {
            i = this.mEnabled ? R.drawable.ic_status_bt_opened : 0;
        }
        if (i != this.mLastIconId) {
            Lg.w(TAG, "[JRSYS_BT][updateStateChange] iconId:" + i);
            if (i == 0) {
                StatusBarManager.getInstance().updateIconVisibility(EStatefulObject.BLUETOOTH, false);
            } else {
                StatusBarManager.getInstance().setIcon(new StatusBarIconEntity(EStatefulObject.BLUETOOTH, i));
            }
            this.mLastIconId = i;
            fireCallbacks();
        }
    }

    private void fireCallbacks() {
        Iterator<BluetoothStateChangeCallback> it = this.mChangeCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onBluetoothStateChange(this.mEnabled, this.mConnectedA2DP, this.mConnectedHFP);
        }
    }

    @Override // com.ecarx.systemui.plugin.policy.BaseController, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Lg.d(TAG, "[JRSYS_NET][onReceive], action: " + action);
        if (TextUtils.isEmpty(action)) {
            return;
        }
        if (this.mBTStrategy == null) {
            this.mBTStrategy = BTStrategy.getStrategy(this.mContext);
        }
        this.mEnabled = this.mBTStrategy.isEnabled();
        this.mConnectedAvrcp = this.mBTStrategy.isAvrcpConnected();
        if (A2DP_CONNECTION_STATE_CHANGED.equals(action)) {
            this.mConnectedA2DP = stateToIsConnect(intent.getIntExtra(ECARX_STATE, -1));
            this.mConnectedHFP = this.mBTStrategy.isHfpConnected();
        } else if (HFP_CONNECTION_STATE_CHANGED.equals(action)) {
            this.mConnectedHFP = stateToIsConnect(intent.getIntExtra(ECARX_STATE, -1));
            this.mConnectedA2DP = this.mBTStrategy.isA2dpConnected();
        } else if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
            if (intExtra == 10) {
                this.mEnabled = false;
                this.mConnectedHFP = false;
                this.mConnectedA2DP = false;
            } else if (intExtra == 12) {
                this.mEnabled = true;
                this.mConnectedHFP = this.mBTStrategy.isHfpConnected();
                this.mConnectedA2DP = this.mBTStrategy.isA2dpConnected();
            }
        }
        updateStateChange();
    }
}

package com.ecarx.systemui.plugin.policy;

import android.content.Context;
import android.os.Handler;
import com.ecarx.systemui.plugin.App;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.psd.PsdBtManager;
import com.ecarx.systemui.plugin.statusbar.StatusBarManager;
import com.ecarx.systemui.plugin.statusbar.entity.StatusBarIconEntity;
import com.ecarx.systemui.plugin.statusbar.iconview.EStatefulObject;
import com.ecarx.systemui.plugin.utils.LogUtils;

/* loaded from: classes.dex */
public class PsdBluetoothController extends BaseController implements PsdBtManager.IHeadsetConnectionCallback {
    private static final String TAG = PsdBluetoothController.class.getSimpleName();
    private static volatile PsdBluetoothController mPsdBTController;
    private Handler mHandler;
    private final Runnable mInitRunnable;
    private boolean mIsConnected;
    private boolean mIsOpen;
    private final Runnable mRefreshRunnable;

    private PsdBluetoothController(Context context) {
        super(context);
        this.mInitRunnable = new Runnable() { // from class: com.ecarx.systemui.plugin.policy.-$$Lambda$PsdBluetoothController$wR0dVsL3zGixZeMy4tt1TYR2L4U
            @Override // java.lang.Runnable
            public final void run() {
                PsdBluetoothController.this.lambda$new$0$PsdBluetoothController();
            }
        };
        this.mRefreshRunnable = new Runnable() { // from class: com.ecarx.systemui.plugin.policy.-$$Lambda$PsdBluetoothController$vd_zrnW63w7n_HphC16P9DAYxmc
            @Override // java.lang.Runnable
            public final void run() {
                PsdBluetoothController.this.lambda$new$1$PsdBluetoothController();
            }
        };
    }

    public static PsdBluetoothController getInstance() {
        if (mPsdBTController == null) {
            synchronized (PsdBluetoothController.class) {
                if (mPsdBTController == null) {
                    mPsdBTController = new PsdBluetoothController(App.getApp().getApplicationContext());
                    mPsdBTController.init();
                }
            }
        }
        return mPsdBTController;
    }

    private void init() {
        Handler handler = new Handler();
        this.mHandler = handler;
        handler.post(this.mInitRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: registBTBattery */
    public void lambda$new$0$PsdBluetoothController() {
        initBt();
    }

    private void initBt() {
        PsdBtManager psdBtManager = PsdBtManager.getInstance(this.mContext);
        this.mIsOpen = psdBtManager.isOpen();
        this.mIsConnected = psdBtManager.isBtConnected();
        LogUtils.stack("mIsOpen", Boolean.valueOf(this.mIsOpen));
        LogUtils.stack("mIsConnected", Boolean.valueOf(this.mIsConnected));
        psdBtManager.setIHeadsetConnectionCallback(this);
        this.mHandler.postDelayed(this.mRefreshRunnable, 0L);
    }

    private void testCode() {
        StatusBarManager.getInstance().setIcon(new StatusBarIconEntity(EStatefulObject.BLUETOOTH, R.drawable.ic_status_bt_a2dp_low_charge));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateStateChange */
    public void lambda$new$1$PsdBluetoothController() {
        int i;
        LogUtils.stack("mIsOpen", Boolean.valueOf(this.mIsOpen));
        LogUtils.stack("mIsConnected", Boolean.valueOf(this.mIsConnected));
        if (this.mIsOpen) {
            i = R.drawable.ic_status_bt_psd_open;
            if (this.mIsConnected) {
                i = R.drawable.ic_status_bt_a2dp_connected;
            }
        } else {
            i = 0;
        }
        if (i == 0) {
            StatusBarManager.getInstance().updateIconVisibility(EStatefulObject.BLUETOOTH, false);
        } else {
            StatusBarManager.getInstance().setIcon(new StatusBarIconEntity(EStatefulObject.BLUETOOTH, i));
        }
    }

    @Override // com.ecarx.systemui.plugin.psd.PsdBtManager.IHeadsetConnectionCallback
    public void onConnected() {
        this.mIsConnected = true;
        LogUtils.stack("onConnected");
        PsdBtManager psdBtManager = PsdBtManager.getInstance(this.mContext);
        this.mIsOpen = psdBtManager.isOpen();
        this.mIsConnected = psdBtManager.isBtConnected();
        this.mHandler.post(this.mRefreshRunnable);
    }

    @Override // com.ecarx.systemui.plugin.psd.PsdBtManager.IHeadsetConnectionCallback
    public void onDisConnected() {
        LogUtils.stack("onDisConnected");
        this.mIsOpen = PsdBtManager.getInstance(this.mContext).isOpen();
        this.mIsConnected = false;
        this.mHandler.post(this.mRefreshRunnable);
    }

    @Override // com.ecarx.systemui.plugin.psd.PsdBtManager.IHeadsetConnectionCallback
    public void isOpened(boolean z) {
        this.mIsOpen = z;
        this.mIsConnected = false;
        LogUtils.stack("isOpened", Boolean.valueOf(z));
        this.mHandler.post(this.mRefreshRunnable);
    }
}

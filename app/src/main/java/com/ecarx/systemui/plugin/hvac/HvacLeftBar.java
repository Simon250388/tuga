package com.ecarx.systemui.plugin.hvac;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecarx.hvac.app.INaviBarTempBeanListener;
import com.ecarx.hvac.app.IOpenHvacAidlInterface;
import com.ecarx.hvac.app.NaviBarTempBean;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.hvac.HvacController;
import com.ecarx.systemui.plugin.navigationbar.AppWatcherService;
import com.ecarx.systemui.plugin.utils.LogUtils;
import com.ecarx.systemui.plugin.utils.ThreadUtils;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.car.hvac.IHvac;


/* loaded from: classes.dex */
public class HvacLeftBar {
    private static final String TAG = HvacLeftBar.class.getSimpleName();
    private static volatile HvacLeftBar mInstance;
    private boolean isLongClick;
    private Context mContext;
    private IOpenHvacAidlInterface mIopenHvacAidlInterface;
    private HvacView mRootView;
    public ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.ecarx.systemui.plugin.hvac.HvacLeftBar.2
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(HvacLeftBar.TAG, "onServiceConnected");
            HvacLeftBar.this.mIopenHvacAidlInterface = IOpenHvacAidlInterface.Stub.asInterface(iBinder);
            try {
                iBinder.linkToDeath(HvacLeftBar.this.mDeathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                HvacLeftBar.this.mIopenHvacAidlInterface.registerListener(new INaviBarTempBeanListener.Stub() { // from class: com.ecarx.systemui.plugin.hvac.HvacLeftBar.2.1
                    @Override // ecarx.hvac.app.INaviBarTempBeanListener
                    public void initTempData(NaviBarTempBean naviBarTempBean) throws RemoteException {
                        HvacLeftBar.this.mRootView.setData(true, naviBarTempBean);
                    }

                    @Override // ecarx.hvac.app.INaviBarTempBeanListener
                    public void onNaviBarTempBeanChange(NaviBarTempBean naviBarTempBean) throws RemoteException {
                        String str = HvacLeftBar.TAG;
                        Log.i(str, "functionid:" + naviBarTempBean.getFucntionId());
                        HvacLeftBar.this.mRootView.refreshData(true, naviBarTempBean);
                    }
                });
                HvacLeftBar.this.mIopenHvacAidlInterface.getBarBean();
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    };
    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() { // from class: com.ecarx.systemui.plugin.hvac.HvacLeftBar.3
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            Log.i(HvacLeftBar.TAG, "binderDied");
            if (HvacLeftBar.this.mIopenHvacAidlInterface == null) {
                return;
            }
            HvacLeftBar.this.mIopenHvacAidlInterface.asBinder().unlinkToDeath(HvacLeftBar.this.mDeathRecipient, 0);
            HvacLeftBar.this.mIopenHvacAidlInterface = null;
            HvacLeftBar.this.startService();
        }
    };
    private final HvacController.SimpleFunctionValueObserver mObserver = new HvacController.SimpleFunctionValueObserver() { // from class: com.ecarx.systemui.plugin.hvac.HvacLeftBar.4
        @Override // com.ecarx.systemui.plugin.hvac.HvacController.FunctionValueObserver
        public void onRegistered() {
            HvacLeftBar.this.refresh(-1, -1);
        }

        @Override // com.ecarx.systemui.plugin.hvac.HvacController.SimpleFunctionValueObserver, com.ecarx.systemui.plugin.hvac.HvacController.FunctionValueObserver
        protected void onChanged(int i, int i2, int i3) {
            HvacLeftBar.this.refresh(i, i2);
        }

        @Override // com.ecarx.systemui.plugin.hvac.HvacController.SimpleFunctionValueObserver, com.ecarx.systemui.plugin.hvac.HvacController.FunctionValueObserver
        protected void onChanged(int i, int i2, float f) {
            HvacLeftBar.this.refresh(i, i2);
        }
    };

    public void refreshView() {
    }

    public static HvacLeftBar getInstance(Context context) {
        if (mInstance == null) {
            synchronized (HvacLeftBar.class) {
                if (mInstance == null) {
                    mInstance = new HvacLeftBar(context);
                }
            }
        }
        return mInstance;
    }

    private HvacLeftBar(Context context) {
        this.mContext = context;
        init();
        initClick();
    }

    private void initClick() {
        this.mRootView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.ecarx.systemui.plugin.hvac.HvacLeftBar.1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                HvacLeftBar.this.isLongClick = true;
                try {
                    if (HvacLeftBar.this.mIopenHvacAidlInterface != null) {
                        HvacLeftBar.this.mIopenHvacAidlInterface.openHvacLeftTemp();
                        return false;
                    }
                    return false;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    public void click() {
        if (!this.isLongClick) {
            try {
                if (this.mIopenHvacAidlInterface != null) {
                    this.mIopenHvacAidlInterface.openHvacMain();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        this.isLongClick = false;
    }

    private void init() {
        try {
            Log.i(TAG, "init");
            getRootView();
        } catch (Throwable th) {
            String str = TAG;
            Log.e(str, "init exception: " + Log.getStackTraceString(th));
        }
        ThreadUtils.postDelay(3000L, new Runnable() { // from class: com.ecarx.systemui.plugin.hvac.-$$Lambda$HvacLeftBar$np7gHXCnFKnT7AmfFy5UhIvkFxU
            @Override // java.lang.Runnable
            public final void run() {
                HvacLeftBar.this.startService();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startService() {
        Intent intent = new Intent();
        intent.setAction("ecarx.hvac.app.HvacAppService");
        intent.setPackage(AppWatcherService.HVAC_PACKAGENAME);
        this.mContext.bindService(intent, this.serviceConnection, 1);
    }

    public void refresh(int i, int i2) {
        if (i == -1) {
            refreshAll();
            return;
        }
        switch (i) {
            case IHvac.HVAC_FUNC_POWER /* 268501248 */:
            case 268830208:
                refreshTemp();
                return;
            case IHvac.HVAC_FUNC_FAN_SPEED /* 268566784 */:
            case IHvac.HVAC_FUNC_AUTO_FAN_SETTING /* 268567040 */:
                refreshFan();
                return;
            case 268828928:
                if (i2 == 1) {
                    refreshTemp();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void refreshAll() {
        refreshTemp();
        refreshFan();
    }

    private void refreshTemp() {
        if (this.mRootView == null) {
            return;
        }
        HvacController hvacController = HvacController.getInstance();
        FunctionStatus driverTempStatus = hvacController.getDriverTempStatus();
        boolean hvacPower = hvacController.getHvacPower();
        float driverTemp = hvacController.getDriverTemp();
        FunctionStatus passengerTempStatus = hvacController.getPassengerTempStatus();
        float passengerTemp = hvacController.getPassengerTemp();
        LogUtils.stack("isHvacOn", Boolean.valueOf(hvacPower));
        LogUtils.stack("driveTempStatus", driverTempStatus);
        LogUtils.stack("driverTemp", Float.valueOf(driverTemp));
        NaviBarTempBean naviBarTempBean = new NaviBarTempBean();
        naviBarTempBean.setMaxTemp(hvacController.getTempMaxValue());
        naviBarTempBean.setMinTemp(hvacController.getTempMinValue());
        naviBarTempBean.setLeftTempStatus(driverTempStatus.name());
        naviBarTempBean.setLeftTemperature(driverTemp);
        naviBarTempBean.setRightTempStatus(passengerTempStatus.name());
        naviBarTempBean.setRightTemperature(passengerTemp);
    }

    private void refreshFan() {
        if (this.mRootView == null) {
            return;
        }
        HvacController hvacController = HvacController.getInstance();
        NaviBarTempBean naviBarTempBean = new NaviBarTempBean();
        naviBarTempBean.setLevel(hvacController.getBlowingLevel());
        naviBarTempBean.setMaxLevel(hvacController.getMaxFanLevel());
        naviBarTempBean.setFanStatus(hvacController.getAutoFanStatus().name());
    }

    public HvacView getRootView() {
        if (this.mRootView == null) {
            Log.i(TAG, "getRootView");
            this.mRootView = (HvacView) LayoutInflater.from(this.mContext).inflate(R.layout.temp_layout, (ViewGroup) null, false);
            HvacController.getInstance().registerObserver(this.mObserver);
        }
        return this.mRootView;
    }
}

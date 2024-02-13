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
import com.ecarx.systemui.plugin.navigationbar.AppWatcherService;


/* loaded from: classes.dex */
public class HvacRightBar {
    private static final String TAG = HvacRightBar.class.getSimpleName();
    private static volatile HvacRightBar mInstance;
    private boolean isLongClick;
    private Context mContext;
    private IOpenHvacAidlInterface mIopenHvacAidlInterface;
    private HvacView mRootView;
    public ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.ecarx.systemui.plugin.hvac.HvacRightBar.2
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(HvacRightBar.TAG, "onServiceConnected");
            HvacRightBar.this.mIopenHvacAidlInterface = IOpenHvacAidlInterface.Stub.asInterface(iBinder);
            try {
                iBinder.linkToDeath(HvacRightBar.this.mDeathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                HvacRightBar.this.mIopenHvacAidlInterface.registerListener(new INaviBarTempBeanListener.Stub() { // from class: com.ecarx.systemui.plugin.hvac.HvacRightBar.2.1
                    @Override // ecarx.hvac.app.INaviBarTempBeanListener
                    public void initTempData(NaviBarTempBean naviBarTempBean) throws RemoteException {
                        HvacRightBar.this.mRootView.setData(false, naviBarTempBean);
                    }

                    @Override // ecarx.hvac.app.INaviBarTempBeanListener
                    public void onNaviBarTempBeanChange(NaviBarTempBean naviBarTempBean) throws RemoteException {
                        String str = HvacRightBar.TAG;
                        Log.i(str, "functionid:" + naviBarTempBean.getFucntionId());
                        HvacRightBar.this.mRootView.refreshData(false, naviBarTempBean);
                    }
                });
                HvacRightBar.this.mIopenHvacAidlInterface.getBarBean();
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    };
    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() { // from class: com.ecarx.systemui.plugin.hvac.HvacRightBar.3
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            Log.i(HvacRightBar.TAG, "binderDied");
            if (HvacRightBar.this.mIopenHvacAidlInterface == null) {
                return;
            }
            HvacRightBar.this.mIopenHvacAidlInterface.asBinder().unlinkToDeath(HvacRightBar.this.mDeathRecipient, 0);
            HvacRightBar.this.mIopenHvacAidlInterface = null;
            HvacRightBar.this.startService();
        }
    };

    public void refreshView() {
    }

    public static HvacRightBar getInstance(Context context) {
        if (mInstance == null) {
            synchronized (HvacRightBar.class) {
                if (mInstance == null) {
                    mInstance = new HvacRightBar(context);
                }
            }
        }
        return mInstance;
    }

    private HvacRightBar(Context context) {
        this.mContext = context;
        init();
        initClick();
    }

    private void initClick() {
        this.mRootView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.ecarx.systemui.plugin.hvac.HvacRightBar.1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                HvacRightBar.this.isLongClick = true;
                try {
                    if (HvacRightBar.this.mIopenHvacAidlInterface != null) {
                        HvacRightBar.this.mIopenHvacAidlInterface.openHvacRightTemp();
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
    }

    public void click() {
        IOpenHvacAidlInterface iOpenHvacAidlInterface = this.mIopenHvacAidlInterface;
        if (iOpenHvacAidlInterface != null) {
            try {
                iOpenHvacAidlInterface.openHvacRightTempToast();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void init() {
        try {
            getRootView();
        } catch (Throwable th) {
            String str = TAG;
            Log.e(str, "init exception: " + Log.getStackTraceString(th));
        }
        startService();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startService() {
        Intent intent = new Intent();
        intent.setAction("ecarx.hvac.app.HvacAppService");
        intent.setPackage(AppWatcherService.HVAC_PACKAGENAME);
        this.mContext.bindService(intent, this.serviceConnection, 1);
    }

    public HvacView getRootView() {
        if (this.mRootView == null) {
            Log.i(TAG, "getRootView");
            this.mRootView = (HvacView) LayoutInflater.from(this.mContext).inflate(R.layout.temp_layout, (ViewGroup) null, false);
        }
        return this.mRootView;
    }
}

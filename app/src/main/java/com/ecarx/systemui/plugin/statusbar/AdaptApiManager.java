package com.ecarx.systemui.plugin.statusbar;

import android.util.Log;
import android.view.Display;
import com.ecarx.xui.adaptapi.binder.IConnectable;
import com.ecarx.xui.adaptapi.car.Car;
import com.ecarx.xui.adaptapi.car.ICar;
import com.ecarx.xui.adaptapi.car.base.ICarFunction;
import com.ecarx.xui.adaptapi.car.base.ICarInfo;
import com.ecarx.xui.adaptapi.car.sensor.ISensor;
import com.ecarx.xui.adaptapi.oncall.OnCall;

/* loaded from: classes.dex */
public class AdaptApiManager {
    public static final int DISPLAY_TYPE_CSD = 101;
    public static final int DISPLAY_TYPE_PSD = 102;
    public static final int DISPLAY_TYPE_RFDM = 104;
    public static final int DISPLAY_TYPE_RSD = 103;
    private static final String TAG = "AdaptApiManager";
    private static AdaptApiManager instance;
    private ICar mICar;
    private ICarFunction mICarFunction;
    private ICarInfo mICarInfo;
    private IConnectable mIConnectable;
    private ISensor mISensor;
    private OnCall onCall;
    private int psdDisplayId = -1;
    private int csdDisplayId = -1;

    public static AdaptApiManager getInstance() {
        if (instance == null) {
            synchronized (AdaptApiManager.class) {
                if (instance == null) {
                    instance = new AdaptApiManager();
                }
            }
        }
        return instance;
    }

    private AdaptApiManager() {
        startPsd();
    }

    public void startPsd() {
        ICar create = Car.create(StatusBar.getInstance(null).getRootView().getContext());
        this.mICar = create;
        if (create instanceof IConnectable) {
            IConnectable iConnectable = (IConnectable) create;
            this.mIConnectable = iConnectable;
            iConnectable.connect();
            this.mIConnectable.registerConnectWatcher(new IConnectable.IConnectWatcher() { // from class: com.ecarx.systemui.plugin.statusbar.AdaptApiManager.1
                @Override // com.ecarx.xui.adaptapi.binder.IConnectable.IConnectWatcher
                public void onConnected() {
                    if (AdaptApiManager.this.mICarInfo != null) {
                        return;
                    }
                    Log.e(AdaptApiManager.TAG, "registerConnectWatcher==onConnected");
                    AdaptApiManager adaptApiManager = AdaptApiManager.this;
                    adaptApiManager.mICarInfo = adaptApiManager.mICar.getCarInfoManager();
                    Log.e(AdaptApiManager.TAG, "mICarInfo==" + AdaptApiManager.this.mICarInfo);
                    AdaptApiManager.this.getDisplayInfo();
                }

                @Override // com.ecarx.xui.adaptapi.binder.IConnectable.IConnectWatcher
                public void onDisConnected() {
                    Log.e(AdaptApiManager.TAG, "onDisConnected");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDisplayInfo() {
        if (getDisplayByType(101) != null) {
            this.csdDisplayId = getDisplayByType(101).getDisplayId();
        }
        if (getDisplayByType(102) != null) {
            this.psdDisplayId = getDisplayByType(102).getDisplayId();
        }
    }

    public int getDisplayId() {
        return this.psdDisplayId;
    }

    public int getCsdDisplayId() {
        return this.csdDisplayId;
    }

    private Display getDisplayByType(int i) {
        Display display = null;
        try {
            ICarInfo carInfoManager = this.mICar.getCarInfoManager();
            this.mICarInfo = carInfoManager;
            if (carInfoManager != null) {
                switch (i) {
                    case 101:
                        display = carInfoManager.getPresentationDisplay(1);
                        break;
                    case 102:
                        display = carInfoManager.getPresentationDisplay(4);
                        break;
                    case 103:
                        display = carInfoManager.getPresentationDisplay(128);
                        break;
                    case 104:
                        display = carInfoManager.getPresentationDisplay(268435456);
                        break;
                }
            }
        } catch (Throwable unused) {
        }
        return display;
    }
}

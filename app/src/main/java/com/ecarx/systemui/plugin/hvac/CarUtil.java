package com.ecarx.systemui.plugin.hvac;

import android.util.Log;
import com.ecarx.systemui.plugin.statusbar.StatusBar;
import com.ecarx.xui.adaptapi.binder.IConnectable;
import com.ecarx.xui.adaptapi.car.Car;
import com.ecarx.xui.adaptapi.car.ICar;
import com.ecarx.xui.adaptapi.car.base.ICarFunction;
import com.ecarx.xui.adaptapi.car.base.ICarInfo;
import com.ecarx.xui.adaptapi.car.sensor.ISensor;
import com.ecarx.xui.adaptapi.device.Device;

/* loaded from: classes.dex */
public class CarUtil {
    private static final String TAG = CarUtil.class.getSimpleName();
    public static boolean isInit = false;
    private Device mDevice;
    private ICar mICar;
    private ICarFunction mICarFunction;
    private ICarInfo mICarInfo;
    private ISensor mISensor;

    /* loaded from: classes.dex */
    private static class SingletonHolder {
        private static CarUtil INSTANCE = new CarUtil();

        private SingletonHolder() {
        }
    }

    public static CarUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private CarUtil() {
        Log.d(TAG, "CarUtil init");
        this.mICar = Car.create(StatusBar.getInstance(null).getRootView().getContext());
        this.mDevice = Device.create(StatusBar.getInstance(null).getRootView().getContext());
        PcodeManager.getInstance().setDevice(this.mDevice);
        String str = TAG;
        Log.i(str, "mDevice = " + this.mDevice);
        ICar iCar = this.mICar;
        if (iCar == null) {
            return;
        }
        if (iCar instanceof IConnectable) {
            registCarManager();
        } else {
            getCarManager();
        }
    }

    private void getCarManager() {
        this.mICarFunction = this.mICar.getICarFunction();
        this.mICarInfo = this.mICar.getCarInfoManager();
        this.mISensor = this.mICar.getSensorManager();
        isInit = true;
        String str = TAG;
        Log.i(str, "onConnected = mICarFunction = " + this.mICarFunction + "   mICarInfo =" + this.mICarInfo + "   mISensor = " + this.mISensor);
    }

    private void registCarManager() {
        Log.i(TAG, "registCarManager");
        IConnectable iConnectable = (IConnectable) this.mICar;
        iConnectable.registerConnectWatcher(new IConnectable.IConnectWatcher() { // from class: com.ecarx.systemui.plugin.hvac.CarUtil.1
            @Override // com.ecarx.xui.adaptapi.binder.IConnectable.IConnectWatcher
            public void onConnected() {
                Log.d(CarUtil.TAG, "registerConnectWatcher onConnected");
                CarUtil carUtil = CarUtil.this;
                carUtil.mICarFunction = carUtil.mICar.getICarFunction();
                CarUtil carUtil2 = CarUtil.this;
                carUtil2.mICarInfo = carUtil2.mICar.getCarInfoManager();
                CarUtil carUtil3 = CarUtil.this;
                carUtil3.mISensor = carUtil3.mICar.getSensorManager();
                String str = CarUtil.TAG;
                Log.i(str, "onConnected = mICarFunction = " + CarUtil.this.mICarFunction + "   mICarInfo =" + CarUtil.this.mICarInfo + "   mISensor = " + CarUtil.this.mISensor);
                CarUtil.isInit = true;
            }

            @Override // com.ecarx.xui.adaptapi.binder.IConnectable.IConnectWatcher
            public void onDisConnected() {
                Log.d(CarUtil.TAG, "registerConnectWatcher onDisConnected");
                CarUtil.isInit = false;
            }
        });
        iConnectable.connect();
    }

    public ICarFunction getICarFunction() {
        return this.mICarFunction;
    }

    public ICarInfo getICarInfo() {
        return this.mICarInfo;
    }

    public ISensor getISensor() {
        return this.mISensor;
    }
}

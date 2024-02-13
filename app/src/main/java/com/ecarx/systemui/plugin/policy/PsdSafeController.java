package com.ecarx.systemui.plugin.policy;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.ecarx.systemui.plugin.App;
import com.ecarx.systemui.plugin.statusbar.StatusBarManager;
import com.ecarx.systemui.plugin.statusbar.iconview.EStatefulObject;
import com.ecarx.systemui.plugin.utils.CarUtils;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.car.ICar;
import com.ecarx.xui.adaptapi.car.base.ICarFunction;
import com.ecarx.xui.adaptapi.car.sensor.ISensor;
import com.ecarx.xui.adaptapi.car.sensor.ISensorEvent;
import com.ecarx.xui.adaptapi.car.vehicle.IBcm;
import java.util.Arrays;

/* loaded from: classes.dex */
public class PsdSafeController extends BaseController {
    private static final int ERROR_FUNC_VALUE = -1;
    private static final String TAG = PsdSafeController.class.getSimpleName();
    private static volatile PsdSafeController mPsdSafeController;
    private ICarFunction mCarFunction;
    private float mCurDoorStatus;
    private int mCurOccupationStatus;
    private int mCurSafeBeltStatus;
    private Runnable mDoorRunnable;
    private Handler mHandler;
    ICarFunction.IFunctionValueWatcher mIFunctionValueWatcher;
    ISensor.ISensorListener mISensorListener;
    private Runnable mInitRunnable;
    private Runnable mSafeBeltRunnable;
    private ISensor mSensor;

    private PsdSafeController(Context context) {
        super(context);
        this.mInitRunnable = new Runnable() { // from class: com.ecarx.systemui.plugin.policy.PsdSafeController.1
            @Override // java.lang.Runnable
            public void run() {
                PsdSafeController.this.initCarWatchers();
            }
        };
        this.mDoorRunnable = new Runnable() { // from class: com.ecarx.systemui.plugin.policy.PsdSafeController.2
            @Override // java.lang.Runnable
            public void run() {
                PsdSafeController.this.updatePassengerDoorChange();
            }
        };
        this.mSafeBeltRunnable = new Runnable() { // from class: com.ecarx.systemui.plugin.policy.PsdSafeController.3
            @Override // java.lang.Runnable
            public void run() {
                PsdSafeController.this.updatePassengerSafeBeltChange();
            }
        };
        this.mIFunctionValueWatcher = new ICarFunction.IFunctionValueWatcher() { // from class: com.ecarx.systemui.plugin.policy.PsdSafeController.4
            @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction.IFunctionValueWatcher
            public void onFunctionChanged(int i) {
            }

            @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction.IFunctionValueWatcher
            public void onFunctionValueChanged(int i, int i2, int i3) {
            }

            @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction.IFunctionValueWatcher
            public void onSupportedFunctionStatusChanged(int i, int i2, FunctionStatus functionStatus) {
            }

            @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction.IFunctionValueWatcher
            public void onSupportedFunctionValueChanged(int i, int[] iArr) {
            }

            @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction.IFunctionValueWatcher
            public void onCustomizeFunctionValueChanged(int i, int i2, float f) {
                String str = PsdSafeController.TAG;
                Log.i(str, "[JRSYS_PSD][onCustomizeFunctionValueChanged]-1 , function: " + i + ", zone: " + i2 + ", value: " + f);
                if (i == 553779968 && i2 == 4) {
                    PsdSafeController.this.mCurDoorStatus = f;
                    PsdSafeController.this.mHandler.removeCallbacks(PsdSafeController.this.mDoorRunnable);
                    PsdSafeController.this.mHandler.post(PsdSafeController.this.mDoorRunnable);
                }
            }
        };
        this.mISensorListener = new ISensor.ISensorListener() { // from class: com.ecarx.systemui.plugin.policy.PsdSafeController.5
            @Override // com.ecarx.xui.adaptapi.car.sensor.ISensor.ISensorListener
            public void onSensorSupportChanged(int i, FunctionStatus functionStatus) {
            }

            @Override // com.ecarx.xui.adaptapi.car.sensor.ISensor.ISensorListener
            public void onSensorValueChanged(int i, float f) {
            }

            @Override // com.ecarx.xui.adaptapi.car.sensor.ISensor.ISensorListener
            public void onSensorEventChanged(int i, int i2) {
                String str = PsdSafeController.TAG;
                Log.i(str, "[JRSYS_PSD][onSensorEventChanged] , sensor: " + i + ", event: " + PsdSafeController.getStatusString(i2));
                if (i == 2102016) {
                    PsdSafeController.this.mCurSafeBeltStatus = i2;
                    PsdSafeController.this.mHandler.removeCallbacks(PsdSafeController.this.mSafeBeltRunnable);
                    PsdSafeController.this.mHandler.post(PsdSafeController.this.mSafeBeltRunnable);
                } else if (i != 2110464) {
                } else {
                    PsdSafeController.this.mCurOccupationStatus = i2;
                    PsdSafeController.this.mHandler.removeCallbacks(PsdSafeController.this.mSafeBeltRunnable);
                    PsdSafeController.this.mHandler.post(PsdSafeController.this.mSafeBeltRunnable);
                }
            }
        };
    }

    public static PsdSafeController getInstance() {
        if (mPsdSafeController == null) {
            synchronized (PsdSafeController.class) {
                if (mPsdSafeController == null) {
                    mPsdSafeController = new PsdSafeController(App.getApp().getApplicationContext());
                    mPsdSafeController.init();
                }
            }
        }
        return mPsdSafeController;
    }

    private void init() {
        Handler handler = new Handler();
        this.mHandler = handler;
        handler.removeCallbacks(this.mInitRunnable);
        this.mHandler.post(this.mInitRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initCarWatchers() {
        CarUtils carUtils = CarUtils.getInstance();
        if (carUtils.isConnected()) {
            ICar iCar = carUtils.getICar();
            ICarFunction iCarFunction = iCar.getICarFunction();
            this.mCarFunction = iCarFunction;
            if (iCarFunction != null) {
                this.mCurDoorStatus = getCustomizeFunctionValue(IBcm.BCM_FUNC_DOOR_POS, 4);
                String str = TAG;
                Log.i(str, "[JRSYS_PSD][initCarWatchers], mCurDoorStatus: " + this.mCurDoorStatus);
                this.mCarFunction.registerFunctionValueWatcher(IBcm.BCM_FUNC_DOOR_POS, this.mIFunctionValueWatcher);
                this.mHandler.removeCallbacks(this.mDoorRunnable);
                this.mHandler.post(this.mDoorRunnable);
            } else {
                Log.w(TAG, "[JRSYS_PSD][initCarWatchers] is null");
            }
            ISensor sensorManager = iCar.getSensorManager();
            this.mSensor = sensorManager;
            if (sensorManager != null) {
                this.mCurSafeBeltStatus = getSensorEvent(ISensor.SENSOR_TYPE_SAFE_BELT_PASSENGER);
                this.mCurOccupationStatus = getSensorEvent(ISensor.SENSOR_TYPE_SEAT_OCCUPATION_STATUS_PASSENGER);
                this.mSensor.registerListener(this.mISensorListener, ISensor.SENSOR_TYPE_SAFE_BELT_PASSENGER);
                this.mSensor.registerListener(this.mISensorListener, ISensor.SENSOR_TYPE_SEAT_OCCUPATION_STATUS_PASSENGER);
                this.mHandler.removeCallbacks(this.mSafeBeltRunnable);
                this.mHandler.post(this.mSafeBeltRunnable);
                return;
            }
            Log.w(TAG, "[JRSYS_PSD][initCarWatchers] Sensor is null");
        }
    }

    private int getSensorEvent(int i) {
        if (isSensorSupported(i)) {
            return this.mSensor.getSensorEvent(i);
        }
        String str = TAG;
        Log.i(str, "[JRSYS_PSD][getSensorEvent] Not support sensor，sensor code is：" + i);
        return -1;
    }

    private boolean isSensorSupported(int i) {
        ISensor iSensor = this.mSensor;
        return iSensor != null && iSensor.isSensorSupported(i) == FunctionStatus.active;
    }

    private float getCustomizeFunctionValue(int i, int i2) {
        if (isFunctionSupported(i, i2)) {
            return this.mCarFunction.getCustomizeFunctionValue(i, i2);
        }
        String str = TAG;
        Log.i(str, "[JRSYS_PSD][getFunctionValue] Not support function，function code is：" + i);
        return -1.0f;
    }

    private boolean isFunctionSupported(int i, int i2) {
        ICarFunction iCarFunction = this.mCarFunction;
        if (iCarFunction != null) {
            int[] supportedFunctionZones = iCarFunction.getSupportedFunctionZones(i);
            if (supportedFunctionZones == null || supportedFunctionZones.length <= 0) {
                if (supportedFunctionZones == null) {
                    Log.i(TAG, "zones == null");
                    return false;
                }
                Log.i(TAG, "zones size = 0");
                return false;
            }
            String str = TAG;
            Log.i(str, "[JRSYS_PSD][isFunctionSupported], zones: " + Arrays.toString(supportedFunctionZones) + ", zone: " + i2);
            for (int i3 = 0; i3 < supportedFunctionZones.length; i3++) {
                if (supportedFunctionZones[i3] == i2) {
                    return true;
                }
            }
            return false;
        }
        Log.i(TAG, "mCarFunction == null");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePassengerDoorChange() {
        String str = TAG;
        Log.i(str, "[JRSYS_PSD][updatePassengerDoorChange], mCurDoorStatus: " + this.mCurDoorStatus);
        StatusBarManager.getInstance().updateIconVisibility(EStatefulObject.WPC, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePassengerSafeBeltChange() {
        String str = TAG;
        Log.i(str, "[JRSYS_PSD][updatePassengerSafeBeltChange], mCurOccupationStatus: " + getStatusString(this.mCurOccupationStatus) + ", mCurSafeBeltStatus: " + getStatusString(this.mCurSafeBeltStatus));
        int i = this.mCurOccupationStatus;
        StatusBarManager.getInstance().updateIconVisibility(EStatefulObject.USB, false);
    }

    private void testCode() {
        this.mCurDoorStatus = 1.0f;
        this.mCurSafeBeltStatus = ISensorEvent.SAFE_BELT_STATE_UNBUCKLED;
        this.mCurOccupationStatus = ISensorEvent.SEAT_OCCUPATION_STATUS_OCCUPIED;
        this.mHandler.removeCallbacks(this.mDoorRunnable);
        this.mHandler.post(this.mDoorRunnable);
        this.mHandler.removeCallbacks(this.mSafeBeltRunnable);
        this.mHandler.post(this.mSafeBeltRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getStatusString(int i) {
        if (i != -1) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 553779457) {
                        switch (i) {
                            case ISensorEvent.SAFE_BELT_STATE_UNBUCKLED /* 2101761 */:
                                return "SAFE_BELT_STATE_UNBUCKLED";
                            case ISensorEvent.SAFE_BELT_STATE_BUCKLED /* 2101762 */:
                                return "SAFE_BELT_STATE_BUCKLED";
                            default:
                                switch (i) {
                                    case ISensorEvent.SEAT_OCCUPATION_STATUS_NONE /* 2110209 */:
                                        return "SEAT_OCCUPATION_STATUS_NONE";
                                    case ISensorEvent.SEAT_OCCUPATION_STATUS_OCCUPIED /* 2110210 */:
                                        return "SEAT_OCCUPATION_STATUS_OCCUPIED";
                                    case ISensorEvent.SEAT_OCCUPATION_STATUS_FAULT /* 2110211 */:
                                        return "SEAT_OCCUPATION_STATUS_FAULT";
                                    default:
                                        return String.valueOf(i);
                                }
                        }
                    }
                    return "DOOR_PAUSE";
                }
                return "DOOR_OPEN";
            }
            return "DOOR_CLOSE";
        }
        return "STATE_UNKNOWN";
    }
}

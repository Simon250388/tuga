package com.ecarx.systemui.plugin.hvac;

import android.util.Log;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.car.sensor.ISensor;

/* loaded from: classes.dex */
public class FunctionManagerCarSensor implements IFunctionManager {
    private static final String TAG = FunctionManagerCarSensor.class.getSimpleName();
    private IFunctionValueCallback callback;
    private ISensor.ISensorListener sensorListener = new ISensor.ISensorListener() { // from class: com.ecarx.systemui.plugin.hvac.FunctionManagerCarSensor.1
        /* JADX WARN: Type inference failed for: r4v1, types: [T, java.lang.Integer] */
        @Override // com.ecarx.xui.adaptapi.car.sensor.ISensor.ISensorListener
        public void onSensorEventChanged(int i, int i2) {
            String str = FunctionManagerCarSensor.TAG;
            Log.i(str, "onSensorEventChanged sensor == " + i + " event = " + i2);
            FunctionWatcherResult functionWatcherResult = new FunctionWatcherResult();
            functionWatcherResult.functionId = i;
            functionWatcherResult.operator = Integer.valueOf(i2);
            FunctionManagerCarSensor.this.callback.functionValueChange(functionWatcherResult);
        }

        /* JADX WARN: Type inference failed for: r4v1, types: [java.lang.Float, T] */
        @Override // com.ecarx.xui.adaptapi.car.sensor.ISensor.ISensorListener
        public void onSensorValueChanged(int i, float f) {
            String str = FunctionManagerCarSensor.TAG;
            Log.i(str, "onSensorEventChanged sensor == " + i + " value = " + f);
            FunctionWatcherResult functionWatcherResult = new FunctionWatcherResult();
            functionWatcherResult.functionId = i;
            functionWatcherResult.operator = Float.valueOf(f);
            FunctionManagerCarSensor.this.callback.functionValueChange(functionWatcherResult);
        }

        @Override // com.ecarx.xui.adaptapi.car.sensor.ISensor.ISensorListener
        public void onSensorSupportChanged(int i, FunctionStatus functionStatus) {
            String str = FunctionManagerCarSensor.TAG;
            Log.i(str, "onSensorSupportChanged sensor == " + i + " status = " + functionStatus);
            FunctionWatcherResult functionWatcherResult = new FunctionWatcherResult();
            functionWatcherResult.functionId = i;
            functionWatcherResult.functionStatus = functionStatus;
            FunctionManagerCarSensor.this.callback.functionValueChange(functionWatcherResult);
        }
    };
    private ISensor mISensor = CarUtil.getInstance().getISensor();

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionManager
    public BaseResult setFunctionValue(BaseParam baseParam) {
        Log.d(TAG, "setFunctionValue is not valid");
        BaseResult baseResult = new BaseResult();
        baseResult.setFunctionSuccess = false;
        return baseResult;
    }

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionManager
    public BaseResult getFunctionValue(BaseParam baseParam) {
        String str = TAG;
        Log.d(str, "getFunctionValue param:" + baseParam);
        BaseResult baseResult = new BaseResult();
        if (this.mISensor == null) {
            Log.d(TAG, "getFunctionValue mICarFunction is null");
            baseResult.value = BaseResult.ERROR_CAR_FUNCTION_IS_NULL;
            return baseResult;
        }
        baseResult.value = getSensorEvent(baseParam.functionId);
        return baseResult;
    }

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionManager
    public BaseResult getCustomizeFunctionValue(BaseParam baseParam) {
        String str = TAG;
        Log.d(str, "getCustomizeFunctionValue param:" + baseParam);
        BaseResult baseResult = new BaseResult();
        if (this.mISensor == null) {
            Log.d(TAG, "getCustomizeFunctionValue mICarFunction is null");
            baseResult.customValue = -65537.0f;
            return baseResult;
        }
        baseResult.customValue = getSensorLatestValue(baseParam.functionId);
        return baseResult;
    }

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionManager
    public void setFunctionValueListener(IFunctionValueCallback iFunctionValueCallback) {
        this.callback = iFunctionValueCallback;
    }

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionManager
    public void release() {
        Log.d(TAG, "unregisterFunctionValueWatcher");
        ISensor iSensor = this.mISensor;
        if (iSensor == null) {
            Log.d(TAG, "unregisterFunctionValueWatcher mISensor is null");
        } else {
            iSensor.unregisterListener(this.sensorListener);
        }
    }

    private int getSensorEvent(int i) {
        return this.mISensor.getSensorEvent(i);
    }

    private float getSensorLatestValue(int i) {
        return this.mISensor.getSensorLatestValue(i);
    }

    public void registerFunctionValueWatcher(int[] iArr) {
        Log.d(TAG, "registerFunctionValueWatcher");
        ISensor iSensor = CarUtil.getInstance().getISensor();
        this.mISensor = iSensor;
        if (iSensor == null) {
            Log.d(TAG, "registerFunctionValueWatcher mISensor is null");
            return;
        }
        for (int i : iArr) {
            this.mISensor.registerListener(this.sensorListener, i);
        }
    }
}

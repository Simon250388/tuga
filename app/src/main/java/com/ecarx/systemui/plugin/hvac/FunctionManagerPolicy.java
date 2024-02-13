package com.ecarx.systemui.plugin.hvac;

import android.util.Log;

/* loaded from: classes.dex */
public class FunctionManagerPolicy implements IFunctionManager {
    private static final String TAG = FunctionManagerPolicy.class.getSimpleName();
    private FunctionManagerCarFunction mFunctionManagerCarFunction = new FunctionManagerCarFunction();
    private FunctionManagerCarSensor mFunctionManagerCarSensor = new FunctionManagerCarSensor();

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionManager
    public BaseResult setFunctionValue(BaseParam baseParam) {
        String str = TAG;
        Log.d(str, "setFunctionValue param:" + baseParam);
        if (baseParam.type == 1) {
            return this.mFunctionManagerCarSensor.setFunctionValue(baseParam);
        }
        if (baseParam.type == 2) {
            return this.mFunctionManagerCarFunction.setFunctionValue(baseParam);
        }
        Log.d(TAG, "setFunctionValue functionId no match");
        BaseResult baseResult = new BaseResult();
        baseResult.value = Integer.MIN_VALUE;
        return baseResult;
    }

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionManager
    public BaseResult getFunctionValue(BaseParam baseParam) {
        String str = TAG;
        Log.d(str, "getFunctionValue param:" + baseParam);
        if (baseParam.type == 1) {
            return this.mFunctionManagerCarSensor.getFunctionValue(baseParam);
        }
        if (baseParam.type == 2) {
            return this.mFunctionManagerCarFunction.getFunctionValue(baseParam);
        }
        Log.d(TAG, "getCustomizeFunctionValue functionId no match");
        BaseResult baseResult = new BaseResult();
        baseResult.value = Integer.MIN_VALUE;
        return baseResult;
    }

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionManager
    public BaseResult getCustomizeFunctionValue(BaseParam baseParam) {
        String str = TAG;
        Log.d(str, "getCustomizeFunctionValue param:" + baseParam);
        if (baseParam.type == 1) {
            return this.mFunctionManagerCarSensor.getCustomizeFunctionValue(baseParam);
        }
        if (baseParam.type == 2) {
            return this.mFunctionManagerCarFunction.getCustomizeFunctionValue(baseParam);
        }
        Log.d(TAG, "getCustomizeFunctionValue functionId no match");
        BaseResult baseResult = new BaseResult();
        baseResult.customValue = Float.MIN_VALUE;
        return baseResult;
    }

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionManager
    public void setFunctionValueListener(IFunctionValueCallback iFunctionValueCallback) {
        this.mFunctionManagerCarFunction.setFunctionValueListener(iFunctionValueCallback);
        this.mFunctionManagerCarSensor.setFunctionValueListener(iFunctionValueCallback);
    }

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionManager
    public void release() {
        this.mFunctionManagerCarFunction.release();
        this.mFunctionManagerCarSensor.release();
    }

    public void registerFunctionValueWatcher() {
        this.mFunctionManagerCarFunction.registerFunctionValueWatcher(Utils.SetToInt(BaseConfig.mFunctionIds));
        this.mFunctionManagerCarSensor.registerFunctionValueWatcher(Utils.SetToInt(BaseConfig.mSensorIds));
    }
}

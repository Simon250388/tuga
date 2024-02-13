package com.ecarx.systemui.plugin.hvac;

import android.util.Log;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.car.base.ICarFunction;

/* loaded from: classes.dex */
public class FunctionManagerCarFunction implements IFunctionManager {
    private static final String TAG = FunctionManagerCarFunction.class.getSimpleName();
    private IFunctionValueCallback callback;
    private ICarFunction.IFunctionValueWatcher functionValueWatcher = new ICarFunction.IFunctionValueWatcher() { // from class: com.ecarx.systemui.plugin.hvac.FunctionManagerCarFunction.1
        @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction.IFunctionValueWatcher
        public void onFunctionChanged(int i) {
            String str = FunctionManagerCarFunction.TAG;
            Log.i(str, "onFunctionChanged function == " + i);
        }

        /* JADX WARN: Type inference failed for: r4v1, types: [T, java.lang.Integer] */
        @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction.IFunctionValueWatcher
        public void onFunctionValueChanged(int i, int i2, int i3) {
            String str = FunctionManagerCarFunction.TAG;
            Log.i(str, "onFunctionValueChanged function == " + Integer.toHexString(i) + "   zone = " + i2 + " value = " + i3);
            FunctionWatcherResult functionWatcherResult = new FunctionWatcherResult();
            functionWatcherResult.functionId = i;
            functionWatcherResult.zone = i2;
            functionWatcherResult.operator = Integer.valueOf(i3);
            FunctionManagerCarFunction.this.callback.functionValueChange(functionWatcherResult);
        }

        /* JADX WARN: Type inference failed for: r4v1, types: [java.lang.Float, T] */
        @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction.IFunctionValueWatcher
        public void onCustomizeFunctionValueChanged(int i, int i2, float f) {
            String str = FunctionManagerCarFunction.TAG;
            Log.i(str, "onCustomizeFunctionValueChanged function == " + Integer.toHexString(i) + "   zone = " + i2 + " value = " + f);
            FunctionWatcherResult functionWatcherResult = new FunctionWatcherResult();
            functionWatcherResult.functionId = i;
            functionWatcherResult.zone = i2;
            functionWatcherResult.operator = Float.valueOf(f);
            FunctionManagerCarFunction.this.callback.functionValueChange(functionWatcherResult);
        }

        @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction.IFunctionValueWatcher
        public void onSupportedFunctionStatusChanged(int i, int i2, FunctionStatus functionStatus) {
            String str = FunctionManagerCarFunction.TAG;
            Log.i(str, "onSupportedFunctionStatusChanged function == " + Integer.toHexString(i) + "   zone = " + i2 + " status = " + functionStatus);
            FunctionWatcherResult functionWatcherResult = new FunctionWatcherResult();
            functionWatcherResult.functionId = i;
            functionWatcherResult.zone = i2;
            functionWatcherResult.functionStatus = functionStatus;
            FunctionManagerCarFunction.this.callback.functionValueChange(functionWatcherResult);
        }

        @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction.IFunctionValueWatcher
        public void onSupportedFunctionValueChanged(int i, int[] iArr) {
            String str = FunctionManagerCarFunction.TAG;
            Log.i(str, "onSupportedFunctionValueChanged function == " + Integer.toHexString(i) + " funcValues = " + iArr);
            FunctionWatcherResult functionWatcherResult = new FunctionWatcherResult();
            functionWatcherResult.functionId = i;
            functionWatcherResult.funcValues = iArr;
            FunctionManagerCarFunction.this.callback.functionValueChange(functionWatcherResult);
        }
    };
    private ICarFunction mICarFunction = CarUtil.getInstance().getICarFunction();

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionManager
    public BaseResult setFunctionValue(BaseParam baseParam) {
        String str = TAG;
        Log.d(str, "setFunctionValue param:" + baseParam);
        BaseResult baseResult = new BaseResult();
        baseResult.setFunctionSuccess = false;
        if (this.mICarFunction == null) {
            Log.d(TAG, "setFunctionValue mICarFunction is null");
            return baseResult;
        }
        if (baseParam.zone == -1) {
            if (baseParam.operator instanceof Integer) {
                baseResult.setFunctionSuccess = setFunctionValue(baseParam.functionId, ((Integer) baseParam.operator).intValue());
            } else if (baseParam.operator instanceof Float) {
                baseResult.setFunctionSuccess = setCustomizeFunctionValue(baseParam.functionId, ((Float) baseParam.operator).floatValue());
            }
        } else if (baseParam.operator instanceof Integer) {
            baseResult.setFunctionSuccess = setFunctionValue(baseParam.functionId, baseParam.zone, ((Integer) baseParam.operator).intValue());
        } else if (baseParam.operator instanceof Float) {
            baseResult.setFunctionSuccess = setCustomizeFunctionValue(baseParam.functionId, baseParam.zone, ((Float) baseParam.operator).floatValue());
        }
        return baseResult;
    }

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionManager
    public BaseResult getFunctionValue(BaseParam baseParam) {
        String str = TAG;
        Log.d(str, "getFunctionValue param:" + baseParam);
        BaseResult baseResult = new BaseResult();
        if (this.mICarFunction == null) {
            Log.d(TAG, "getFunctionValue mICarFunction is null");
            baseResult.value = BaseResult.ERROR_CAR_FUNCTION_IS_NULL;
            return baseResult;
        }
        if (baseParam.zone == -1) {
            baseResult.value = getFunctionValue(baseParam.functionId);
        } else {
            baseResult.value = getFunctionValue(baseParam.functionId, baseParam.zone);
        }
        String str2 = TAG;
        Log.i(str2, "getFunctionValue result = " + baseResult);
        return baseResult;
    }

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionManager
    public BaseResult getCustomizeFunctionValue(BaseParam baseParam) {
        String str = TAG;
        Log.d(str, "getCustomizeFunctionValue param:" + baseParam);
        BaseResult baseResult = new BaseResult();
        if (this.mICarFunction == null) {
            Log.d(TAG, "getCustomizeFunctionValue mICarFunction is null");
            this.mICarFunction = CarUtil.getInstance().getICarFunction();
            baseResult.customValue = -65537.0f;
            return baseResult;
        }
        if (baseParam.zone == -1) {
            baseResult.customValue = getCustomizeFunctionValue(baseParam.functionId);
        } else {
            baseResult.customValue = getCustomizeFunctionValue(baseParam.functionId, baseParam.zone);
        }
        String str2 = TAG;
        Log.i(str2, "getCustomizeFunctionValue result = " + baseResult);
        return baseResult;
    }

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionManager
    public void setFunctionValueListener(IFunctionValueCallback iFunctionValueCallback) {
        this.callback = iFunctionValueCallback;
    }

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionManager
    public void release() {
        Log.d(TAG, "unregisterFunctionValueWatcher");
        ICarFunction iCarFunction = this.mICarFunction;
        if (iCarFunction == null) {
            Log.d(TAG, "unregisterFunctionValueWatcher mICarFunction is null");
            this.mICarFunction = CarUtil.getInstance().getICarFunction();
            return;
        }
        iCarFunction.unregisterFunctionValueWatcher(this.functionValueWatcher);
    }

    private int getFunctionValue(int i) {
        return this.mICarFunction.getFunctionValue(i);
    }

    private int getFunctionValue(int i, int i2) {
        return this.mICarFunction.getFunctionValue(i, i2);
    }

    private float getCustomizeFunctionValue(int i) {
        float customizeFunctionValue = this.mICarFunction.getCustomizeFunctionValue(i);
        String str = TAG;
        Log.d(str, "getCustomizeFunctionValue functionId:" + Integer.toHexString(i) + "  result:" + customizeFunctionValue);
        return customizeFunctionValue;
    }

    private float getCustomizeFunctionValue(int i, int i2) {
        return this.mICarFunction.getCustomizeFunctionValue(i, i2);
    }

    private boolean setFunctionValue(int i, int i2) {
        String str = TAG;
        Log.d(str, "setFunctionValue functionId:" + Integer.toHexString(i) + " value:" + i2);
        boolean functionValue = this.mICarFunction.setFunctionValue(i, i2);
        String str2 = TAG;
        Log.i(str2, "setFunctionValue result:" + functionValue);
        return functionValue;
    }

    private boolean setFunctionValue(int i, int i2, int i3) {
        String str = TAG;
        Log.d(str, "setFunctionValue functionId:" + Integer.toHexString(i) + "   zone:" + i2 + " value:" + i3);
        boolean functionValue = this.mICarFunction.setFunctionValue(i, i2, i3);
        String str2 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("setFunctionValue result:");
        sb.append(functionValue);
        Log.i(str2, sb.toString());
        return functionValue;
    }

    private boolean setCustomizeFunctionValue(int i, float f) {
        String str = TAG;
        Log.d(str, "setCustomizeFunctionValue functionId:" + Integer.toHexString(i) + " value:" + f);
        boolean customizeFunctionValue = this.mICarFunction.setCustomizeFunctionValue(i, f);
        String str2 = TAG;
        Log.d(str2, "setCustomizeFunctionValue result:" + customizeFunctionValue);
        return customizeFunctionValue;
    }

    private boolean setCustomizeFunctionValue(int i, int i2, float f) {
        String str = TAG;
        Log.d(str, "setCustomizeFunctionValue functionId:" + Integer.toHexString(i) + "   zone:" + i2 + " value:" + f);
        boolean customizeFunctionValue = this.mICarFunction.setCustomizeFunctionValue(i, i2, f);
        String str2 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("setCustomizeFunctionValue result:");
        sb.append(customizeFunctionValue);
        Log.d(str2, sb.toString());
        return customizeFunctionValue;
    }

    public boolean registerFunctionValueWatcher(int[] iArr) {
        Log.d(TAG, "registerFunctionValueWatcher");
        ICarFunction iCarFunction = CarUtil.getInstance().getICarFunction();
        this.mICarFunction = iCarFunction;
        if (iCarFunction == null) {
            Log.d(TAG, "registerFunctionValueWatcher mICarFunction is null");
            return false;
        }
        for (int i = 0; i < iArr.length; i++) {
            boolean registerFunctionValueWatcher = this.mICarFunction.registerFunctionValueWatcher(iArr[i], this.functionValueWatcher);
            String str = TAG;
            Log.d(str, "registerFunctionValueWatcher functionIds=" + iArr[i] + " succ=" + registerFunctionValueWatcher);
        }
        return true;
    }
}

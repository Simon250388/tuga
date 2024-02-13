package com.ecarx.systemui.plugin.hvac;

import android.util.Log;

/* loaded from: classes.dex */
public class FunctionSupportPolicy implements IFunctionSupport {
    private static final String TAG = FunctionSupportPolicy.class.getSimpleName();
    private FunctionSupportPCode mFunctionSupportPCode = new FunctionSupportPCode();
    private FunctionSupportCarFunction mFunctionSupportAdapterAPI = new FunctionSupportCarFunction();
    private FunctionSupportCarSensor mFunctionSupportCarSensor = new FunctionSupportCarSensor();

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionSupport
    public BaseResult isFunctionSupport(BaseParam<Integer> baseParam) {
        String str = TAG;
        Log.d(str, "isFunctionSupport param:" + baseParam);
        if (baseParam.type == 0) {
            return this.mFunctionSupportPCode.isFunctionSupport(baseParam);
        }
        if (baseParam.type == 1) {
            return this.mFunctionSupportCarSensor.isFunctionSupport(baseParam);
        }
        if (baseParam.type == 2) {
            return this.mFunctionSupportAdapterAPI.isFunctionSupport(baseParam);
        }
        Log.d(TAG, "isFunctionSupport functionId no match");
        BaseResult baseResult = new BaseResult();
        baseResult.isFunctionSupport = false;
        return baseResult;
    }
}

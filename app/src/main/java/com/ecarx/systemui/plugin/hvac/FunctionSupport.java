package com.ecarx.systemui.plugin.hvac;

import android.util.Log;

/* loaded from: classes.dex */
public class FunctionSupport implements IFunctionSupport {
    private static final String TAG = FunctionSupport.class.getSimpleName();
    private FunctionSupportPolicy mFunctionSupportPolicy;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SingletonHolder {
        private static FunctionSupport INSTANCE = new FunctionSupport();

        private SingletonHolder() {
        }
    }

    public static FunctionSupport getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private FunctionSupport() {
        this.mFunctionSupportPolicy = new FunctionSupportPolicy();
    }

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionSupport
    public BaseResult isFunctionSupport(BaseParam baseParam) {
        if (baseParam == null) {
            Log.d(TAG, "isFunctionSupport param is null");
            BaseResult baseResult = new BaseResult();
            baseResult.isFunctionSupport = false;
            return baseResult;
        }
        return this.mFunctionSupportPolicy.isFunctionSupport(baseParam);
    }
}

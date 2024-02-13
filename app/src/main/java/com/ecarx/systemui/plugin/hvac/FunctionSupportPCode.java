package com.ecarx.systemui.plugin.hvac;

import android.util.Log;

/* loaded from: classes.dex */
public class FunctionSupportPCode implements IFunctionSupport {
    private static final String TAG = FunctionSupportPCode.class.getSimpleName();

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionSupport
    public BaseResult isFunctionSupport(BaseParam baseParam) {
        if ((baseParam.functionId & IHvacFunctionId.HVAC_FUNC_PCODE) != 269549824) {
            Log.d(TAG, "isFunctionSupport PCode functionId is invalid");
            BaseResult baseResult = new BaseResult();
            baseResult.isFunctionSupport = false;
            return baseResult;
        }
        return isFunctionSupport(baseParam.functionId);
    }

    private BaseResult isFunctionSupport(int i) {
        return PcodeManager.getInstance().isFunctionSupport(i);
    }
}

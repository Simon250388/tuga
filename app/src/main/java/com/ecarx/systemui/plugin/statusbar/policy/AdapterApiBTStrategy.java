package com.ecarx.systemui.plugin.statusbar.policy;

/* loaded from: classes.dex */
public class AdapterApiBTStrategy extends BTStrategy {

    public void destroy() {
    }

    @Override
    public boolean isA2dpConnected() {
        return false;
    }

    @Override
    public boolean isAvrcpConnected() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public boolean isHfpConnected() {
        return false;
    }

    @Override
    public boolean isSupport() {
        return false;
    }

    @Override
    public void setCallBack(BTStrategyCallback bTStrategyCallback) {

    }
}

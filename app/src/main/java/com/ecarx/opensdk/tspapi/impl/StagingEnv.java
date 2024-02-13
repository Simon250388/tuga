package com.ecarx.opensdk.tspapi.impl;

import com.ecarx.opensdk.tspapi.IEnvType;

/* loaded from: classes.dex */
public final class StagingEnv implements IEnvType {
    static final String TYPE = "staging";

    @Override // com.ecarx.opensdk.tspapi.IEnvType
    public boolean isDevelopment() {
        return false;
    }

    @Override // com.ecarx.opensdk.tspapi.IEnvType
    public boolean isProductionEnv() {
        return false;
    }

    @Override // com.ecarx.opensdk.tspapi.IEnvType
    public boolean isStagingEnv() {
        return true;
    }

    @Override // com.ecarx.opensdk.tspapi.IEnvType
    public boolean isTestingEnv() {
        return false;
    }

    @Override // com.ecarx.opensdk.tspapi.IEnvType
    public String string() {
        return TYPE;
    }
}

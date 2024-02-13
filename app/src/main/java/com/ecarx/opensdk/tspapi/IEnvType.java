package com.ecarx.opensdk.tspapi;

/* loaded from: classes.dex */
public interface IEnvType {
    boolean isDevelopment();

    boolean isProductionEnv();

    boolean isStagingEnv();

    boolean isTestingEnv();

    String string();
}

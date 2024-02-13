package com.ecarx.opensdk.tspapi.impl;

import android.content.Context;
import com.ecarx.opensdk.tspapi.IEnvType;
import com.ecarx.opensdk.tspapi.TspAPI;

/* loaded from: classes.dex */
public final class TspAPIImpl extends TspAPI {
    private static final String PROP_PERSIST_TSP_ENV_TYPE = "persist.sys.tsp_env";
    private Context context;

    public TspAPIImpl(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override // com.ecarx.opensdk.tspapi.TspAPI
    public IEnvType getEnvType() {
        String stringProp = PropertiesUtil.getStringProp(this.context, PROP_PERSIST_TSP_ENV_TYPE);
        if (stringProp == null) {
            return new ProductionEnv();
        }
        if (stringProp.equalsIgnoreCase("development")) {
            return new DevelopmentEnv();
        }
        if (stringProp.equalsIgnoreCase("testing")) {
            return new TestingEnv();
        }
        if (stringProp.equalsIgnoreCase("staging")) {
            return new StagingEnv();
        }
        return new ProductionEnv();
    }
}

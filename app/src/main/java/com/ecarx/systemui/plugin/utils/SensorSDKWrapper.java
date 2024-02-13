package com.ecarx.systemui.plugin.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import com.ecarx.opensdk.tspapi.IEnvType;
import com.ecarx.opensdk.tspapi.TspAPI;
import com.ecarx.sdk.openapi.ECarXApiClient;
import com.ecarx.sdk.user.IUser;
import com.ecarx.sdk.user.UserAPI;
import com.sensorsdata.analytics.android.sdk.SAConfigOptions;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.SensorsDataDynamicSuperProperties;
import java.math.BigDecimal;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SensorSDKWrapper {
    private static final String SERVER_URL_PRODUCTION = "https://shence.ecarx.cn:8106/sa?project=production&token=schemaLimited-mGJ7a2jT";
    private static final String SERVER_URL_TESTING = "https://shence.ecarx.cn:8106/sa?project=default&token=schemaLimited-hu0F7V3l";
    private static final String TAG = "SensorSDKWrapper";
    private static double height;
    private static double latitude;
    private static double longitude;
    private static Context sContext;
    private static boolean sIsUserAPIReady;
    private static UserAPI userAPI;

    private SensorSDKWrapper() {
    }

    public static void init(Context context) {
        sContext = context;
        SAConfigOptions sAConfigOptions = new SAConfigOptions(getServerUrl(context));
        sAConfigOptions.setAutoTrackEventType(3).enableLog(true).enableTrackAppCrash();
        SensorsDataAPI.startWithConfigOptions(context, sAConfigOptions);
        registerSuperProperties();
        registerDynamicSuperProperties();
    }

    public static String getServerUrl(Context context) {
        try {
            IEnvType envType = TspAPI.create(context).getEnvType();
            Lg.i(TAG, "[JRSYS][getServerUrl], type: " + envType);
            return envType.isProductionEnv() ? SERVER_URL_PRODUCTION : SERVER_URL_TESTING;
        } catch (Exception e) {
            Lg.e(TAG, "[JRSYS] getServerUrl error, default server_url is https://shence.ecarx.cn:8106/sa?project=default&token=schemaLimited-hu0F7V3l", e);
            return SERVER_URL_TESTING;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:1|2|3|(3:4|5|6)|(2:7|8)|10|11|12|(3:14|15|17)(1:22)|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005d, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005e, code lost:
        com.ecarx.systemui.plugin.utils.Lg.w(com.ecarx.systemui.plugin.utils.SensorSDKWrapper.TAG, "[JRSYS][registerSuperProperties], e: " + android.util.Log.getStackTraceString(r0));
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0099 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void registerSuperProperties() {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = "SensorSDKWrapper"
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            android.content.Context r3 = com.ecarx.systemui.plugin.utils.SensorSDKWrapper.sContext
            android.content.pm.PackageManager r3 = r3.getPackageManager()
            android.content.Context r4 = com.ecarx.systemui.plugin.PluginContext.getPluginContext()     // Catch: java.lang.Throwable -> L3e
            android.content.pm.ApplicationInfo r4 = r4.getApplicationInfo()     // Catch: java.lang.Throwable -> L3e
            java.lang.CharSequence r4 = r3.getApplicationLabel(r4)     // Catch: java.lang.Throwable -> L3e
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch: java.lang.Throwable -> L3e
            android.content.Context r5 = com.ecarx.systemui.plugin.PluginContext.getPluginContext()     // Catch: java.lang.Throwable -> L3b
            android.content.pm.ApplicationInfo r5 = r5.getApplicationInfo()     // Catch: java.lang.Throwable -> L3b
            java.lang.String r5 = r5.packageName     // Catch: java.lang.Throwable -> L3b
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch: java.lang.Throwable -> L3b
            r6 = 0
            android.content.pm.PackageInfo r3 = r3.getPackageInfo(r5, r6)     // Catch: java.lang.Throwable -> L39
            java.lang.String r3 = r3.versionName     // Catch: java.lang.Throwable -> L39
            java.lang.String r0 = java.lang.String.valueOf(r3)     // Catch: java.lang.Throwable -> L39
            goto L46
        L39:
            r3 = move-exception
            goto L41
        L3b:
            r3 = move-exception
            r5 = r0
            goto L41
        L3e:
            r3 = move-exception
            r4 = r0
            r5 = r4
        L41:
            java.lang.String r6 = "get commonProperties error"
            com.ecarx.systemui.plugin.utils.Lg.e(r1, r6, r3)
        L46:
            java.lang.String r3 = "app_name"
            r2.putOpt(r3, r4)     // Catch: org.json.JSONException -> L5d
            java.lang.String r3 = "app_package_name"
            r2.putOpt(r3, r5)     // Catch: org.json.JSONException -> L5d
            java.lang.String r3 = "app_version"
            r2.putOpt(r3, r0)     // Catch: org.json.JSONException -> L5d
            java.lang.String r0 = "device_type"
            java.lang.String r3 = "1"
            r2.putOpt(r0, r3)     // Catch: org.json.JSONException -> L5d
            goto L76
        L5d:
            r0 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "[JRSYS][registerSuperProperties], e: "
            r3.append(r4)
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.ecarx.systemui.plugin.utils.Lg.w(r1, r0)
        L76:
            com.sensorsdata.analytics.android.sdk.SensorsDataAPI r0 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.sharedInstance()
            r0.registerSuperProperties(r2)
            android.content.Context r0 = com.ecarx.systemui.plugin.utils.SensorSDKWrapper.sContext
            com.ecarx.sdk.device.DeviceAPI r0 = com.ecarx.sdk.device.DeviceAPI.get(r0)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "[JRSYS][onAPIReady], deviceAPI: "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            com.ecarx.systemui.plugin.utils.Lg.w(r1, r2)
            if (r0 == 0) goto Laa
            android.content.Context r2 = com.ecarx.systemui.plugin.utils.SensorSDKWrapper.sContext     // Catch: java.lang.Throwable -> La4
            com.ecarx.systemui.plugin.utils.SensorSDKWrapper$1 r3 = new com.ecarx.systemui.plugin.utils.SensorSDKWrapper$1     // Catch: java.lang.Throwable -> La4
            r3.<init>()     // Catch: java.lang.Throwable -> La4
            r0.init(r2, r3)     // Catch: java.lang.Throwable -> La4
            goto Laa
        La4:
            r0 = move-exception
            java.lang.String r2 = "[JRSYS] deviceAPI init"
            com.ecarx.systemui.plugin.utils.Lg.e(r1, r2, r0)
        Laa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecarx.systemui.plugin.utils.SensorSDKWrapper.registerSuperProperties():void");
    }

    private static void registerDynamicSuperProperties() {
        String str;
        UserAPI userAPI2 = UserAPI.get(sContext);
        userAPI = userAPI2;
        if (userAPI2 != null) {
            try {
                userAPI2.init(sContext, new ECarXApiClient.Callback() { // from class: com.ecarx.systemui.plugin.utils.SensorSDKWrapper.2
                    @Override // com.ecarx.sdk.openapi.ECarXApiClient.Callback
                    public void onAPIReady(boolean z) {
                        Lg.i(SensorSDKWrapper.TAG, "[JRSYS][onAPIReady], ready: " + z);
                        boolean unused = SensorSDKWrapper.sIsUserAPIReady = z;
                    }
                });
            } catch (Throwable th) {
                Lg.e(TAG, "[JRSYS] UserAPI init", th);
            }
        }
        LocationManager locationManager = (LocationManager) sContext.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            List<String> providers = locationManager.getProviders(true);
            if (providers.contains("gps")) {
                str = "gps";
            } else if (providers.contains("network")) {
                str = "network";
            } else {
                str = providers.contains("passive") ? "passive" : null;
            }
            if (str != null && sContext.checkPermission("android.permission.ACCESS_FINE_LOCATION", Process.myPid(), Process.myUid()) == PackageManager.PERMISSION_GRANTED && sContext.checkPermission("android.permission.ACCESS_COARSE_LOCATION", Process.myPid(), Process.myUid()) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(str, 5000L, 10.0f, new LocationListener() { // from class: com.ecarx.systemui.plugin.utils.SensorSDKWrapper.3
                    @Override // android.location.LocationListener
                    public void onProviderDisabled(String str2) {
                    }

                    @Override // android.location.LocationListener
                    public void onProviderEnabled(String str2) {
                    }

                    @Override // android.location.LocationListener
                    public void onStatusChanged(String str2, int i, Bundle bundle) {
                    }

                    @Override // android.location.LocationListener
                    public void onLocationChanged(Location location) {
                        if (location != null) {
                            double unused = SensorSDKWrapper.longitude = new BigDecimal(location.getLongitude()).setScale(5, 1).doubleValue();
                            double unused2 = SensorSDKWrapper.latitude = new BigDecimal(location.getLatitude()).setScale(5, 1).doubleValue();
                            double unused3 = SensorSDKWrapper.height = new BigDecimal(location.getAltitude()).setScale(5, 1).doubleValue();
                        }
                    }
                });
            }
        }
        SensorsDataAPI.sharedInstance().registerDynamicSuperProperties(new SensorsDataDynamicSuperProperties() { // from class: com.ecarx.systemui.plugin.utils.SensorSDKWrapper.4
            @Override // com.sensorsdata.analytics.android.sdk.SensorsDataDynamicSuperProperties
            public JSONObject getDynamicSuperProperties() {
                IUser loginUser;
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.putOpt("longitude", Double.valueOf(SensorSDKWrapper.longitude));
                    jSONObject.putOpt("latitude", Double.valueOf(SensorSDKWrapper.latitude));
                    jSONObject.putOpt("height", Double.valueOf(SensorSDKWrapper.height));
                    Lg.i(SensorSDKWrapper.TAG, "[JRSYS][getDynamicSuperProperties], sIsUserAPIReady: " + SensorSDKWrapper.sIsUserAPIReady);
                    String str2 = "";
                    if (SensorSDKWrapper.sIsUserAPIReady && (loginUser = SensorSDKWrapper.userAPI.getLoginUser()) != null) {
                        str2 = loginUser.getUserId();
                    }
                    jSONObject.putOpt("GID", str2);
                } catch (Exception e) {
                    Lg.w(SensorSDKWrapper.TAG, "[JRSYS][getDynamicSuperProperties], e: " + Log.getStackTraceString(e));
                }
                return jSONObject;
            }
        });
    }
}

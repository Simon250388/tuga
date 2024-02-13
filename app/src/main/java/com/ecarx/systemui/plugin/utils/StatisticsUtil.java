package com.ecarx.systemui.plugin.utils;

import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class StatisticsUtil {
    public static final String SENSORS_EVENT_APPPANE_CLK = "desktop_navigationBar_appPanel_click";
    public static final String SENSORS_EVENT_BACK_CLK = "desktop_navigationBar_back_click";
    public static final String SENSORS_EVENT_HOME_CLK = "desktop_navigationBar_home_click";
    public static final String SENSORS_EVENT_LASTAPP_CLK = "desktop_navigationBar_lastApp_click";
    public static final String SENSORS_VALUE_APP_NAME = "launcher_appname";
    private static final String TAG = StatisticsUtil.class.getSimpleName();
    private static final String SENSORS_VALUE_OPERATE_TYPE = "operate_type";
    private static final ValueEntry<String> SCREEN_ENTRY = new ValueEntry<>(SENSORS_VALUE_OPERATE_TYPE, "3");

    public static void recoardSensorsEvent(String str) {
        String str2 = TAG;
        Lg.d(str2, "[JRSYS][recoardSensorsEvent], eventName: " + str);
        recoardSensorsNumberString(str, null, null);
    }

    public static void recoardSensorsNumberString(String str, ValueEntry<Integer> valueEntry, ValueEntry<String> valueEntry2) {
        if (str == null) {
            return;
        }
        String str2 = TAG;
        Lg.i(str2, "[JRSYS][recoardSensorsNumberString], eventName: " + str + ", numberEntry: " + valueEntry + ", stringEntry: " + valueEntry2);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(SCREEN_ENTRY.key, SCREEN_ENTRY.value);
            if (valueEntry != null) {
                jSONObject.put(valueEntry.key, valueEntry.value);
            }
            if (valueEntry2 != null) {
                jSONObject.put(valueEntry2.key, valueEntry2.value);
            }
            SensorsDataAPI.sharedInstance().track(str, jSONObject);
        } catch (JSONException e) {
            String str3 = TAG;
            Lg.w(str3, "[JRSYS][recoardSensorsNumberString], e: " + e.getMessage());
        }
    }

    /* loaded from: classes.dex */
    public static class ValueEntry<T> {
        String key;
        T value;

        public ValueEntry(String str, T t) {
            this.key = str;
            this.value = t;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("key: [");
            stringBuffer.append(this.key);
            stringBuffer.append("], value: [");
            stringBuffer.append(this.value);
            stringBuffer.append("]");
            return stringBuffer.toString();
        }
    }
}

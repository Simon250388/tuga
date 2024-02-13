package com.ecarx.systemui.plugin.navigationbar;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;
import com.ecarx.systemui.plugin.utils.Lg;

/* loaded from: classes.dex */
public class AccessibilityHelper {
    private static final String TAG = "AccessibilityHelper";

    public static void enableAccessibility(Context context, String str, String str2) {
        if (isAccessibilityEnabled(context)) {
            Lg.d(TAG, "enableAccessibility: the accessibility has been enabled");
            return;
        }
        String string = Settings.Secure.getString(context.getContentResolver(), "enabled_accessibility_services");
        String str3 = str + str2.replace(str, "/");
        if (!TextUtils.isEmpty(string)) {
            Lg.d(TAG, "openAccessibility: Already open app = " + string);
            if (string.contains(str3)) {
                return;
            }
            updateAccessibilityDB(context, string + ":" + str3);
            return;
        }
        updateAccessibilityDB(context, str3);
    }

    private static void updateAccessibilityDB(Context context, String str) {
        Lg.d(TAG, "updateAccessibilityDB: " + str);
        Settings.Secure.putString(context.getContentResolver(), "enabled_accessibility_services", str);
        Settings.Secure.putString(context.getContentResolver(), "accessibility_enabled", "1");
    }

    public static void toAccessibilitySettings(Context context) {
        try {
            try {
                context.startActivity(new Intent("android.settings.ACCESSIBILITY_SETTINGS"));
            } catch (Throwable unused) {
                Intent intent = new Intent("android.settings.ACCESSIBILITY_SETTINGS");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        } catch (Throwable th) {
            Lg.e(TAG, th);
        }
    }

    private static boolean isAccessibilityEnabled(Context context) {
        int i;
        String string;
        try {
            i = Settings.Secure.getInt(context.getContentResolver(), "accessibility_enabled");
        } catch (Settings.SettingNotFoundException e) {
            Lg.e(TAG, e);
            i = 0;
        }
        if (i != 1 || (string = Settings.Secure.getString(context.getContentResolver(), "enabled_accessibility_services")) == null) {
            return false;
        }
        return string.toLowerCase().contains(context.getPackageName().toLowerCase());
    }
}

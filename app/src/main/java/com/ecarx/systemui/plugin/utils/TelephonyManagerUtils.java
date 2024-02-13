package com.ecarx.systemui.plugin.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrength;
import android.telephony.TelephonyManager;
import com.ecarx.xui.adaptapi.policy.IAudioAttributes;
import java.util.List;

/* loaded from: classes.dex */
public class TelephonyManagerUtils {
    public static final int NETWORK_TYPE_1xRTT = 7;
    public static final int NETWORK_TYPE_CDMA = 4;
    public static final int NETWORK_TYPE_EDGE = 2;
    public static final int NETWORK_TYPE_EHRPD = 14;
    public static final int NETWORK_TYPE_EVDO_0 = 5;
    public static final int NETWORK_TYPE_EVDO_A = 6;
    public static final int NETWORK_TYPE_EVDO_B = 12;
    public static final int NETWORK_TYPE_GPRS = 1;
    public static final int NETWORK_TYPE_HSDPA = 8;
    public static final int NETWORK_TYPE_HSPA = 10;
    public static final int NETWORK_TYPE_HSPAP = 15;
    public static final int NETWORK_TYPE_HSUPA = 9;
    public static final int NETWORK_TYPE_IDEN = 11;
    public static final int NETWORK_TYPE_LTE = 13;
    public static final int NETWORK_TYPE_UMTS = 3;
    public static final int NETWORK_TYPE_UNKNOWN = 0;

    public static String getNetworkTypeName(int i) {
        switch (i) {
            case 1:
                return "GPRS";
            case 2:
                return "EDGE";
            case 3:
                return "UMTS";
            case 4:
                return "CDMA";
            case 5:
                return "CDMA - EvDo rev. 0";
            case 6:
                return "CDMA - EvDo rev. A";
            case 7:
                return "CDMA - 1xRTT";
            case 8:
                return "HSDPA";
            case 9:
                return "HSUPA";
            case 10:
                return "HSPA";
            case 11:
                return "iDEN";
            case 12:
                return "CDMA - EvDo rev. B";
            case 13:
                return "LTE";
            case 14:
                return "CDMA - eHRPD";
            case 15:
                return "HSPA+";
            default:
                return IAudioAttributes.CONTENT_TYPE_MD_UNKNOWN;
        }
    }

    public static CellSignalStrength getSignalStrength(Context context) {
        @SuppressLint("MissingPermission") List<CellInfo> allCellInfo = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getAllCellInfo();
        if (Build.VERSION.SDK_INT < 17 || allCellInfo == null) {
            return null;
        }
        for (CellInfo cellInfo : allCellInfo) {
            if (cellInfo instanceof CellInfoGsm) {
                return ((CellInfoGsm) cellInfo).getCellSignalStrength();
            }
            if (cellInfo instanceof CellInfoCdma) {
                return ((CellInfoCdma) cellInfo).getCellSignalStrength();
            }
            if (cellInfo instanceof CellInfoWcdma) {
                return ((CellInfoWcdma) cellInfo).getCellSignalStrength();
            }
            if (cellInfo instanceof CellInfoLte) {
                return ((CellInfoLte) cellInfo).getCellSignalStrength();
            }
        }
        return null;
    }
}

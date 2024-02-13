package com.ecarx.systemui.plugin.statusbar.policy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.policy.BaseController;
import com.ecarx.systemui.plugin.utils.ConnectivityManagerUtils;
import com.ecarx.systemui.plugin.utils.Lg;
import com.ecarx.systemui.plugin.utils.LogUtil;
import com.ecarx.systemui.plugin.utils.ReflectionUtils;
import com.ecarx.systemui.plugin.utils.TelephonyIcons;
import com.ecarx.systemui.plugin.utils.ThaderUtils;
import com.ecarx.systemui.plugin.utils.WifiIcons;
import com.ecarx.xui.adaptapi.wifiap.IWifiApClient;
import com.ecarx.xui.adaptapi.wifiap.IWifiApClientCallback;
import com.ecarx.xui.adaptapi.wifiap.WifiAp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class NetworkController extends BaseController {
    public static final String EXTRA_CSQ_VALUE = "csq_value";
    public static final String EXTRA_NET_CLASS = "net_class";
    public static final int NETWORK_CLASS_2_G = 1;
    public static final int NETWORK_CLASS_3_G = 2;
    public static final int NETWORK_CLASS_4_G = 3;
    public static final int NETWORK_CLASS_5_G = 4;
    public static final int NETWORK_CLASS_UNKNOWN = 0;
    public static final String SIGNAL_CSQ_AND_NETCLASS_CHANGED_ACTION = "android.telephony.SIGNAL_CSQ_AND_NETCLASS_CHANGED";
    private static volatile NetworkController networkController;
    private final int WIFI_AP_MAX;
    int csqValue;
    private boolean flag;
    private IWifiApClientCallback iWifiApClientCallback;
    int iconLevel;
    int[] iconList;
    private int mApConnectNumFact;
    private int mApConnectNumShow;
    private boolean mApEnabled;
    private boolean mConnected;
    private ConnectivityManager mConnectivityManager;
    int mDataNetType;
    private int mDataState;
    private int mLastApConnectNumShow;
    private boolean mLastApEnabled;
    private int mLastNetworkStrengthIconResId;
    private int mLastNetworkTypeIconResId;
    private int mLastWifiIconId;
    private int mMobileNetworkType;
    private int mNetworkStrengthIconResId;
    private int mNetworkStrengthLevel;
    private int mNetworkTypeIconResId;
    private final TelephonyManager mPhone;
    PhoneStateListener mPhoneStateListener;
    private ArrayList<SignalCluster> mSignalClusters;
    private SignalStrength mSignalStrength;
    private boolean mWifiConnected;
    private boolean mWifiEnabled;
    private int mWifiIconId;
    private int mWifiLevel;
    private final WifiManager mWifiManager;
    private int mWifiRssi;
    private WifiAp wifiAp;
    private static final String TAG = NetworkController.class.getSimpleName();
    public static final int[] DATA_SIGNAL_STRENGTH = {R.drawable.ic_status_signal_strength_0, R.drawable.ic_status_signal_strength_1, R.drawable.ic_status_signal_strength_2, R.drawable.ic_status_signal_strength_3, R.drawable.ic_status_signal_strength_4};

    /* loaded from: classes.dex */
    public interface SignalCluster {
        void setMobileSignalStrengthIndicators(boolean z, int i, int i2, String str);

        void setWifiOrApIndicators(boolean z, boolean z2, boolean z3, int i, int i2, String str, String str2);
    }

    private int getLevelFromCSQ(int i) {
        if (i < 0 || i > 31) {
            return 4;
        }
        if (i < 0 || i >= 11) {
            if (i >= 11 && i < 14) {
                return 1;
            }
            if (i >= 14 && i < 18) {
                return 2;
            }
            if (i >= 18 && i < 23) {
                return 3;
            }
            if (i >= 23 && i <= 31) {
                return 4;
            }
        }
        return 0;
    }

    private NetworkController(Context context) {
        super(context);
        int i = 0;
        this.mMobileNetworkType = 0;
        this.mDataState = 0;
        this.mConnected = false;
        this.csqValue = 0;
        this.mDataNetType = 0;
        this.WIFI_AP_MAX = 9;
        this.mSignalClusters = new ArrayList<>();
        this.mLastWifiIconId = this.mWifiIconId;
        this.mLastApConnectNumShow = this.mApConnectNumShow;
        this.mLastNetworkTypeIconResId = this.mNetworkTypeIconResId;
        this.mLastNetworkStrengthIconResId = this.mNetworkStrengthIconResId;
        this.flag = false;
        this.mPhoneStateListener = new PhoneStateListener() { // from class: com.ecarx.systemui.plugin.statusbar.policy.NetworkController.2
            @Override // android.telephony.PhoneStateListener
            public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                String str = NetworkController.TAG;
                Log.i(str, "[JRSYS_NET][onSignalStrengthsChanged], signalStrength: " + signalStrength);
                NetworkController.this.mSignalStrength = signalStrength;
                NetworkController.this.updateTelephonySignalStrength();
            }

            @Override // android.telephony.PhoneStateListener
            public void onDataConnectionStateChanged(int i2, int i3) {
                NetworkController.this.flag = false;
                String str = NetworkController.TAG;
                Lg.i(str, "[JRSYS_NET][onDataConnectionStateChanged] " + i2 + " networkType: " + i3 + "flag:" + NetworkController.this.flag);
                NetworkController.this.mConnected = true;
                NetworkController.this.mDataState = i2;
                NetworkController.this.mDataNetType = i3;
                NetworkController.this.mMobileNetworkType = i3;
                NetworkController.this.updateTelephonySignalStrength();
            }
        };
        TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService(Context.TELEPHONY_SERVICE);
        this.mPhone = telephonyManager;
        telephonyManager.listen(this.mPhoneStateListener, 481);
        @SuppressLint("MissingPermission") int networkType = this.mPhone.getNetworkType();
        this.mMobileNetworkType = networkType;
        getNetworkTypeIcon(networkType);
        this.mWifiManager = (WifiManager) this.mContext.getSystemService(Context.WIFI_SERVICE);
        this.mConnectivityManager = (ConnectivityManager) this.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        this.mWifiEnabled = this.mWifiManager.getWifiState() == 3;
        ConnectivityManager connectivityManager = this.mConnectivityManager;
        if (connectivityManager != null && connectivityManager.getNetworkInfo(1) != null) {
            this.mWifiConnected = this.mConnectivityManager.getNetworkInfo(1).isConnected();
        }
        try {
            this.mApEnabled = ((Boolean) ReflectionUtils.invoke(this.mWifiManager, "isWifiApEnabled")).booleanValue();
        } catch (Exception e) {
            Lg.e(TAG, e);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.WIFI_AP_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.RSSI_CHANGED");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction(ConnectivityManagerUtils.INET_CONDITION_ACTION);
        intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
        intentFilter.addAction("android.telephony.SIGNAL_CSQ_AND_NETCLASS_CHANGED");
        this.mContext.registerReceiver(this, intentFilter);
        try {
            this.wifiAp = WifiAp.create(this.mContext);
            String str = TAG;
            Log.d(str, "[JRSYS_NET][NetworkController], wifiAp: " + this.wifiAp);
            if (this.wifiAp != null) {
                IWifiApClientCallback iWifiApClientCallback = new IWifiApClientCallback() { // from class: com.ecarx.systemui.plugin.statusbar.policy.NetworkController.1
                    @Override // com.ecarx.xui.adaptapi.wifiap.IWifiApClientCallback
                    public void onWifiApClientChanged(List<IWifiApClient> list) {
                        NetworkController.this.mApConnectNumFact = list == null ? 0 : list.size();
                        String str2 = NetworkController.TAG;
                        Log.d(str2, "[JRSYS_NET][onWifiApClientChanged], mApConnectNumFact: " + NetworkController.this.mApConnectNumFact);
                        ThaderUtils.post(new Runnable() { // from class: com.ecarx.systemui.plugin.statusbar.policy.NetworkController.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                NetworkController.this.updateWifiIcons();
                            }
                        });
                    }
                };
                this.iWifiApClientCallback = iWifiApClientCallback;
                this.wifiAp.setWifiApClientCallback(iWifiApClientCallback);
                List<IWifiApClient> wifiApClients = this.wifiAp.getWifiApClients();
                if (wifiApClients != null) {
                    i = wifiApClients.size();
                }
                this.mApConnectNumFact = i;
            }
        } catch (Throwable th) {
            Lg.e(TAG, th);
        }
    }

    public static NetworkController getInstance(Context context) {
        if (networkController == null) {
            synchronized (NetworkController.class) {
                if (networkController == null) {
                    networkController = new NetworkController(context);
                }
            }
        }
        networkController.initState();
        return networkController;
    }

    private void initState() {
        Log.i(TAG, "[JRSYS_NET][initState]");
        updateWifiIcons();
        updateTelephonySignalStrength();
    }

    public boolean isApEnabled() {
        return this.mApEnabled;
    }

    public void addSignalCluster(SignalCluster signalCluster) {
        Log.i(TAG, "[JRSYS_NET][addSignalCluster]");
        this.mSignalClusters.clear();
        this.mSignalClusters.add(signalCluster);
        initState();
    }

    public void refreshSignalCluster(SignalCluster signalCluster) {
        String str = TAG;
        Log.d(str, "[JRSYS_NET][refreshSignalCluster], mApEnabled: " + this.mApEnabled + ", mWifiEnabled: " + this.mWifiEnabled + ", mWifiConnected: " + this.mWifiConnected + ", mApConnectNumShow: " + this.mApConnectNumShow);
        signalCluster.setMobileSignalStrengthIndicators(this.mWifiEnabled && this.mWifiConnected && !this.mApEnabled, this.mNetworkStrengthIconResId, this.mNetworkTypeIconResId, null);
        signalCluster.setWifiOrApIndicators(this.mApEnabled, this.mWifiEnabled, this.mWifiConnected, this.mApConnectNumShow, this.mWifiIconId, null, null);
    }

    @Override // com.ecarx.systemui.plugin.policy.BaseController, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String str = TAG;
        Log.d(str, "[JRSYS_NET][onReceive], action: " + action);
        if (TextUtils.isEmpty(action)) {
            return;
        }
        if ("android.net.wifi.WIFI_AP_STATE_CHANGED".equals(action)) {
            updateAPState(intent);
        } else if ("android.net.wifi.RSSI_CHANGED".equals(action) || "android.net.wifi.WIFI_STATE_CHANGED".equals(action) || "android.net.wifi.STATE_CHANGE".equals(action)) {
            updateWifiState(intent);
        } else if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action) || ConnectivityManagerUtils.INET_CONDITION_ACTION.equals(action)) {
            Lg.w("CONNECTIVITY_ACTION");
            updateConnectivity(intent);
        } else if ("android.intent.action.CONFIGURATION_CHANGED".equals(action)) {
            refreshViews();
        } else if ("android.intent.action.AIRPLANE_MODE".equals(action)) {
            refreshViews();
        }
    }

    @Override // com.ecarx.systemui.plugin.policy.BaseController
    public void destroy() {
        Log.i(TAG, "[JRSYS_NET][destroy]");
        super.destroy();
        ArrayList<SignalCluster> arrayList = this.mSignalClusters;
        if (arrayList != null) {
            arrayList.clear();
        }
        this.mLastNetworkTypeIconResId = 0;
        this.mLastNetworkStrengthIconResId = 0;
        this.mLastWifiIconId = 0;
        this.mLastApEnabled = false;
        this.mLastApConnectNumShow = -1;
        this.mApConnectNumShow = -1;
        this.mApConnectNumFact = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTelephonySignalStrength() {
        if (this.mDataNetType == 0) {
            Lg.i(TAG, "[JRSYS_NET][updateTelephonySignalStrength]mDataNetType == TelephonyManager.NETWORK_TYPE_UNKNOWN");
            this.mNetworkStrengthIconResId = R.drawable.ic_status_signal_strength_null;
            this.mNetworkTypeIconResId = getNetworkTypeIcon(0);
            refreshViews();
            return;
        }
        if (this.flag) {
            this.iconList = TelephonyIcons.DATA_SIGNAL_STRENGTH;
            this.iconLevel = getLevelFromCSQ(this.csqValue);
            int iconLevel = getIconLevel();
            this.iconLevel = iconLevel;
            this.mNetworkStrengthLevel = iconLevel;
            this.mNetworkStrengthIconResId = DATA_SIGNAL_STRENGTH[iconLevel];
            this.mNetworkTypeIconResId = getNetworkTypeIcon(this.mDataNetType);
        } else if (this.mDataState != 2 || !this.mConnected) {
            Lg.i(TAG, "[JRSYS_NET][updateTelephonySignalStrength]mDataState != TelephonyManager.DATA_CONNECTED || !mConnected");
            this.mNetworkStrengthIconResId = R.drawable.ic_status_signal_strength_null;
            this.mNetworkTypeIconResId = getNetworkTypeIcon(0);
            refreshViews();
            return;
        } else if (this.mSignalStrength == null || this.mMobileNetworkType == 0) {
            Lg.i(TAG, "[JRSYS_NET][updateTelephonySignalStrength]mSignalStrength == null || mMobileNetworkType == TelephonyManager.NETWORK_TYPE_UNKNOWN");
            this.mNetworkStrengthIconResId = R.drawable.ic_status_signal_strength_null;
            this.mNetworkTypeIconResId = getNetworkTypeIcon(0);
        } else {
            Lg.i(TAG, "[JRSYS_NET][updateTelephonySignalStrength]update success!");
            this.iconList = DATA_SIGNAL_STRENGTH;
            this.iconLevel = ((Integer) ReflectionUtils.invoke(this.mSignalStrength, "getLevel")).intValue();
            int iconLevel2 = getIconLevel();
            this.iconLevel = iconLevel2;
            this.mNetworkStrengthLevel = iconLevel2;
            this.mNetworkStrengthIconResId = DATA_SIGNAL_STRENGTH[iconLevel2];
            this.mNetworkTypeIconResId = getNetworkTypeIcon(this.mMobileNetworkType);
        }
        refreshViews();
    }

    private int getIconLevel() {
        int i = this.iconLevel;
        int[] iArr = this.iconList;
        if (i >= iArr.length) {
            this.iconLevel = iArr.length - 1;
        } else if (i <= 0) {
            this.iconLevel = 0;
        }
        String str = TAG;
        Log.d(str, "iconLevel: " + this.iconLevel);
        return this.iconLevel;
    }

    private void updateAPState(Intent intent) {
        int intExtra = intent.getIntExtra("wifi_state", 4);
        Lg.w("updateAPState:" + intExtra);
        if (intExtra == 13) {
            this.mApEnabled = true;
        } else {
            this.mApEnabled = false;
        }
        updateWifiIcons();
    }

    private void updateConnectivity(Intent intent) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        this.mConnected = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        String str = TAG;
        Log.i(str, "[JRSYS_NET][updateConnectivity], mConnected: " + this.mConnected);
        updateTelephonySignalStrength();
        updateWifiIcons();
    }

    private int getNetworkTypeIcon(int i) {
        int i2 = -1;
        switch (i) {
            case 1:
                i2 = R.drawable.ic_status_signal_type_g;
                break;
            case 2:
                i2 = R.drawable.ic_status_signal_type_e;
                break;
            case 3:
            case 5:
            case 6:
            case 12:
            case 17:
                i2 = R.drawable.ic_status_signal_type_3g;
                break;
            case 4:
            case 7:
            case 11:
            case 16:
                i2 = R.drawable.ic_status_signal_type_2g;
                break;
            case 8:
            case 9:
            case 10:
                i2 = R.drawable.ic_status_signal_type_h;
                break;
            case 13:
            case 19:
                i2 = R.drawable.ic_status_signal_type_4g;
                break;
            case 14:
            case 15:
                i2 = R.drawable.ic_status_signal_type_h_plus;
                break;
        }
        String str = TAG;
        LogUtil.i(str, "updateDataNetType mNetworkType = " + i);
        return i2;
    }

    private void updateWifiState(Intent intent) {
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return;
        }
        if (action.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
            this.mWifiEnabled = intent.getIntExtra("wifi_state", 4) == 3;
        } else if (action.equals("android.net.wifi.STATE_CHANGE")) {
            NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
            StringBuilder sb = new StringBuilder();
            sb.append("NetworkController updateWifiState networkInfo:");
            sb.append(networkInfo == null ? "networkInfo为空" : networkInfo.toString());
            sb.append(" mWifiConnected: ");
            sb.append(this.mWifiConnected);
            LogUtil.logNetworkD(sb.toString());
            boolean z = this.mWifiConnected;
            boolean r2 = true;
            if (networkInfo == null || !networkInfo.isConnected()) {
                r2 = false;
            }
            this.mWifiConnected = r2;
            LogUtil.logNetworkD("NetworkController updateWifiState mWifiConnected:" + this.mWifiConnected);
            if (this.mWifiConnected && !z) {
                WifiInfo wifiInfo = (WifiInfo) intent.getParcelableExtra("wifiInfo");
                if (wifiInfo == null) {
                    wifiInfo = (WifiInfo) ReflectionUtils.invoke(this.mWifiManager, "getConnectionInfo");
                }
                if (wifiInfo != null) {
                    this.mWifiLevel = WifiManager.calculateSignalLevel(this.mWifiRssi, WifiIcons.WIFI_LEVEL_COUNT);
                }
            }
        } else if (action.equals("android.net.wifi.RSSI_CHANGED")) {
            int intExtra = intent.getIntExtra("newRssi", -200);
            this.mWifiRssi = intExtra;
            this.mWifiLevel = WifiManager.calculateSignalLevel(intExtra, WifiIcons.WIFI_LEVEL_COUNT);
        }
        updateWifiIcons();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWifiIcons() {
        LogUtil.logNetworkD("NetworkController updateWifiState mApEnabled:" + this.mApEnabled + " mWifiEnabled: " + this.mWifiEnabled + " mWifiConnected: " + this.mWifiConnected + " mWifiLevel:" + this.mWifiLevel);
        if (this.mApEnabled) {
            int i = this.mApConnectNumFact;
            if (i > 9) {
                this.mApConnectNumShow = 9;
            } else {
                this.mApConnectNumShow = i;
            }
        } else {
            this.mApConnectNumShow = -1;
        }
        if (this.mWifiEnabled) {
            if (this.mWifiConnected) {
                this.mWifiIconId = WifiIcons.WIFI_SIGNAL_STRENGTH[this.mWifiLevel];
            } else {
                this.mWifiIconId = R.drawable.ic_status_wifi_strength_no;
            }
        } else {
            this.mWifiIconId = 0;
        }
        String str = TAG;
        Lg.d(str, "[JRSYS_NET][updateWifiIcons] mApEnabled : " + this.mApEnabled + "; mApConnectNumShow : " + this.mApConnectNumShow + "; mWifiEnabled : " + this.mWifiEnabled + "; mWifiConnected : " + this.mWifiConnected + "; mWifiLevel : " + this.mWifiLevel + "; mWifiRssi : " + this.mWifiRssi);
        refreshViews();
    }

    void refreshViews() {
        if (this.mSignalClusters.size() == 0) {
            Lg.i("[JRSYS_NET][refreshViews] mSignalClusters size:" + this.mSignalClusters.size() + " method return");
            return;
        }
        if (this.mLastNetworkTypeIconResId != this.mNetworkTypeIconResId || this.mLastNetworkStrengthIconResId != this.mNetworkStrengthIconResId || this.mLastWifiIconId != this.mWifiIconId || this.mLastApEnabled != this.mApEnabled || this.mLastApConnectNumShow != this.mApConnectNumShow) {
            Iterator<SignalCluster> it = this.mSignalClusters.iterator();
            while (it.hasNext()) {
                refreshSignalCluster(it.next());
            }
        }
        int i = this.mLastNetworkTypeIconResId;
        int i2 = this.mNetworkTypeIconResId;
        if (i != i2) {
            this.mLastNetworkTypeIconResId = i2;
        }
        int i3 = this.mLastNetworkStrengthIconResId;
        int i4 = this.mNetworkStrengthIconResId;
        if (i3 != i4) {
            this.mLastNetworkStrengthIconResId = i4;
        }
        int i5 = this.mLastWifiIconId;
        int i6 = this.mWifiIconId;
        if (i5 != i6) {
            this.mLastWifiIconId = i6;
        }
        boolean z = this.mLastApEnabled;
        boolean z2 = this.mApEnabled;
        if (z != z2) {
            this.mLastApEnabled = z2;
        }
        int i7 = this.mLastApConnectNumShow;
        int i8 = this.mApConnectNumShow;
        if (i7 != i8) {
            this.mLastApConnectNumShow = i8;
        }
    }
}

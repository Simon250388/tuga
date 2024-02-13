package com.ecarx.systemui.plugin.psd;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;
import com.ecarx.xui.adaptapi.device.Device;
import com.ecarx.xui.adaptapi.device.ext.IA2dpCallback;
import com.ecarx.xui.adaptapi.device.ext.IA2dpExtension;
import com.ecarx.xui.adaptapi.device.ext.IBtExtension;
import com.ecarx.xui.adaptapi.device.ext.IBtExtensionCallback;
import com.ecarx.xui.adaptapi.device.ext.common.BtDevice;
import java.util.List;

/* loaded from: classes.dex */
public class PsdBtManager {
    private static final int DEFAULT_MAX_VOLUME = 39;
    private static final int DEFAULT_MIN_VOLUME = 0;
    private static final int DEFAULT_THRESHOLD_VOLUME = 10;
    private static final String INVALIDE_ADDRESS = "00:00:00:00:00:00";
    private static final int STREAM_MUSIC_PSD = 24;
    private static final String TAG = PsdBtManager.class.getSimpleName();
    private static PsdBtManager mInstance;
    private String currentConnectedAddress;
    private boolean isBtConnected;
    private IA2dpExtension mA2dpExtension;
    private AudioManager mAudioManager;
    private int mBTState;
    private IBtExtension mBtExtension;
    private Context mContext;
    private IHeadsetConnectionCallback mIHeadsetConnectionCallback;
    private int mLastVolume;
    private IBtExtensionCallback mBtExtensionCallback = new IBtExtensionCallback() { // from class: com.ecarx.systemui.plugin.psd.PsdBtManager.1
        @Override // com.ecarx.xui.adaptapi.device.ext.IBtExtensionCallback
        public void onAdapterStateChanged(int i, int i2) {
            String str = PsdBtManager.TAG;
            Log.i(str, "PsdBtManager onAdapterStateChanged prevState : " + i + " newState : " + i2);
            PsdBtManager.this.mBTState = i2;
            if (PsdBtManager.this.mIHeadsetConnectionCallback != null) {
                PsdBtManager.this.mIHeadsetConnectionCallback.isOpened(PsdBtManager.this.mBTState == 302);
            }
        }

        @Override // com.ecarx.xui.adaptapi.device.ext.IBtExtensionCallback
        public void onDeviceFoundChanged(int i, BtDevice btDevice) {
            String str = PsdBtManager.TAG;
            Log.i(str, "PsdBtManager onDeviceFoundChanged scanState : " + i + " btDevice : " + PsdBtManager.this.getDeviceString(btDevice));
        }

        @Override // com.ecarx.xui.adaptapi.device.ext.IBtExtensionCallback
        public void onDeviceBondStateChanged(BtDevice btDevice, int i, int i2) {
            String str = PsdBtManager.TAG;
            Log.i(str, "PsdBtManager onDeviceBondStateChanged btDevice : " + PsdBtManager.this.getDeviceString(btDevice) + "  prevState : " + i + " newState : " + i2);
        }

        @Override // com.ecarx.xui.adaptapi.device.ext.IBtExtensionCallback
        public void onDeviceUuidsUpdated(BtDevice btDevice) {
            String str = PsdBtManager.TAG;
            Log.i(str, "PsdBtManager onDeviceUuidsUpdated btDevice : " + PsdBtManager.this.getDeviceString(btDevice));
        }

        @Override // com.ecarx.xui.adaptapi.device.ext.IBtExtensionCallback
        public void onDevicePowerUpdated(BtDevice btDevice, int i) {
            String str = PsdBtManager.TAG;
            Log.i(str, "PsdBtManager onDevicePowerUpdated btDevice : " + PsdBtManager.this.getDeviceString(btDevice) + " , value : " + i);
        }

        @Override // com.ecarx.xui.adaptapi.device.ext.IBtExtensionCallback
        public void onPairedDevicesChanged(List<BtDevice> list) {
            String str = PsdBtManager.TAG;
            Log.i(str, "PsdBtManager onPairedDevicesChanged list.size : " + list.size());
        }
    };
    private IA2dpCallback mA2dpCallback = new IA2dpCallback() { // from class: com.ecarx.systemui.plugin.psd.PsdBtManager.2
        @Override // com.ecarx.xui.adaptapi.device.ext.IA2dpCallback
        public void onA2dpServiceReady() {
            Log.i(PsdBtManager.TAG, "PsdBtManager onA2dpServiceReady");
        }

        @Override // com.ecarx.xui.adaptapi.device.ext.IA2dpCallback
        public void onA2dpStateChanged(String str, int i, int i2) {
            String str2 = PsdBtManager.TAG;
            Log.d(str2, "PsdBtManager onA2dpStateChanged address :" + str + " ,prevState =" + i + " ,newState =" + i2);
            if (i2 == 140 && i != 140) {
                PsdBtManager.this.isBtConnected = true;
                if (PsdBtManager.this.mIHeadsetConnectionCallback != null) {
                    PsdBtManager.this.mIHeadsetConnectionCallback.onConnected();
                }
            } else if (i2 != 140 && (i == 125 || i == 140)) {
                PsdBtManager.this.isBtConnected = false;
                if (PsdBtManager.this.mIHeadsetConnectionCallback != null) {
                    PsdBtManager.this.mIHeadsetConnectionCallback.onDisConnected();
                }
            }
            if (PsdBtManager.this.isBtConnected) {
                PsdBtManager.this.currentConnectedAddress = str;
            }
        }
    };

    /* loaded from: classes.dex */
    public interface IHeadsetConnectionCallback {
        void isOpened(boolean z);

        void onConnected();

        void onDisConnected();
    }

    public int getBtMaxVolume() {
        return 39;
    }

    public int getBtMinVolume() {
        return 0;
    }

    private PsdBtManager(Context context) {
        this.isBtConnected = false;
        this.mContext = context;
        IBtExtension btExtension = Device.create(context).getBtExtension();
        this.mBtExtension = btExtension;
        this.mA2dpExtension = btExtension.getA2dpExtension();
        boolean registerBtCallback = this.mBtExtension.registerBtCallback(this.mBtExtensionCallback);
        boolean registerA2dpCallback = this.mA2dpExtension.registerA2dpCallback(this.mA2dpCallback);
        this.mBTState = this.mBtExtension.getBtState();
        this.isBtConnected = this.mA2dpExtension.isA2dpConnected();
        this.currentConnectedAddress = this.mA2dpExtension.getA2dpConnectedAddress();
        this.mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        this.mLastVolume = getBtCurrentVolume();
        String str = TAG;
        Log.e(str, "PsdBtManager registerBtCallback state  = " + registerBtCallback + " ,registerA2dpCallback state = " + registerA2dpCallback + " , mBTState : " + this.mBTState + " , currentConnectedAddress : " + this.currentConnectedAddress);
    }

    public static PsdBtManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (PsdBtManager.class) {
                if (mInstance == null) {
                    mInstance = new PsdBtManager(context);
                }
            }
        }
        return mInstance;
    }

    public void setIHeadsetConnectionCallback(IHeadsetConnectionCallback iHeadsetConnectionCallback) {
        this.mIHeadsetConnectionCallback = iHeadsetConnectionCallback;
    }

    public boolean setBtEnable(boolean z) {
        IBtExtension iBtExtension = this.mBtExtension;
        boolean btEnable = iBtExtension != null ? iBtExtension.setBtEnable(z) : false;
        String str = TAG;
        Log.i(str, "PsdBtManager setBtEnable enable : " + z + " setResult : " + btEnable);
        return btEnable;
    }

    public boolean isOpen() {
        boolean z = this.mBTState == 302;
        String str = TAG;
        Log.i(str, "PsdBtManager isOpen : " + z);
        return z;
    }

    public boolean isBtConnected() {
        String a2dpConnectedAddress = this.mA2dpExtension.getA2dpConnectedAddress();
        this.currentConnectedAddress = a2dpConnectedAddress;
        boolean z = (!this.isBtConnected || a2dpConnectedAddress == null || a2dpConnectedAddress == INVALIDE_ADDRESS) ? false : true;
        String str = TAG;
        Log.i(str, "PsdBtManager isConnect : " + z);
        return z;
    }

    public boolean isBtMute() {
        AudioManager audioManager = this.mAudioManager;
        boolean isStreamMute = audioManager != null ? audioManager.isStreamMute(24) : false;
        String str = TAG;
        Log.i(str, "PsdBtManager isMute : " + isStreamMute);
        return isStreamMute;
    }

    public void muteBt(boolean z) {
        if (this.mA2dpExtension != null) {
            String str = TAG;
            Log.i(str, "PsdBtManager muteBt mute : " + z);
            this.mA2dpExtension.muteA2dpAudio(z, this.currentConnectedAddress);
        }
    }

    public int getThresholdVolume() {
        IA2dpExtension iA2dpExtension = this.mA2dpExtension;
        int a2dpThresholdVolume = iA2dpExtension != null ? iA2dpExtension.getA2dpThresholdVolume(this.currentConnectedAddress) : -1;
        String str = TAG;
        Log.i(str, "PsdBtManager btMaxVolume : " + a2dpThresholdVolume);
        return a2dpThresholdVolume;
    }

    public int getBtCurrentVolume() {
        IA2dpExtension iA2dpExtension = this.mA2dpExtension;
        int a2dpLocalVolume = iA2dpExtension != null ? iA2dpExtension.getA2dpLocalVolume(this.currentConnectedAddress) : -1;
        if (a2dpLocalVolume < 0) {
            a2dpLocalVolume = 10;
        }
        String str = TAG;
        Log.i(str, "PsdBtManager currentBtVolume : " + a2dpLocalVolume);
        return a2dpLocalVolume;
    }

    public void setBtCurrentVolume(int i) {
        if (this.mA2dpExtension != null) {
            this.mLastVolume = getBtCurrentVolume();
            boolean a2dpLocalVolume = this.mA2dpExtension.setA2dpLocalVolume(i);
            String str = TAG;
            Log.i(str, "PsdBtManager setBtCurrentVolume result : " + a2dpLocalVolume);
        }
    }

    public void release() {
        Log.i(TAG, "PsdBtManager release ");
        IBtExtension iBtExtension = this.mBtExtension;
        if (iBtExtension != null) {
            iBtExtension.unregisterBtCallback(this.mBtExtensionCallback);
        }
        IA2dpExtension iA2dpExtension = this.mA2dpExtension;
        if (iA2dpExtension != null) {
            iA2dpExtension.unregisterA2dpCallback(this.mA2dpCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getDeviceString(BtDevice btDevice) {
        if (btDevice != null) {
            return "[ addass : " + btDevice.getAddress() + " , name : " + btDevice.getName() + " bondstate : " + btDevice.getBondState() + " , connectstate : " + btDevice.getConnectState() + " ]";
        }
        return "device is null";
    }
}

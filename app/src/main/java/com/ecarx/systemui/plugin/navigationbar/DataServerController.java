package com.ecarx.systemui.plugin.navigationbar;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.ecarx.systemui.plugin.navigationbar.IDataListener;
import com.ecarx.systemui.plugin.navigationbar.IDataService;

/* loaded from: classes.dex */
public class DataServerController {
    private static final String PACKAGE_NAME = "com.ecarx.systemui.plugin";
    private static final long RECONNECT_TIME_INTERVAL = 5000;
    private static final long RECONNECT_TIME_INTERVAL10S = 10000;
    private static final long SENSORS_SERVICE_BIND_FIRST_TIME = 60000;
    private static final long SENSORS_SERVICE_BIND_TIME = 180000;
    public static final String SYSTEMUI_PLUGIN = "systemui.plugin";
    private static final int WHAT_RECONNECT = 1;
    private static DataServerController instance;
    private ConnectedCallBack mCntCallBack;
    private Context mContext;
    private IDataService mDataService;
    private boolean mIsConnected;
    private static final String TAG = DataServerController.class.getSimpleName();
    private static final String CLASS_NAME = UserDataService.class.getName();
    private long allTime = 0;
    private ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.ecarx.systemui.plugin.navigationbar.DataServerController.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder != null) {
                Log.d(DataServerController.TAG, "[JRSYS_USER] SensorsHelpController connected");
                DataServerController.this.mDataService = IDataService.Stub.asInterface(iBinder);
                DataServerController.this.mIsConnected = true;
                DataServerController.this.allTime = 0L;
                DataServerController.this.mHandler.removeMessages(1);
                DataServerController.this.mCntCallBack.onConnected(true);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(DataServerController.TAG, "[JRSYS_USER] SensorsHelpController disconnected");
            DataServerController.this.mIsConnected = false;
            DataServerController.this.allTime = 0L;
            DataServerController.this.reconnect(true);
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
            Log.d(DataServerController.TAG, "[JRSYS_USER] SensorsHelpController onBindingDied");
            DataServerController.this.mIsConnected = false;
            DataServerController.this.allTime = 0L;
            DataServerController.this.mContext.unbindService(DataServerController.this.serviceConnection);
            DataServerController.this.reconnect(true);
        }
    };
    private Handler mHandler = new DataHelpControllerHandler(Looper.myLooper());

    /* loaded from: classes.dex */
    public interface ConnectedCallBack {
        void onConnected(boolean z);
    }

    /* loaded from: classes.dex */
    public interface DataReadyCallBack {
        void onImageReady(IShortCut iShortCut);
    }

    public static DataServerController getInstance(Context context) {
        if (instance == null) {
            synchronized (DataServerController.class) {
                if (instance == null) {
                    instance = new DataServerController(context);
                }
            }
        }
        return instance;
    }

    private DataServerController(Context context) {
        this.mContext = context;
    }

    public void init(ConnectedCallBack connectedCallBack) {
        this.mCntCallBack = connectedCallBack;
        bindUserDataHelp(false);
    }

    public boolean isIsConnected() {
        return this.mIsConnected;
    }

    private void bindUserDataHelp(boolean z) {
        String str = TAG;
        Log.i(str, "[JRSYS_USER][bindSensorsHelp], allTime: " + this.allTime + ", isDied: " + z);
        if (!z) {
            Intent intent = new Intent(UserDataService.DATA_HELP_SERVICE);
            intent.setComponent(new ComponentName("com.ecarx.systemui.plugin", CLASS_NAME));
            String str2 = TAG;
            Log.i(str2, "[JRSYS_USER][bindSensorsHelp], intent: " + intent + ", PACKAGE_NAME: com.ecarx.systemui.plugin, CLASS_NAME: " + CLASS_NAME);
            this.mContext.bindService(intent, this.serviceConnection, 1);
        }
        long j = this.allTime;
        if (j >= SENSORS_SERVICE_BIND_TIME) {
            Log.d(TAG, "[JRSYS_USER] connect time out");
            return;
        }
        this.allTime = j + RECONNECT_TIME_INTERVAL;
        this.mHandler.removeMessages(1);
        if (this.allTime > SENSORS_SERVICE_BIND_FIRST_TIME) {
            this.mHandler.sendEmptyMessageDelayed(1, RECONNECT_TIME_INTERVAL10S);
        } else {
            this.mHandler.sendEmptyMessageDelayed(1, RECONNECT_TIME_INTERVAL);
        }
    }

    /* loaded from: classes.dex */
    private class DataHelpControllerHandler extends Handler {
        DataHelpControllerHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            String str = DataServerController.TAG;
            Log.i(str, "[JRSYS_USER][handleMessage], allTime: " + DataServerController.this.allTime);
            if (message.what == 1 && !DataServerController.this.mIsConnected) {
                DataServerController.this.reconnect(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reconnect(boolean z) {
        bindUserDataHelp(z);
    }

    public void addDataListener(String str, DataReadyCallBack dataReadyCallBack) {
        if (this.mDataService != null) {
            try {
                this.mDataService.addDataListener(new DataListenerWrapper(dataReadyCallBack));
                return;
            } catch (RemoteException e) {
                String str2 = TAG;
                Log.e(str2, "exception: " + e);
                return;
            }
        }
        Log.e(TAG, "need init api");
    }

    public void getUserImageByUrl(String str) {
        try {
            String str2 = TAG;
            Log.d(str2, "[JRSYS_USER][getUserImageByUrl] in Client, url: " + str);
            this.mDataService.getHeaderBitmapByUrl(str);
        } catch (RemoteException e) {
            String str3 = TAG;
            Log.w(str3, "[JRSYS_USER][updateEvent], e: " + Log.getStackTraceString(e));
        }
    }

    /* loaded from: classes.dex */
    public static class DataListenerWrapper extends IDataListener.Stub {
        private DataReadyCallBack mCallBack;

        public DataListenerWrapper(DataReadyCallBack dataReadyCallBack) {
            this.mCallBack = dataReadyCallBack;
        }

        @Override // com.ecarx.systemui.plugin.navigationbar.IDataListener
        public void onImageReady(IShortCut iShortCut) throws RemoteException {
            Log.w(DataServerController.TAG, "[JRSYS_USER][onImageReady] in Client");
            this.mCallBack.onImageReady(iShortCut);
        }
    }
}

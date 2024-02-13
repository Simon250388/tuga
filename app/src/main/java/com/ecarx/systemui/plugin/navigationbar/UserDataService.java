package com.ecarx.systemui.plugin.navigationbar;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.ecarx.systemui.plugin.App;
import com.ecarx.systemui.plugin.psd.PsdNavigationController;

/* loaded from: classes.dex */
public class UserDataService extends Service {
    public static final String DATA_HELP_SERVICE = "com.ecarx.systemui.plugin.DATA_HELP_SERVICE";
    private static final String TAG = UserDataService.class.getSimpleName();
    private DataHelpProxy mDataHelp = new DataHelpProxy();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        String str = TAG;
        Log.w(str, "[JRSYS_USER][onBind], App: " + App.getApp() + ", mDataHelp: " + this.mDataHelp);
        new ProvisioningObserver();
        return this.mDataHelp;
    }

    /* loaded from: classes.dex */
    public static class DataHelpProxy extends IDataService.Stub {
        private IDataListener mDataListener;
        private SimpleTarget<Bitmap> mSimpleTarget = new SimpleTarget<Bitmap>() { // from class: com.ecarx.systemui.plugin.navigationbar.UserDataService.DataHelpProxy.1
//            @Override // com.bumptech.glide.request.target.Target
//            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
//                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
//            }

            public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                String str = UserDataService.TAG;
                Log.w(str, "[JRSYS_USER][onResourceReady], resBitmap: " + bitmap + ", resWidth: " + bitmap.getWidth() + ", resHeight: " + bitmap.getHeight());
                if (bitmap == null) {
                    return;
                }
                IShortCut iShortCut = new IShortCut();
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(IShortCut.EXTRA_USER_IMAGE, bitmap);
                    iShortCut.setBundle(bundle);
                    DataHelpProxy.this.mDataListener.onImageReady(iShortCut);
                } catch (Throwable th) {
                    String str2 = UserDataService.TAG;
                    Log.w(str2, "[JRCard][onResourceReady], e: " + th.getMessage());
                }
            }
        };

        @Override // com.ecarx.systemui.plugin.navigationbar.IDataService
        public void getHeaderBitmapByUrl(String str) throws RemoteException {
            String str2 = UserDataService.TAG;
            Log.d(str2, "[JRSYS_USER][getHeaderBitmapByUrl] in Server, url: " + str);
            asyncImageLoader(App.getApp().getApplicationContext(), str);
        }

        @Override // com.ecarx.systemui.plugin.navigationbar.IDataService
        public void addDataListener(IDataListener iDataListener) throws RemoteException {
            this.mDataListener = iDataListener;
        }

        public void asyncImageLoader(Context context, String str) {
            String str2 = UserDataService.TAG;
            Log.d(str2, "[JRSYS_USER][asyncImageLoader] in Server, url: " + str);
            Glide.with(context).asBitmap().load(str).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into(this.mSimpleTarget);
        }
    }

    /* loaded from: classes.dex */
    private class ProvisioningObserver extends ContentObserver {
        ProvisioningObserver() {
            super(new Handler());
            UserDataService.this.getContentResolver().registerContentObserver(Settings.Global.getUriFor("device_provisioned"), false, this);
            onChange(false);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            if (UserDataService.this.isDeviceProvisioned()) {
                UserDataService.this.attachPsdNavigation();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDeviceProvisioned() {
        return Settings.Global.getInt(getContentResolver(), "device_provisioned", 0) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void attachPsdNavigation() {
        Log.i(TAG, "[JRSYS_PSD][attachPsdNavigation]");
        try {
            PsdNavigationController.getInstance().attachToWindow(null, false);
        } catch (Exception e) {
            String str = TAG;
            Log.w(str, "[JRSYS_PSD][attachPsdNavigation], exception: " + Log.getStackTraceString(e));
        }
    }
}

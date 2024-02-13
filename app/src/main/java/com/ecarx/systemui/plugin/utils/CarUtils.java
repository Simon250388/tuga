package com.ecarx.systemui.plugin.utils;

import android.content.Context;
import android.util.Log;
import com.ecarx.xui.adaptapi.binder.IConnectable;
import com.ecarx.xui.adaptapi.car.Car;
import com.ecarx.xui.adaptapi.car.ICar;
import com.ecarx.xui.adaptapi.car.base.ICarInfo;

/* loaded from: classes.dex */
public class CarUtils {
    public static final int DISPLAY_TYPE_CSD = 101;
    public static final int DISPLAY_TYPE_PSD = 102;
    public static final int DISPLAY_TYPE_RFDM = 104;
    public static final int DISPLAY_TYPE_RSD = 103;
    private static final String TAG = CarUtils.class.getSimpleName();
    private static CarUtils mInstance;
    private boolean mConnected;
    private Context mContext;
    private ICar mICar;
    private ICarInfo mICarInfo;
    private IConnectable mIConnectable;
    private OnConnectListener mListener;

    /* loaded from: classes.dex */
    public interface OnConnectListener {
        void onConnectChanged(boolean z);
    }

    private CarUtils(Context context) {
        this.mContext = context;
        this.mICar = Car.create(context);
        String str = TAG;
        Log.w(str, "[JRSYS_PSD][CarUtils] mICar: " + this.mICar);
        ICar iCar = this.mICar;
        if (iCar instanceof IConnectable) {
            this.mIConnectable = (IConnectable) iCar;
            Log.w(TAG, "[JRSYS_PSD][CarUtils]--1");
            this.mIConnectable.connect();
            this.mIConnectable.registerConnectWatcher(new IConnectable.IConnectWatcher() { // from class: com.ecarx.systemui.plugin.utils.CarUtils.1
                @Override // com.ecarx.xui.adaptapi.binder.IConnectable.IConnectWatcher
                public void onConnected() {
                    Log.w(CarUtils.TAG, "[JRSYS_PSD][CarUtils][onConnected]");
                    CarUtils.this.mConnected = true;
                    CarUtils carUtils = CarUtils.this;
                    carUtils.mICarInfo = carUtils.mICar.getCarInfoManager();
                    if (CarUtils.this.mListener != null) {
                        CarUtils.this.mListener.onConnectChanged(CarUtils.this.mConnected);
                    }
                }

                @Override // com.ecarx.xui.adaptapi.binder.IConnectable.IConnectWatcher
                public void onDisConnected() {
                    Log.w(CarUtils.TAG, "[JRSYS_PSD][CarUtils][onDisConnected]");
                    CarUtils.this.mConnected = false;
                    if (CarUtils.this.mListener != null) {
                        CarUtils.this.mListener.onConnectChanged(CarUtils.this.mConnected);
                    }
                }
            });
            return;
        }
        Log.w(TAG, "[JRSYS_PSD][CarUtils] mICar is not Iconnectable");
    }

    public static CarUtils initCarUtils(Context context) {
        synchronized (CarUtils.class) {
            if (mInstance == null) {
                try {
                    mInstance = new CarUtils(context);
                } catch (Error e) {
                    String str = TAG;
                    Log.e(str, "[JRSYS_PSD][CarUtils][initCarUtils], error: " + e.getMessage());
                } catch (Exception e2) {
                    String str2 = TAG;
                    Log.e(str2, "[JRSYS_PSD][CarUtils][initCarUtils], exception: " + e2.getMessage());
                }
            }
        }
        return mInstance;
    }

    public static CarUtils getInstance() {
        CarUtils carUtils;
        synchronized (CarUtils.class) {
            if (mInstance == null) {
                throw new IllegalStateException("[JRSYS_PSD] CarUtils getInstance must init before!");
            }
            carUtils = mInstance;
        }
        return carUtils;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004d A[Catch: all -> 0x0052, TRY_LEAVE, TryCatch #0 {all -> 0x0052, blocks: (B:3:0x0017, B:5:0x0021, B:7:0x0025, B:16:0x004d, B:8:0x002c, B:9:0x0033, B:12:0x003d, B:13:0x0044), top: B:22:0x0017 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getDisplayByType(int r6) {
        /*
            r5 = this;
            java.lang.String r0 = com.ecarx.systemui.plugin.utils.CarUtils.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "[JRSYS_PSD][CarUtils][getDisplayByType]type"
            r1.append(r2)
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            android.util.Log.e(r0, r1)
            r0 = 0
            com.ecarx.xui.adaptapi.car.ICar r1 = r5.mICar     // Catch: java.lang.Throwable -> L52
            com.ecarx.xui.adaptapi.car.base.ICarInfo r1 = r1.getCarInfoManager()     // Catch: java.lang.Throwable -> L52
            r5.mICarInfo = r1     // Catch: java.lang.Throwable -> L52
            if (r1 == 0) goto L4a
            switch(r6) {
                case 101: goto L44;
                case 102: goto L33;
                case 103: goto L2c;
                case 104: goto L25;
                default: goto L24;
            }     // Catch: java.lang.Throwable -> L52
        L24:
            goto L4a
        L25:
            r2 = 268435456(0x10000000, float:2.5243549E-29)
            android.view.Display r1 = r1.getPresentationDisplay(r2)     // Catch: java.lang.Throwable -> L52
            goto L4b
        L2c:
            r2 = 128(0x80, float:1.794E-43)
            android.view.Display r1 = r1.getPresentationDisplay(r2)     // Catch: java.lang.Throwable -> L52
            goto L4b
        L33:
            r2 = 4
            android.view.Display r1 = r1.getPresentationDisplay(r2)     // Catch: java.lang.Throwable -> L52
            if (r1 != 0) goto L3d
            r0 = 999999(0xf423f, float:1.401297E-39)
        L3d:
            com.ecarx.xui.adaptapi.car.base.ICarInfo r1 = r5.mICarInfo     // Catch: java.lang.Throwable -> L52
            android.view.Display r1 = r1.getPresentationDisplay(r2)     // Catch: java.lang.Throwable -> L52
            goto L4b
        L44:
            r2 = 1
            android.view.Display r1 = r1.getPresentationDisplay(r2)     // Catch: java.lang.Throwable -> L52
            goto L4b
        L4a:
            r1 = 0
        L4b:
            if (r1 == 0) goto L6d
            int r0 = r1.getDisplayId()     // Catch: java.lang.Throwable -> L52
            goto L6d
        L52:
            r1 = move-exception
            java.lang.String r2 = com.ecarx.systemui.plugin.utils.CarUtils.TAG
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "[JRSYS_PSD][CarUtils][getDisplayByType], throwable: "
            r3.append(r4)
            java.lang.String r1 = android.util.Log.getStackTraceString(r1)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            android.util.Log.e(r2, r1)
        L6d:
            java.lang.String r1 = com.ecarx.systemui.plugin.utils.CarUtils.TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "[JRSYS_PSD][CarUtils][getDisplayByType], type: "
            r2.append(r3)
            r2.append(r6)
            java.lang.String r6 = ", displayId: "
            r2.append(r6)
            r2.append(r0)
            java.lang.String r6 = r2.toString()
            android.util.Log.i(r1, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecarx.systemui.plugin.utils.CarUtils.getDisplayByType(int):int");
    }

    public void registConnectListener(OnConnectListener onConnectListener) {
        this.mListener = onConnectListener;
    }

    public boolean isConnected() {
        return this.mConnected;
    }

    public ICar getICar() {
        return this.mICar;
    }
}

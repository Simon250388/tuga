package com.ecarx.systemui.plugin.psd;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.ecarx.systemui.plugin.App;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.utils.CarUtils;
import com.ecarx.xui.adaptapi.policy.IWindowManagerPolicy;

import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class PsdNavigationController {
    private static final String TAG = PsdNavigationController.class.getSimpleName();
    private static PsdNavigationController mInstance;
    private final Runnable mCarConnectedRunnable = new Runnable() { // from class: com.ecarx.systemui.plugin.psd.PsdNavigationController.1
        @Override // java.lang.Runnable
        public void run() {
            PsdNavigationController.this.initPsdAfterCarConnected();
        }
    };
    private Context mContext;
    private Handler mHandler;
    private int mNavWindowHeight;
    private int mNavWindowWidth;
    private int mNavX;
    private int mNavY;
    private ViewGroup mNavigationBar;
    private ViewGroup mStatusBar;
    private int mStatusWindowHeight;
    private int mStatusWindowWidth;
    private int mStatusX;
    private int mStatusY;
    private WindowHelper mWindowHelper;
    private WindowManager mWindowManager;

    private PsdNavigationController() {
        Log.i(TAG, "[JRSYS_PSD][PsdNavigationController]--1");
        this.mHandler = new Handler();
        registCarUtils();
    }

    private void registCarUtils() {
        CarUtils carUtils = CarUtils.getInstance();
        carUtils.registConnectListener(new CarUtils.OnConnectListener() { // from class: com.ecarx.systemui.plugin.psd.PsdNavigationController.2
            @Override // com.ecarx.systemui.plugin.utils.CarUtils.OnConnectListener
            public void onConnectChanged(boolean z) {
                if (z) {
                    Log.w(PsdNavigationController.TAG, "[JRSYS_PSD][onConnectChanged] CarUtils Connected CallBack.");
                    PsdNavigationController.this.mHandler.removeCallbacks(PsdNavigationController.this.mCarConnectedRunnable);
                    PsdNavigationController.this.mHandler.post(PsdNavigationController.this.mCarConnectedRunnable);
                }
            }
        });
        if (carUtils.isConnected()) {
            Log.w(TAG, "[JRSYS_PSD][registCarUtils] CarUtils is Connected.");
            this.mHandler.removeCallbacks(this.mCarConnectedRunnable);
            this.mHandler.post(this.mCarConnectedRunnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initPsdAfterCarConnected() {
        Context createPresentationContext = createPresentationContext(App.getApp().getApplicationContext());
        this.mContext = createPresentationContext;
        if (createPresentationContext == null) {
            return;
        }
        this.mWindowManager = (WindowManager) createPresentationContext.getSystemService(Context.WINDOW_SERVICE);
        try {
            this.mWindowHelper = new WindowHelper(this.mContext);
        } catch (Throwable th) {
            String str = TAG;
            Log.w(str, "[JRSYS_PSD][PsdNavigationController] WindowHelper , error: " + Log.getStackTraceString(th));
        }
        this.mNavWindowWidth = this.mContext.getResources().getDimensionPixelSize(R.dimen.psd_navi_content_width);
        this.mNavWindowHeight = this.mContext.getResources().getDimensionPixelSize(R.dimen.psd_navi_content_height);
        this.mNavX = this.mContext.getResources().getDimensionPixelSize(R.dimen.psd_navi_content_x);
        this.mNavY = this.mContext.getResources().getDimensionPixelSize(R.dimen.psd_navi_content_y);
        this.mStatusWindowWidth = this.mContext.getResources().getDimensionPixelSize(R.dimen.psd_status_content_width);
        this.mStatusWindowHeight = this.mContext.getResources().getDimensionPixelSize(R.dimen.psd_status_content_height);
        this.mStatusX = this.mContext.getResources().getDimensionPixelSize(R.dimen.psd_status_content_x);
        this.mStatusY = this.mContext.getResources().getDimensionPixelSize(R.dimen.psd_status_content_y);
        attachToWindow(null, true);
        attachToWindow(null, false);
    }

    public static PsdNavigationController getInstance() {
        String str = TAG;
        Log.i(str, "[JRSYS_PSD][getInstance]--1, mInstance: " + mInstance);
        synchronized (PsdNavigationController.class) {
            if (mInstance == null) {
                Log.i(TAG, "[JRSYS_PSD][getInstance]--2");
                mInstance = new PsdNavigationController();
            }
        }
        return mInstance;
    }

    public void attachToWindow(Runnable runnable, boolean z) {
        Field declaredField;
        Context createPresentationContext = createPresentationContext(App.getApp().getApplicationContext());
        if (createPresentationContext == null) {
            return;
        }
        LayoutInflater from = LayoutInflater.from(createPresentationContext);
        if (z) {
            this.mStatusBar = (ViewGroup) from.inflate(R.layout.psd_status_bar, (ViewGroup) null);
        } else {
            this.mNavigationBar = (ViewGroup) from.inflate(R.layout.psd_navigation_layout, (ViewGroup) null);
        }
        int typeByCode = this.mWindowHelper.getTypeByCode(IWindowManagerPolicy.CODE_SWIPE_FROM_TOP_VIEW);
        try {
            if (z) {
                declaredField = WindowManager.LayoutParams.class.getDeclaredField("TYPE_CODE_STATUS_BAR");
            } else {
                declaredField = WindowManager.LayoutParams.class.getDeclaredField("TYPE_CODE_NAVIGATION_BAR");
            }
            declaredField.setAccessible(true);
            typeByCode = declaredField.getInt(null);
        } catch (Exception e) {
            String str = TAG;
            Log.w(str, "[JRSYS_PSD][attachToWindow]--1, isStatus: " + z + ", exception:" + Log.getStackTraceString(e));
        }
        WindowManager.LayoutParams defaultLayoutParams = WindowHelper.getDefaultLayoutParams(typeByCode);
        if (z) {
            defaultLayoutParams.x = this.mStatusX;
            defaultLayoutParams.y = this.mStatusY;
            defaultLayoutParams.width = this.mStatusWindowWidth;
            defaultLayoutParams.height = this.mStatusWindowHeight;
        } else {
            defaultLayoutParams.x = this.mNavX;
            defaultLayoutParams.y = this.mNavY;
            defaultLayoutParams.width = this.mNavWindowWidth;
            defaultLayoutParams.height = this.mNavWindowHeight;
        }
        defaultLayoutParams.gravity = Gravity.TOP | Gravity.START;
        defaultLayoutParams.flags = defaultLayoutParams.flags | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        defaultLayoutParams.packageName = App.getApp().getPackageName();
        String str2 = TAG;
        Log.i(str2, "[JRSYS_PSD][attachToWindow][addNotFocus]--3, isStatus: " + z + ", addView lp: " + defaultLayoutParams);
        if (z) {
            this.mStatusBar.setVisibility(View.VISIBLE);
            this.mWindowManager.addView(this.mStatusBar, defaultLayoutParams);
        } else {
            this.mNavigationBar.setVisibility(View.VISIBLE);
            this.mWindowManager.addView(this.mNavigationBar, defaultLayoutParams);
        }
        if (runnable != null) {
            runnable.run();
        }
    }

    private static Context createPresentationContext(Context context) {
        Display display;
        Context r1 = null;
        if (context == null) {
            throw new IllegalArgumentException("[JRSYS_PSD] outerContext must not be null");
        }
        Display[] displays = ((DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE)).getDisplays();
        if (displays != null && displays.length > 0) {
            int displayByType = CarUtils.getInstance().getDisplayByType(102);
            if (displayByType == 999999) {
                return null;
            }
            int i = 0;
            while (true) {
                if (i >= displays.length) {
                    display = null;
                    break;
                } else if (displays[i].getDisplayId() == displayByType) {
                    display = displays[i];
                    break;
                } else {
                    i++;
                }
            }
            r1 = display != null ? context.createDisplayContext(display) : null;
            Log.i(TAG, "[JRSYS_PSD][createPresentationContext]--1, displayContext: " + r1 + ", displays size: " + displays.length + ", psdDisplayId: " + displayByType + ", psdDisplay: " + display);
        }
        if (r1 != null) {
            return r1;
        }
        throw new IllegalArgumentException("[JRSYS_PSD] displayContext must not be null");
    }

    public View getRootView(boolean z) {
        if (z) {
            return this.mStatusBar;
        }
        return this.mNavigationBar;
    }
}

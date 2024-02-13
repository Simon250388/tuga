package com.ecarx.systemui.plugin;

import android.os.SystemClock;
import android.view.View;

import com.sensorsdata.analytics.android.autotrack.aop.SensorsDataAutoTrackHelper;

public abstract class NoDoubleClickListener implements View.OnClickListener {
    public static final int MIN_CLICK_DELAY_TIME = 400;
    private static long lastClickTime;
    private String TAG = NoDoubleClickListener.class.getSimpleName();
    private int viewId = -1;

    public abstract void onNoDoubleClick(View view);

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - lastClickTime > 400) {
            handleClick(elapsedRealtime, view);
        }
        SensorsDataAutoTrackHelper.trackViewOnClick(view);
    }

    private void handleClick(long j, View view) {
        lastClickTime = j;
        onNoDoubleClick(view);
    }
}


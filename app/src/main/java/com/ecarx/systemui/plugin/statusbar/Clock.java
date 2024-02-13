package com.ecarx.systemui.plugin.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.format.DateFormat;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.ecarx.systemui.plugin.utils.Lg;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes.dex */
public class Clock extends AppCompatTextView {
    private static final int AM_PM_STYLE = 0;
    private static final int AM_PM_STYLE_GONE = 2;
    private static final int AM_PM_STYLE_NORMAL = 0;
    private static final int AM_PM_STYLE_SMALL = 1;
    private boolean mAttached;
    private Calendar mCalendar;
    private SimpleDateFormat mClockFormat;
    private String mClockFormatString;
    private final BroadcastReceiver mIntentReceiver;
    private Locale mLocale;

    public Clock(Context context) {
        this(context, null);
    }

    public Clock(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Clock(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIntentReceiver = new BroadcastReceiver() { // from class: com.ecarx.systemui.plugin.statusbar.Clock.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                Lg.i("Clock", "onReceive: " + action);
                if (action != null && action.equals("android.intent.action.TIMEZONE_CHANGED")) {
                    final String stringExtra = intent.getStringExtra("time-zone");
                    Clock.this.getHandler().post(new Runnable() { // from class: com.ecarx.systemui.plugin.statusbar.Clock.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Clock.this.mCalendar = Calendar.getInstance(TimeZone.getTimeZone(stringExtra));
                            if (Clock.this.mClockFormat != null) {
                                Clock.this.mClockFormat.setTimeZone(Clock.this.mCalendar.getTimeZone());
                            }
                        }
                    });
                } else if (action != null && action.equals("android.intent.action.CONFIGURATION_CHANGED")) {
                    final Locale locale = Clock.this.getResources().getConfiguration().locale;
                    Clock.this.getHandler().post(new Runnable() { // from class: com.ecarx.systemui.plugin.statusbar.Clock.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (locale.equals(Clock.this.mLocale)) {
                                return;
                            }
                            Clock.this.mLocale = locale;
                            Clock.this.mClockFormatString = "";
                        }
                    });
                }
                Clock.this.getHandler().post(new Runnable() { // from class: com.ecarx.systemui.plugin.statusbar.Clock.1.3
                    @Override // java.lang.Runnable
                    public void run() {
                        Clock.this.updateClock();
                    }
                });
            }
        };
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.mAttached) {
            this.mAttached = true;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_TICK");
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
            intentFilter.addAction("android.intent.action.USER_SWITCHED");
            HandlerThread handlerThread = new HandlerThread("TIME_TICK_ECARX");
            handlerThread.start();
            getContext().registerReceiver(this.mIntentReceiver, intentFilter, null, new Handler(handlerThread.getLooper()));
        }
        this.mCalendar = Calendar.getInstance(TimeZone.getDefault());
        updateClock();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mAttached) {
            getContext().unregisterReceiver(this.mIntentReceiver);
            this.mAttached = false;
        }
    }

    final void updateClock() {
        Lg.d("System.currentTimeMillis() : " + System.currentTimeMillis());
        this.mCalendar.setTimeInMillis(System.currentTimeMillis());
        setText(getSmallTime());
    }

    private CharSequence getSmallTime() {
        SimpleDateFormat simpleDateFormat;
        String str = DateFormat.is24HourFormat(getContext()) ? "HH:mm" : "h:mm a";
        if (!str.equals(this.mClockFormatString)) {
            simpleDateFormat = new SimpleDateFormat(str, Locale.ENGLISH);
            this.mClockFormat = simpleDateFormat;
            this.mClockFormatString = str;
        } else {
            simpleDateFormat = this.mClockFormat;
        }
        return simpleDateFormat.format(this.mCalendar.getTime());
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        super.setPressed(z);
        setAlpha(z ? 0.5f : 1.0f);
    }
}

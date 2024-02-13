package com.ecarx.systemui.plugin.statusbar;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.statusbar.entity.StatusBarIconEntity;
import com.ecarx.systemui.plugin.statusbar.iconview.EStatefulObject;
import com.ecarx.systemui.plugin.statusbar.iconview.StatusBarIconView;
import com.ecarx.systemui.plugin.statusbar.iconview.WifiOrHotspotIconView;
import com.ecarx.systemui.plugin.statusbar.policy.NetworkController;
import com.ecarx.systemui.plugin.utils.IntentConstants;
import com.ecarx.systemui.plugin.utils.Lg;
import com.sensorsdata.analytics.android.autotrack.aop.SensorsDataAutoTrackHelper;


/* loaded from: classes.dex */
public class SystemIconContainer extends LinearLayout implements StatusBarManager.SystemIconStatusManager, NetworkController.SignalCluster, View.OnClickListener {
    private static final int MAX_ICON_COUNT = 9;
    static final String TAG = SystemIconContainer.class.getSimpleName();
    private int iconMarginRight;
    private Context mConfigurationContext;

    @Override // com.ecarx.systemui.plugin.statusbar.policy.NetworkController.SignalCluster
    public void setWifiOrApIndicators(boolean z, boolean z2, boolean z3, int i, int i2, String str, String str2) {
    }

    public SystemIconContainer(Context context) {
        this(context, null);
    }

    public SystemIconContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SystemIconContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mConfigurationContext = context;
        this.iconMarginRight = getContext().getResources().getDimensionPixelSize(R.dimen.status_bar_icon_margin_right);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        EStatefulObject[] values = EStatefulObject.values();
        removeAllViews();
        for (int length = values.length - 1; length >= 0; length--) {
            StatusBarIconView createStatusBarIconView = createStatusBarIconView(values[length]);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.rightMargin = this.iconMarginRight;
            layoutParams.gravity = 16;
            addView(createStatusBarIconView, layoutParams);
        }
    }

    @Override // com.ecarx.systemui.plugin.statusbar.policy.NetworkController.SignalCluster
    public void setMobileSignalStrengthIndicators(boolean z, int i, int i2, String str) {
        StatusBarManager.getInstance().setIcon(new StatusBarIconEntity(EStatefulObject.NETWORK_SIGNAL_STRENGTH, i));
        if (!z) {
            StatusBarManager.getInstance().setIcon(new StatusBarIconEntity(EStatefulObject.NETWORK_SIGNAL, i2));
            return;
        }
        StatusBarManager.getInstance().updateIconVisibility(EStatefulObject.NETWORK_SIGNAL, !z);
    }

    @Override // com.ecarx.systemui.plugin.statusbar.StatusBarManager.SystemIconStatusManager
    public void updateIconView(final StatusBarIconEntity statusBarIconEntity) {
        post(new Runnable() { // from class: com.ecarx.systemui.plugin.statusbar.SystemIconContainer.1
            @Override // java.lang.Runnable
            public void run() {
                StatusBarIconView childViewByStatefulObject = SystemIconContainer.this.getChildViewByStatefulObject(statusBarIconEntity.getStatefulObject());
                if (childViewByStatefulObject != null) {
                    childViewByStatefulObject.setVisibility(View.VISIBLE);
                    childViewByStatefulObject.setIcon(statusBarIconEntity);
                    SystemIconContainer.this.checkStatusViewCount();
                }
            }
        });
    }

    @Override // com.ecarx.systemui.plugin.statusbar.StatusBarManager.SystemIconStatusManager
    public void updateIconVisibility(final EStatefulObject eStatefulObject, final boolean z) {
        post(new Runnable() { // from class: com.ecarx.systemui.plugin.statusbar.SystemIconContainer.2
            @Override // java.lang.Runnable
            public void run() {
                StatusBarIconView childViewByStatefulObject = SystemIconContainer.this.getChildViewByStatefulObject(eStatefulObject);
                if (childViewByStatefulObject != null) {
                    childViewByStatefulObject.setVisibility(z ? View.VISIBLE : View.GONE);
                }
            }
        });
    }

    public void refreshView() {
        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
            requestLayout();
        } else {
            post(new Runnable() { // from class: com.ecarx.systemui.plugin.statusbar.SystemIconContainer.3
                @Override // java.lang.Runnable
                public void run() {
                    SystemIconContainer.this.requestLayout();
                }
            });
        }
    }

    public StatusBarIconView getChildViewByStatefulObject(EStatefulObject eStatefulObject) {
        for (int i = 0; i < getChildCount(); i++) {
            if (eStatefulObject.equals(getChildAt(i).getTag()) && (getChildAt(i) instanceof StatusBarIconView)) {
                return (StatusBarIconView) getChildAt(i);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkStatusViewCount() {
        if (getChildCount() > 9) {
            for (int i = 9; i < getChildCount(); i++) {
                getChildAt(i).setVisibility(View.GONE);
            }
        }
    }

    private StatusBarIconView createStatusBarIconView(EStatefulObject eStatefulObject) {
        StatusBarIconView statusBarIconView;
        if (eStatefulObject.equals(EStatefulObject.WIFI_AP)) {
            statusBarIconView = new WifiOrHotspotIconView(getContext());
        } else {
            statusBarIconView = new StatusBarIconView(getContext());
        }
        statusBarIconView.setTag(eStatefulObject);
        statusBarIconView.setVisibility(View.GONE);
        return statusBarIconView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getTag() instanceof EStatefulObject) {
            EStatefulObject eStatefulObject = (EStatefulObject) view.getTag();
            String str = TAG;
            Lg.w(str, "onClick  : " + eStatefulObject.name());
            int i = AnonymousClass4.$SwitchMap$com$ecarx$systemui$plugin$statusbar$iconview$EStatefulObject[eStatefulObject.ordinal()];
            if (i == 1) {
                startIntent(new Intent(IntentConstants.ACTION_BLUETOOTH_SETTINGS));
            } else if (i == 2) {
                startIntent(new Intent(IntentConstants.ACTION_WPC_SETTINGS));
            } else if (i == 3) {
                Intent intent = new Intent();
                intent.setAction(IntentConstants.ACTION_USB);
                intent.putExtra("diskId", "2");
                intent.putExtra("USB_TYPE", 2);
                try {
                    getContext().sendBroadcast(intent);
                } catch (Exception e) {
                    Lg.e(TAG, e);
                }
            } else if (i == 4) {
                Intent intent2 = new Intent();
                intent2.setAction(IntentConstants.ACTION_MESSAGE);
                intent2.putExtra("ecarx.controlboard.EXTRA_CURRENT_PAGE", 1);
                try {
                    getContext().sendBroadcast(intent2);
                } catch (Exception e2) {
                    Lg.e(TAG, e2);
                }
            } else if (i == 5) {
                startIntent(new Intent(IntentConstants.ACTION_DVR));
            }
            SensorsDataAutoTrackHelper.trackViewOnClick(view);
            return;
        }
        SensorsDataAutoTrackHelper.trackViewOnClick(view);
    }

    /* renamed from: com.ecarx.systemui.plugin.statusbar.SystemIconContainer$4  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$ecarx$systemui$plugin$statusbar$iconview$EStatefulObject;

        static {
            int[] iArr = new int[EStatefulObject.values().length];
            $SwitchMap$com$ecarx$systemui$plugin$statusbar$iconview$EStatefulObject = iArr;
            try {
                iArr[EStatefulObject.BLUETOOTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ecarx$systemui$plugin$statusbar$iconview$EStatefulObject[EStatefulObject.WPC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ecarx$systemui$plugin$statusbar$iconview$EStatefulObject[EStatefulObject.USB.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$ecarx$systemui$plugin$statusbar$iconview$EStatefulObject[EStatefulObject.UNREAD_MESSAGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$ecarx$systemui$plugin$statusbar$iconview$EStatefulObject[EStatefulObject.DVR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private void startIntent(Intent intent) {
        try {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        } catch (Exception e) {
            Lg.e(TAG, e);
        }
    }

    public void onUIModeChanged(Context context) {
        Lg.w(" systemIconContainer onUIModeChanged");
        this.mConfigurationContext = context;
        for (int i = 0; i < getChildCount(); i++) {
            StatusBarIconView statusBarIconView = (StatusBarIconView) getChildAt(i);
            statusBarIconView.setConfigurationContext(context);
            statusBarIconView.refreshIcon();
        }
    }
}

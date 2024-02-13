package com.ecarx.systemui.plugin.statusbar;

import android.content.Context;
import com.ecarx.systemui.plugin.statusbar.entity.StatusBarIconEntity;
import com.ecarx.systemui.plugin.statusbar.iconview.EStatefulObject;

/* loaded from: classes.dex */
public class StatusBarManager {
    private static final int ACTION_REMOVE = 0;
    private static final int ACTION_SET = 1;
    private static final int ACTION_VISIBILITY = 2;
    private static final String TAG = "StatusBarManager";
    private static StatusBarManager mStatusBarManager;
    private Context mContext;
    private SystemIconContainer mSystemIconContainer;

    /* loaded from: classes.dex */
    public interface SystemIconStatusManager {
        void updateIconView(StatusBarIconEntity statusBarIconEntity);

        void updateIconVisibility(EStatefulObject eStatefulObject, boolean z);
    }

    public static StatusBarManager getInstance() {
        return mStatusBarManager;
    }

    public static void init(Context context, SystemIconContainer systemIconContainer) {
        mStatusBarManager = new StatusBarManager(context, systemIconContainer);
    }

    private StatusBarManager(Context context, SystemIconContainer systemIconContainer) {
        this.mContext = context;
        this.mSystemIconContainer = systemIconContainer;
    }

    public void setIcon(StatusBarIconEntity statusBarIconEntity) {
        this.mSystemIconContainer.updateIconView(statusBarIconEntity);
    }

    public void updateIconVisibility(EStatefulObject eStatefulObject, boolean z) {
        this.mSystemIconContainer.updateIconVisibility(eStatefulObject, z);
    }
}

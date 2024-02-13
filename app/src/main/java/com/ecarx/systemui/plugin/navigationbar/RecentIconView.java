package com.ecarx.systemui.plugin.navigationbar;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.core.graphics.drawable.DrawableCompat;

import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.utils.AlphaImageView;
import com.ecarx.systemui.plugin.utils.Lg;
import com.ecarx.systemui.plugin.utils.ThemeHelper;
import com.ecarx.systemui.plugin.utils.UIModeModel;

/* loaded from: classes.dex */
public class RecentIconView extends AlphaImageView {
    private static PackageManager packageManager;
    private String TAG;
    private Context mConfigurationContext;
    private int mNormalIconSize;
    private String mPackageNam;
    private int mSmallIconSize;

    public RecentIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = RecentIconView.class.getSimpleName();
        this.mConfigurationContext = context;
        packageManager = context.getPackageManager();
        this.mNormalIconSize = context.getResources().getDimensionPixelSize(R.dimen.app_icon_size);
        this.mSmallIconSize = context.getResources().getDimensionPixelSize(R.dimen.app_small_icon_size);
    }

    public void updateView(String str) {
        Drawable notBackgroundApplicationIcon;
        Drawable applicationIcon;
        this.mPackageNam = str;
        Context configurationContext = UIModeModel.getConfigurationContext(1);
        this.mConfigurationContext = configurationContext;
        if (configurationContext == null) {
            this.mConfigurationContext = getContext();
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            if (AppWatcherService.isInnerApps(str)) {
                layoutParams.width = this.mNormalIconSize;
                layoutParams.height = this.mNormalIconSize;
            } else {
                layoutParams.width = this.mSmallIconSize;
                layoutParams.height = this.mSmallIconSize;
            }
            String str2 = this.TAG;
            Lg.i(str2, "[JRSYS][updateView], packageName: " + str + ", lp.width: " + layoutParams.width + ", lp.height: " + layoutParams.height);
        }
        try {
            if (this.mPackageNam.equals("com.ecarx.xiaoka")) {
                notBackgroundApplicationIcon = ThemeHelper.getDefault().getNoBackgroundActivityIcon(packageManager.getResourcesForApplication(this.mPackageNam), new ComponentName("com.ecarx.xiaoka", "com.ecarx.magicbook.ui.MagicActivity"), this.mConfigurationContext.getResources().getDisplayMetrics().densityDpi);
            } else {
                notBackgroundApplicationIcon = ThemeHelper.getDefault().getNotBackgroundApplicationIcon(packageManager.getResourcesForApplication(this.mPackageNam), this.mPackageNam, this.mConfigurationContext.getResources().getDisplayMetrics().densityDpi);
            }
            Lg.d("densityDpi:" + this.mConfigurationContext.getResources().getDisplayMetrics().densityDpi);
            if (notBackgroundApplicationIcon != null) {
                setImageDrawable(getColoredDrawable(notBackgroundApplicationIcon, this.mConfigurationContext.getResources().getColor(R.color.status_bar_text_color)));
                return;
            }
            if (this.mPackageNam.equals("com.ecarx.xiaoka")) {
                applicationIcon = packageManager.getActivityIcon(new ComponentName("com.ecarx.xiaoka", "com.ecarx.magicbook.ui.MagicActivity"));
            } else {
                applicationIcon = packageManager.getApplicationIcon(this.mPackageNam);
            }
            setImageDrawable(applicationIcon);
        } catch (Exception e) {
            Lg.e(this.TAG, e);
        }
    }

    public Drawable getColoredDrawable(Drawable drawable, int i) {
        Drawable mutate = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTint(mutate, i);
        Lg.i("RecentIconView getColoredDrawable:" + Integer.toHexString(i));
        return mutate;
    }
}

package com.ecarx.systemui.plugin.statusbar.iconview;

import android.content.Context;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.statusbar.entity.StatusBarIconEntity;
import com.ecarx.systemui.plugin.utils.AlphaImageView;
import com.ecarx.systemui.plugin.utils.Lg;
import com.ecarx.systemui.plugin.utils.SvgUtils;
import com.ecarx.systemui.plugin.utils.Utilities;

/* loaded from: classes.dex */
public class StatusBarIconView extends AlphaImageView {
    static final String TAG = "StatusBarIconView";
    private int iconHeight;
    private int iconWidth;
    public Context mConfigurationContext;
    public StatusBarIconEntity mIcon;

    public StatusBarIconView(Context context) {
        this(context, null);
    }

    public StatusBarIconView(Context context, AttributeSet attributeSet) {
        this(context, null, 0);
    }

    public StatusBarIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mConfigurationContext = context;
        this.iconHeight = (int) getResources().getDimension(R.dimen.status_bar_icon_height);
        this.iconWidth = (int) getResources().getDimension(R.dimen.status_bar_icon_width);
    }

    public void setConfigurationContext(Context context) {
        this.mConfigurationContext = context;
    }

    public StatusBarIconEntity getIcon() {
        return this.mIcon;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            size = this.iconWidth;
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = this.iconHeight;
        }
        setMeasuredDimension(size, size2);
        Log.w(TAG, "[JRSYS][onMeasure] width:" + size + " heightSize:" + size2);
    }

    public <T extends StatusBarIconEntity> void setIcon(final T t) {
        this.mIcon = t;
        Lg.w("setIcon:" + t.getStatefulObject().name() + " " + Integer.toHexString(t.getIconId()) + " visibility:" + getVisibility());
        if (this.mIcon == null) {
            return;
        }
        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
            setDrawable(t);
        } else {
            post(new Runnable() { // from class: com.ecarx.systemui.plugin.statusbar.iconview.StatusBarIconView.1
                @Override // java.lang.Runnable
                public void run() {
                    StatusBarIconView.this.setDrawable(t);
                }
            });
        }
    }

    protected void setDrawable(StatusBarIconEntity statusBarIconEntity) {
        if (statusBarIconEntity == null || statusBarIconEntity.getIconId() <= 0) {
            setVisibility(View.GONE);
            return;
        }
        setBackgroundResource(Utilities.getBgId(statusBarIconEntity.getStatefulObject()));
        if (statusBarIconEntity.getStatefulObject().equals(EStatefulObject.DVR)) {
            setImageDrawable(this.mConfigurationContext.getResources().getDrawable(statusBarIconEntity.getIconId()));
        } else {
            setImageDrawable(SvgUtils.getTintDrawable(this.mConfigurationContext, statusBarIconEntity.getIconId(), -1));
        }
    }

    public void refreshIcon() {
        StatusBarIconEntity statusBarIconEntity = this.mIcon;
        if (statusBarIconEntity != null) {
            setIcon(statusBarIconEntity);
        }
    }
}

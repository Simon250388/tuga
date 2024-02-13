package com.ecarx.systemui.plugin.statusbar.iconview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.statusbar.entity.StatusBarIconEntity;
import com.ecarx.systemui.plugin.statusbar.entity.StatusBarIconWithSubIconEntity;
import com.ecarx.systemui.plugin.utils.Lg;
import com.ecarx.systemui.plugin.utils.SvgUtils;

/* loaded from: classes.dex */
public class MobileNetworkIconView extends StatusBarIconView {
    private int iconHeight;
    private int iconWidth;
    private int networkTypeIconWidth;
    private Drawable strengthDrawable;
    private Drawable typeDrawable;

    public MobileNetworkIconView(Context context) {
        this(context, null);
    }

    public MobileNetworkIconView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MobileNetworkIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.iconHeight = (int) getResources().getDimension(R.dimen.status_bar_icon_height);
        this.iconWidth = (int) getResources().getDimension(R.dimen.status_bar_icon_width);
        setBackgroundResource(R.color.colorPrimary_test2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ecarx.systemui.plugin.statusbar.iconview.StatusBarIconView, android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            size = this.iconWidth + getStrengthIconLeft();
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = this.iconHeight;
        }
        setMeasuredDimension(size, size2);
        Lg.w("MobileNetworkIconView onMeasure width:" + size + " networkTypeIconWidth:" + getStrengthIconLeft());
    }

    @Override // com.ecarx.systemui.plugin.statusbar.iconview.StatusBarIconView
    public <T extends StatusBarIconEntity> void setIcon(T t) {
        this.mIcon = t;
        if (t != null && (t instanceof StatusBarIconWithSubIconEntity)) {
            StatusBarIconWithSubIconEntity statusBarIconWithSubIconEntity = (StatusBarIconWithSubIconEntity) t;
            if (statusBarIconWithSubIconEntity.getIconId() > 0) {
                this.strengthDrawable = SvgUtils.getTintDrawable(this.mConfigurationContext, statusBarIconWithSubIconEntity.getIconId(), -1);
            } else {
                this.strengthDrawable = null;
            }
            int subIconId = statusBarIconWithSubIconEntity.getSubIconId();
            int i = 0;
            if (subIconId > 0) {
                Drawable tintDrawable = SvgUtils.getTintDrawable(this.mConfigurationContext, subIconId, -1);
                this.typeDrawable = tintDrawable;
                if (tintDrawable != null) {
                    i = tintDrawable.getMinimumWidth();
                }
            } else {
                this.typeDrawable = null;
            }
            if (this.networkTypeIconWidth != i || getMeasuredWidth() != getStrengthIconLeft() + this.iconWidth) {
                this.networkTypeIconWidth = i;
                Lg.w("MobileNetworkIconView isInLayout:" + isInLayout() + " isLayoutRequested:" + isLayoutRequested());
                requestLayout();
            }
            invalidate();
            StringBuilder sb = new StringBuilder();
            sb.append("MobileNetworkIconView isVisible:");
            sb.append(getVisibility());
            sb.append(" typeIconId:");
            sb.append(Integer.toHexString(subIconId));
            sb.append(" networkTypeIconWidth:");
            sb.append(this.networkTypeIconWidth);
            sb.append(" typeDrawable:");
            sb.append(this.typeDrawable == null ? " null" : " not null");
            Lg.w(sb.toString());
        }
    }

    private int getStrengthIconLeft() {
        if (this.typeDrawable == null) {
            return 0;
        }
        return this.networkTypeIconWidth;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Lg.w("MobileNetworkIconView onDraw width:" + getWidth() + " networkTypeIconWidth:" + getStrengthIconLeft());
        Drawable drawable = this.typeDrawable;
        if (drawable != null) {
            drawable.setBounds(0, 0, getStrengthIconLeft(), this.iconHeight);
            this.typeDrawable.draw(canvas);
        }
        Drawable drawable2 = this.strengthDrawable;
        if (drawable2 != null) {
            drawable2.setBounds(getStrengthIconLeft(), 0, getStrengthIconLeft() + this.iconWidth, this.iconHeight);
            this.strengthDrawable.draw(canvas);
        }
    }
}

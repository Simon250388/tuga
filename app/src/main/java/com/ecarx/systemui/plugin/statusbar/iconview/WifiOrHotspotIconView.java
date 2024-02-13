package com.ecarx.systemui.plugin.statusbar.iconview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.statusbar.entity.StatusBarIconEntity;
import com.ecarx.systemui.plugin.statusbar.entity.StatusBarIconWithCountEntity;
import com.ecarx.systemui.plugin.utils.Lg;
import com.ecarx.systemui.plugin.utils.SvgUtils;

/* loaded from: classes.dex */
public class WifiOrHotspotIconView extends StatusBarIconView {
    private static final int HOTSPOT_COUNT_MAX = 9;
    private String TAG;
    private int hotspotCount;
    private String hotspotText;
    private int hotspotTextOffsetX;
    private int hotspotTextOffsetY;
    private int hotspotTextWidth;
    private int iconHeight;
    private int iconWidth;
    private Paint mPaint;
    private Drawable wifiOrHotspotDrawable;

    public WifiOrHotspotIconView(Context context) {
        this(context, null);
    }

    public WifiOrHotspotIconView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WifiOrHotspotIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = WifiOrHotspotIconView.class.getSimpleName();
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setTextSize(getResources().getDimension(R.dimen.message_text_size));
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mPaint.setAntiAlias(true);
        this.iconHeight = getResources().getDimensionPixelSize(R.dimen.status_bar_icon_height);
        this.iconWidth = getResources().getDimensionPixelSize(R.dimen.status_bar_icon_width);
        setBackgroundResource(R.color.colorPrimary_test4);
    }

    @Override // com.ecarx.systemui.plugin.statusbar.iconview.StatusBarIconView
    public <T extends StatusBarIconEntity> void setIcon(T t) {
        this.mIcon = t;
        if (t == null || t.getIconId() == 0) {
            Lg.d(this.TAG, "setIcon icon is empty ");
        } else if (t instanceof StatusBarIconWithCountEntity) {
            this.wifiOrHotspotDrawable = SvgUtils.getTintDrawable(this.mConfigurationContext, t.getIconId(), -1);
            this.hotspotCount = ((StatusBarIconWithCountEntity) t).getCount();
            Lg.d(this.TAG, "[JRSYS_NET] hotspotCount:" + this.hotspotCount);
            this.mPaint.setColor(this.mConfigurationContext.getResources().getColor(R.color.status_bar_text_color));
            int i = this.hotspotCount;
            if (i >= 0) {
                if (i > 9) {
                    i = 9;
                }
                this.hotspotCount = i;
                String valueOf = String.valueOf(i);
                this.hotspotText = valueOf;
                int measureText = (int) this.mPaint.measureText(valueOf);
                if (measureText != this.hotspotTextWidth) {
                    this.hotspotTextWidth = measureText;
                }
            } else {
                this.hotspotText = null;
                this.hotspotTextWidth = 0;
            }
            this.hotspotTextOffsetX = 1;
            this.hotspotTextOffsetY = 1;
            requestLayout();
            invalidate();
        }
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
            size = this.iconWidth;
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = this.iconHeight;
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mPaint.getFontMetrics();
        Drawable drawable = this.wifiOrHotspotDrawable;
        if (drawable == null) {
            return;
        }
        drawable.setBounds(0, 0, this.iconWidth, this.iconHeight);
        this.wifiOrHotspotDrawable.draw(canvas);
        if (TextUtils.isEmpty(this.hotspotText)) {
        }
    }
}

package com.ecarx.systemui.plugin.statusbar.iconview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.statusbar.entity.StatusBarIconEntity;
import com.ecarx.systemui.plugin.statusbar.entity.StatusBarIconWithCountEntity;
import com.ecarx.systemui.plugin.utils.Lg;

/* loaded from: classes.dex */
public class MessageView extends StatusBarIconView {
    private final int MESSAGE_COUNT_MAX;
    private String TAG;
    private int iconHeight;
    private int iconWidth;
    private Paint mPaint;
    private Drawable messageDrawable;
    private String messageText;
    private int messageTextWidth;
    private int unreadMessage;

    public MessageView(Context context) {
        this(context, null);
    }

    public MessageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MessageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = MessageView.class.getSimpleName();
        this.MESSAGE_COUNT_MAX = 9;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setTextSize(getResources().getDimension(R.dimen.message_text_size));
        this.mPaint.setAntiAlias(true);
        this.iconHeight = getResources().getDimensionPixelSize(R.dimen.status_bar_icon_height);
        this.iconWidth = getResources().getDimensionPixelSize(R.dimen.status_bar_icon_width);
    }

    @Override // com.ecarx.systemui.plugin.statusbar.iconview.StatusBarIconView
    public <T extends StatusBarIconEntity> void setIcon(T t) {
        this.mIcon = t;
        if (t instanceof StatusBarIconWithCountEntity) {
            int count = ((StatusBarIconWithCountEntity) t).getCount();
            this.unreadMessage = count;
            if (count > 9) {
                this.messageText = "9+";
            } else {
                this.messageText = this.unreadMessage + "";
            }
            Lg.w("unreadMessage:" + this.unreadMessage);
            this.mPaint.setColor(this.mConfigurationContext.getResources().getColor(R.color.status_bar_text_color));
            int measureText = (int) this.mPaint.measureText(this.messageText);
            this.messageDrawable = this.mConfigurationContext.getResources().getDrawable(R.drawable.ic_status_unread_message);
            if (measureText != this.messageTextWidth) {
                this.messageTextWidth = measureText;
                requestLayout();
            }
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
            size = this.iconWidth + this.messageTextWidth;
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = this.iconHeight;
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint.FontMetrics fontMetrics = this.mPaint.getFontMetrics();
        Drawable drawable = this.messageDrawable;
        if (drawable == null) {
            return;
        }
        int i = this.messageTextWidth;
        drawable.setBounds(i, 0, this.iconWidth + i, this.iconHeight);
        this.messageDrawable.draw(canvas);
        canvas.drawText(this.messageText, 0.0f, (this.iconHeight / 2.0f) + (((fontMetrics.bottom - fontMetrics.top) / 2.0f) - fontMetrics.bottom), this.mPaint);
    }
}

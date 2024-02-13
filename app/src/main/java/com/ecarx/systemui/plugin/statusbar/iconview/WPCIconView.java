package com.ecarx.systemui.plugin.statusbar.iconview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.statusbar.entity.StatusBarIconEntity;
import com.ecarx.systemui.plugin.statusbar.entity.StatusBarIconWithTypeEntity;
import com.ecarx.systemui.plugin.utils.Lg;

/* loaded from: classes.dex */
public class WPCIconView extends StatusBarIconView {
    private ValueAnimator mChargeAnimator;
    private Paint mPaint;
    private int mProgress;
    private int mProgressColor;
    private int mProgressHeight;
    private Rect mProgressRect;
    private int mState;

    public WPCIconView(Context context) {
        this(context, null);
    }

    public WPCIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mProgress = -1;
        this.mProgressHeight = -1;
        this.mState = -1;
        this.mProgressRect = new Rect();
        Resources resources = getResources();
        this.mProgressColor = resources.getColor(R.color.power_progress_bg);
        this.mProgressRect.set((int) resources.getDimension(R.dimen.power_rect_left), (int) resources.getDimension(R.dimen.power_rect_top), (int) resources.getDimension(R.dimen.power_rect_right), (int) resources.getDimension(R.dimen.power_rect_bottom));
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setColor(this.mProgressColor);
    }

    @Override // com.ecarx.systemui.plugin.statusbar.iconview.StatusBarIconView
    public <T extends StatusBarIconEntity> void setIcon(T t) {
        super.setIcon(t);
        if (t instanceof StatusBarIconWithTypeEntity) {
            StringBuilder sb = new StringBuilder();
            sb.append("setState:");
            StatusBarIconWithTypeEntity statusBarIconWithTypeEntity = (StatusBarIconWithTypeEntity) t;
            sb.append(statusBarIconWithTypeEntity.getType());
            Lg.w(sb.toString());
            setState(statusBarIconWithTypeEntity.getType());
        }
    }

    public void setState(int i) {
        if (this.mState == i) {
            return;
        }
        this.mState = i;
        if (i == 637665539) {
            if (this.mChargeAnimator == null) {
                ValueAnimator ofInt = ValueAnimator.ofInt(0, 100);
                this.mChargeAnimator = ofInt;
                ofInt.setRepeatCount(-1);
                this.mChargeAnimator.setRepeatMode(ValueAnimator.RESTART);
                this.mChargeAnimator.setDuration(4000L);
                this.mChargeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ecarx.systemui.plugin.statusbar.iconview.WPCIconView.1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        if (WPCIconView.this.mState == 637665539) {
                            WPCIconView.this.mProgress = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                            WPCIconView.this.invalidate();
                        }
                    }
                });
            }
            this.mChargeAnimator.start();
            return;
        }
        ValueAnimator valueAnimator = this.mChargeAnimator;
        if (valueAnimator != null) {
            valueAnimator.end();
            this.mChargeAnimator = null;
        }
        if (i == 637665540) {
            this.mProgress = 100;
        } else {
            this.mProgress = 0;
        }
        invalidate();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ValueAnimator valueAnimator = this.mChargeAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        int i = this.mProgress;
        if (i > 0) {
            if (i == 100) {
                this.mProgressHeight = this.mProgressRect.height();
            } else {
                this.mProgressHeight = (int) (((this.mProgressRect.height() * this.mProgress) * 1.0f) / 100.0f);
            }
            canvas.drawRect(this.mProgressRect.left, this.mProgressRect.bottom - this.mProgressHeight, this.mProgressRect.right, this.mProgressRect.bottom, this.mPaint);
        }
        super.onDraw(canvas);
    }
}

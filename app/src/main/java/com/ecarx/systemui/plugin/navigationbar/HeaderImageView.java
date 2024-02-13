package com.ecarx.systemui.plugin.navigationbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.utils.AlphaImageView;

/* loaded from: classes.dex */
public class HeaderImageView extends AlphaImageView {
    private static final String TAG = HeaderImageView.class.getSimpleName();
    private static final float USER_RADIUS = 40.0f;
    private Paint mPaint;
    private int mStrokeWidth;
    private float r;
    private float x;
    private float y;

    public HeaderImageView(Context context) {
        this(context, null);
    }

    public HeaderImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HeaderImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mStrokeWidth = 2;
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
        this.x = 0.0f;
        this.y = 0.0f;
        this.r = USER_RADIUS;
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setAntiAlias(true);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        float min = Math.min(getWidth(), getHeight());
        Log.w(TAG, "[JRSYS][onDraw], sw: " + min + ", r: " + this.r);
        if (min >= this.r) {
            this.x = (getWidth() - (this.r * 2.0f)) / 2.0f;
            this.y = (getHeight() - (this.r * 2.0f)) / 2.0f;
            return;
        }
        this.r = min;
        this.x = (getWidth() - (this.r * 2.0f)) / 2.0f;
        this.y = (getHeight() - (this.r * 2.0f)) / 2.0f;
    }

    private void drawTargetBitmap2(Canvas canvas, Bitmap bitmap, Drawable drawable) {
        String str = TAG;
        Log.w(str, "[JRSYS_USER][drawTargetBitmap2], bitmap: " + bitmap + ", bitmap width: " + bitmap.getWidth() + ", bitmap height: " + bitmap.getHeight());
        int saveLayer = canvas.saveLayer(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(bitmap, (float) ((getWidth() / 2) - (bitmap.getWidth() / 2)), (float) ((getHeight() / 2) - (bitmap.getHeight() / 2)), this.mPaint);
        this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        this.mPaint.setStrokeWidth((float) this.mStrokeWidth);
        this.mPaint.setColor(getResources().getColor(R.color.nav_user_border_color));
        this.mPaint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(this.x, this.y);
        float f = this.x;
        float f2 = this.r;
        path.addCircle(f + f2, this.y + f2, f2, Path.Direction.CCW);
        canvas.drawPath(path, this.mPaint);
        this.mPaint.setXfermode(null);
        canvas.restoreToCount(saveLayer);
        if (bitmap.isRecycled()) {
            return;
        }
        bitmap.recycle();
    }

    private void drawTargetBitmap(Canvas canvas, Bitmap bitmap, Drawable drawable) {
        String str = TAG;
        Log.w(str, "[JRSYS_USER][drawTargetBitmap], bitmap: " + bitmap + ", bitmap width: " + bitmap.getWidth() + ", bitmap height: " + bitmap.getHeight());
        int saveLayer = canvas.saveLayer(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), null, Canvas.ALL_SAVE_FLAG);
        int min = Math.min(getWidth(), getHeight()) / 2;
        canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), this.r, this.mPaint);
        this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, (float) ((getWidth() / 2) - (bitmap.getWidth() / 2)), (float) ((getHeight() / 2) - (bitmap.getHeight() / 2)), this.mPaint);
        this.mPaint.setXfermode(null);
        canvas.restoreToCount(saveLayer);
    }
}

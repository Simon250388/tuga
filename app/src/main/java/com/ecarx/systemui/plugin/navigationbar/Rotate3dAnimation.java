package com.ecarx.systemui.plugin.navigationbar;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/* loaded from: classes.dex */
public class Rotate3dAnimation extends Animation {
    public static final Byte ROTATE_X_AXIS = (byte) 0;
    public static final Byte ROTATE_Y_AXIS = (byte) 1;
    public static final Byte ROTATE_Z_AXIS = (byte) 2;
    private Camera mCamera;
    private final float mCenterX;
    private final float mCenterY;
    private final float mDepthZ;
    private final float mFromDegrees;
    private final boolean mReverse;
    private Byte mRotateAxis;
    private final float mToDegrees;

    public Rotate3dAnimation(float f, float f2, float f3, float f4, float f5, Byte b, boolean z) {
        this.mFromDegrees = f;
        this.mToDegrees = f2;
        this.mCenterX = f3;
        this.mCenterY = f4;
        this.mDepthZ = f5;
        this.mRotateAxis = b;
        this.mReverse = z;
    }

    @Override // android.view.animation.Animation
    public void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        this.mCamera = new Camera();
    }

    @Override // android.view.animation.Animation
    protected void applyTransformation(float f, Transformation transformation) {
        float f2 = this.mFromDegrees;
        float f3 = f2 + ((this.mToDegrees - f2) * f);
        float f4 = this.mCenterX;
        float f5 = this.mCenterY;
        Camera camera = this.mCamera;
        Matrix matrix = transformation.getMatrix();
        camera.save();
        if (this.mReverse) {
            camera.translate(0.0f, 0.0f, this.mDepthZ * f);
        } else {
            camera.translate(0.0f, 0.0f, this.mDepthZ * (1.0f - f));
        }
        if (ROTATE_X_AXIS.equals(this.mRotateAxis)) {
            camera.rotateX(f3);
        } else if (ROTATE_Y_AXIS.equals(this.mRotateAxis)) {
            camera.rotateY(f3);
        } else {
            camera.rotateZ(f3);
        }
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-f4, -f5);
        matrix.postTranslate(f4, f5);
    }
}

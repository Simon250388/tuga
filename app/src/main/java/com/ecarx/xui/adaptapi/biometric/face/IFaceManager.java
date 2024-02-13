package com.ecarx.xui.adaptapi.biometric.face;

import android.os.CancellationSignal;
import android.os.Handler;
import java.util.List;

/* loaded from: classes.dex */
public interface IFaceManager {

    /* loaded from: classes.dex */
    public interface AuthenticationCallback {
        void onAuthenticationAcquired(int i);

        void onAuthenticationError(int i, CharSequence charSequence);

        void onAuthenticationFailed();

        void onAuthenticationHelp(int i, CharSequence charSequence);

        void onAuthenticationSucceeded(AuthenticationResult authenticationResult);
    }

    /* loaded from: classes.dex */
    public interface AuthenticationResult extends FaceResult {
    }

    /* loaded from: classes.dex */
    public interface EnrollmentCallback {
        void onEnrollmentError(int i, CharSequence charSequence);

        void onEnrollmentHelp(int i, CharSequence charSequence);

        void onEnrollmentProgress(int i);

        void onEnrollmentSucceeded(EnrollmentResult enrollmentResult);
    }

    /* loaded from: classes.dex */
    public interface EnrollmentResult extends FaceResult {
    }

    /* loaded from: classes.dex */
    public interface FaceResult {
        IFace getFace();

        int getUserId();

        boolean isStrongBiometric();
    }

    /* loaded from: classes.dex */
    public interface RemovalCallback {
        void onRemovalError(IFace iFace, int i, CharSequence charSequence);

        void onRemovalSucceeded(IFace iFace, int i);
    }

    void authenticate(CancellationSignal cancellationSignal, int i, AuthenticationCallback authenticationCallback, Handler handler);

    void enroll(byte[] bArr, CancellationSignal cancellationSignal, EnrollmentCallback enrollmentCallback);

    List<IFace> getEnrolledFaces();

    boolean isHardwareDetected();

    void remove(IFace iFace, RemovalCallback removalCallback);
}

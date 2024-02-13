package com.ecarx.xui.adaptapi.car.userprofile;

import android.os.Bundle;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface IUserProfile {
    public static final int ACTION_TYPE_FACE_BIND = 33;
    public static final int ACTION_TYPE_FACE_UNBIND = 34;
    public static final int ACTION_TYPE_PROFILE_ADD = 1;
    public static final int ACTION_TYPE_PROFILE_APPLY = 6;
    public static final int ACTION_TYPE_PROFILE_BIND = 17;
    public static final int ACTION_TYPE_PROFILE_LOGIN = 3;
    public static final int ACTION_TYPE_PROFILE_LOGOUT = 4;
    public static final int ACTION_TYPE_PROFILE_REMOVE = 2;
    public static final int ACTION_TYPE_PROFILE_RESET = 7;
    public static final int ACTION_TYPE_PROFILE_SWITCH = 5;
    public static final int ACTION_TYPE_PROFILE_UNBIND = 18;
    public static final int ACTION_TYPE_PROFILE_UNBIND_RESET = 19;
    public static final int ERROR_CODE_TIMEOUT = 1;
    public static final int ERROR_CODE_UNKNOWN = 0;
    public static final int STATUS_FAILED = 4;
    public static final int STATUS_PREPARE = 1;
    public static final int STATUS_PROGRESS = 2;
    public static final int STATUS_SUCCEED = 3;
    public static final int STATUS_UNKNOWN = 0;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ActionStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ActionType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ErrorCode {
    }

    /* loaded from: classes.dex */
    public interface IUserProfileObserver {
        void onUserProfileActionError(int i, int i2);

        void onUserProfileActionStatus(int i, int i2, int i3);

        void onUserProfileAdded(int i);
    }

    int addUserProfile();

    boolean applyUserProfileData(int i, IProfile iProfile);

    int getCurrentId();

    int getProfileId(String str);

    String getUserId(int i);

    IProfile getUserProfileData(int i);

    boolean loginUserProfile(int i);

    boolean logoutUserProfile(int i);

    boolean notifyUIDInfoToProfile(int i, String str, Bundle bundle);

    boolean registerUserProfileObserver(IUserProfileObserver iUserProfileObserver);

    boolean removeUserProfile(int i);

    boolean resetUserProfileDataDefault(int i);

    boolean switchUserProfile(int i, int i2);

    boolean unegisterUserProfileObserver(IUserProfileObserver iUserProfileObserver);
}

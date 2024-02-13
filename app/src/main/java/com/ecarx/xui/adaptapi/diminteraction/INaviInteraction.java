package com.ecarx.xui.adaptapi.diminteraction;

import android.graphics.Bitmap;
import android.os.Bundle;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/* loaded from: classes.dex */
public interface INaviInteraction {
    public static final int ACTION_ADD_WAY_POINT = 7;
    public static final int ACTION_AUTO_ZOOM_OFF = 18;
    public static final int ACTION_AUTO_ZOOM_ON = 17;
    public static final int ACTION_CANCEL_MUTE = 4;
    public static final int ACTION_CANCEL_NAVIGATION = 2;
    public static final int ACTION_DIM_DISPLAY_AR = 36;
    public static final int ACTION_DIM_DISPLAY_FULL = 35;
    public static final int ACTION_DIM_DISPLAY_OFF = 33;
    public static final int ACTION_DIM_DISPLAY_SIMPLIFY = 34;
    public static final int ACTION_GET_FAVORITE = 5;
    public static final int ACTION_GET_HISTORY = 6;
    public static final int ACTION_MUTE = 3;
    public static final int ACTION_NEARBY_GAS_CHARGING_STATION = 8;
    public static final int ACTION_START_NAVIGATION = 1;
    public static final int ACTION_ZOOM_IN = 19;
    public static final int ACTION_ZOOM_OUT = 20;
    public static final int DIRECTION_EAST = 0;
    public static final int DIRECTION_EAST_NORTH = 4;
    public static final int DIRECTION_EAST_SOUTH = 5;
    public static final int DIRECTION_NORTH = 3;
    public static final int DIRECTION_SOUTH = 1;
    public static final int DIRECTION_WEST = 2;
    public static final int DIRECTION_WEST_NORTH = 6;
    public static final int DIRECTION_WEST_SOUTH = 7;
    public static final String EXT_KEY_DIM_DISPLAY_MODE = "EXT_KEY_DIM_DISPLAY_MODE";
    public static final int NAVIGATION_STATUS_END = 3;
    public static final int NAVIGATION_STATUS_REROUTING = 4;
    public static final int NAVIGATION_STATUS_START = 2;
    public static final int NAVIGATION_STATUS_SUCCEED = 1;
    public static final int NAVIGATION_STATUS_TUNNEL_END = 6;
    public static final int NAVIGATION_STATUS_TUNNEL_ENTER = 5;
    public static final int NAVIGATION_STATUS_UNNAVI = 0;
    public static final int TYPE_DEFAULT = 0;
    public static final int TYPE_DESTINATION = 3;
    public static final int TYPE_FAVORITE = 1;
    public static final int TYPE_HISTORY = 2;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ActionType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AddressType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Direction {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ExtensionKey {
    }

    /* loaded from: classes.dex */
    public interface IAddress {
        String getAddressName();

        String getCityName();

        long getDistance();

        String getFullAddress();

        double getLatitude();

        double getLongitude();
    }

    /* loaded from: classes.dex */
    public interface IHighwayExitInfo {
        int getEtaDistance();

        int getEtaTime();

        String getExitDirection();

        String getExitNumber();
    }

    /* loaded from: classes.dex */
    public interface ILaneInfo {
        Bitmap getLaneBackIcon();

        int getLaneBackIconId();

        int getLaneNumber();
    }

    /* loaded from: classes.dex */
    public interface INavigationInfo {
        int getDayNightMode();

        long getDistanceToDestination();

        long getDistanceToNextGuidancePoint();

        int getDrivingDirection();

        long getETA();

        IHighwayExitInfo getHighwayExitInfo();

        ILaneInfo[] getLaneInfo();

        int getMuteState();

        int getNavigationStatus();

        int getNavigationTurnId();

        String getNavigationTurnSVG();

        String getNextGuidancePointName();

        IRoadCamera getRoadCameraInfo();

        IServiceArea getServiceAreaInfo();
    }

    /* loaded from: classes.dex */
    public interface INavigationInteractionCallback {
        void onDoInteractionAction(int i, IAddress iAddress);

        void onSearchAddress(String str);
    }

    /* loaded from: classes.dex */
    public interface IRoadCamera {
        public static final int TYPE_AGAINST_RULES = 3;
        public static final int TYPE_BICYCLE_LANE = 7;
        public static final int TYPE_BUS_LANE = 4;
        public static final int TYPE_EMERGENCY_LANE = 5;
        public static final int TYPE_MONITOR = 1;
        public static final int TYPE_RAD_LIGHT = 2;
        public static final int TYPE_SPEED = 0;
        public static final int TYPE_TAKE_PHOTO = 6;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes.dex */
        public @interface CameraType {
        }

        int getAreaDistance();

        int getCameraType();

        int getLimitSpeed();
    }

    /* loaded from: classes.dex */
    public interface IServiceArea {
        int getAreaDistance();

        String getAreaName();

        int getAreaType();

        int getEtaTime();
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface NavigationStatus {
    }

    void notifyDestinationReached(String str, int i);

    void notifyDestinationReached(String str, Bitmap bitmap);

    void notifyNavigationStatus(int i);

    void notifyTurnByTurnStarted();

    void notifyTurnByTurnStopped();

    void registerNavigationInteractionCallback(INavigationInteractionCallback iNavigationInteractionCallback);

    void unregisterNavigationInteractionCallback(INavigationInteractionCallback iNavigationInteractionCallback);

    void updateAddresses(int i, List<IAddress> list);

    void updateDayNightMode(int i);

    void updateDistanceToDestination(int i);

    void updateDistanceToNextGuidancePoint(int i);

    void updateDriveDirection(int i);

    void updateETA(int i);

    void updateExtensionInfo(Bundle bundle);

    void updateHighwayExitInfo(IHighwayExitInfo iHighwayExitInfo);

    void updateLaneInfo(ILaneInfo[] iLaneInfoArr);

    void updateMuteState(int i);

    void updateNavigationInfo(INavigationInfo iNavigationInfo);

    void updateNextGuidancePointName(String str);

    void updateRoadCameraInfo(IRoadCamera iRoadCamera);

    void updateSearchAddresses(int i, List<IAddress> list, String str);

    void updateServiceAreaInfo(IServiceArea iServiceArea);

    void updateTurnByTurnArrow(int i);

    void updateTurnByTurnArrow(Bitmap bitmap);

    void updateTurnByTurnArrow(String str);
}

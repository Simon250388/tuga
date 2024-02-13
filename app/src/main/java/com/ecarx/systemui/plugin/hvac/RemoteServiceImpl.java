package com.ecarx.systemui.plugin.hvac;

import android.util.Log;

import com.ecarx.hvac.app.NaviBarTempBean;
import com.ecarx.xui.adaptapi.FunctionStatus;


/* loaded from: classes.dex */
public class RemoteServiceImpl {
    private static final String TAG = "RemoteServiceImpl";
    private static volatile RemoteServiceImpl instance;
    private TempCallback mTempCallback;
    private float minTemp = 15.5f;
    private float maxTemp = 28.5f;

    /* loaded from: classes.dex */
    public interface TempCallback {
        void initTempData(NaviBarTempBean naviBarTempBean);

        void onTempChange(NaviBarTempBean naviBarTempBean);
    }

    public static RemoteServiceImpl getInstance() {
        if (instance == null) {
            synchronized (RemoteServiceImpl.class) {
                if (instance == null) {
                    instance = new RemoteServiceImpl();
                }
            }
        }
        return instance;
    }

    private RemoteServiceImpl() {
    }

    public synchronized void getTempData() {
        HvacManager hvacManager = HvacManager.getInstance();
        BaseResult isSupportTemperature = hvacManager.isSupportTemperature(1);
        BaseResult isSupportTemperature2 = hvacManager.isSupportTemperature(4);
        BaseResult isSupportFan = hvacManager.isSupportFan();
        BaseResult isSupportAutoFan = hvacManager.isSupportAutoFan();
        float leftTemperature = hvacManager.getLeftTemperature();
        float rightTemperature = hvacManager.getRightTemperature();
        this.minTemp = hvacManager.getMinTemperature();
        this.maxTemp = hvacManager.getMaxTemperature();
        Log.d(TAG, "leftTemperature==" + leftTemperature);
        Log.d(TAG, "rightTemperature==" + rightTemperature);
        NaviBarTempBean naviBarTempBean = new NaviBarTempBean();
        naviBarTempBean.setMinTemp(this.minTemp);
        naviBarTempBean.setMaxTemp(this.maxTemp);
        if (isSupportTemperature.functionStatus != null) {
            naviBarTempBean.setLeftTempStatus(isSupportTemperature.functionStatus.name());
        }
        if (isSupportTemperature2.functionStatus != null) {
            naviBarTempBean.setRightTempStatus(isSupportTemperature2.functionStatus.name());
        }
        if (isSupportFan.functionStatus != null) {
            naviBarTempBean.setFanStatus(isSupportFan.functionStatus.name());
        }
        if (isSupportAutoFan.functionStatus != null) {
            naviBarTempBean.setFanStatus(isSupportAutoFan.functionStatus.name());
        }
        naviBarTempBean.setLeftTemperature(leftTemperature >= this.maxTemp ? this.maxTemp : Math.max(leftTemperature, this.minTemp));
        if (isSupportTemperature2.functionStatus != FunctionStatus.notavailable) {
            naviBarTempBean.setRightTemperature(rightTemperature >= this.maxTemp ? this.maxTemp : Math.max(rightTemperature, this.minTemp));
        }
        if (isSupportFan.functionStatus != FunctionStatus.notavailable || isSupportAutoFan.functionStatus != FunctionStatus.notavailable) {
            getBlowingLevel(naviBarTempBean, hvacManager);
        }
        if (this.mTempCallback != null) {
            this.mTempCallback.initTempData(naviBarTempBean);
        }
    }

    private void getBlowingLevel(NaviBarTempBean naviBarTempBean, HvacManager hvacManager) {
        int autoFanLevel;
        if (hvacManager.getAutoFanLevel(8) == 255) {
            autoFanLevel = hvacManager.getFanLevel(8);
            naviBarTempBean.setMaxLevel(hvacManager.getFanMaxLevel());
            naviBarTempBean.setFanStatus(hvacManager.isSupportFan().functionStatus.name());
        } else {
            autoFanLevel = hvacManager.getAutoFanLevel(8);
            naviBarTempBean.setMaxLevel(hvacManager.getAutoFanMaxLevel());
            naviBarTempBean.setFanStatus(hvacManager.isSupportAutoFan().functionStatus.name());
        }
        naviBarTempBean.setLevel(autoFanLevel);
    }

    public synchronized void tempChange(int i) {
        NaviBarTempBean naviBarTempBean = new NaviBarTempBean();
        HvacManager hvacManager = HvacManager.getInstance();
        naviBarTempBean.setFucntionId(i);
        FunctionStatus functionStatus = hvacManager.isSupportTemperature(1).functionStatus;
        Log.i(TAG, "tempChange>>>leftTempStatus:" + functionStatus.name());
        naviBarTempBean.setLeftTempStatus(functionStatus.name());
        this.minTemp = hvacManager.getMinTemperature();
        this.maxTemp = hvacManager.getMaxTemperature();
        if (functionStatus != FunctionStatus.notavailable) {
            float leftTemperature = hvacManager.getLeftTemperature();
            naviBarTempBean.setLeftTemperature(leftTemperature >= this.maxTemp ? this.maxTemp : Math.max(leftTemperature, this.minTemp));
        }
        FunctionStatus functionStatus2 = hvacManager.isSupportTemperature(4).functionStatus;
        Log.i(TAG, "tempChange>>>rightTempStatus:" + functionStatus2.name());
        naviBarTempBean.setRightTempStatus(functionStatus2.name());
        if (functionStatus2 != FunctionStatus.notavailable) {
            float rightTemperature = hvacManager.getRightTemperature();
            naviBarTempBean.setRightTemperature(rightTemperature >= this.maxTemp ? this.maxTemp : Math.max(rightTemperature, this.minTemp));
        }
        Log.i(TAG, "tempChange>>>minTemp:" + this.minTemp);
        Log.i(TAG, "tempChange>>>maxTemp:" + this.maxTemp);
        naviBarTempBean.setMaxTemp(this.maxTemp);
        naviBarTempBean.setMinTemp(this.minTemp);
        FunctionStatus functionStatus3 = hvacManager.isSupportFan().functionStatus;
        FunctionStatus functionStatus4 = hvacManager.isSupportAutoFan().functionStatus;
        Log.i(TAG, "tempChange>>>fanStatus:" + functionStatus3.name());
        Log.i(TAG, "tempChange>>>autoFanStatus:" + functionStatus4.name());
        if (functionStatus3 != FunctionStatus.notavailable || functionStatus4 != FunctionStatus.notavailable) {
            naviBarTempBean.setLevel(getBlowingLevel());
            naviBarTempBean.setMaxLevel(getBlowingMaxLevel());
        }
        if (hvacManager.getAutoFanLevel(8) == 255) {
            naviBarTempBean.setFanStatus(hvacManager.isSupportFan().functionStatus.name());
        } else {
            naviBarTempBean.setFanStatus(hvacManager.isSupportAutoFan().functionStatus.name());
        }
        if (this.mTempCallback != null) {
            this.mTempCallback.onTempChange(naviBarTempBean);
        }
    }

    public int getBlowingLevel() {
        int autoFanLevel = HvacManager.getInstance().getAutoFanLevel(8);
        Log.d(TAG, "refreshBlowingLevel blowerLevel:" + autoFanLevel);
        if (autoFanLevel == 255) {
            return HvacManager.getInstance().getFanLevel(8);
        }
        return HvacManager.getInstance().getAutoFanLevel(8);
    }

    public int getBlowingMaxLevel() {
        int autoFanLevel = HvacManager.getInstance().getAutoFanLevel(8);
        Log.d(TAG, "getBlowingMaxLevel:" + autoFanLevel);
        return autoFanLevel == 255 ? 9 : 5;
    }

    public void setTempCallback(TempCallback tempCallback) {
        this.mTempCallback = tempCallback;
    }
}

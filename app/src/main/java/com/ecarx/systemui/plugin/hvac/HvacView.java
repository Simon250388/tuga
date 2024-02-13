package com.ecarx.systemui.plugin.hvac;

import android.content.Context;
import android.content.res.Configuration;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecarx.hvac.app.NaviBarTempBean;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.utils.AlphaImageView;


/* loaded from: classes.dex */
public class HvacView extends RelativeLayout {
    private static final String TAG = HvacView.class.getSimpleName();
    private int[] mAutoFanIds;
    private Context mContext;
    private AlphaImageView mFanLevel;
    private int mLevel;
    private int[] mManualFanIds;
    private TextView mTempText;
    private TextView mTempTextUnit;
    private int maxLevel;
    private String tempMax;
    private String tempMin;

    public HvacView(Context context) {
        super(context);
        this.mManualFanIds = new int[]{R.drawable.kx11_max_air_quantity_0, R.drawable.kx11_max_air_quantity_1, R.drawable.kx11_max_air_quantity_2, R.drawable.kx11_max_air_quantity_3, R.drawable.kx11_max_air_quantity_4, R.drawable.kx11_max_air_quantity_5, R.drawable.kx11_max_air_quantity_6, R.drawable.kx11_max_air_quantity_7, R.drawable.kx11_max_air_quantity_8};
        this.mAutoFanIds = new int[]{R.drawable.kx11_air_quantity_0, R.drawable.kx11_air_quantity_1, R.drawable.kx11_air_quantity_2, R.drawable.kx11_air_quantity_3, R.drawable.kx11_air_quantity_4};
        this.tempMax = "28.5";
        this.tempMin = "15.5";
        this.mContext = context;
    }

    public HvacView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mManualFanIds = new int[]{R.drawable.kx11_max_air_quantity_0, R.drawable.kx11_max_air_quantity_1, R.drawable.kx11_max_air_quantity_2, R.drawable.kx11_max_air_quantity_3, R.drawable.kx11_max_air_quantity_4, R.drawable.kx11_max_air_quantity_5, R.drawable.kx11_max_air_quantity_6, R.drawable.kx11_max_air_quantity_7, R.drawable.kx11_max_air_quantity_8};
        this.mAutoFanIds = new int[]{R.drawable.kx11_air_quantity_0, R.drawable.kx11_air_quantity_1, R.drawable.kx11_air_quantity_2, R.drawable.kx11_air_quantity_3, R.drawable.kx11_air_quantity_4};
        this.tempMax = "28.5";
        this.tempMin = "15.5";
        this.mContext = context;
    }

    public HvacView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mManualFanIds = new int[]{R.drawable.kx11_max_air_quantity_0, R.drawable.kx11_max_air_quantity_1, R.drawable.kx11_max_air_quantity_2, R.drawable.kx11_max_air_quantity_3, R.drawable.kx11_max_air_quantity_4, R.drawable.kx11_max_air_quantity_5, R.drawable.kx11_max_air_quantity_6, R.drawable.kx11_max_air_quantity_7, R.drawable.kx11_max_air_quantity_8};
        this.mAutoFanIds = new int[]{R.drawable.kx11_air_quantity_0, R.drawable.kx11_air_quantity_1, R.drawable.kx11_air_quantity_2, R.drawable.kx11_air_quantity_3, R.drawable.kx11_air_quantity_4};
        this.tempMax = "28.5";
        this.tempMin = "15.5";
        this.mContext = context;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initView();
    }

    public void setData(final boolean z, final NaviBarTempBean naviBarTempBean) {
        post(new Runnable() { // from class: com.ecarx.systemui.plugin.hvac.-$$Lambda$HvacView$4NN3lId2AGeBhS7Ycwh892-rdlE
            @Override // java.lang.Runnable
            public final void run() {
                HvacView.this.lambda$setData$0$HvacView(naviBarTempBean, z);
            }
        });
    }

    public /* synthetic */ void lambda$setData$0$HvacView(NaviBarTempBean naviBarTempBean, boolean z) {
        this.tempMin = String.format("%.1f", Float.valueOf(naviBarTempBean.getMinTemp()));
        this.tempMax = String.format("%.1f", Float.valueOf(naviBarTempBean.getMaxTemp()));
        if (z) {
            String leftTempStatus = naviBarTempBean.getLeftTempStatus();
            float leftTemperature = naviBarTempBean.getLeftTemperature();
            setViewStateTemp(this.mTempText, leftTempStatus, naviBarTempBean);
            setViewStateTemp(this.mTempTextUnit, leftTempStatus, naviBarTempBean);
            String str = TAG;
            Log.d(str, "setVisible isleft==" + leftTempStatus);
            setVisibility(View.VISIBLE);
            if (leftTemperature > 0.0f) {
                String str2 = TAG;
                Log.d(str2, "setData isleft==" + leftTemperature);
                this.mTempText.setText(getTempValue(leftTemperature));
                this.mTempText.setTextColor(getResources().getColorStateList(R.color.temp_tv_color));
            }
        } else {
            String rightTempStatus = naviBarTempBean.getRightTempStatus();
            float rightTemperature = naviBarTempBean.getRightTemperature();
            setViewStateTemp(this.mTempText, rightTempStatus, naviBarTempBean);
            setViewStateTemp(this.mTempTextUnit, rightTempStatus, naviBarTempBean);
            String str3 = TAG;
            Log.d(str3, "setVisible isRight==" + rightTempStatus);
            setVisible(rightTempStatus);
            if (rightTemperature > 0.0f) {
                String str4 = TAG;
                Log.d(str4, "setData isRight==" + rightTemperature);
                this.mTempText.setText(getTempValue(rightTemperature));
            }
        }
        String fanStatus = naviBarTempBean.getFanStatus();
        this.mLevel = naviBarTempBean.getLevel();
        this.maxLevel = naviBarTempBean.getMaxLevel();
        setViewState(this.mFanLevel, fanStatus);
        setFanLevel(this.mLevel, this.maxLevel);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVisible(String str) {
        if ("notavailable".equals(str)) {
            setVisibility(View.GONE);
        } else {
            setVisibility(View.VISIBLE);
        }
    }

    public void refreshData(final boolean z, final NaviBarTempBean naviBarTempBean) {
        post(new Runnable() { // from class: com.ecarx.systemui.plugin.hvac.HvacView.1
            /* JADX WARN: Removed duplicated region for block: B:16:0x00a2  */
            /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:8:0x005e  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 296
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.ecarx.systemui.plugin.hvac.HvacView.AnonymousClass1.run():void");
            }
        });
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Log.d(TAG, "onConfigurationChanged");
        this.mTempText.setTextColor(getResources().getColorStateList(R.color.temp_tv_color));
        this.mTempTextUnit.setTextColor(getResources().getColorStateList(R.color.temp_tv_color));
        setFanLevel(this.mLevel, this.maxLevel);
    }

    private void initView() {
        this.mTempText = (TextView) findViewById(R.id.text_temp);
        this.mFanLevel = (AlphaImageView) findViewById(R.id.iv_fan_level);
        this.mTempTextUnit = (TextView) findViewById(R.id.text_temp_unit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFanLevel(int i, int i2) {
        String str = TAG;
        Log.i(str, "level:" + i + ";maxLevel:" + i2);
        if (i < 0 || i2 <= 0) {
            return;
        }
        if (i == 0) {
            this.mFanLevel.setVisibility(View.INVISIBLE);
            this.mTempText.setAlpha(0.4f);
            this.mTempTextUnit.setAlpha(0.4f);
            return;
        }
        this.mFanLevel.setVisibility(View.VISIBLE);
        this.mTempText.setAlpha(1.0f);
        this.mTempTextUnit.setAlpha(1.0f);
        if (i2 == 5) {
            this.mFanLevel.setImageResource(this.mAutoFanIds[i - 1]);
        } else {
            this.mFanLevel.setImageResource(this.mManualFanIds[i - 1]);
        }
    }

    public void setViewState(View view, String str) {
        if (str != null) {
            setEnable(view, str.equals("active"));
        }
    }

    public void setViewStateTemp(View view, String str, NaviBarTempBean naviBarTempBean) {
        if (str != null) {
            if (naviBarTempBean.getLevel() <= 0) {
                setEnable(view, false);
            } else {
                setEnable(view, str.equals("active"));
            }
        }
    }

    public void setEnable(View view, boolean z) {
        view.setEnabled(z);
        view.setAlpha(z ? 1.0f : 0.4f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SpannableString getTempValue(float f) {
        String str = TAG;
        Log.i(str, "getTempValue = " + f);
        String valueOf = String.valueOf(f);
        SpannableString spannableString = new SpannableString(valueOf);
        if (valueOf.equals(this.tempMax)) {
            this.mTempTextUnit.setVisibility(View.GONE);
            return SpannableString.valueOf(this.mContext.getResources().getString(R.string.hi));
        } else if (valueOf.equals(this.tempMin)) {
            this.mTempTextUnit.setVisibility(View.GONE);
            return SpannableString.valueOf(this.mContext.getResources().getString(R.string.LO));
        } else {
            spannableString.setSpan(new AbsoluteSizeSpan(20), valueOf.length() - 2, valueOf.length(), 17);
            this.mTempTextUnit.setVisibility(View.VISIBLE);
            return spannableString;
        }
    }
}

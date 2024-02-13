package com.ecarx.systemui.plugin.hvac;

import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.statusbar.StatusBar;
import com.ecarx.xui.adaptapi.device.Device;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class PcodeManager {
    private static final String FUNCTIONID = "functionId";
    private static final String FUNCTIONNAME = "functionName";
    private static final String PREFIX = "prefix";
    private static final String PRIORITY = "Priority";
    private static final String TAG = PcodeManager.class.getSimpleName();
    private static final int XML_PATH = 2131558401;
    private BaseResult hvacResult;
    private SparseArray<ArrayList<BaseResult>> mFunctionPCodeMap;
    private String pCode;

    public static PcodeManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /* loaded from: classes.dex */
    private static class SingletonHolder {
        private static PcodeManager INSTANCE = new PcodeManager();

        private SingletonHolder() {
        }
    }

    public void setDevice(Device device) {
        this.pCode = getPCode(device);
        this.mFunctionPCodeMap = parseXMLWithPull();
    }

    private String getPCode(Device device) {
        try {
            String projectCode = device.getProjectCode();
            String str = TAG;
            Log.d(str, "getPCode = " + projectCode);
            return TextUtils.isEmpty(projectCode) ? HvacConfig.XE38_31D : projectCode;
        } catch (Exception e) {
            String str2 = TAG;
            Log.d(str2, "getPCode e:" + e.getMessage());
            return HvacConfig.XE38_31D;
        }
    }

    private SparseArray<ArrayList<BaseResult>> parseXMLWithPull() {
        SparseArray<ArrayList<BaseResult>> sparseArray = new SparseArray<>();
        ArrayList<BaseResult> arrayList = new ArrayList<>();
        BaseResult baseResult = new BaseResult();
        try {
            XmlResourceParser xml = StatusBar.getInstance(null).getRootView().getContext().getResources().getXml(R.xml.functionid_pcode);
            int i = -1;
            String str = "";
            while (xml.getEventType() != 1) {
                int eventType = xml.getEventType();
                if (eventType == 2) {
                    String name = xml.getName();
                    if (PRIORITY.equals(name)) {
                        String attributeValue = xml.getAttributeValue(null, FUNCTIONNAME);
                        str = attributeValue;
                        i = Integer.parseInt(xml.getAttributeValue(null, FUNCTIONID));
                    } else if (HvacConfig.PCODE.equals(name)) {
                        baseResult = new BaseResult();
                        baseResult.functionName = str;
                        baseResult.functionId = i;
                        baseResult.prefix = xml.getAttributeValue(null, PREFIX);
                        baseResult.pcode = xml.nextText();
                        String str2 = TAG;
                        Log.d(str2, "   functionName:" + baseResult.functionName + "\n  functionId:" + baseResult.functionId + "\n  prefix:" + baseResult.prefix + "\n  PCode:" + baseResult.pcode);
                        arrayList.add(baseResult);
                    }
                } else if (eventType == 3 && PRIORITY.equals(xml.getName())) {
                    sparseArray.put(baseResult.functionId, arrayList);
                }
                xml.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
        return sparseArray;
    }

    public BaseResult isFunctionSupport(int i) {
        if (this.hvacResult == null) {
            BaseResult baseResult = new BaseResult();
            this.hvacResult = baseResult;
            baseResult.isFunctionSupport = false;
            ArrayList<BaseResult> arrayList = this.mFunctionPCodeMap.get(i);
            if (arrayList == null) {
                Log.d(TAG, "isFunctionSupport list is null");
                return this.hvacResult;
            }
            String str = TAG;
            Log.d(str, "isFunctionSupport list:" + arrayList);
            Iterator<BaseResult> it = arrayList.iterator();
            while (it.hasNext()) {
                BaseResult next = it.next();
                if (next.pcode.equalsIgnoreCase(this.pCode)) {
                    this.hvacResult = next;
                    return next;
                }
            }
            this.hvacResult.prefix = HvacConfig.KX11;
            this.hvacResult.pcode = HvacConfig.XE38_31D;
        }
        String str2 = TAG;
        Log.i(str2, "hvacResult = " + this.hvacResult);
        return this.hvacResult;
    }
}

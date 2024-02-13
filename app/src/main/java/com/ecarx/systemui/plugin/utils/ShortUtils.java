package com.ecarx.systemui.plugin.utils;

import android.content.Context;
import java.util.Collection;
import java.util.List;

/* loaded from: classes.dex */
public class ShortUtils {
    public static void addShortListToDb(Context context, String str, String str2, String str3) {
//        SQLiteDB dbUtils = DbUtils.getInstance(context);
//        List queryAll = dbUtils.queryAll(ShortCutBean.class);
//        if (!isExistForDb(queryAll, str2, str3)) {
//            queryAll.remove(0);
//        } else {
//            for (int i = 0; i < queryAll.size(); i++) {
//                if (((ShortCutBean) queryAll.get(i)).getPageName().equals(str2) && ((ShortCutBean) queryAll.get(i)).getCpmName().equals(str3)) {
//                    queryAll.remove(i);
//                }
//            }
//        }
//        ShortCutBean shortCutBean = new ShortCutBean();
//        shortCutBean.setDrawableID(-1);
//        shortCutBean.setName(str);
//        shortCutBean.setPageName(str2);
//        shortCutBean.setCpmName(str3);
//        shortCutBean.setFunctionID("FunctionID_0033");
//        queryAll.add(shortCutBean);
//        dbUtils.deleteAll(ShortCutBean.class);
//        dbUtils.save((Collection) queryAll);
    }

//    private static boolean isExistForDb(List<ShortCutBean> list, String str, String str2) {
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).getPageName().equals(str) && list.get(i).getCpmName().equals(str2)) {
//                return true;
//            }
//        }
//        return false;
//    }
}

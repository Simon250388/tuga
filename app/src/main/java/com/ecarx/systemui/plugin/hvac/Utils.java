package com.ecarx.systemui.plugin.hvac;

import java.util.Set;

/* loaded from: classes.dex */
public class Utils {
    public static int[] SetToInt(Set<Integer> set) {
        Integer[] numArr = (Integer[]) set.toArray(new Integer[0]);
        int[] iArr = new int[numArr.length];
        for (int i = 0; i < numArr.length; i++) {
            iArr[i] = numArr[i].intValue();
        }
        return iArr;
    }
}

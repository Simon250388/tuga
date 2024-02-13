package com.ecarx.systemui.plugin.psd;

import android.content.ComponentName;
import android.content.res.Configuration;

/* loaded from: classes.dex */
public class StackInfo {
    public final Configuration configuration = new Configuration();
    public int displayId;
    public int position;
    public int stackId;
    public int[] taskIds;
    public String[] taskNames;
    public int[] taskUserIds;
    public ComponentName topActivity;
    public int userId;
    public boolean visible;

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Stack id=");
        stringBuffer.append(this.stackId);
        stringBuffer.append(" displayId=");
        stringBuffer.append(this.displayId);
        stringBuffer.append(" visible=");
        stringBuffer.append(this.visible);
        stringBuffer.append(" position=");
        stringBuffer.append(this.position);
        for (int i = 0; i < this.taskNames.length; i++) {
            stringBuffer.append(" taskName[");
            stringBuffer.append(i);
            stringBuffer.append("]:");
            stringBuffer.append(this.taskNames[i]);
            if (this.topActivity != null) {
                stringBuffer.append(" topActivity=");
                stringBuffer.append(this.topActivity);
            }
        }
        stringBuffer.append("\n");
        return stringBuffer.toString();
    }
}

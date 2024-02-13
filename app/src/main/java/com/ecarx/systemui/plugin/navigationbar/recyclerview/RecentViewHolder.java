package com.ecarx.systemui.plugin.navigationbar.recyclerview;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.ecarx.systemui.plugin.NoDoubleClickListener;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.navigationbar.RecentIconView;
import com.ecarx.systemui.plugin.utils.Lg;
import com.ecarx.systemui.plugin.utils.StatisticsUtil;

import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class RecentViewHolder extends RecyclerView.ViewHolder {
    private RecentIconView recentIconIv;

    public RecentViewHolder(View view) {
        super(view);
        this.recentIconIv = view.findViewById(R.id.item_recent_icon_view);
    }

    public static RecentViewHolder create(ViewGroup viewGroup) {
        return new RecentViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recent_layout, viewGroup, false));
    }

    public void bind(final String str) {
        this.recentIconIv.updateView(str);
        this.recentIconIv.setOnClickListener(new NoDoubleClickListener() { // from class: com.ecarx.systemui.plugin.navigationbar.recyclerview.RecentViewHolder.1
            @Override // com.ecarx.systemui.plugin.NoDoubleClickListener
            public void onNoDoubleClick(View view) {
                Lg.i("onClick:" + str);
                RecentViewHolder recentViewHolder = RecentViewHolder.this;
                recentViewHolder.startApp(recentViewHolder.itemView.getContext(), str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startApp(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            Lg.e("pkg " + str + " not found ", e);
            packageInfo = null;
        }
        if (packageInfo == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.MAIN", null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(packageInfo.packageName);
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities == null || queryIntentActivities.isEmpty() || queryIntentActivities.get(0) == null) {
            return;
        }
        ResolveInfo resolveInfo = queryIntentActivities.get(0);
        if (packageInfo.packageName.equals("com.ecarx.xiaoka")) {
            Iterator<ResolveInfo> it = queryIntentActivities.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ResolveInfo next = it.next();
                if (next.activityInfo.name.equals("com.ecarx.magicbook.ui.MagicActivity")) {
                    resolveInfo = next;
                    break;
                }
            }
        }
        String str2 = resolveInfo.activityInfo.packageName;
        @SuppressLint("WrongConstant") Intent flags = new Intent("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER").setComponent(new ComponentName(str2, resolveInfo.activityInfo.name)).setFlags(270532608);
        String charSequence = resolveInfo.activityInfo.loadLabel(context.getPackageManager()).toString();
        Lg.i("RecentView", "[JRSYS][startApp], title: " + charSequence);
        StatisticsUtil.recoardSensorsNumberString(StatisticsUtil.SENSORS_EVENT_LASTAPP_CLK, null, new StatisticsUtil.ValueEntry(StatisticsUtil.SENSORS_VALUE_APP_NAME, charSequence));
        try {
            context.startActivity(flags);
        } catch (Exception e2) {
            Lg.e("pkg " + str2 + " start error ", e2);
        }
    }
}

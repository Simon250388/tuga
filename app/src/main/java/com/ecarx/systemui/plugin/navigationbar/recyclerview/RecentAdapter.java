package com.ecarx.systemui.plugin.navigationbar.recyclerview;


import android.view.ViewGroup;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/* loaded from: classes.dex */
public class RecentAdapter extends RecyclerView.Adapter<RecentViewHolder> {
    private List<String> packageNameList;

    public RecentAdapter(List<String> list) {
        this.packageNameList = list;
    }

    public void refresh(List<String> list) {
        if (list == null) {
            List<String> list2 = this.packageNameList;
            if (list2 != null) {
                list2.clear();
            }
            notifyDataSetChanged();
            return;
        }
        DiffUtil.DiffResult calculateDiff = DiffUtil.calculateDiff(new AppItemDiffUtil(this.packageNameList, list), false);
        this.packageNameList = list;
        calculateDiff.dispatchUpdatesTo(this);
    }

    public void forceRefresh() {
        notifyDataSetChanged();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public RecentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return RecentViewHolder.create(viewGroup);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecentViewHolder recentViewHolder, int i) {
        List<String> list = this.packageNameList;
        if (list != null) {
            recentViewHolder.bind(list.get(i));
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<String> list = this.packageNameList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}

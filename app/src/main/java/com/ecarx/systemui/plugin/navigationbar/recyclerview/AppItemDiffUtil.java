package com.ecarx.systemui.plugin.navigationbar.recyclerview;


import android.text.TextUtils;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

/* loaded from: classes.dex */
public class AppItemDiffUtil extends DiffUtil.Callback {
    private List<String> mNewList;
    private List<String> mOldList;

    @Override // android.support.v7.util.DiffUtil.Callback
    public boolean areContentsTheSame(int i, int i2) {
        return false;
    }

    public AppItemDiffUtil(List<String> list, List<String> list2) {
        this.mOldList = list;
        this.mNewList = list2;
    }

    @Override // android.support.v7.util.DiffUtil.Callback
    public int getOldListSize() {
        List<String> list = this.mOldList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.support.v7.util.DiffUtil.Callback
    public int getNewListSize() {
        List<String> list = this.mNewList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.support.v7.util.DiffUtil.Callback
    public boolean areItemsTheSame(int i, int i2) {
        List<String> list = this.mOldList;
        if (list == null || this.mNewList == null) {
            return false;
        }
        return TextUtils.equals(list.get(i), this.mNewList.get(i2));
    }
}

package com.ecarx.systemui.plugin.navigationbar;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class IShortCut implements Parcelable {
    public static final Parcelable.Creator<IShortCut> CREATOR = new Parcelable.Creator<IShortCut>() { // from class: com.ecarx.systemui.plugin.navigationbar.IShortCut.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IShortCut createFromParcel(Parcel parcel) {
            return new IShortCut(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IShortCut[] newArray(int i) {
            return new IShortCut[i];
        }
    };
    public static final String EXTRA_USER_IMAGE = "com.ecarx.user_image";
    private Bundle bundle;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bundle getBundle() {
        return this.bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public IShortCut() {
    }

    protected IShortCut(Parcel parcel) {
        this.bundle = parcel.readBundle(IShortCut.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.bundle);
    }
}

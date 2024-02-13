package com.ecarx.xui.adaptapi.diminteraction;

import android.net.Uri;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/* loaded from: classes.dex */
public interface IContactsInteraction {
    public static final int ACTION_ADD_FAVORITE = 1;
    public static final int ACTION_RM_FAVORITE = 2;
    public static final int CALL_LOG_TYPE_INCOMING = 2;
    public static final int CALL_LOG_TYPE_MISSED = 3;
    public static final int CALL_LOG_TYPE_OUTGOING = 1;
    public static final int CALL_LOG_TYPE_UNKNOWN = 0;
    public static final int TYPE_CALL_LOG = 2;
    public static final int TYPE_CONTACT = 1;
    public static final int TYPE_DEFAULT = 0;
    public static final int TYPE_FAVORITE = 3;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ActionType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface CallLogType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ContactType {
    }

    /* loaded from: classes.dex */
    public interface ICallLog {
        Uri getAvatar();

        int getCallType();

        String getContactName();

        String getContactNumber();

        int getCount();

        long getTimestamp();
    }

    /* loaded from: classes.dex */
    public interface IContact {
        Uri getAvatar();

        String getName();

        String getNumber();

        int getType();
    }

    /* loaded from: classes.dex */
    public interface IContactsInteractionCallback {
        void onDoContactInteractionAction(int i, String str);

        void onSearchContacts(int i, String str);
    }

    void registerContactsInteractionCallback(IContactsInteractionCallback iContactsInteractionCallback);

    void unregisterContactsInteractionCallback(IContactsInteractionCallback iContactsInteractionCallback);

    void updateCallLogList(List<ICallLog> list);

    void updateContacts(int i, List<IContact> list);

    void updateSearchContacts(int i, List<IContact> list, String str);
}

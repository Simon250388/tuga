package com.ecarx.systemui.plugin.statusbar.policy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.policy.BaseController;
import com.ecarx.systemui.plugin.statusbar.StatusBarManager;
import com.ecarx.systemui.plugin.statusbar.entity.StatusBarIconEntity;
import com.ecarx.systemui.plugin.statusbar.iconview.EStatefulObject;
import com.ecarx.systemui.plugin.utils.Lg;
import com.ecarx.systemui.plugin.utils.LogUtil;

/* loaded from: classes.dex */
public class MessageController extends BaseController {
    private static volatile MessageController messageController;
    private final int MESSAGE_COUNT_MAX;
    private String TAG;
    private BroadcastReceiver broadcastReceiver;
    private int[] mcount;
    private int unreadCount;

    private MessageController(Context context) {
        super(context);
        this.TAG = MessageController.class.getSimpleName();
        this.MESSAGE_COUNT_MAX = 9;
        this.mcount = new int[]{0};
        this.broadcastReceiver = new BroadcastReceiver() { // from class: com.ecarx.systemui.plugin.statusbar.policy.MessageController.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                MessageController.this.mcount[0] = intent.getExtras().getInt("readCount");
                MessageController.this.updateMessageIcon();
            }
        };
        try {
            updateMessageIcon();
        } catch (Exception e) {
            Lg.e(this.TAG, e);
        }
    }

    public static MessageController getInstance(Context context) {
        if (messageController == null) {
            synchronized (MessageController.class) {
                messageController = new MessageController(context);
            }
        }
        messageController.initState();
        return messageController;
    }

    private void initState() {
        updateMessageIcon();
        LogUtil.e("GCTAG", "messageController初始化");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMessageIcon() {
        int i = this.mcount[0];
        this.unreadCount = i;
        if (i == 0) {
            StatusBarManager.getInstance().updateIconVisibility(EStatefulObject.UNREAD_MESSAGE, false);
            return;
        }
        StatusBarManager.getInstance().setIcon(new StatusBarIconEntity(EStatefulObject.UNREAD_MESSAGE, getMessageIconByCount(this.unreadCount)));
    }

    private void testCode() {
        this.unreadCount = 5;
        if (5 == 0) {
            StatusBarManager.getInstance().updateIconVisibility(EStatefulObject.UNREAD_MESSAGE, false);
            return;
        }
        StatusBarManager.getInstance().setIcon(new StatusBarIconEntity(EStatefulObject.UNREAD_MESSAGE, getMessageIconByCount(this.unreadCount)));
    }

    private int getMessageIconByCount(int i) {
        int i2 = this.mcount[0];
        if (i2 > 9) {
            i2 = 10;
        }
        switch (i2) {
            case 1:
            default:
                return R.drawable.ic_status_unread_message_1;
            case 2:
                return R.drawable.ic_status_unread_message_2;
            case 3:
                return R.drawable.ic_status_unread_message_3;
            case 4:
                return R.drawable.ic_status_unread_message_4;
            case 5:
                return R.drawable.ic_status_unread_message_5;
            case 6:
                return R.drawable.ic_status_unread_message_6;
            case 7:
                return R.drawable.ic_status_unread_message_7;
            case 8:
                return R.drawable.ic_status_unread_message_8;
            case 9:
                return R.drawable.ic_status_unread_message_9;
            case 10:
                return R.drawable.ic_status_unread_message_9_plus;
        }
    }

    public BroadcastReceiver getBroadcastReceiver() {
        return this.broadcastReceiver;
    }
}

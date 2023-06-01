package com.phoenixzone.fake_product_detection.notification;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.LayerDrawable;
import android.view.MenuItem;

/**
 * Created by Dinesh on 10/30/2018.
 */

public class NotificationCountSetClass extends Activity {
    private static LayerDrawable icon;

    public NotificationCountSetClass() {
        //constructor
    }

    public static void setNotification(Context context, MenuItem item, int numMessages) {
        icon = (LayerDrawable) item.getIcon();
        SetNotificationCount.setBadgeCount(context, icon, setNotifyCount(numMessages));

    }

    public static int setNotifyCount(int numMessages) {
        int count = numMessages;
        return count;

    }
}

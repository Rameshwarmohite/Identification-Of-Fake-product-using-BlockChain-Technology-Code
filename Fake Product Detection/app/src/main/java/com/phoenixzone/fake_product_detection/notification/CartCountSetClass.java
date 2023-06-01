package com.phoenixzone.fake_product_detection.notification;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.LayerDrawable;
import android.view.MenuItem;

/**
 * Created by priyankam on 19-07-2016.
 */
public class CartCountSetClass extends Activity {
    private static LayerDrawable icon;
    public CartCountSetClass() {
        //constructor
    }

    public static void setAddToCart(Context context, MenuItem item, int numMessages) {
        icon = (LayerDrawable) item.getIcon();
        SetNotificationCount.setBadgeCount(context, icon, CartCountSetClass.setNotifyCount(numMessages));

    }

    public static int setNotifyCount(int numMessages) {
        int count=numMessages;
        return count;

    }


}

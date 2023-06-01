package com.phoenixzone.fake_product_detection.utility;

/**
 * Created by Dinesh on 10/31/2018.
 */

public class NotificationInfo {
    private String title;
    private String notification;
    private String time;

    public NotificationInfo(String title, String notification, String time) {
        this.title = title;
        this.notification = notification;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getNotification() {
        return notification;
    }

    public String getTime() {
        return time;
    }
}

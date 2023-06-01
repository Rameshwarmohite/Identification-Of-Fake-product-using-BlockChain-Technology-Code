package com.phoenixzone.fake_product_detection.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Html;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.phoenixzone.fake_product_detection.R;
import com.phoenixzone.fake_product_detection.startup.ScanQRActivity;
import com.phoenixzone.fake_product_detection.utility.DBHelper;
import com.phoenixzone.fake_product_detection.utility.PrefManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dell on 24-10-2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    DBHelper dbHelper;
    PrefManager prefManager;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        sendNotification("" + Html.fromHtml(remoteMessage.getData().get("title")), "" + Html.fromHtml(remoteMessage.getData().get("body")));


    }

    private void sendNotification(String messageTitle, String messageBody) {
        Intent intent = new Intent(this, ScanQRActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* request code */, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long[] pattern = {500, 500, 500, 500, 500};
        prefManager = new PrefManager(this);
        int count = prefManager.getNotificationCount() + 1;
        prefManager.setNotificationCount(count);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        dbHelper = new DBHelper(this);
        dbHelper.open();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String timestamp = simpleDateFormat.format(date);
        dbHelper.insertNotificationInfo(timestamp, messageTitle, messageBody);
        dbHelper.close();

        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setVibrate(pattern)
                .setLights(Color.BLUE, 1, 1)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(messageBody))
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

        Intent intent1 = new Intent(String.valueOf(1001)).putExtra("MESSAGE", messageTitle);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent1);
    }
}

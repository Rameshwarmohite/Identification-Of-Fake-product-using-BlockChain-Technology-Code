package com.phoenixzone.fake_product_detection.notification;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.phoenixzone.fake_product_detection.R;

/**
 * Created by Dell on 24-10-2017.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    private final static String REG_TOKEN = "REG_TOKEN";
    public static String recent_token = "";

    @Override
    public void onTokenRefresh() {
        try {
            recent_token = FirebaseInstanceId.getInstance().getToken();
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(getString(R.string.FCM_TOKEN), recent_token);
            editor.commit();
            Log.d(REG_TOKEN,recent_token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

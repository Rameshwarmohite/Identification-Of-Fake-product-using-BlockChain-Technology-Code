package com.phoenixzone.fake_product_detection.utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Lincoln on 05/05/16.
 */
public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences constants
    private static final String PREF_NAME = "MyPreference";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String IS_USER_LOGIN = "IsUserLogin";
    private static final String USER_NAME = "UserName";
    private static final String USER_MOBILE = "Mobile";
    private static final String USER_EMAIL = "Email";
    private static final String USER_PASSWORD = "Password";
    private static final String OTP = "Otp";
    private static final String USER_ADDRESS = "User_Address";
    private static final String ZIP_CODE = "Zip_Code";
    private static final String DELIVERY_FEE = "Delivery_Fee";
    private static final String DELIVERY_KM = "Delivery_KM";
    private static final String NOTIFICATION_COUNT = "Notification_count";


    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setNotificationCount(int notificationCount) {
        editor.putInt(NOTIFICATION_COUNT, notificationCount);
        editor.commit();
    }

    public int getNotificationCount() {
        return pref.getInt(NOTIFICATION_COUNT, 0);
    }

    public void setDeliveryFee(String fee) {
        editor.putString(DELIVERY_FEE, fee);
        editor.commit();
    }

    public void setDeliveryKm(String deliveryKm) {
        editor.putString(DELIVERY_KM, deliveryKm);
        editor.commit();
    }

    public String getDeliveryKM() {
        return pref.getString(DELIVERY_KM, "5");
    }

    public String getDeliveryFee() {
        return pref.getString(DELIVERY_FEE, "15");
    }

    public void setZipCode(String zipCode) {
        editor.putString(ZIP_CODE, zipCode);
        editor.commit();
    }

    public String getZipCode() {
        return pref.getString(ZIP_CODE, "");
    }

    public void setUserName(String userName) {
        editor.putString(USER_NAME, userName);
        editor.commit();
    }


    public void setUserAddress(String address) {
        editor.putString(USER_ADDRESS, address);
        editor.commit();
    }

    public String getUserAddress() {
        return pref.getString(USER_ADDRESS, "");
    }

    public String getUserName() {
        return pref.getString(USER_NAME, "");
    }


    public void setUserEmail(String email) {
        editor.putString(USER_EMAIL, email);
        editor.commit();
    }

    public String getUserEmail() {
        return pref.getString(USER_EMAIL, "");
    }

    public void setUserMobile(String mobile) {
        editor.putString(USER_MOBILE, mobile);
        editor.commit();
    }

    public String getUserMobile() {
        return pref.getString(USER_MOBILE, "");
    }

    public void setOTP(String otp) {
        editor.putString(OTP, otp);
        editor.commit();
    }

    public void setUserPassword(String password) {
        editor.putString(USER_PASSWORD, password);
        editor.commit();
    }

    public String getUserPassword() {
        return pref.getString(USER_PASSWORD, "");
    }

    public String getOTP() {
        return pref.getString(OTP, "");
    }

    public void setIsUserLogin(boolean isUserLogin) {
        editor.putBoolean(IS_USER_LOGIN, isUserLogin);
        editor.commit();
    }

    public boolean isUserLogin() {
        return pref.getBoolean(IS_USER_LOGIN, false);
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

}

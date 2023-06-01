package com.phoenixzone.fake_product_detection.connectivity;

/**
 * Created by Dell on 28-10-2017.
 */
public class ServerUtility {
    public static String Server_URL = "http://192.168.43.139:8080/OnlinePayment/";
    //  public static String Server_URL = "http://192.168.0.102:8080/gram_panchayat";
    public static double latitude = 18.151490, longitude = 74.576135;
    public static boolean flag_Activity = false;
    public static String TROLLEY_ID="1";
    public static String CART_IP="192.168.182.122";
    public static String url_get_user_info() {
        return ServerUtility.Server_URL + "UserLogin";
    }

    public static String url_register_user() {
        return ServerUtility.Server_URL + "api/api/user";
    }

    public static String url_verify_otp() {
        return ServerUtility.Server_URL + "api/api/otp";
    }

    public static String url_update_profile() {
        return ServerUtility.Server_URL + "api/api/profile";
    }

    public static String url_place_order() {
        return ServerUtility.Server_URL + "api/api/order";
    }

    public static String url_get_product_info() {
        return ServerUtility.Server_URL + "api/api/getProduct";
    }

    public static String url_get_product_by_id_info() {
        return ServerUtility.Server_URL + "DetectProduct";
    }
    public static String url_get_trolley_by_id_info() {
        return ServerUtility.Server_URL + "api/api/getTrollyIpById";
    }

    public static String url_get_order_history() {
        return ServerUtility.Server_URL + "api/api/orderhistory";
    }


    public static final String TAG_SUCCESS = "success";

}

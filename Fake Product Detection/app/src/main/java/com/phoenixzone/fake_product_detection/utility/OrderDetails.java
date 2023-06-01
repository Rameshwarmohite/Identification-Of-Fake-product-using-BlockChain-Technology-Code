package com.phoenixzone.fake_product_detection.utility;

/**
 * Created by Dinesh on 10/23/2018.
 */

public class OrderDetails {
    private String orderid;
    private String order_date;
    private int order_status;
    private double order_amount;
    private CartItem cartItems;
    private int product_count;
    private double delivery_fee;

    public OrderDetails(String orderid, String order_date, int order_status, double order_amount, CartItem cartItems, double delivery_fee) {
        this.orderid = orderid;
        this.order_date = order_date;
        this.order_status = order_status;
        this.order_amount = order_amount;
        this.cartItems = cartItems;
        this.delivery_fee = delivery_fee;
    }

    public double getDelivery_fee() {
        return delivery_fee;
    }

    public int getProduct_count() {
        return product_count;
    }

    public void setProduct_count(int product_count) {
        this.product_count = product_count;
    }

    public String getOrderid() {
        return orderid;
    }

    public String getOrder_date() {
        return order_date;
    }

    public int getOrder_status() {
        return order_status;
    }

    public double getOrder_amount() {
        return order_amount;
    }

    public CartItem getCartItems() {
        return cartItems;
    }
}

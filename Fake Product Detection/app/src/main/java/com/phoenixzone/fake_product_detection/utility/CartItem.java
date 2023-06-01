package com.phoenixzone.fake_product_detection.utility;

/**
 * Created by Dinesh on 10/17/2018.
 */

public class CartItem {
    private String product_id;
    private String category_fk;
    private String product_name;
    private String product_description;
    private double product_price;
    private int product_count;
    private String product_ratings;
    private String product_reviews;
    private String product_photo;
    private double product_total;

    public CartItem(String product_name, double product_price, int product_count, String product_photo, double product_total, String product_description) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_count = product_count;
        this.product_photo = product_photo;
        this.product_total = product_total;
        this.product_description = product_description;
    }

    public CartItem(String product_id, String category_fk, String product_name, String product_description, double product_price, int product_count, String product_ratings, String product_reviews, String product_photo) {
        this.product_id = product_id;
        this.category_fk = category_fk;
        this.product_name = product_name;
        this.product_description = product_description;
        this.product_price = product_price;
        this.product_count = product_count;
        this.product_ratings = product_ratings;
        this.product_reviews = product_reviews;
        this.product_photo = product_photo;
        this.product_total=this.product_price*this.product_count;
    }

    public CartItem() {

    }

    public String getProduct_id() {
        return product_id;
    }

    public String getCategory_fk() {
        return category_fk;
    }

    public String getProduct_photo() {
        return product_photo;
    }

    public void setProduct_count(int product_count) {
        this.product_count = product_count;
        this.product_total=this.product_price*this.product_count;

    }

    public double getProduct_total() {
        return product_total;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public double getProduct_price() {
        return product_price;
    }

    public int getProduct_count() {
        return product_count;
    }

    public String getProduct_ratings() {
        return product_ratings;
    }

    public String getProduct_reviews() {
        return product_reviews;
    }

}

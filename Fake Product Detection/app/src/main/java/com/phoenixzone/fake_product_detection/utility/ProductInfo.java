package com.phoenixzone.fake_product_detection.utility;

/**
 * Created by Dinesh on 10/17/2018.
 */

public class ProductInfo {
    private String product_id;
    private String category_fk;
    private String product_name;
    private String product_desc;
    private String product_price;
    private String product_photo;

    public ProductInfo(String product_id, String category_fk, String product_name, String product_desc, String product_price, String product_photo) {
        this.product_id = product_id;
        this.category_fk = category_fk;
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.product_price = product_price;
        this.product_photo = product_photo;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getCategory_fk() {
        return category_fk;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public String getProduct_price() {
        return product_price;
    }

    public String getProduct_photo() {
        return product_photo;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import java.io.Serializable;

public class Item implements Serializable{
    private String id;
    private double price;
    private int count;
    private double total;

    public Item(String id, double price, int count) {
        this.id = id;
        this.price = price;
        this.count = count;
        setTotal(price);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        double total = count * this.price;
        setTotal(total);
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}

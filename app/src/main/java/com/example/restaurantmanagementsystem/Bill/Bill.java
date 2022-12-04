package com.example.restaurantmanagementsystem.Bill;

public class Bill {
    private int bill_id;
    private int customer_id;

    public Bill(int bill_id, int customer_id, int price) {
        this.bill_id = bill_id;
        this.customer_id = customer_id;
        this.price = price;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private int price;
}

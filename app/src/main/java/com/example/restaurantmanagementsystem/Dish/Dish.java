package com.example.restaurantmanagementsystem.Dish;

public class Dish {
    private String dish_name;
    private int price;

    public Dish(String dish_name, int price, int quantity) {
        this.dish_name = dish_name;
        this.price = price;
        this.quantity = quantity;
    }

    private int quantity;

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

package com.example.restaurantmanagementsystem.Dish;

public class Dish {
    private String dish_name;
    private double price;
    private int quantity;

    public Dish(String dish_name, double price, int quantity) {
        this.dish_name = dish_name;
        this.price = price;
        this.quantity = quantity;
    }

    public Dish(String dish_name, double price) {
        this.dish_name = dish_name;
        this.price = price;
    }


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

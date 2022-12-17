package com.example.restaurantmanagementsystem.Order;

import com.example.restaurantmanagementsystem.Dish.Dish;

import java.util.List;

public class Order {
    private int orderId;
    private Dish dish;

    public Order(int orderId, Dish dish) {
        this.orderId = orderId;
        this.dish = dish;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

}

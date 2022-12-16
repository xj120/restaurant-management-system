package com.example.restaurantmanagementsystem.Order;

import com.example.restaurantmanagementsystem.Dish.Dish;

import java.util.List;

public class Order {
    private int orderId;
    private List<Dish> dish;

    public Order(int orderId, List<Dish> dish) {
        this.orderId = orderId;
        this.dish = dish;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<Dish> getDish() {
        return dish;
    }

    public void setDish(List<Dish> dish) {
        this.dish = dish;
    }

}

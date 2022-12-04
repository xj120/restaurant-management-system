package com.example.restaurantmanagementsystem.Order;

import com.example.restaurantmanagementsystem.Dish.Dish;

import java.util.List;

public class Order {
    private int orderId;
    private List<Dish> dish;

    public Order(int orderId, List<Dish> dish, OrderState state) {
        this.orderId = orderId;
        this.dish = dish;
        this.state = state;
    }

    private OrderState state;

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

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }
}

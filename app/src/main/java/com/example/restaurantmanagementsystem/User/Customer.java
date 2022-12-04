package com.example.restaurantmanagementsystem.User;

public class Customer extends User{
    public Customer(int user_id, String account, String password) {
        super(user_id, account, password);
        this.setType(UserType.CUSTOMER);
    }
}

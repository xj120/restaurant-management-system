package com.example.restaurantmanagementsystem.User;

public class Customer extends User{
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Customer(int user_id, String account, String password) {
        super(user_id, account);
        this.password = password;
        this.setType(UserType.CUSTOMER);
    }
}

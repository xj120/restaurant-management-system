package com.example.restaurantmanagementsystem.User;

public abstract class User {
    private int user_id;

    public User(int user_id, String account) {
        this.user_id = user_id;
        this.account = account;
    }

    private String account;

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    private UserType type;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

}

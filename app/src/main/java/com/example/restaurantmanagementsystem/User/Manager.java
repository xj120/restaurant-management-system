package com.example.restaurantmanagementsystem.User;

public class Manager extends User{
    private String name;

    public Manager(int user_id, String account, String password, String name) {
        super(user_id, account, password);
        this.name = name;
        this.setType(UserType.MANAGER);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

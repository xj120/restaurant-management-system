package com.example.restaurantmanagementsystem.User;

public class Manager extends User{
    private String name;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Manager(int user_id, String account, String password, String name) {
        super(user_id, account);
        this.password = password;
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

package com.example.restaurantmanagementsystem.User;

public class Employee extends User{
    private String name;

    public Employee(int user_id, String account, String password, String name) {
        super(user_id, account, password);
        this.name = name;
        this.setType(UserType.EMPLOYEE);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

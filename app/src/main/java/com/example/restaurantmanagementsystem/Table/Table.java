package com.example.restaurantmanagementsystem.Table;

public class Table {
    private int table_id;
    private int user_id;
    private TableType type;

    public Table(int table_id, TableType type) {
        this.table_id = table_id;
        this.type = type;
    }

    public Table(int table_id, int user_id, TableType type) {
        this.table_id = table_id;
        this.user_id = user_id;
        this.type = type;
    }

    public TableType getType() {
        return type;
    }

    public void setType(TableType type) {
        this.type = type;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}

package com.example.restaurantmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.restaurantmanagementsystem.Adapter.OrderAdapter;
import com.example.restaurantmanagementsystem.DatabaseHelper.DatabaseHelper;
import com.example.restaurantmanagementsystem.Order.Order;
import com.example.restaurantmanagementsystem.R;

import java.util.List;

public class CustomerOrderActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private List<Order> orderList;
    private Intent gIntent;
    private int customerId;
    private OrderAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_manage_order_layout);

        dbHelper = new DatabaseHelper(this, "Restaurant.db", null, 2);

        gIntent = getIntent();
        customerId = gIntent.getIntExtra("customer_id", 0);
    }
}
package com.example.restaurantmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.restaurantmanagementsystem.Adapter.ListAdapter;
import com.example.restaurantmanagementsystem.Adapter.MenuAdapter;
import com.example.restaurantmanagementsystem.DatabaseHelper.DatabaseHelper;
import com.example.restaurantmanagementsystem.Dish.Dish;
import com.example.restaurantmanagementsystem.R;

import java.util.List;

public class CustomerListActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private Intent gIntent;
    private Bundle gBundle;
    private int customerId;
    private int orderId;
    private ListAdapter adapter;
    private double totalMoney = 0;
    private int totalAmount = 0;
    private List<Dish> orderList;
    private Button pay;
    private Button clear;
    private Button turnback;
    private Bundle pBundle;
    private Intent pIntent;

    public static IndirectClass indirectClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_list_layout);

        indirectClass = new IndirectClass(this, CustomerListActivity.this);

        dbHelper = new DatabaseHelper(this, "Restaurant.db", null, 2);

        gIntent = getIntent();
        gBundle = gIntent.getExtras();
        orderId = dbHelper.getMaxOrderId() + 1;

        orderList = gBundle.getParcelableArrayList("order_list");
        customerId = gIntent.getIntExtra("customer_id", 0);

        for (Dish d : orderList) {
            totalAmount++;
        }
        TextView totalNumber = (TextView) findViewById(R.id.totalNumber);
        totalNumber.setText(String.valueOf(totalAmount));


        totalMoney = gIntent.getDoubleExtra("total_money", 0);
        TextView totalPrice = (TextView) findViewById(R.id.totalPrice);
        totalPrice.setText(String.valueOf(totalMoney));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.GoodsListRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ListAdapter(orderList);
        recyclerView.setAdapter(adapter);

    }

    public void moreAmount() {
        totalAmount++;
        TextView total = (TextView) findViewById(R.id.totalNumber);
        total.setText(String.valueOf(totalAmount));
    }

    public void lessAmount() {
        totalAmount--;
        TextView total = (TextView) findViewById(R.id.totalNumber);
        total.setText(String.valueOf(totalAmount));
    }

    public void moreMoney(double money) {
        totalMoney += money;
        TextView total = (TextView) findViewById(R.id.totalPrice);
        total.setText(String.valueOf(totalMoney));
    }

    public void lessMoney(double money) {
        totalMoney -= money;
        TextView total = (TextView) findViewById(R.id.totalPrice);
        total.setText(String.valueOf(totalMoney));
    }
}
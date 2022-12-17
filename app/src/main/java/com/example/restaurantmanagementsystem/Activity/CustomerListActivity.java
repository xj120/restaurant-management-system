package com.example.restaurantmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.Adapter.ListAdapter;
import com.example.restaurantmanagementsystem.Adapter.MenuAdapter;
import com.example.restaurantmanagementsystem.DatabaseHelper.DatabaseHelper;
import com.example.restaurantmanagementsystem.Dish.Dish;
import com.example.restaurantmanagementsystem.R;

import java.util.ArrayList;
import java.util.List;

public class CustomerListActivity extends AppCompatActivity implements View.OnClickListener{

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

        pay = (Button) findViewById(R.id.payNow);
        clear = (Button) findViewById(R.id.clearAllGoods);
        turnback = (Button) findViewById(R.id.returnBack);

        pay.setOnClickListener(this);
        clear.setOnClickListener(this);
        turnback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.payNow:
                for (Dish d : orderList) {
                    dbHelper.addOrder(orderId, customerId, d);
                }
                dbHelper.addBill(customerId, totalMoney);
                pIntent = new Intent(CustomerListActivity.this, CustomerPaidActivity.class);
                pIntent.putExtra("total_money", totalMoney);
                pIntent.putExtra("customer_id", customerId);
                startActivity(pIntent);
                break;
            case R.id.clearAllGoods:
                Toast.makeText(CustomerListActivity.this, "clear", Toast.LENGTH_SHORT).show();
                int size = orderList.size();
                orderList.removeAll(orderList);
                adapter.notifyItemRangeRemoved(0, size);
                TextView tPrice = (TextView) findViewById(R.id.totalPrice);
                tPrice.setText("0.0");
                TextView tNumber = (TextView) findViewById(R.id.totalNumber);
                tNumber.setText("0");
                break;
            case R.id.returnBack:
                finish();
                break;
            default:
                break;
        }
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
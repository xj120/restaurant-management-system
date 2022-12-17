package com.example.restaurantmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.restaurantmanagementsystem.Adapter.MenuAdapter;
import com.example.restaurantmanagementsystem.DatabaseHelper.DatabaseHelper;
import com.example.restaurantmanagementsystem.Dish.Dish;
import com.example.restaurantmanagementsystem.R;

import java.util.List;

public class CustomerMenuActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private List<Dish> dishList;
    private Intent gIntent;
    private int customerId;
    private MenuAdapter adapter;
    public double totalMoney = 0;
    private List<Dish> orderList;

    public static IndirectClass indirectClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_menu_layout);

        indirectClass = new IndirectClass(this, CustomerMenuActivity.this);

        dbHelper = new DatabaseHelper(this, "Restaurant.db", null, 2);

        gIntent = getIntent();
        customerId = gIntent.getIntExtra("customerId", 0);
        dishList = dbHelper.getDishList();
        for (Dish dish : dishList) {
            dish.setQuantity(0);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.contentRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MenuAdapter(dishList);
        recyclerView.setAdapter(adapter);
    }

    public void moreMoney(double money) {
        totalMoney += money;
        TextView total = (TextView) findViewById(R.id.money);
        total.setText(String.valueOf(totalMoney));
    }

    public void lessMoney(double money) {
        totalMoney -= money;
        TextView total = (TextView) findViewById(R.id.money);
        total.setText(String.valueOf(totalMoney));
    }

    public void updateButtonColor() {
        TextView total = (TextView) findViewById(R.id.money);
        Button button = (Button) findViewById(R.id.settlement);
        double money = Double.parseDouble(total.getText().toString());
        if (money > 0) {
            button.setBackgroundColor(Color.rgb(255, 128, 0));
        }
        else {
            button.setBackgroundColor(Color.rgb(128, 128, 128));
        }
    }

}
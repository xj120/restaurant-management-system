package com.example.restaurantmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.restaurantmanagementsystem.DatabaseHelper.DatabaseHelper;
import com.example.restaurantmanagementsystem.R;

public class CustomerPaidActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private TextView payMoney;
    private Button backHome;
    private Intent gIntent;
    private Intent hIntent;
    private double money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_paid_layout);

        dbHelper = new DatabaseHelper(this, "Restaurant.db", null, 2);

        // 获取数据
        gIntent = getIntent();
        money = gIntent.getDoubleExtra("total_money",0);
        payMoney = (TextView) findViewById(R.id.PaySuccessMoney);
        payMoney.setText(String.valueOf(money));

        // 获取实例，监听事件
        backHome = (Button) findViewById(R.id.back_to_home);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hIntent = new Intent(CustomerPaidActivity.this, CustomerMainActivity.class);
                hIntent.putExtra("customer_id", gIntent.getIntExtra("customer_id", 0));
                startActivity(hIntent);
            }
        });
    }
}
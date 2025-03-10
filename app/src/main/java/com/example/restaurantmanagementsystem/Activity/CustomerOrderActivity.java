package com.example.restaurantmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.restaurantmanagementsystem.Adapter.OrderAdapter;
import com.example.restaurantmanagementsystem.DatabaseHelper.DatabaseHelper;
import com.example.restaurantmanagementsystem.Dish.Dish;
import com.example.restaurantmanagementsystem.Order.Order;
import com.example.restaurantmanagementsystem.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerOrderActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private List<Order> orderList;
    private List<Order> realList;
    private Intent gIntent;
    private int customerId;
    private OrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_manage_order_layout);

        dbHelper = new DatabaseHelper(this, "Restaurant.db", null, 2);

        // 获取数据
        gIntent = getIntent();
        customerId = gIntent.getIntExtra("customer_id", 0);

        // 获取数据源
        orderList = dbHelper.getOrderByCustomer(customerId);
        realList = convert(orderList);
        Collections.reverse(realList);

        // 初始化适配器
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.myHistoryOrderRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new OrderAdapter(realList);
        recyclerView.setAdapter(adapter);
    }

    // 对数据源进行统一格式处理
    public List<Order> convert(List<Order> orderList) {
        realList = new ArrayList<>();
        Map<Integer, List<Order>> orderMap = splitGroup(orderList);
        for (Map.Entry<Integer, List<Order>> entry : orderMap.entrySet()) {
            int dishCount = 0;
            double orderSum = 0;
            String generalName;
            Dish dish;
            int keyId = entry.getKey();
            List<Order> valueList = entry.getValue();
            for (Order order : valueList) {
                dishCount += order.getDish().getQuantity();
                orderSum += order.getDish().getPrice() * order.getDish().getQuantity();
            }
            if (valueList.size() > 1) {
                generalName = valueList.get(0).getDish().getDish_name() + "...";
            }
            else {
                generalName = valueList.get(0).getDish().getDish_name();
            }
            dish = new Dish(generalName, orderSum, dishCount);
            realList.add(new Order(keyId, dish));
        }
        return realList;
    }

    // 将从数据库中获取的订单根据ID进行分组
    public Map<Integer,List<Order>> splitGroup(List<Order> orderList) {
        Map<Integer, List<Order>> map = new HashMap<>();
        for(Order order : orderList) {
            int key = order.getOrderId();
            if(map.containsKey(key)) {
                map.get(key).add(order);
            } else {
                List<Order> orders = new ArrayList<>();
                orders.add(order);
                map.put(key, orders);
            }
        }
        return map;
    }
}
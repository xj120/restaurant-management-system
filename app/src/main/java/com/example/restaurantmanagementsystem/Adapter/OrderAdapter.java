package com.example.restaurantmanagementsystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagementsystem.Activity.CustomerMenuActivity;
import com.example.restaurantmanagementsystem.Activity.IndirectClass;
import com.example.restaurantmanagementsystem.Dish.Dish;
import com.example.restaurantmanagementsystem.Order.Order;
import com.example.restaurantmanagementsystem.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{

    private List<Order> mOrderList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderId;
        TextView orderItem;
        TextView orderQuantity;
        TextView priceSum;

        public ViewHolder (View view) {
            super(view);
            orderId = (TextView) view.findViewById(R.id.order_id);
            orderItem = (TextView) view.findViewById(R.id.itemName);
            orderQuantity = (TextView) view.findViewById(R.id.itemNumber);
            priceSum=  (TextView) view.findViewById(R.id.priceSum);
        }
    }

    public OrderAdapter(List<Order> orderList) {
        mOrderList = orderList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mOrderList.size();
    }

}

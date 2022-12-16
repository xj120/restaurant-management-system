package com.example.restaurantmanagementsystem.Adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagementsystem.Dish.Dish;

import java.util.List;


// extends RecyclerView.Adapter<MenuAdapter.ViewHolder>
public class MenuAdapter{
    private List<Dish> mDishList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderName;
        TextView price;
        TextView orderNumber;
        ImageView sub;
        ImageView add;

        public ViewHolder (View view) {
            super(view);
        }
    }
}

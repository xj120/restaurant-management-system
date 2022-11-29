package com.example.restaurantmanagementsystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.restaurantmanagementsystem.Dish.Dish;

import java.util.List;

public class DishAdapter extends ArrayAdapter<Dish> {
    private int resourceId;
    public DishAdapter(Context context, int textViewResourceId, List<Dish> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Dish dish = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
//        ImageView dishImage = (ImageView) view.findViewById();
//        TextView dishName = (TextView) view.findViewById();
//        dishImage.setImageResource();
//        dishName.setText();
        return view;
    }
}

package com.example.restaurantmanagementsystem.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagementsystem.Dish.Dish;
import com.example.restaurantmanagementsystem.R;

import java.util.List;


//
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder>{
    private List<Dish> mDishList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderName;
        TextView price;
        TextView orderNumber;
        ImageView sub;
        ImageView add;

        public ViewHolder (View view) {
            super(view);
            orderName = (TextView) view.findViewById(R.id.each_dish_name);
            price = (TextView) view.findViewById(R.id.each_dish_price);
            orderNumber = (TextView) view.findViewById(R.id.Number);
            sub = (ImageView) view.findViewById(R.id.Sub);
            add = (ImageView) view.findViewById(R.id.Add);
        }
    }

    public MenuAdapter(List<Dish> dishList) {
        mDishList = dishList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Dish dish = mDishList.get(position);
        holder.orderName.setText(dish.getDish_name());
        holder.price.setText(String.valueOf(dish.getPrice()));
        holder.orderNumber.setText(String.valueOf(dish.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return mDishList.size();
    }
}

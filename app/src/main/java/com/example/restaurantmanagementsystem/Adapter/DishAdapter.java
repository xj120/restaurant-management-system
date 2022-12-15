package com.example.restaurantmanagementsystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.restaurantmanagementsystem.Dish.Dish;
import com.example.restaurantmanagementsystem.R;

import java.util.List;

public class DishAdapter extends ArrayAdapter<Dish> {

    private int resourceId;
    private DeleteClickListener deleteClickListener;
    private ModifyClickListener modifyClickListener;

    public DishAdapter(Context context, int textViewResourceId, List<Dish> objects,
                       DeleteClickListener dlistener, ModifyClickListener mlistener) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        deleteClickListener = dlistener;
        modifyClickListener = mlistener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Dish dish = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.dishName = (TextView) view.findViewById(R.id.dish_name);
            viewHolder.dishPrice = (TextView) view.findViewById(R.id.dish_price);
            viewHolder.menuDelete = (Button) view.findViewById(R.id.menu_delete);
            viewHolder.menuModify = (Button) view.findViewById(R.id.menu_modify);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.dishPrice.setText(String.valueOf(dish.getPrice()));
        viewHolder.dishName.setText(dish.getDish_name());
        viewHolder.menuModify.setOnClickListener(modifyClickListener);
        viewHolder.menuModify.setTag(position);
        viewHolder.menuDelete.setOnClickListener(deleteClickListener);
        viewHolder.menuDelete.setTag(position);
        return view;
    }

    class ViewHolder {
        TextView dishName;
        TextView dishPrice;
        Button menuDelete;
        Button menuModify;
    }

    public static abstract class DeleteClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            myOnClick((Integer)v.getTag(), v);
        }
        public abstract void myOnClick(int position, View v);
    }

    public static abstract class ModifyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            myOnClick((Integer)v.getTag(), v);
        }
        public abstract void myOnClick(int position, View v);
    }


}

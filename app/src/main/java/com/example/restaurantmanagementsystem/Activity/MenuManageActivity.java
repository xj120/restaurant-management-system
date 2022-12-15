package com.example.restaurantmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.restaurantmanagementsystem.Adapter.DishAdapter;
import com.example.restaurantmanagementsystem.Adapter.TableAdapter;
import com.example.restaurantmanagementsystem.DatabaseHelper.DatabaseHelper;
import com.example.restaurantmanagementsystem.Dish.Dish;
import com.example.restaurantmanagementsystem.R;
import com.example.restaurantmanagementsystem.Table.Table;

import java.util.List;

public class MenuManageActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private DishAdapter adapter;
    private List<Dish> dishList;
    private ListView listView;
    private Button dishAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_information);

        dbHelper = new DatabaseHelper(this, "Restaurant.db", null, 2);

        dishList = dbHelper.getDishList();

        adapter = new DishAdapter(MenuManageActivity.this,
                R.layout.menu_item, dishList, dListener, mListener);

        listView = (ListView) findViewById(R.id.lv_menu);
        listView.setAdapter(adapter);

        dishAdd = (Button) findViewById(R.id.menu_add);
        dishAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    private DishAdapter.ModifyClickListener mListener = new DishAdapter.ModifyClickListener() {
        @Override
        public void myOnClick(int position, View v) {

        }
    };

    private DishAdapter.DeleteClickListener dListener = new DishAdapter.DeleteClickListener() {
        @Override
        public void myOnClick(int position, View v) {

        }
    };

    private void notifyDataSetChanged(int position, ListView listView) {
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int lastVisiblePosition = listView.getLastVisiblePosition();
        if(position >= firstVisiblePosition && position <= lastVisiblePosition) {
            View view = listView.getChildAt(position - firstVisiblePosition);
            adapter.getView(position, view, listView);
        }
    }
}
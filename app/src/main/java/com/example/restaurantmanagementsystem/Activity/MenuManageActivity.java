package com.example.restaurantmanagementsystem.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
                View addView = View.inflate(MenuManageActivity.this, R.layout.add_dish_layout, null);
                AlertDialog alertDialog = new AlertDialog.Builder(MenuManageActivity.this)
                        .setTitle("新增菜品")
                        .setView(addView)
                        .create();
                alertDialog.show();
                EditText dishName = addView.findViewById(R.id.add_dish_name);
                EditText price = addView.findViewById(R.id.add_dish_price);
                Button confirm = addView.findViewById(R.id.adddish_save_pop);
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (dbHelper.addDish(dishName.getText().toString(), price.getText().toString())) {
                            Toast.makeText(MenuManageActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
                            adapter.notifyDataSetChanged();
                        }
                        else {
                            Toast.makeText(MenuManageActivity.this, "添加失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


    private DishAdapter.ModifyClickListener mListener = new DishAdapter.ModifyClickListener() {
        @Override
        public void myOnClick(int position, View v) {
            View modView = View.inflate(MenuManageActivity.this, R.layout.modify_dish_layout, null);
            Dish dish = dishList.get(position);
            EditText dishName = modView.findViewById(R.id.modify_dish_name);
            EditText price = modView.findViewById(R.id.modify_dish_price);
            dishName.setText(dish.getDish_name());
            price.setText(String.valueOf(dish.getPrice()));
            AlertDialog alertDialog = new AlertDialog.Builder(MenuManageActivity.this)
                    .setTitle("修改菜品")
                    .setView(modView)
                    .create();
            alertDialog.show();
            Button confirm = modView.findViewById(R.id.moddish_save_pop);
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dbHelper.updateDish(dish.getDish_name(), dishName.getText().toString(),
                            Double.parseDouble(price.getText().toString()))) {
                        Toast.makeText(MenuManageActivity.this, "更新成功！", Toast.LENGTH_SHORT).show();
                        dishList.get(position).setDish_name(dishName.getText().toString());
                        dishList.get(position).setPrice(Double.parseDouble(price.getText().toString()));
                        notifyDataSetChanged(position, listView);
                    }
                    else {
                        Toast.makeText(MenuManageActivity.this, "更新失败！", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    };

    private DishAdapter.DeleteClickListener dListener = new DishAdapter.DeleteClickListener() {
        @Override
        public void myOnClick(int position, View v) {
            Dish dish = dishList.get(position);
            String name = dish.getDish_name();
            if (dbHelper.deleteDish(name)) {
                Toast.makeText(MenuManageActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged(position, listView);
            }
            else {
                Toast.makeText(MenuManageActivity.this, "删除失败！", Toast.LENGTH_SHORT).show();
            }
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
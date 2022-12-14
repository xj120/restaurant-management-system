package com.example.restaurantmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.restaurantmanagementsystem.Adapter.TableAdapter;
import com.example.restaurantmanagementsystem.DatabaseHelper.DatabaseHelper;
import com.example.restaurantmanagementsystem.R;
import com.example.restaurantmanagementsystem.Table.Table;

import java.util.List;

public class TableStateManageActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_state_manage);

        dbHelper = new DatabaseHelper(this, "Restaurant.db", null, 2);

        List<Table> tableList = dbHelper.getTableList();

        TableAdapter adapter = new TableAdapter(TableStateManageActivity.this,
                R.layout.table_item, tableList);
        ListView listView = (ListView) findViewById(R.id.lv_table);
        listView.setAdapter(adapter);

//        if(tableList != null) {
//
//        }
    }
}
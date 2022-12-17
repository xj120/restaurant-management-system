package com.example.restaurantmanagementsystem.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.Adapter.TableAdapter;
import com.example.restaurantmanagementsystem.DatabaseHelper.DatabaseHelper;
import com.example.restaurantmanagementsystem.R;
import com.example.restaurantmanagementsystem.Table.Table;
import com.example.restaurantmanagementsystem.Table.TableType;

import java.util.List;

public class ManagerTableActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private TableAdapter adapter;
    private List<Table> tableList;
    private ListView listView;
    final String[] stateList = new String[]{"AVAILABLE", "DINING", "CLEANING"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_state_manage);

        dbHelper = new DatabaseHelper(this, "Restaurant.db", null, 2);

        tableList = dbHelper.getTableList();

        adapter = new TableAdapter(ManagerTableActivity.this,
                R.layout.table_item, tableList, mListener);
        listView = (ListView) findViewById(R.id.lv_table);
        listView.setAdapter(adapter);

    }

    private TableAdapter.MyClickListener mListener = new TableAdapter.MyClickListener() {
        @Override
        public void myOnClick(int position, View v) {
            AlertDialog alertDialog = new AlertDialog.Builder(ManagerTableActivity.this)
                    .setTitle("改变餐桌" + (position + 1) + "状态")
                    .setItems(stateList, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (dbHelper.updateTableState(stateList[i], position + 1)) {
                                if (stateList[i].equals("CLEANING")) {
                                    dbHelper.removeCustomerToTable(position + 1);
                                }
                                Toast.makeText(ManagerTableActivity.this, "Update success",
                                        Toast.LENGTH_SHORT).show();
                                tableList.get(position).setType(TableType.valueOf(stateList[i]));
//                                adapter.notifyDataSetChanged();
                                notifyDataSetChanged(position, listView);
                            }
                            else {
                                Toast.makeText(ManagerTableActivity.this, "Update fail",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .create();
            alertDialog.show();
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
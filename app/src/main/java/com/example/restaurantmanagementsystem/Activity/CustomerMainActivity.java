package com.example.restaurantmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.DatabaseHelper.DatabaseHelper;
import com.example.restaurantmanagementsystem.R;
import com.example.restaurantmanagementsystem.Table.Table;
import com.example.restaurantmanagementsystem.Table.TableType;

import java.util.List;

public class CustomerMainActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseHelper dbHelper;
    private List<Table> tableList;
    private Intent gIntent;
    private int customerId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_main_layout);

        dbHelper = new DatabaseHelper(this, "Restaurant.db", null, 2);

        gIntent = getIntent();
        customerId = gIntent.getIntExtra("customer_id", 0);
        tableList = dbHelper.getTableList();

        Button orderNow = (Button) findViewById(R.id.eat_now_mode1);
        Button checkHistory = (Button) findViewById(R.id.eat_now_mode2);

        orderNow.setOnClickListener(this);
        checkHistory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.eat_now_mode1:
                if (dbHelper.checkCustomerInTable(customerId)) {
                    Intent eIntent = new Intent(CustomerMainActivity.this, CustomerMenuActivity.class);
                    eIntent.putExtra("customer_id", customerId);
                    Toast.makeText(CustomerMainActivity.this, "欢迎回到点餐!", Toast.LENGTH_SHORT).show();
                    startActivity(eIntent);
                }
                else {
                    Table table = assignTable(tableList);
                    if (table != null) {
                        if (dbHelper.setCustomerToTable(table.getTable_id(), customerId)
                        && dbHelper.updateTableState("DINING", table.getTable_id())) {
                            Intent eIntent = new Intent(CustomerMainActivity.this, CustomerMenuActivity.class);
                            eIntent.putExtra("customer_id", customerId);
                            Toast.makeText(CustomerMainActivity.this,
                                    "已为您分配桌号："+table.getTable_id()+"!", Toast.LENGTH_SHORT).show();
                            startActivity(eIntent);
                        }
                        else {
                            Toast.makeText(CustomerMainActivity.this, "分配桌号失败！请排队等候或询问工作人员！",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(CustomerMainActivity.this, "分配桌号失败！请排队等候或询问工作人员！",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.eat_now_mode2:
                Intent hIntent = new Intent(CustomerMainActivity.this, CustomerOrderActivity.class);
                hIntent.putExtra("customer_id", customerId);
                startActivity(hIntent);
                break;
            default:
                break;
        }
    }

    public Table assignTable (List<Table> tableList) {
        for (Table table : tableList) {
            if(table.getType() == TableType.AVAILABLE){
                return table;
            }
        }
        return null;
    }
}
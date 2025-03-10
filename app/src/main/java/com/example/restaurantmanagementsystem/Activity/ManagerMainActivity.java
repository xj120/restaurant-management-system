package com.example.restaurantmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.restaurantmanagementsystem.R;

public class ManagerMainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_main_layout);

        // 获取实例
        Button employeeButton = (Button) findViewById(R.id.employee_manage);
        Button menuButton = (Button) findViewById(R.id.menu_manage);
        Button tableButton = (Button) findViewById(R.id.table_manage);
        Button billButton = (Button) findViewById(R.id.bill_manage);

        // 监听
        employeeButton.setOnClickListener(this);
        tableButton.setOnClickListener(this);
        menuButton.setOnClickListener(this);
        billButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.employee_manage:
                Intent eIntent = new Intent(ManagerMainActivity.this, ManagerEmployeeActivity.class);
                startActivity(eIntent);
                break;
            case R.id.table_manage:
                Intent tIntent = new Intent(ManagerMainActivity.this, ManagerTableActivity.class);
                startActivity(tIntent);
                break;
            case R.id.bill_manage:
                Intent bIntent = new Intent(ManagerMainActivity.this, ManagerAccountsActivity.class);
                startActivity(bIntent);
                break;
            case R.id.menu_manage:
                Intent mIntent = new Intent(ManagerMainActivity.this, ManagerMenuActivity.class);
                startActivity(mIntent);
                break;
            default:
                break;
        }
    }
}
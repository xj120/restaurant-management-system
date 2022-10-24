package com.example.restaurantmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.restaurantmanagementsystem.DatabaseHelper.DatabaseHelper;
import com.example.restaurantmanagementsystem.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initialization_layout);

        dbHelper = new DatabaseHelper(this, "Restaurant.db", null, 2);
        dbHelper.getWritableDatabase();

        Button customerButton = (Button) findViewById(R.id.customer_mode);
        Button managerButton = (Button) findViewById(R.id.manager_mode);

        customerButton.setOnClickListener(this);
        managerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.customer_mode:
                Intent cIntent = new Intent(MainActivity.this, CustomerLoginActivity.class);
                startActivity(cIntent);
                break;
            case R.id.manager_mode:
                Intent mIntent = new Intent(MainActivity.this, ManagerLoginActivity.class);
                startActivity(mIntent);
                break;
            default:
                break;
        }
    }

}
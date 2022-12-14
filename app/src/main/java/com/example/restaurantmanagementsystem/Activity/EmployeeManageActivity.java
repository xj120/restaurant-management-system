package com.example.restaurantmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.restaurantmanagementsystem.Adapter.EmployeeAdapter;
import com.example.restaurantmanagementsystem.DatabaseHelper.DatabaseHelper;
import com.example.restaurantmanagementsystem.R;
import com.example.restaurantmanagementsystem.User.Employee;

import java.util.List;

public class EmployeeManageActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_information_management);

        dbHelper = new DatabaseHelper(this, "Restaurant.db", null, 2);

        List<Employee> employeeList = dbHelper.getEmployeeList();

        EmployeeAdapter adapter = new EmployeeAdapter(EmployeeManageActivity.this,
                R.layout.employee_item, employeeList);

        ListView listView = (ListView) findViewById(R.id.lv_employee);
        listView.setAdapter(adapter);
    }

}
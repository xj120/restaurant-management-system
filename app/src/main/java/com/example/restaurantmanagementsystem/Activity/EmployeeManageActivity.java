package com.example.restaurantmanagementsystem.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.Adapter.EmployeeAdapter;
import com.example.restaurantmanagementsystem.DatabaseHelper.DatabaseHelper;
import com.example.restaurantmanagementsystem.R;
import com.example.restaurantmanagementsystem.Table.Table;
import com.example.restaurantmanagementsystem.User.Employee;

import java.util.List;

public class EmployeeManageActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private List<Employee> employeeList;
    private EmployeeAdapter adapter;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_information_management);

        dbHelper = new DatabaseHelper(this, "Restaurant.db", null, 2);

        employeeList = dbHelper.getEmployeeList();

        adapter = new EmployeeAdapter(EmployeeManageActivity.this,
                R.layout.employee_item, employeeList, mListener);

        listView = (ListView) findViewById(R.id.lv_employee);
        listView.setAdapter(adapter);
    }

    private EmployeeAdapter.MyClickListener mListener = new EmployeeAdapter.MyClickListener() {
        @Override
        public void myOnClick(int position, View v) {
            Employee emp = employeeList.get(position);
            int empId = emp.getUser_id();
            if (dbHelper.deleteEmployee(empId)) {
                Toast.makeText(EmployeeManageActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
            else {
                Toast.makeText(EmployeeManageActivity.this, "删除失败！", Toast.LENGTH_SHORT).show();
            }
        }
    };

}
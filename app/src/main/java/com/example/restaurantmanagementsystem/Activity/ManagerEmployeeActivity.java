package com.example.restaurantmanagementsystem.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.Adapter.EmployeeAdapter;
import com.example.restaurantmanagementsystem.DatabaseHelper.DatabaseHelper;
import com.example.restaurantmanagementsystem.R;
import com.example.restaurantmanagementsystem.User.Employee;

import java.util.List;

public class ManagerEmployeeActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private List<Employee> employeeList;
    private EmployeeAdapter adapter;
    private ListView listView;
    private Button employeeAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_information_management);

        dbHelper = new DatabaseHelper(this, "Restaurant.db", null, 2);

        // 从数据库获取数据源
        employeeList = dbHelper.getEmployeeList();

        // 初始化适配器
        adapter = new EmployeeAdapter(ManagerEmployeeActivity.this,
                R.layout.employee_item, employeeList, mListener);

        listView = (ListView) findViewById(R.id.lv_employee);
        listView.setAdapter(adapter);

        // 建立的监听事件
        employeeAdd = (Button) findViewById(R.id.employee_add);
        employeeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View addView = View.inflate(ManagerEmployeeActivity.this, R.layout.add_employee_layout, null);
                AlertDialog alertDialog = new AlertDialog.Builder(ManagerEmployeeActivity.this)
                        .setTitle("新增员工")
                        .setView(addView)
                        .create();
                alertDialog.show();
                EditText name = addView.findViewById(R.id.add_employee_name);
                EditText phone = addView.findViewById(R.id.add_employee_mobile);
                Button confirm = addView.findViewById(R.id.addemp_save_pop);
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (dbHelper.addEmployee(name.getText().toString(), phone.getText().toString())) {
                            Toast.makeText(ManagerEmployeeActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
                            adapter.notifyDataSetChanged();
                        }
                        else {
                            Toast.makeText(ManagerEmployeeActivity.this, "添加失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    // 建立LISTVIEW子项控件的监听事件
    private EmployeeAdapter.MyClickListener mListener = new EmployeeAdapter.MyClickListener() {
        @Override
        public void myOnClick(int position, View v) {
            Employee emp = employeeList.get(position);
            int empId = emp.getUser_id();
            if (dbHelper.deleteEmployee(empId)) {
                Toast.makeText(ManagerEmployeeActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
            else {
                Toast.makeText(ManagerEmployeeActivity.this, "删除失败！", Toast.LENGTH_SHORT).show();
            }
        }
    };

}
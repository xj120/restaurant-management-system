package com.example.restaurantmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.DatabaseHelper.DatabaseHelper;
import com.example.restaurantmanagementsystem.R;

public class ManagerLoginActivity extends AppCompatActivity {

    private Button managerLoginButton;
    private EditText managerAccount;
    private EditText managerPassword;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_login_layout);
        // 获取实例
        managerLoginButton = (Button) findViewById(R.id.manager_login);
        managerAccount = (EditText) findViewById(R.id.manager_login_account);
        managerPassword = (EditText) findViewById(R.id.manager_login_password);

        dbHelper = new DatabaseHelper(this, "Restaurant.db", null, 2);

        // 监听
        managerLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String manAccountStr = managerAccount.getText().toString();
                String manPasswordStr = managerPassword.getText().toString();
                if(dbHelper.managerLogin(manAccountStr, manPasswordStr)) {
                    Toast.makeText(ManagerLoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                    Intent mIntent = new Intent(ManagerLoginActivity.this, ManagerMainActivity.class);
                    startActivity(mIntent);
                }
                else{
                    Toast.makeText(ManagerLoginActivity.this, "登录失败！请检查账号密码！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
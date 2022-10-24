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

public class CustomerLoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button customerLoginButton;
    private Button customerRegisterButton;
    private EditText customerAccount;
    private EditText customerPassword;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_login_layout);
        customerLoginButton = (Button) findViewById(R.id.customer_login);
        customerRegisterButton = (Button) findViewById(R.id.customer_register);
        customerAccount = (EditText) findViewById(R.id.customer_login_account);
        customerPassword = (EditText) findViewById(R.id.customer_login_password);

        dbHelper = new DatabaseHelper(this, "Restaurant.db", null, 2);

        customerLoginButton.setOnClickListener(this);
        customerRegisterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.customer_login:
                String cusAccountStr = customerAccount.getText().toString();
                String cusPasswordStr = customerPassword.getText().toString();
                if(dbHelper.customerLogin(cusAccountStr, cusPasswordStr)){
                    Toast.makeText(CustomerLoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                    Intent lIntent = new Intent(CustomerLoginActivity.this, CustomerMainActivity.class);
                    startActivity(lIntent);
                }
                else{
                    Toast.makeText(CustomerLoginActivity.this, "登录失败！请检查账号密码！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.customer_register:
                Intent rIntent = new Intent(CustomerLoginActivity.this, CustomerRegisterActivity.class);
                startActivity(rIntent);
                break;
            default:
                break;
        }
    }
}
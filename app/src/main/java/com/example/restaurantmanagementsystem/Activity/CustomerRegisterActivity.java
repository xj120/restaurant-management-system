package com.example.restaurantmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.DatabaseHelper.DatabaseHelper;
import com.example.restaurantmanagementsystem.R;

public class CustomerRegisterActivity extends AppCompatActivity{

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_register_layout);
        Button confirmRegister = (Button) findViewById(R.id.customer_register_confirm);
        EditText phone = (EditText) findViewById(R.id.customer_register_phone);
        EditText registerPassword = (EditText) findViewById(R.id.customer_register_password);
        EditText confirmPassword = (EditText) findViewById(R.id.customer_password_confirm);

        dbHelper = new DatabaseHelper(this, "Restaurant.db", null, 2);

        confirmRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneStr = phone.getText().toString();
                String regPasswordStr = registerPassword.getText().toString();
                String conPasswordStr = confirmPassword.getText().toString();
                if(!regPasswordStr.equals(conPasswordStr)) {
                    Toast.makeText(CustomerRegisterActivity.this, "两次密码输入不一致！", Toast.LENGTH_SHORT).show();
                }
                else if(phoneStr.matches("^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\\\d{8}$"))
                {
                    Toast.makeText(CustomerRegisterActivity.this, "请输入正确格式的手机号！", Toast.LENGTH_SHORT).show();
                }
                else if(dbHelper.checkCustomerPhone(phoneStr)){
                    Toast.makeText(CustomerRegisterActivity.this, "该手机号已注册！", Toast.LENGTH_SHORT).show();
                }
                else if(!regPasswordStr.matches("(?![0-9A-Z]+$)(?![0-9a-z]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,18}$")){
                    Toast.makeText(CustomerRegisterActivity.this, "您输入的密码不符合要求，须同时包含数字、大小写字母，且至少六位", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(dbHelper.insertCustomer(phoneStr, regPasswordStr)){
                        Toast.makeText(CustomerRegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                        Intent rIntent = new Intent(CustomerRegisterActivity.this, CustomerMainActivity.class);
                        startActivity(rIntent);
                    }
                    else{
                        Toast.makeText(CustomerRegisterActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
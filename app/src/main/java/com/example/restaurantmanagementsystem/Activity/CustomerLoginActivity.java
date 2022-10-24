package com.example.restaurantmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.restaurantmanagementsystem.DatabaseHelper.DatabaseHelper;
import com.example.restaurantmanagementsystem.R;

public class CustomerLoginActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_login_layout);
        Button customerLoginButton = (Button) findViewById(R.id.customer_login);
        Button customerRegisterButton = (Button) findViewById(R.id.customer_register);
        EditText customerAccount = (EditText) findViewById(R.id.customer_login_account);
        EditText customerPassword = (EditText) findViewById(R.id.customer_login_password);
    }

    @Override
    public void onClick(View v) {

    }
}
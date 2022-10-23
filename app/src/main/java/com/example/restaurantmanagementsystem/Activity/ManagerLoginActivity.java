package com.example.restaurantmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.restaurantmanagementsystem.R;

public class ManagerLoginActivity extends AppCompatActivity {

    private Button managerLoginButton;

    private EditText customerAccount;

    private EditText customerPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_login_layout);
        managerLoginButton = (Button) findViewById(R.id.manager_login);
        customerAccount = (EditText) findViewById(R.id.manager_login_account);
        customerPassword = (EditText) findViewById(R.id.manager_login_password);
    }
}
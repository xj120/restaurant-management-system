package com.example.restaurantmanagementsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.restaurantmanagementsystem.Adapter.BillAdapter;
import com.example.restaurantmanagementsystem.Bill.Bill;
import com.example.restaurantmanagementsystem.DatabaseHelper.DatabaseHelper;
import com.example.restaurantmanagementsystem.R;

import java.util.List;

public class AccountsManageActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private List<Bill> billList;
    private BillAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accounts_management);

        dbHelper = new DatabaseHelper(this, "Restaurant.db", null, 2);

        billList = dbHelper.getBillList();

        adapter =  new BillAdapter(AccountsManageActivity.this, R.layout.account_item, billList);

        listView = (ListView) findViewById(R.id.lv_accounts);
        listView.setAdapter(adapter);
    }
}
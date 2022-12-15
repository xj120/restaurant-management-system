package com.example.restaurantmanagementsystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.restaurantmanagementsystem.Bill.Bill;
import com.example.restaurantmanagementsystem.R;

import java.util.List;

import javax.xml.transform.Templates;

public class BillAdapter extends ArrayAdapter<Bill> {

    private int resourceId;

    public BillAdapter(Context context, int textViewResourceId, List<Bill> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Bill bill = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.accountId = (TextView) view.findViewById(R.id.account_id);
            viewHolder.customerId = (TextView) view.findViewById(R.id.customer_id);
            viewHolder.amountBill = (TextView) view.findViewById(R.id.amount_bill);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.accountId.setText(String.valueOf(bill.getBill_id()));
        viewHolder.customerId.setText(String.valueOf(bill.getCustomer_id()));
        viewHolder.amountBill.setText(String.valueOf(bill.getPrice()));
        return view;
    }

    class ViewHolder {
        TextView accountId;
        TextView customerId;
        TextView amountBill;
    }
}

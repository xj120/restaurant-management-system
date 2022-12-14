package com.example.restaurantmanagementsystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.restaurantmanagementsystem.R;
import com.example.restaurantmanagementsystem.User.Employee;

import java.util.List;

public class EmployeeAdapter extends ArrayAdapter<Employee> {

    private int resourceId;
    private MyClickListener myClickListener;

    public EmployeeAdapter(Context context, int textViewResourceId, List<Employee> objects, MyClickListener listener) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        myClickListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Employee employee = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.employeeId = (TextView) view.findViewById(R.id.employee_id);
            viewHolder.employeeName = (TextView) view.findViewById(R.id.employee_name);
            viewHolder.employeePhone = (TextView) view.findViewById(R.id.employee_phone);
            viewHolder.employeeDelete = (Button) view.findViewById(R.id.employee_delete);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.employeeId.setText(String.valueOf(employee.getUser_id()));
        viewHolder.employeeName.setText(employee.getName());
        viewHolder.employeePhone.setText(employee.getAccount());
        viewHolder.employeeDelete.setOnClickListener(myClickListener);
        viewHolder.employeeDelete.setTag(position);
        return view;
    }

    class ViewHolder {
        TextView employeeId;
        TextView employeeName;
        TextView employeePhone;
        Button employeeDelete;
    }

    public static abstract class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            myOnClick((Integer)v.getTag(), v);
        }
        public abstract void myOnClick(int position, View v);
    }

}

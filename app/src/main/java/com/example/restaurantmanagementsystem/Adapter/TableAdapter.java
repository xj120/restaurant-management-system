package com.example.restaurantmanagementsystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagementsystem.Table.Table;

import com.example.restaurantmanagementsystem.R;

import java.util.List;

public class TableAdapter extends ArrayAdapter<Table> {

    private int resourceId;
    private MyClickListener myClickListener;


    public TableAdapter(Context context, int textViewResourceId, List<Table> objects, MyClickListener listener) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        myClickListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Table table = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tableId = (TextView) view.findViewById(R.id.tv_table_id_show);
            viewHolder.state = (TextView) view.findViewById(R.id.tv_table_state_show);
            viewHolder.modify = (Button) view.findViewById(R.id.table_modify);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tableId.setText(String.valueOf(table.getTable_id()));
        viewHolder.state.setText(table.getType().toString());
        viewHolder.modify.setOnClickListener(myClickListener);
        viewHolder.modify.setTag(position);
        return view;
    }

    class ViewHolder {
        TextView tableId;
        Button modify;
        TextView state;
    }

    public static abstract class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            myOnClick((Integer)v.getTag(), v);
        }
        public abstract void myOnClick(int position, View v);
    }

}

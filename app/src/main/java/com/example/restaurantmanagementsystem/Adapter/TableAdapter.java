package com.example.restaurantmanagementsystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.restaurantmanagementsystem.Table.Table;

import com.example.restaurantmanagementsystem.R;

import java.util.List;

public class TableAdapter extends ArrayAdapter<Table> {

    private int resourceId;

    public TableAdapter(Context context, int textViewResourceId, List<Table> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Table table = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView tableId = (TextView) view.findViewById(R.id.tv_table_id_show);
        TextView state = (TextView) view.findViewById(R.id.tv_table_state_show);
        tableId.setText(String.valueOf(table.getTable_id()));
        state.setText(table.getType().toString());
        return view;
    }

}

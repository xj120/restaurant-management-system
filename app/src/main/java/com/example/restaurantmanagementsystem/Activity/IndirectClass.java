package com.example.restaurantmanagementsystem.Activity;

import android.app.Activity;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public class IndirectClass {
    private Context contxt;
    private AppCompatActivity activity;

    public Context getContxt() {
        return contxt;
    }

    public void setContxt(Context contxt) {
        this.contxt = (AppCompatActivity) contxt;
    }

    public Activity getActivity() {
        return (AppCompatActivity) activity;
    }

    public void setActivity(Activity activity) {
        this.activity = (AppCompatActivity) activity;
    }

    public IndirectClass(Context context, AppCompatActivity activity) {
        this.setContxt(context);
        this.setActivity(activity);
    }
}

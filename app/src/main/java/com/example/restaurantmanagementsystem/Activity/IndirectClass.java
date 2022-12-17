package com.example.restaurantmanagementsystem.Activity;

import android.app.Activity;
import android.content.Context;

public class IndirectClass {
    private Context contxt;
    private CustomerMenuActivity activity;

    public Context getContxt() {
        return contxt;
    }

    public void setContxt(Context contxt) {
        this.contxt = (CustomerMenuActivity) contxt;
    }

    public Activity getActivity() {
        return (CustomerMenuActivity) activity;
    }

    public void setActivity(Activity activity) {
        this.activity = (CustomerMenuActivity) activity;
    }

    public IndirectClass(Context context, CustomerMenuActivity activity) {
        this.setContxt(context);
        this.setActivity(activity);
    }
}

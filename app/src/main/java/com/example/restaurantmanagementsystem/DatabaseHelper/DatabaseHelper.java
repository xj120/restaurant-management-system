package com.example.restaurantmanagementsystem.DatabaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String db_name = "restaurant.db";

    private Context mContext;

    public static final String CREATE_CUSTOMER = "CREATE TABLE Customer ("
            + "customer_id INTEGER NOT NULL,"
            + "password TEXT NOT NULL,"
            + "phone TEXT,"
            + "PRIMARY KEY (customer_id));";

    public static final String CREATE_DININGTABLE = "CREATE TABLE \"Diningtable\" (\n" +
            "  \"table_id\" INTEGER NOT NULL,\n" +
            "  \"customer_id\" INTEGER,\n" +
            "  \"state\" TEXT,\n" +
            "  PRIMARY KEY (\"table_id\"),\n" +
            "  CONSTRAINT \"customer_id\" FOREIGN KEY (\"customer_id\") REFERENCES \"Customer\" (\"customer_id\") ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
            ");";

    public static final String CREATE_BILL = "CREATE TABLE \"Bill\" (\n" +
            "  \"bill_id\" INTEGER NOT NULL,\n" +
            "  \"customer_id\" INTEGER,\n" +
            "  \"price\" integer,\n" +
            "  PRIMARY KEY (\"bill_id\"),\n" +
            "  CONSTRAINT \"customer_id\" FOREIGN KEY (\"customer_id\") REFERENCES \"Customer\" (\"customer_id\") ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
            ");";

    public static final String CREATE_EMPLOYEE = "CREATE TABLE \"Employee\" (\n" +
            "  \"emp_id\" INTEGER NOT NULL,\n" +
            "  \"name\" TEXT,\n" +
            "  \"phone\" TEXT,\n" +
            "  \"password\" TEXT,\n" +
            "  PRIMARY KEY (\"emp_id\")\n" +
            ");";

    public static final String CREATE_MANAGER = "CREATE TABLE \"Manager\" (\n" +
            "  \"man_id\" INTEGER NOT NULL,\n" +
            "  \"password\" TEXT,\n" +
            "  \"phone\" TEXT,\n" +
            "  \"name\" TEXT,\n" +
            "  PRIMARY KEY (\"man_id\")\n" +
            ");";

    public static final String CREATE_MENU = "CREATE TABLE \"Menu\" (\n" +
            "  \"dish_name\" TEXT NOT NULL,\n" +
            "  \"price\" integer,\n" +
            "  \"sales\" integer,\n" +
            "  PRIMARY KEY (\"dish_name\")\n" +
            ");";

    public static final String CREATE_ORDER = "CREATE TABLE \"Order\" (\n" +
            "  \"order_id\" INTEGER NOT NULL,\n" +
            "  \"dish_name\" TEXT NOT NULL,\n" +
            "  \"quantity\" integer,\n" +
            "  \"price\" integer,\n" +
            "  \"state\" TEXT,\n" +
            "  PRIMARY KEY (\"order_id\", \"dish_name\"),\n" +
            "  CONSTRAINT \"dish_name\" FOREIGN KEY (\"dish_name\") REFERENCES \"Menu\" (\"dish_name\") ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
            ");";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CUSTOMER);
        db.execSQL(CREATE_DININGTABLE);
        db.execSQL(CREATE_BILL);
        db.execSQL(CREATE_EMPLOYEE);
        db.execSQL(CREATE_MANAGER);
        db.execSQL(CREATE_MENU);
        db.execSQL(CREATE_ORDER);
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

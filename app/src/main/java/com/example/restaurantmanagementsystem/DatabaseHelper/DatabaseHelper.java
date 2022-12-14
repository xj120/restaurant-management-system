package com.example.restaurantmanagementsystem.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.Table.Table;
import com.example.restaurantmanagementsystem.Table.TableType;
import com.example.restaurantmanagementsystem.User.Employee;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String db_name = "restaurant.db";
    private static final String TAG = "DatabaseHelper";

    private Context mContext;

    public static final String CREATE_CUSTOMER = "CREATE TABLE \"Customer\" (\n" +
            "  \"customer_id\" INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  \"password\" TEXT NOT NULL,\n" +
            "  \"phone\" TEXT\n" +
            ");";

    public static final String CREATE_DININGTABLE = "CREATE TABLE \"Diningtable\" (\n" +
            "  \"table_id\" INTEGER NOT NULL,\n" +
            "  \"customer_id\" INTEGER,\n" +
            "  \"state\" TEXT,\n" +
            "  PRIMARY KEY (\"table_id\"),\n" +
            "  CONSTRAINT \"customer_id\" FOREIGN KEY (\"customer_id\") REFERENCES \"Customer\" (\"customer_id\") ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
            ");";

    public static final String CREATE_BILL = "CREATE TABLE \"Bill\" (\n" +
            "  \"bill_id\" INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  \"customer_id\" INTEGER,\n" +
            "  \"price\" real,\n" +
            "  CONSTRAINT \"customer_id\" FOREIGN KEY (\"customer_id\") REFERENCES \"Customer\" (\"customer_id\") ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
            ");";

    public static final String CREATE_EMPLOYEE = "CREATE TABLE \"Employee\" (\n" +
            "  \"emp_id\" INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  \"name\" TEXT,\n" +
            "  \"phone\" TEXT\n" +
            ");";

    public static final String CREATE_MANAGER = "CREATE TABLE \"Manager\" (\n" +
            "  \"man_id\" INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  \"password\" TEXT,\n" +
            "  \"phone\" TEXT,\n" +
            "  \"name\" TEXT\n" +
            ");";

    public static final String CREATE_MENU = "CREATE TABLE \"Menu\" (\n" +
            "  \"dish_name\" TEXT NOT NULL,\n" +
            "  \"price\" real,\n" +
            "  PRIMARY KEY (\"dish_name\")\n" +
            ");";

    public static final String CREATE_ORDER = "CREATE TABLE \"Order\" (\n" +
            "  \"order_id\" INTEGER NOT NULL,\n" +
            "  \"dish_name\" TEXT NOT NULL,\n" +
            "  \"customer_id\" integer,\n" +
            "  \"quantity\" integer,\n" +
            "  \"price\" real,\n" +
            "  \"state\" TEXT,\n" +
            "  PRIMARY KEY (\"order_id\", \"dish_name\"),\n" +
            "  CONSTRAINT \"dish_name\" FOREIGN KEY (\"dish_name\") REFERENCES \"Menu\" (\"dish_name\") ON DELETE NO ACTION ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT \"customer_id\" FOREIGN KEY (\"customer_id\") REFERENCES \"Customer\" (\"customer_id\") ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
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

    public boolean insertCustomer(String phone, String password) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("password", password);
            values.put("phone", phone);
            db.insert("Customer", null, values);
            return true;
        }catch (Exception e)
        {
            Log.e(TAG, "insertCustomer: insert failed!", e);
            return false;
        }
    }

    public boolean checkCustomerPhone(String phone) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.query("Customer",null, "phone=?",
                    new String[]{phone}, null, null, null);
            if(cursor.moveToFirst()){
                cursor.close();
                return true;
            }
            else{
                cursor.close();
                return false;
            }
        }catch (Exception e){
            Log.e(TAG, "checkCustomerPhone: query failed!", e);
            return false;
        }
    }

    public boolean customerLogin(String phone, String password) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.query("Customer",null, "phone=? and password=?",
                    new String[]{phone, password}, null, null, null);
            if(cursor.moveToFirst()){
                cursor.close();
                return true;
            }
            else{
                cursor.close();
                return false;
            }
        }catch (Exception e){
            Log.e(TAG, "customerLogin: login failed!", e);
            return false;
        }
    }

    public boolean managerLogin(String phone, String password) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.query("Manager",null, "phone=? and password=?",
                    new String[]{phone, password}, null, null, null);
            if(cursor.moveToFirst()){
                cursor.close();
                return true;
            }
            else{
                cursor.close();
                return false;
            }
        }catch (Exception e){
            Log.e(TAG, "managerLogin: login failed!", e);
            return false;
        }
    }

    public List<Table> getTableList() {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            List<Table> tableList = new ArrayList<>();
            Cursor cursor = db.query("Diningtable",null,null,null,
                    null,null,null);
            if(cursor.moveToFirst()) {
                do{
                    int table_id = cursor.getInt(cursor.getColumnIndexOrThrow("table_id"));
                    String state = cursor.getString(cursor.getColumnIndexOrThrow("state"));
                    TableType type = null;
                    if(state.equals("AVAILABLE")){
                        type = TableType.AVAILABLE;
                    }else if(state.equals("DINING")){
                        type = TableType.DINING;
                    }else if(state.equals("CLEANING")){
                        type = TableType.CLEANING;
                    }
                    Table table = new Table(table_id, type);
                    tableList.add(table);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return tableList;
        }catch (Exception e){
            Log.e(TAG, "getTableList: query failed!", e);
            return null;
        }
    }


    public List<Employee> getEmployeeList() {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            List<Employee> employeeList = new ArrayList<>();
            Cursor cursor = db.query("Employee", null, null, null,
                    null, null, null);
            if(cursor.moveToFirst()) {
                do {
                    int employeeId = cursor.getInt(cursor.getColumnIndexOrThrow("emp_id"));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                    Employee emp = new Employee(employeeId, phone, name);
                    employeeList.add(emp);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return employeeList;
        }catch (Exception e){
            Log.e(TAG, "getEmployeeList: query failded!", e);
            return null;
        }
    }


    public boolean updateTableState (String state, int tableId) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("state", state);
            db.update("Diningtable", values, "table_id = ?",
                    new String[] {String.valueOf(tableId)});
            return true;
        }catch (Exception e){
            Log.e(TAG, "updateTableState: update failed!", e);
            return false;
        }
    }


    public boolean deleteEmployee (int empId) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("Employee", "emp_id = ?", new String[] {String.valueOf(empId)});
            return true;
        }catch (Exception e) {
            Log.e(TAG, "deleteEmployee: delete failed!", e);
            return false;
        }
    }
}

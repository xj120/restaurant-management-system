package com.example.restaurantmanagementsystem.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.Bill.Bill;
import com.example.restaurantmanagementsystem.Dish.Dish;
import com.example.restaurantmanagementsystem.Order.Order;
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

    // 以下为建表语句
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

    // 插入顾客数据
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

    // 检查顾客手机号是否已存在于数据库
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

    // 顾客登录检查函数
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


    // 管理人员登录检查函数
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


    // 得到餐桌列表
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


    // 得到员工列表
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


    // 更新餐桌的状态
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


    // 删除员工
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


    // 添加员工
    public boolean addEmployee (String name, String phone) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("phone", phone);
            db.insert("Employee", null, values);
            return true;
        }catch (Exception e) {
            Log.e(TAG, "addEmployee: add failed!", e);
            return false;
        }
    }


    // 得到账单列表
    public List<Bill> getBillList () {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            List<Bill> billList = new ArrayList<>();
            Cursor cursor = db.query("Bill", null, null, null,
                    null, null, null);
            if(cursor.moveToFirst()) {
                do {
                    int billId = cursor.getInt(cursor.getColumnIndexOrThrow("bill_id"));
                    int customerId = cursor.getInt(cursor.getColumnIndexOrThrow("customer_id"));
                    double price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));
                    Bill bill = new Bill(billId, customerId, price);
                    billList.add(bill);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return billList;
        }catch (Exception e){
            Log.e(TAG, "getBillList: query failed!", e);
            return null;
        }
    }


    // 得到菜单列表
    public List<Dish> getDishList () {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            List<Dish> dishList = new ArrayList<>();
            Cursor cursor = db.query("Menu", null, null, null,
                    null, null, null);
            if(cursor.moveToFirst()) {
                do {
                    String dishName = cursor.getString(cursor.getColumnIndexOrThrow("dish_name"));
                    double price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));
                    Dish dish = new Dish(dishName, price);
                    dishList.add(dish);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return dishList;
        }catch (Exception e){
            Log.e(TAG, "getDishList: query failed!", e);
            return null;
        }
    }


    // 删除菜品
    public boolean deleteDish (String dishName) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("Menu", "dish_name = ?", new String[] {dishName});
            return true;
        }catch (Exception e) {
            Log.e(TAG, "deleteDish: delete failed!", e);
            return false;
        }
    }


    // 修改菜品
    public boolean updateDish (String dishName, String mdishName, double mprice) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("dish_name", mdishName);
            values.put("price", mprice);
            db.update("Menu", values, "dish_name = ?",
                    new String[] {dishName});
            return true;
        }catch (Exception e){
            Log.e(TAG, "updateDish: update failed!", e);
            return false;
        }
    }


    // 添加菜品
    public boolean addDish (String dishName, String price) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("dish_name", dishName);
            values.put("price", price);
            db.insert("Menu", null, values);
            return true;
        }catch (Exception e) {
            Log.e(TAG, "addDish: add failed!", e);
            return false;
        }
    }


    // 设置顾客用餐
    public boolean setCustomerToTable (int tableId, int customerId) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("customer_id", customerId);
            db.update("Diningtable", values, "table_id = ?",
                    new String[] {String.valueOf(tableId)});
            return true;
        }catch (Exception e){
            Log.e(TAG, "setCustomerToTable: update failed!", e);
            return false;
        }
    }


    // 用餐结束
    public boolean removeCustomerToTable (int tableId) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            String sql = "UPDATE Diningtable SET customer_id = NULL WHERE table_id = " + tableId + ";";
            db.execSQL(sql);
            return true;
        }catch (Exception e){
            Log.e(TAG, "removeCustomerToTable: update failed!", e);
            return false;
        }
    }


    // 添加账单
    public boolean addBill (int customerId, double price) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("customer_id", customerId);
            values.put("price", price);
            db.insert("Bill", null, values);
            return true;
        }catch (Exception e) {
            Log.e(TAG, "addBill: add failed!", e);
            return false;
        }
    }


    // 获取最大的ORDER ID
    public int getMaxOrderId () {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            int maxId = 0;
            Cursor cursor = db.query("'Order'", null, null, null,
                    null, null, null);
            if(cursor.moveToLast()) {
                maxId = cursor.getInt(cursor.getColumnIndexOrThrow("order_id"));
            }
            cursor.close();
            return maxId;
        }catch (Exception e){
            Log.e(TAG, "getMaxOrderId: query failed!", e);
            return 0;
        }
    }


    // 添加订单
    public boolean addOrder (int orderId, int customerId, Dish dish) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("order_id", orderId);
            values.put("customer_id", customerId);
            values.put("dish_name", dish.getDish_name());
            values.put("quantity", dish.getQuantity());
            values.put("price", dish.getPrice());
            db.insert("'Order'", null, values);
            return true;
        }catch (Exception e) {
            Log.e(TAG, "addOrder: add failed!", e);
            return false;
        }
    }


    // 得到一个顾客的订单
    public List<Order> getOrderByCustomer (int customerId) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            List<Order> historyOrder = new ArrayList<>();
            Cursor cursor = db.query("'Order'", null, "customer_id=?",
                    new String[]{String.valueOf(customerId)}, null, null, null);
            if(cursor.moveToFirst()) {
                do {
                    int orderId = cursor.getInt(cursor.getColumnIndexOrThrow("order_id"));
                    String dishName = cursor.getString(cursor.getColumnIndexOrThrow("dish_name"));
                    int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));
                    double price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));
                    Dish dish = new Dish(dishName, price, quantity);
                    Order order = new Order(orderId, dish);
                    historyOrder.add(order);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return historyOrder;
        }catch (Exception e){
            Log.e(TAG, "getOrderByCustomer: query failed!", e);
            return null;
        }
    }


    // 检查该顾客是否用餐中
    public boolean checkCustomerInTable (int customerId) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.query("Diningtable",null, "customer_id=?",
                    new String[]{String.valueOf(customerId)}, null, null, null);
            if(cursor.moveToFirst()){
                cursor.close();
                return true;
            }
            else{
                cursor.close();
                return false;
            }
        }catch (Exception e){
            Log.e(TAG, "checkCustomerInTable: check failed!", e);
            return false;
        }
    }


    // 通过手机号得到该顾客的ID
    public int getCustomerIdByPhone (String phone) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            int customerId = 0;
            Cursor cursor = db.query("Customer", null, "phone=?",
                    new String[]{phone}, null, null, null);
            if(cursor.moveToFirst()) {
                customerId = cursor.getInt(cursor.getColumnIndexOrThrow("customer_id"));
            }
            cursor.close();
            return customerId;
        }catch (Exception e){
            Log.e(TAG, "getCustomerIdByPhone: query failed!", e);
            return 0;
        }
    }
}

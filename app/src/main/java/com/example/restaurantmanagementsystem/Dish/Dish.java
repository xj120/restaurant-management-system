package com.example.restaurantmanagementsystem.Dish;

import android.os.Parcel;
import android.os.Parcelable;

public class Dish implements Parcelable {
    private String dish_name;
    private double price;
    private int quantity;

    public Dish(String dish_name, double price, int quantity) {
        this.dish_name = dish_name;
        this.price = price;
        this.quantity = quantity;
    }

    public Dish(String dish_name, double price) {
        this.dish_name = dish_name;
        this.price = price;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dish_name);
        dest.writeDouble(price);
        dest.writeInt(quantity);
    }


    public static final Parcelable.Creator<Dish> CREATOR = new Creator<Dish>() {
        @Override
        public Dish createFromParcel(Parcel parcel) {
            return new Dish(parcel.readString(), parcel.readDouble(), parcel.readInt());
        }

        @Override
        public Dish[] newArray(int i) {
            return new Dish[i];
        }
    };


    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

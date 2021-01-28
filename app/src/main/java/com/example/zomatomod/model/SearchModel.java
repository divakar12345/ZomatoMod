package com.example.zomatomod.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchModel {

    @SerializedName("restaurants")
    ArrayList<RestaurantArrayModel> restaurantArrayModelArrayList;

    public ArrayList<RestaurantArrayModel> getRestaurantArrayModelArrayList() {
        return restaurantArrayModelArrayList;
    }
}

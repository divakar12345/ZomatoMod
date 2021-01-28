package com.example.zomatomod.model;

import com.google.gson.annotations.SerializedName;

public class RestaurantArrayModel {

    @SerializedName("restaurant")
    private RestaurantModel restaurantModel;

    public RestaurantModel getRestaurantModel() {
        return restaurantModel;
    }
}

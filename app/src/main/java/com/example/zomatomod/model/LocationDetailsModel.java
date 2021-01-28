package com.example.zomatomod.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LocationDetailsModel {

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("location")
    private LocationModel locationModel;

    @SerializedName("best_rated_restaurant")
    private ArrayList<RestaurantArrayModel> restaurantModelArrayList;

    public float getPopularity() {
        return popularity;
    }

    public LocationModel getLocationModel() {
        return locationModel;
    }

    public ArrayList<RestaurantArrayModel> getRestaurantModelArrayList() {
        return restaurantModelArrayList;
    }
}

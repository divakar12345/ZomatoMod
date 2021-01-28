package com.example.zomatomod.model;

import com.google.gson.annotations.SerializedName;

public class DishDetailsModel {

    @SerializedName("name")
    private String dishName;

    public String getDishName() {
        return dishName;
    }
}

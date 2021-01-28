package com.example.zomatomod.model;

import com.google.gson.annotations.SerializedName;

public class DishesModel {

    @SerializedName("dish")
    DishDetailsModel dishDetailsModel;

    public DishDetailsModel getDishDetailsModel() {
        return dishDetailsModel;
    }
}

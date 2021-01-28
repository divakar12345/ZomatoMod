package com.example.zomatomod.model;

import com.google.gson.annotations.SerializedName;

public class DailyMenuModel {

    @SerializedName("daily_menu")
    DishesArrayModel dishesArrayModel;

    public DishesArrayModel getDishesArrayModel() {
        return dishesArrayModel;
    }
}

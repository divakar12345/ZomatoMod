package com.example.zomatomod.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DishesArrayModel {

    @SerializedName("dishes")
    ArrayList<DishesModel> dishesModelArrayList;

    public ArrayList<DishesModel> getDishesModelArrayList() {
        return dishesModelArrayList;
    }
}

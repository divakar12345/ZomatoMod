package com.example.zomatomod.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DailyMenuArrayModel {

    @SerializedName("daily_menus")
    ArrayList<DailyMenuModel> dailyMenuModelArrayList;

    public ArrayList<DailyMenuModel> getDailyMenuModelArrayList() {
        return dailyMenuModelArrayList;
    }
}

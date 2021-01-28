package com.example.zomatomod.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LocationListModel {

    @SerializedName("location_suggestions")
    ArrayList<SuggestedLocationModel> suggestedLocationModelArrayList;

    public ArrayList<SuggestedLocationModel> getSuggestedLocationModelArrayList() {
        return suggestedLocationModelArrayList;
    }
}

package com.example.zomatomod.model;

import com.google.gson.annotations.SerializedName;

public class SuggestedLocationModel {

    @SerializedName("id")
    private int entityId;

    @SerializedName("name")
    private String cityName;

    public int getEntityId() {
        return entityId;
    }

    public String getCityName() {
        return cityName;
    }
}

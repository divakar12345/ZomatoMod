package com.example.zomatomod.model;

import com.google.gson.annotations.SerializedName;

public class LocationModel {

    @SerializedName("entity_id")
    private int entityId;

    @SerializedName("entity_type")
    private String entityType;

    @SerializedName("city_name")
    private String cityName;

    public int getEntityId() {
        return entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public String getCityName() {
        return cityName;
    }
}

package com.example.zomatomod.model;

import com.google.gson.annotations.SerializedName;

public class UserRatingModel {

    @SerializedName("aggregate_rating")
    private float aggregateRating;

    public float getAggregateRating() {
        return aggregateRating;
    }
}

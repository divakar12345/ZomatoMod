package com.example.zomatomod.model;

import com.google.gson.annotations.SerializedName;

public class RestaurantModel {

    @SerializedName("id")
    private int restaurantId;

    @SerializedName("name")
    private String restaurantName;

    @SerializedName("user_rating")
    private UserRatingModel userRatingModel;

    @SerializedName("cuisines")
    private String cuisines;

    @SerializedName("featured_image")
    private String featuredImage;

    @SerializedName("all_reviews_count")
    private int reviewCount;

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public UserRatingModel getUserRatingModel() {
        return userRatingModel;
    }

    public String getCuisines() {
        return cuisines;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public int getReviewCount() {
        return reviewCount;
    }
}

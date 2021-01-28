package com.example.zomatomod.model;

import com.google.gson.annotations.SerializedName;

public class UserReviewModel {

    @SerializedName("rating")
    private int userRating;

    @SerializedName("review_text")
    private String reviewText;

    @SerializedName("user")
    private UserModel userModel;

    public int getUserRating() {
        return userRating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public UserModel getUserModel() {
        return userModel;
    }
}

package com.example.zomatomod.model;

import com.google.gson.annotations.SerializedName;

public class UserReviewsArrayModel {

    @SerializedName("review")
    UserReviewModel userReviewModel;

    public UserReviewModel getUserReviewModel() {
        return userReviewModel;
    }
}

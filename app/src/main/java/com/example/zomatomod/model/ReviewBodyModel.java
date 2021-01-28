package com.example.zomatomod.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReviewBodyModel {

    @SerializedName("user_reviews")
    ArrayList<UserReviewsArrayModel> userReviewsModelArrayList;

    public ArrayList<UserReviewsArrayModel> getUserReviewsModelArrayList() {
        return userReviewsModelArrayList;
    }
}

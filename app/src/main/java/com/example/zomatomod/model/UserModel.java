package com.example.zomatomod.model;

import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("name")
    private String userName;

    public String getUserName() {
        return userName;
    }
}

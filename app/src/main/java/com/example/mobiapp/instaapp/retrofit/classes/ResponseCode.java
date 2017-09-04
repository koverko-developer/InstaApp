package com.example.mobiapp.instaapp.retrofit.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mobi app on 04.09.2017.
 */
public class ResponseCode {

    @SerializedName("access_token")
    @Expose
    private String access_token;

    @SerializedName("user")
    @Expose
    private User user;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

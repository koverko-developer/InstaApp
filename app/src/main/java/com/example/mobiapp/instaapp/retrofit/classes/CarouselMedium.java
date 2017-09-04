package com.example.mobiapp.instaapp.retrofit.classes;

/**
 * Created by mobi app on 04.09.2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarouselMedium {

    @SerializedName("images")
    @Expose
    private Images_ images;
    @SerializedName("users_in_photo")
    @Expose
    private List<Object> usersInPhoto = null;
    @SerializedName("type")
    @Expose
    private String type;

    public Images_ getImages() {
        return images;
    }

    public void setImages(Images_ images) {
        this.images = images;
    }

    public List<Object> getUsersInPhoto() {
        return usersInPhoto;
    }

    public void setUsersInPhoto(List<Object> usersInPhoto) {
        this.usersInPhoto = usersInPhoto;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
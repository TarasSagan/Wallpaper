
package com.example.taras.wallpers.api.ModelsOfResponse.likeResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LikeResponse {

    @SerializedName("photo")
    @Expose
    private Photo photo;
    @SerializedName("user")
    @Expose
    private User user;

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

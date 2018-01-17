package com.example.taras.wallpers.repository;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Photo {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String photoId;
    private long width;
    private long height;
    private String color;
    private String urlRAW;
    private String urlSMALL;
    private String urlTHUMB;

    public Photo(Integer id, String photoId, long width, long height, String color, String urlRAW, String urlSMALL, String urlTHUMB) {
        this.id = id;
        this.photoId = photoId;
        this.width = width;
        this.height = height;
        this.color = color;
        this.urlRAW = urlRAW;
        this.urlSMALL = urlSMALL;
        this.urlTHUMB = urlTHUMB;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUrlRAW() {
        return urlRAW;
    }

    public void setUrlRAW(String urlRAW) {
        this.urlRAW = urlRAW;
    }

    public String getUrlSMALL() {
        return urlSMALL;
    }

    public void setUrlSMALL(String urlSMALL) {
        this.urlSMALL = urlSMALL;
    }

    public String getUrlTHUMB() {
        return urlTHUMB;
    }

    public void setUrlTHUMB(String urlTHUMB) {
        this.urlTHUMB = urlTHUMB;
    }

}

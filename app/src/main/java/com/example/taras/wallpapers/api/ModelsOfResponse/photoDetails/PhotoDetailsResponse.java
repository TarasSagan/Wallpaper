
package com.example.taras.wallpapers.api.ModelsOfResponse.photoDetails;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoDetailsResponse {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("categories")
    @Expose
    private List<String> categories = null;
    @SerializedName("urls")
    @Expose
    private Urls urls;
    @SerializedName("links")
    @Expose
    private Links links;
    @SerializedName("liked_by_user")
    @Expose
    private Boolean likedByUser;
    @SerializedName("sponsored")
    @Expose
    private Boolean sponsored;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("current_user_collections")
    @Expose
    private List<String> currentUserCollections = null;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("exif")
    @Expose
    private Exif exif;
    @SerializedName("views")
    @Expose
    private Integer views;
    @SerializedName("downloads")
    @Expose
    private Integer downloads;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Boolean getLikedByUser() {
        return likedByUser;
    }

    public void setLikedByUser(Boolean likedByUser) {
        this.likedByUser = likedByUser;
    }

    public Boolean getSponsored() {
        return sponsored;
    }

    public void setSponsored(Boolean sponsored) {
        this.sponsored = sponsored;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getCurrentUserCollections() {
        return currentUserCollections;
    }

    public void setCurrentUserCollections(List<String> currentUserCollections) {
        this.currentUserCollections = currentUserCollections;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Exif getExif() {
        return exif;
    }

    public void setExif(Exif exif) {
        this.exif = exif;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

}

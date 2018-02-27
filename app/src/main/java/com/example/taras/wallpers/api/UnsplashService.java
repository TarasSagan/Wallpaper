package com.example.taras.wallpers.api;



import com.example.taras.wallpers.api.ModelsOfResponse.photo.PhotoItem;
import com.example.taras.wallpers.api.ModelsOfResponse.likeResponce.LikeResponse;
import com.example.taras.wallpers.api.ModelsOfResponse.profile.ProfileResponse;


import java.util.LinkedList;
import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UnsplashService {

    @POST("/photos/{id}/like")
    Flowable<LikeResponse> postLike(@Path("id") String id);

    @DELETE("/photos/{id}/like")
    Flowable<LikeResponse> postUnlike(@Path("id") String id);

    @GET("/photos/random")
    Flowable<List<PhotoItem>> getPhotosRandom(@Query("count") int count);

    @GET("/users/")
    Flowable<ProfileResponse> getProfile(@Query("username") String username);




    @GET("/photos/curated")
    Flowable<List<PhotoItem>> getPhotosCurated(@Query("page") int page,
                                               @Query("per_page") int perPage,
                                               @Query("order_by") String orderBy); //Valid values: latest, oldest, popular; default: latest


    @GET("/photos")
    Flowable<List<PhotoItem>> getPhotos(@Query("page") int page,
                                              @Query("per_page") int perPage,
                                              @Query("order_by") String orderBy); //Valid values: latest, oldest, popular; default: latest

}

package com.example.taras.wallpapers.api;



import com.example.taras.wallpapers.api.ModelsOfResponse.photo.PhotoItem;
import com.example.taras.wallpapers.api.ModelsOfResponse.likeResponse.LikeResponse;
import com.example.taras.wallpapers.api.ModelsOfResponse.photoDetails.PhotoDetailsResponse;
import com.example.taras.wallpapers.api.ModelsOfResponse.profile.ProfileResponse;
import com.example.taras.wallpapers.api.ModelsOfResponse.search.photos.SearchPhotosResponse;
import com.example.taras.wallpapers.api.ModelsOfResponse.search.users.SearchUsersResponse;


import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UnsplashService {

    @GET("/search/photos")
    Flowable<SearchPhotosResponse> searchPhotos (@Query("query") String query,
                                                @Query("page") int page, //Optional; default: 1
                                                @Query("per_page") int per_page //Optional; default: 10
//                                        @Query("orientation") String orientation //Filter search results by photo orientation. Valid values are landscape, portrait, and squarish.
                                        );
    @GET("/me")
    Flowable<ProfileResponse> getCurrentUserProfile();

    @GET("/search/users")
    Flowable<SearchUsersResponse> searchUsers (@Query("query") String query,
                                               @Query("page") int page, //Optional; default: 1
                                               @Query("per_page") int perPage);//Optional; default: 10

    @POST("/photos/{id}/like")
    Flowable<LikeResponse> postLike(@Path("id") String id);

    @DELETE("/photos/{id}/like")
    Flowable<LikeResponse> postUnlike(@Path("id") String id);

    @GET("/photos/random")
    Flowable<List<PhotoItem>> getPhotosRandom(@Query("count") int count);

    @GET("/photos/{id}")
    Flowable<PhotoDetailsResponse> getPhotoByID(@Path("id") String id);

    @GET("/users/{username}")
    Flowable<ProfileResponse> getProfile(@Path("username") String username);

    @GET("/users/{username}/photos")
    Flowable<List<PhotoItem>> getProfilePhotos(@Path("username") String username,
                                               @Query("page") int page, //Optional; default: 1
                                               @Query("per_page") int per_page, //Optional; default: 10
                                               @Query("order_by") String orderBy);//Valid values: latest, oldest, popular; default: latest
     @GET("/users/{username}/likes")
    Flowable<List<PhotoItem>> getProfileLikesPhotos(@Path("username") String username,
                                                    @Query("page") int page, //Optional; default: 1
                                                    @Query("per_page") int per_page, //Optional; default: 10
                                                    @Query("order_by") String orderBy);//Valid values: latest, oldest, popular; default: latest

    @GET("/photos/curated")
    Flowable<List<PhotoItem>> getPhotosCurated(@Query("page") int page,//Optional; default: 1
                                               @Query("per_page") int perPage, //Optional; default: 10
                                               @Query("order_by") String orderBy); //Valid values: latest, oldest, popular; default: latest


    @GET("/photos")
    Flowable<List<PhotoItem>> getPhotos(@Query("page") int page, //Optional; default: 1
                                              @Query("per_page") int perPage, //Optional; default: 10
                                              @Query("order_by") String orderBy); //Valid values: latest, oldest, popular; default: latest

}

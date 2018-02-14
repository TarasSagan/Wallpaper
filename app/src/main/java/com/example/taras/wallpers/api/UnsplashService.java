package com.example.taras.wallpers.api;

import com.example.taras.wallpers.api.ModelsOfResponse.ResponseRandomPhotos;
import com.example.taras.wallpers.api.ModelsOfResponse.token.AuthorizationToken;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UnsplashService {

    @Headers({
            "Authorization: Client-ID bafddaf9af46bc71e5c6ff4ce4d4f35bbc56821e7e138d8e3797d1e59ef6d0d2"
    })

    @GET("photos/random")
    Flowable<List<ResponseRandomPhotos>> getPhotosRandom(@Query("count") int count);

}

package com.example.taras.wallpers.api;

import com.example.taras.wallpers.api.responses.RandomPhoto.WithoutCOUNT.ResponseRandomWithoutCount;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


public interface UnsplashService {
    @Headers({
            "Authorization: Client-ID bafddaf9af46bc71e5c6ff4ce4d4f35bbc56821e7e138d8e3797d1e59ef6d0d2"
    })

    @GET("/photos/random")
    Call<List<ResponseRandomWithoutCount>> getPhotosRandom(@Query("count") int count);

}

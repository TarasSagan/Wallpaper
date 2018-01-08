package com.example.taras.wallpers.repository;

import android.util.Log;

import com.example.taras.wallpers.api.UnsplashService;
import com.example.taras.wallpers.api.responses.RandomPhoto.WithoutCOUNT.ResponseRandomWithoutCount;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RepositoryController {
    public static void getResponse(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.unsplash.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UnsplashService api = retrofit.create(UnsplashService.class);
        Call<List<ResponseRandomWithoutCount>> call = api.getPhotosRandom(29);
        call.enqueue(new Callback<List<ResponseRandomWithoutCount>>() {
            @Override
            public void onResponse(Call<List<ResponseRandomWithoutCount>> call, Response<List<ResponseRandomWithoutCount>> response) {
                if (response.isSuccessful()){
                    Log.d("RESPONSE  ", "Удачно");
                    Log.d("RESPONSE  ", Integer.toString(response.body().size()));

                }else  Log.d("RESPONSE  ", "не удалчно");
            }

            @Override
            public void onFailure(Call<List<ResponseRandomWithoutCount>> call, Throwable t) {

            }
        });
    }
}

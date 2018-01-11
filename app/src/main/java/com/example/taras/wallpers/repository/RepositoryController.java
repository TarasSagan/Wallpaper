package com.example.taras.wallpers.repository;

import android.util.Log;
import com.example.taras.wallpers.api.UnsplashService;
import com.example.taras.wallpers.api.ModelsOfResponse.ResponseRandomPhotos;
import java.util.List;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



public class RepositoryController {
    public static void getResponse(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.unsplash.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        UnsplashService api = retrofit.create(UnsplashService.class);
        Flowable<List<ResponseRandomPhotos>> call = api.getPhotosRandom(29);

        call
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response ->Log.d("RESPONSE  ", Integer.toString(response.size())));

    }
}

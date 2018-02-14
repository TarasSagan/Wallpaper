package com.example.taras.wallpers.repository;

import android.content.Context;
import android.util.Log;
import com.example.taras.wallpers.api.UnsplashService;
import com.example.taras.wallpers.api.ModelsOfResponse.ResponseRandomPhotos;
import com.example.taras.wallpers.repository.local.DbMethods;

import java.util.List;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



public class RepositoryController {
    private RepositoryUtils repositoryUtils;
    private DbMethods dbMethods;

    public void getResponse(Context context){
        repositoryUtils = new RepositoryUtils();
        dbMethods = new DbMethods();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.unsplash.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        UnsplashService api = retrofit.create(UnsplashService.class);
        Flowable<List<ResponseRandomPhotos>> call = api.getPhotosRandom(29);

        call
                .map(list -> repositoryUtils.transormResponse(list))
                .subscribeOn(Schedulers.io())
                .subscribe(photos -> {
                        dbMethods.insertData(photos, context);
                        Log.d("RESPONSE  ", Integer.toString(photos.size()));
                });

    }
}

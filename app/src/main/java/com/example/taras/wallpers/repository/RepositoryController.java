package com.example.taras.wallpers.repository;

import android.content.Context;
import android.util.Log;

import com.example.taras.wallpers.api.ModelsOfResponse.photo.PhotoItem;
import com.example.taras.wallpers.api.UnsplashService;
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

//
//    public void getResponse(Context context, IRandom callback){
//        this.callback = callback;
//        repositoryUtils = new RepositoryUtils();
//        dbMethods = new DbMethods();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.unsplash.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//
//        UnsplashService api = retrofit.create(UnsplashService.class);
//        Flowable<List<PhotoItem>> call = api.getPhotos(30);
//
//        call
////                .map(list -> repositoryUtils.transormResponse(list))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(photos -> {
////                        dbMethods.insertData(photos, context);
//                        callback.onCalbeck(photos);
//                        Log.d("RESPONSE  ", Integer.toString(photos.size()));
//                });
//
//    }

}

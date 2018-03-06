package com.example.taras.wallpapers.repository;

import com.example.taras.wallpapers.repository.local.DbMethods;


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

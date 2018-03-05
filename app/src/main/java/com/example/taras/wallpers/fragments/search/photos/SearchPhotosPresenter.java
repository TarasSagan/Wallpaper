package com.example.taras.wallpers.fragments.search.photos;


import android.text.TextUtils;

import com.example.taras.wallpers.api.ModelsOfResponse.search.photos.SearchPhotosResponse;
import com.example.taras.wallpers.fragments.baseListFragment.BaseListPresenter;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchPhotosPresenter extends BaseListPresenter implements ISearchPhotosPresenter {
    private String query;


    @Override
    public void onLoadNextPhotos(int currentPage, int perPage) {
        if(!TextUtils.isEmpty(query)){
            Flowable<SearchPhotosResponse> searchPhotos = unsplashService.searchPhotos(query, currentPage, perPage);
            searchPhotos
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> onShowNextPhotos(response.getResults()));
        }

    }

    @Override
    public void onSetQuery(String query) {
        this.query = query;
        resetByDefault();
        getNextData();
    }
}


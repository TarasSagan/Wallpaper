package com.example.taras.wallpapers.fragments.listProfileLikesPhotos;

import com.example.taras.wallpapers.api.ModelsOfResponse.photo.PhotoItem;
import com.example.taras.wallpapers.fragments.baseListFragment.BaseListPresenter;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ProfileLikesPhotosPresenter extends BaseListPresenter {
    private String username;

    public ProfileLikesPhotosPresenter(String username) {
        this.username = username;
    }

    @Override
    public void onLoadNextPhotos(int currentPage, int perPage) {
        Flowable<List<PhotoItem>> getProfileLikesPhotos = unsplashService.getProfileLikesPhotos(username, currentPage, perPage, orderBy);
        getProfileLikesPhotos
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(photos -> onShowNextPhotos(photos));
    }
}

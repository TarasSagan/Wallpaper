package com.example.taras.wallpers.fragments.listProfilePhotos;

import com.example.taras.wallpers.api.ModelsOfResponse.photo.PhotoItem;
import com.example.taras.wallpers.fragments.baseListFragment.BaseListPresenter;
import java.util.List;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ProfilePhotosPresenter extends BaseListPresenter {
    private String username;

    public ProfilePhotosPresenter(String username) {
        this.username = username;
    }

    @Override
    public void onLoadNextPhotos(int currentPage, int perPage) {
        Flowable<List<PhotoItem>> getProfilePhotos = unsplashService.getProfilePhotos(username, currentPage, perPage, orderBy);
        getProfilePhotos
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(photos -> onShowNextPhotos(photos));
    }

    @Override
    public void onShowUser(PhotoItem item) {
        //disable to start new UserProfileActivity
    }
}

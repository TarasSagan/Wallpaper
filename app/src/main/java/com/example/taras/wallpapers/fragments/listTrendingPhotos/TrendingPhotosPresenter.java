package com.example.taras.wallpapers.fragments.listTrendingPhotos;

import com.example.taras.wallpapers.api.ModelsOfResponse.photo.PhotoItem;
import com.example.taras.wallpapers.fragments.baseListFragment.BaseListPresenter;
import java.util.List;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TrendingPhotosPresenter extends BaseListPresenter{

    @Override
    public void onLoadNextPhotos(int currentPage, int perPage) {
        Flowable<List<PhotoItem>> flowable = unsplashService.getPhotosCurated(currentPage, perPage, orderBy);
        flowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(photos -> onShowNextPhotos(photos));
    }
}
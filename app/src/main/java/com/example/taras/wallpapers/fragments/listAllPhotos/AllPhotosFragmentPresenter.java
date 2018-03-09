package com.example.taras.wallpapers.fragments.listAllPhotos;


import com.example.taras.wallpapers.api.ModelsOfResponse.photo.PhotoItem;
import com.example.taras.wallpapers.fragments.baseListFragment.BaseListPresenter;
import java.util.List;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AllPhotosFragmentPresenter extends BaseListPresenter{
    public String orderBy = "latest";

    @Override
    public void onLoadNextPhotos(int currentPage, int perPage) {
        Flowable<List<PhotoItem>> flowable = unsplashService.getPhotos(currentPage, perPage, orderBy);
        flowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(photos -> onShowNextPhotos(photos));
    }
}
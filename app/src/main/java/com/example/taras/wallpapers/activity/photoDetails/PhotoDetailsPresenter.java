package com.example.taras.wallpapers.activity.photoDetails;

import android.app.DownloadManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.taras.wallpapers.App;
import com.example.taras.wallpapers.R;
import com.example.taras.wallpapers.api.ModelsOfResponse.photoDetails.PhotoDetailsResponse;
import com.example.taras.wallpapers.api.UnsplashService;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class PhotoDetailsPresenter extends MvpBasePresenter<PhotoDetailsContract.View>
        implements PhotoDetailsContract.Presenter{
    @Inject DownloadManager downloadManager;
    @Inject UnsplashService unsplashService;
    @Inject Context context;
    private String photoID;

    public PhotoDetailsPresenter(String photoID) {
        this.photoID = photoID;
        App.getComponent().inject(this);
    }

    @Override
    public void getDetails() {
        if(isNetworkConnected()){
            Flowable<PhotoDetailsResponse> getPhotoByID = unsplashService.getPhotoByID(photoID);
            getPhotoByID
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                            ifViewAttached(view -> view.showDetails(response));
                    });
        }else ifViewAttached(view -> view.showMessage(context.getString(R.string.no_internet)));
    }
    private boolean isNetworkConnected() {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE); // 1
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo(); // 2
        return networkInfo != null && networkInfo.isConnected(); // 3
    }
}

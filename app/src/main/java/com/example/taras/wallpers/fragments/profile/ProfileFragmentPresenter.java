package com.example.taras.wallpers.fragments.profile;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.taras.wallpers.R;
import com.example.taras.wallpers.api.ModelsOfResponse.profile.ProfileResponse;
import com.example.taras.wallpers.api.UnsplashService;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProfileFragmentPresenter extends MvpBasePresenter<ProfileFragmentView>{
    @Inject Context context;
    @Inject UnsplashService unsplashService;
    private ProfileResponse profileResponse;

   public void onGetProfile(String username){
        if (isNetworkConnected()){
        Flowable<ProfileResponse> getProfile = unsplashService.getProfile(username);
        getProfile
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(profileResponse -> {
                    this.profileResponse = profileResponse;
                    ifViewAttached(view -> view.showContent(profileResponse));
                });
        }else ifViewAttached(view -> view.showInfo(context.getString(R.string.no_internet)));
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE); // 1
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo(); // 2
        return networkInfo != null && networkInfo.isConnected(); // 3
    }
}

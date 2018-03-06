package com.example.taras.wallpapers.activity.main;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.taras.wallpapers.App;
import com.example.taras.wallpapers.R;
import com.example.taras.wallpapers.activity.login.LoginActivity;
import com.example.taras.wallpapers.activity.photoDetails.PhotoDetailsActivity;
import com.example.taras.wallpapers.activity.userProfile.UserProfileActivity;
import com.example.taras.wallpapers.api.ModelsOfResponse.profile.ProfileResponse;
import com.example.taras.wallpapers.api.ModelsOfResponse.search.users.SearchUsersResponse;
import com.example.taras.wallpapers.api.UnsplashService;
import com.example.taras.wallpapers.repository.SharedPreferences.TokenManager;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;


import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter extends MvpBasePresenter<MainActivityContract.View>
        implements MainActivityContract.Presenter{
    @Inject Context context;
    @Inject TokenManager tokenManager;
    @Inject UnsplashService unsplashService;

    public MainActivityPresenter(Context context) {
        App.getComponent().inject(this);
    }
    protected void initPresenter(){
        if (isNetworkConnected()){
            if(tokenManager.containsToken()){
                Flowable<ProfileResponse> getCurrentUserProfile = unsplashService.getCurrentUserProfile();
                getCurrentUserProfile
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(user -> {
                            ifViewAttached(view -> view.setAuthUser(user) );
                        });
            }else {ifViewAttached(view -> view.setPublicUser());}
        }else ifViewAttached(view -> view.showMessage(context.getString(R.string.no_internet)));
    }
    private boolean isNetworkConnected() {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE); // 1
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo(); // 2
        return networkInfo != null && networkInfo.isConnected(); // 3
    }

    @Override
    public void onLoginLogout() {
        Intent intent = new Intent(context, LoginActivity.class);
        if(tokenManager.containsToken()){
            tokenManager.removeToken();
        }
        context.startActivity(intent);
    }

    @Override
    public void onShowCurrentUserProfile() {
        if(isNetworkConnected()){
            Intent intent = new Intent(context, UserProfileActivity.class);
            intent.putExtra(UserProfileActivity.CURRENT_USER_KEY, UserProfileActivity.CURRENT_USER_KEY);
            context.startActivity(intent);
        }else ifViewAttached(view -> view.showMessage(context.getString(R.string.no_internet)));
    }

}

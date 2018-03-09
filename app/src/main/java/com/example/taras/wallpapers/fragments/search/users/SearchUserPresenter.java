package com.example.taras.wallpapers.fragments.search.users;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.taras.wallpapers.App;
import com.example.taras.wallpapers.R;
import com.example.taras.wallpapers.activity.userProfile.UserProfileActivity;
import com.example.taras.wallpapers.api.ModelsOfResponse.search.users.SearchUsersResponse;
import com.example.taras.wallpapers.api.ModelsOfResponse.search.users.User;
import com.example.taras.wallpapers.api.UnsplashService;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import javax.inject.Inject;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchUserPresenter extends MvpBasePresenter<ISearchUsersContract.View>
        implements ISearchUsersContract.Presenter, ISearchUsersPresenter{
    private String query;
    private int currentPage = 1;
    private int perPage = 10;
    @Inject Context context;
    @Inject UnsplashService unsplashService;

    public SearchUserPresenter() {
        App.getComponent().inject(this);
    }
    private void onLoadNextData(String query, int page, int perPage){
        Flowable<SearchUsersResponse> searchUsers = unsplashService.searchUsers(query, page, perPage);
        searchUsers
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchUsersResponse -> {
                    ifViewAttached(view -> view.showContent(searchUsersResponse.getResults()));
                });
    }
    @Override
    public void getNextData() {
        if(isNetworkConnected()) {
            onLoadNextData(query, currentPage, perPage);
            currentPage++;
        }else ifViewAttached(view -> view.showMessage(context.getString(R.string.no_internet)));
    }

    @Override
    public void onShowUser(User item) {
        if (isNetworkConnected()) {
            String username = item.getUsername();
            Intent intent = new Intent(context, UserProfileActivity.class);
            intent.putExtra(UserProfileActivity.USERNAME_KEY, username);
            context.startActivity(intent);
        }else ifViewAttached(view -> view.showMessage(context.getString(R.string.no_internet)));
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE); // 1
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo(); // 2
        return networkInfo != null && networkInfo.isConnected(); // 3
    }


    @Override
    public void onSetQuery(String query) {
        this.query = query;
        currentPage = 1;
        perPage = 10;
        ifViewAttached(view -> view.removeContent());
        getNextData();
    }
}

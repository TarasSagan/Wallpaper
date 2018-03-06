package com.example.taras.wallpapers.activity.main;

import com.example.taras.wallpapers.api.ModelsOfResponse.profile.ProfileResponse;

import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface MainActivityContract {
    public interface View extends MvpView{
        void showMessage(String message);
        void setAuthUser(ProfileResponse user);
        void setPublicUser();

    }
    public interface Presenter{
        void onLoginLogout();
        void onShowCurrentUserProfile();
    }
}

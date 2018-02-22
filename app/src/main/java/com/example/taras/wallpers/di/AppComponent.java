package com.example.taras.wallpers.di;

import com.example.taras.wallpers.activity.loginActivity.LoginActivity;
import com.example.taras.wallpers.activity.loginActivity.LoginPresenter;
import com.example.taras.wallpers.fragments.listNewPhotos.NewPhotosFragmentPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, ApiModule.class})
@Singleton
public interface AppComponent {
    void inject(LoginActivity loginActivity);
    void inject(LoginPresenter loginPresenter);
    void inject(NewPhotosFragmentPresenter newPhotosFragmentPresenter);

}

package com.example.taras.wallpers.di;

import com.example.taras.wallpers.activity.login.LoginActivity;
import com.example.taras.wallpers.activity.login.LoginPresenter;

import com.example.taras.wallpers.fragments.baseListFragment.BaseListPresenter;
import com.example.taras.wallpers.fragments.listNewPhotos.NewPhotosFragmentPresenter;
import com.example.taras.wallpers.fragments.listTrendingPhotos.TrendingPhotosPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, ApiModule.class})
@Singleton
public interface AppComponent {
    void inject(LoginActivity loginActivity);
    void inject(LoginPresenter loginPresenter);
    void inject(NewPhotosFragmentPresenter newPhotosFragmentPresenter);
    void inject(TrendingPhotosPresenter trendingPhotosPresenter);
    void inject(BaseListPresenter baseListPresenter);

}

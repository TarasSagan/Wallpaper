package com.example.taras.wallpapers.di;

import com.example.taras.wallpapers.activity.login.LoginActivity;
import com.example.taras.wallpapers.activity.login.LoginPresenter;
import com.example.taras.wallpapers.activity.main.MainActivityPresenter;
import com.example.taras.wallpapers.activity.photoDetails.PhotoDetailsPresenter;
import com.example.taras.wallpapers.fragments.baseListFragment.BaseListPresenter;
import com.example.taras.wallpapers.fragments.listNewPhotos.NewPhotosFragmentPresenter;
import com.example.taras.wallpapers.fragments.listTrendingPhotos.TrendingPhotosPresenter;
import com.example.taras.wallpapers.fragments.profile.ProfileFragmentPresenter;
import com.example.taras.wallpapers.fragments.search.users.SearchUserPresenter;
import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, ApiModule.class})
@Singleton
public interface AppComponent {
    void inject(LoginActivity loginActivity);
    void inject(MainActivityPresenter mainActivityPresenter);
    void inject(LoginPresenter loginPresenter);
    void inject(PhotoDetailsPresenter photoDetailsPresenter);
    void inject(NewPhotosFragmentPresenter newPhotosFragmentPresenter);
    void inject(TrendingPhotosPresenter trendingPhotosPresenter);
    void inject(BaseListPresenter baseListPresenter);
    void inject(ProfileFragmentPresenter profileFragmentPresenter);
    void inject(SearchUserPresenter searchUserPresenter);

}

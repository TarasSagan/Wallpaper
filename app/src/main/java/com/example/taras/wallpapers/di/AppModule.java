package com.example.taras.wallpapers.di;

import android.app.DownloadManager;
import android.content.Context;
import android.support.annotation.NonNull;
import com.example.taras.wallpapers.api.authorization.AuthorizationManager;
import com.example.taras.wallpapers.repository.SharedPreferences.TokenManager;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Context appContext;

    public AppModule(Context appContext) {
        this.appContext = appContext;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return appContext;
    }

    @Provides
    @Singleton
    TokenManager provideTokenManager(){
        return new TokenManager(appContext);
    }
    @Provides
    @NonNull
    @Singleton
    AuthorizationManager provideAuthorizationManager(Context context){
        return new AuthorizationManager(context);
    }
    @Provides
    @NonNull
    @Singleton
    DownloadManager provideDownloadManager(Context context){
        return (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
    }
}

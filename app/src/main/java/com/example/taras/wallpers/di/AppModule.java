package com.example.taras.wallpers.di;

import android.content.Context;
import android.support.annotation.NonNull;
import com.example.taras.wallpers.api.authorization.AuthorizationManager;
import com.example.taras.wallpers.repository.SharedPreferences.TokenManager;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {
    private Context appContext;
    AuthorizationManager authorizationManager;
    TokenManager tokenManager;


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
}

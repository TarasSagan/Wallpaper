package com.example.taras.wallpapers.api.authorization;

import android.content.Context;
import android.content.Intent;

import com.example.taras.wallpapers.R;
import com.example.taras.wallpapers.activity.login.LoginActivity;
import com.example.taras.wallpapers.activity.main.MainActivity;
import com.example.taras.wallpapers.api.ModelsOfResponse.token.AuthorizationToken;
import com.example.taras.wallpapers.repository.SharedPreferences.TokenManager;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthorizationManager {
    private AuthorizationService authorizationService;
    @Inject TokenManager tokenManager;
    private Retrofit retrofit;
    @Inject Context context;

    public AuthorizationManager(Context context) {
        this.context = context;
        tokenManager = new TokenManager(context);
        prepareService();
    }

    private void prepareService(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://unsplash.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        authorizationService = retrofit.create(AuthorizationService.class);
    }
    public void getToken(String code){
        Flowable<AuthorizationToken> call = authorizationService.getToken(
                context.getString(R.string.AppID),
                context.getString(R.string.AppSecret),
                context.getString(R.string.AppRedirectURI),
                code,
                context.getString(R.string.AppGrant_type));

        call
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(authorizationToken -> {
                    tokenManager.setToken(authorizationToken);
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                });


    }

}

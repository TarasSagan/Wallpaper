package com.example.taras.wallpers.api.authorization;

import android.content.Context;
import android.util.Log;
import com.example.taras.wallpers.R;
import com.example.taras.wallpers.api.ModelsOfResponse.token.AuthorizationToken;
import com.example.taras.wallpers.repository.SharedPreferences.TokenManager;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthorizationManager {
    private AuthorizationService authorizationService;
    private TokenManager tokenManager;
    private Retrofit retrofit;
    private Context context;

    public AuthorizationManager(Context context) {
        this.context = context;
        tokenManager = new TokenManager(context);
        prepareService();
    }

    void prepareService(){
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
                });


    }

}
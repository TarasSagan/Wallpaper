package com.example.taras.wallpapers.di;

import android.content.Context;
import android.support.annotation.NonNull;
import com.example.taras.wallpapers.R;
import com.example.taras.wallpapers.api.UnsplashService;
import com.example.taras.wallpapers.repository.SharedPreferences.TokenManager;
import java.io.IOException;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    private UnsplashService unsplashService;
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;


    public ApiModule() {
    }

    @Provides
    @Singleton
    @NonNull
    public UnsplashService provideUnsplashService(TokenManager tokenManager, Context context) {
        initOkHttpClient(tokenManager, context);
        initRetrofit(okHttpClient);
        return unsplashService;
    }


    private void initOkHttpClient(TokenManager tokenManager, Context context){
        if (tokenManager.containsToken()){
            initHttpWithToken(tokenManager);
        }else initHttpWithoutToken(context);
    }
    private void initRetrofit(OkHttpClient client) {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.unsplash.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        unsplashService = retrofit.create(UnsplashService.class);
    }

    private void initHttpWithToken(TokenManager tokenManager){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "Bearer " + tokenManager.getToken().getAccessToken());

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        okHttpClient =  httpClient.build();
    }
    private void initHttpWithoutToken(Context context){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("Authorization", "Client-ID " + context.getString(R.string.AppID));

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        okHttpClient =  httpClient.build();
    }
}

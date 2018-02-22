package com.example.taras.wallpers;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.taras.wallpers.di.ApiModule;
import com.example.taras.wallpers.di.AppComponent;
import com.example.taras.wallpers.di.AppModule;
import com.example.taras.wallpers.di.DaggerAppComponent;


public class App extends Application{
    private static AppComponent component;
    public static AppComponent getComponent(){
        return component;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    protected AppComponent buildComponent(){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .build();
    }
}

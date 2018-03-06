package com.example.taras.wallpapers;

import android.app.Application;

import com.example.taras.wallpapers.di.ApiModule;
import com.example.taras.wallpapers.di.AppComponent;
import com.example.taras.wallpapers.di.AppModule;
import com.example.taras.wallpapers.di.DaggerAppComponent;


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

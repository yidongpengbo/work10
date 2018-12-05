package com.example.week2;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration = null;
        configuration = new ImageLoaderConfiguration.Builder(this).diskCacheSize(50 * 1024 * 1024).memoryCacheSize(10).build();
        ImageLoader.getInstance().init(configuration);
    }
}
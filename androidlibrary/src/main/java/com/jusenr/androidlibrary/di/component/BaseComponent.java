package com.jusenr.androidlibrary.di.component;

import android.app.Application;

import com.jusenr.androidlibrary.di.module.BaseModule;
import com.jusenr.androidlibrary.di.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * Created by riven_chris on 2017/4/16.
 */

@Singleton
@Component(modules = {BaseModule.class, NetModule.class})
public interface BaseComponent {

    Application application();

    OkHttpClient okHttpClient();
}

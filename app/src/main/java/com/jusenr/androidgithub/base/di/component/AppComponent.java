package com.jusenr.androidgithub.base.di.component;

import com.google.gson.Gson;
import com.jusenr.androidgithub.base.di.module.ApiModule;
import com.jusenr.androidgithub.retrofit.api.PassApi;
import com.jusenr.androidgithub.retrofit.api.RepoApi;
import com.jusenr.androidlibrary.di.component.BaseComponent;
import com.jusenr.androidlibrary.di.scope.AppScope;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * Created by riven_chris on 2017/4/17.
 */

@AppScope
@Component(modules = {ApiModule.class,}, dependencies = BaseComponent.class)
public interface AppComponent {

    Gson gson();

    PassApi passApi();

    RepoApi repoApi();

    OkHttpClient okHttpClient();
}

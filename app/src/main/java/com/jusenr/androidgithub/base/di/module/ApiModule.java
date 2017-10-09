package com.jusenr.androidgithub.base.di.module;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jusenr.androidgithub.base.BaseApi;
import com.jusenr.androidgithub.retrofit.api.PassApi;
import com.jusenr.androidgithub.retrofit.api.RepoApi;
import com.jusenr.androidlibrary.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by riven_chris on 2017/4/14.
 */
@Module
public final class ApiModule {

    @Provides
    @AppScope
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @AppScope
    PassApi providePassApi(Gson gson, OkHttpClient okHttpClient) {
        return RetrofitFactory
                .create(gson, okHttpClient, BaseApi.PASS_BASE_URL)
                .create(PassApi.class);
    }

    @Provides
    @AppScope
    RepoApi provideRepoApi(Gson gson, OkHttpClient okHttpClient) {
        return RetrofitFactory
                .create(gson, okHttpClient, BaseApi.PASS_BASE_URL)
                .create(RepoApi.class);
    }

    private static class RetrofitFactory {

        public static Retrofit create(Gson gson, OkHttpClient okHttpClient, String baseUrl) {
            return new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .build();
        }
    }
}

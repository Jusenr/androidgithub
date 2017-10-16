package com.jusenr.androidlibrary.di.module;

import android.app.Application;

import com.jusenr.androidlibrary.commn.okhttp.interceptor.CacheStrategyInterceptor;
import com.jusenr.androidlibrary.commn.okhttp.interceptor.HeaderInfoInterceptor;
import com.jusenr.toolslibrary.log.logger.Logger;
import com.jusenr.toolslibrary.utils.AppUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by riven_chris on 2017/4/13.
 */

@Module
public class NetModule {

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 20 * 1024 * 1024; // 20 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Application application, Cache cache) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(10 * 1000, TimeUnit.MILLISECONDS)
                .writeTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(10 * 1000, TimeUnit.MILLISECONDS);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Logger.d("OKHTTP-DATA: %s", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);

        builder.addInterceptor(new CacheStrategyInterceptor(application));
//        builder.addInterceptor(new ProgressInterceptor(new ProgressResponseListener() {
//            @Override
//            public void onResponseProgress(long bytesRead, long contentLength, boolean done) {
//
//            }
//        }));
        builder.addInterceptor(new HeaderInfoInterceptor(AppUtils.getVersionName(application)));

        builder.cache(cache);

        return builder.build();
    }
}

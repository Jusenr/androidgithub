package com.jusenr.androidlibrary.di.module;

import android.app.Application;

import com.jusenr.androidlibrary.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by riven_chris on 2017/4/13.
 */

@Module
public final class BaseModule {
    private BaseApplication mApplication;

    public BaseModule(BaseApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesBaseApplication() {
        return mApplication;
    }
}

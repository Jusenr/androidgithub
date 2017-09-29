package com.jusenr.androidlibrary;

import android.app.Application;

import com.jusenr.androidlibrary.commn.ActivityManager;
import com.jusenr.androidlibrary.commn.BaseActivityLifecycleCallback;
import com.jusenr.androidlibrary.di.component.BaseComponent;
import com.jusenr.androidlibrary.di.component.DaggerBaseComponent;
import com.jusenr.androidlibrary.di.module.BaseModule;
import com.jusenr.toolslibrary.AndroidTools;

/**
 * Created by riven_chris on 2017/4/13.
 */

public abstract class BaseApplication extends Application {
    public static final String TAG = BaseApplication.class.getSimpleName();

    protected BaseComponent baseComponent;
    protected BaseActivityLifecycleCallback activityLifecycleCallbacks;
    protected ActivityManager mActivityManager;

    @Override
    public void onCreate() {
        super.onCreate();
        //API initialization
        initEnvironment();
        //AndroidTools initialization
        AndroidTools.init(getApplicationContext(), getLogTag());
        //Dagger2 initialization
        baseComponent = DaggerBaseComponent.builder()
                .baseModule(new BaseModule(this))
                .build();
        mActivityManager = new ActivityManager();

        //ActivityLifecycle
        registerActivityLifecycleCallbacks(new BaseActivityLifecycleCallback(mActivityManager));
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    public ActivityManager getActivityManager() {
        return mActivityManager;
    }

    /**
     * 初始化API环境
     */
    protected abstract void initEnvironment();

    /**
     * 设置调试日志标签名
     *
     * @return 调试日志标签名
     */
    protected abstract String getLogTag();
}

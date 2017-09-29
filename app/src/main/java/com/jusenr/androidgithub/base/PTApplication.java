package com.jusenr.androidgithub.base;

import android.content.Context;
import android.content.Intent;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jusenr.androidgithub.BuildConfig;
import com.jusenr.androidgithub.base.di.component.AppComponent;
import com.jusenr.androidgithub.base.di.component.DaggerAppComponent;
import com.jusenr.androidgithub.realm.APPRealmMigration;
import com.jusenr.androidgithub.utils.AccountHelper;
import com.jusenr.androidlibrary.BaseApplication;
import com.jusenr.androidlibrary.commn.database.DBManager;
import com.jusenr.androidlibrary.widgets.fresco.ImagePipelineFactory;


/**
 * Created by riven_chris on 2017/4/13.
 */

public class PTApplication extends BaseApplication {
    AppComponent appComponent;
    private static PTApplication sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        appComponent = DaggerAppComponent.builder()
                .baseComponent(baseComponent)
                .build();

        Fresco.initialize(getApplicationContext(),
                ImagePipelineFactory.imagePipelineConfig(getApplicationContext()
                        , getAppComponent().okHttpClient()
                        , getCacheDir().getAbsolutePath()));
        DBManager.init(this);
        if (AccountHelper.isLogin())
            DBManager.initDefaultRealm(AccountHelper.getCurrentUid(), APPRealmMigration.VERSION, new APPRealmMigration());
        startService(new Intent(this, InitializeIntentService.class));
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void initEnvironment() {
        BaseApi.init(this, BuildConfig.HOST_NOW, "1");

        pushInstall();
    }

    //初始化Push
    private void pushInstall() {

    }

    @Override
    public String getLogTag() {
        return BuildConfig.LOG_TAG;
    }

    /**
     * debug模式
     *
     * @return 是否开启
     */
    protected static boolean isDebug() {
        return BuildConfig.IS_TEST;
    }

    public static Context instance() {
        return sContext;
    }
}

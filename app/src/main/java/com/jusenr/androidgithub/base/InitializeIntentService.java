package com.jusenr.androidgithub.base;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.jusenr.androidgithub.BuildConfig;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by riven_chris on 2017/5/24.
 */

public class InitializeIntentService extends IntentService {

    public InitializeIntentService(String name) {
        super(name);
    }

    public InitializeIntentService() {
        super("InitializeIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        setupUmeng();

        //Logcat异常捕捉
//        CrashHandler.getInstance().initCrashHandler(getApplication());

    }

    private void setupUmeng() {
        MobclickAgent.setDebugMode(BuildConfig.IS_TEST);
        MobclickAgent.setCatchUncaughtExceptions(true);
        MobclickAgent.openActivityDurationTrack(false);
    }
}

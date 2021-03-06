package com.jusenr.androidgithub.guidance;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.base.PTActivity;
import com.trello.rxlifecycle.android.ActivityEvent;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Description:
 * Copyright  : Copyright (c) 2017
 * Email      : jusenr@163.com
 * Author     : Jusenr
 * Date       : 2017/09/30
 * Time       : 17:10
 * Project    ：androidgithub.
 */
public class LogoSplashActivity extends PTActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_logo_splash;
    }

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .compose(this.<Long>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long time) {
                        startActivity(new Intent(getApplicationContext(), GuidanceActivity.class));
                        finish();
                    }
                });
    }
}

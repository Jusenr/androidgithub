package com.jusenr.androidgithub.guidance;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.base.PTActivity;
import com.jusenr.androidgithub.home.ui.activity.MainActivity;
import com.jusenr.androidgithub.user.ui.activity.LoginActivity;
import com.jusenr.androidgithub.utils.AccountHelper;
import com.trello.rxlifecycle.android.ActivityEvent;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
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
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //提前登录
                        if (AccountHelper.isLogin()) {

                        }
                    }
                })
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long time) {
                        if (AccountHelper.isLogin()) {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }
                        finish();
                    }
                });
    }
}

package com.jusenr.androidlibrary.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by riven_chris on 2017/4/12.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onPreViewCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

    protected void onPreViewCreate(@NonNull Bundle savedInstanceState){};

    protected abstract int getLayoutId();
}

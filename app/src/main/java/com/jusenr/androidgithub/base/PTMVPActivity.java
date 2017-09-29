package com.jusenr.androidgithub.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jusenr.androidlibrary.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by riven_chris on 2017/4/13.
 */

public abstract class PTMVPActivity<P extends BasePresenter> extends PTActivity {

    @Inject
    protected P mPresenter;

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        injectComponent();
    }

    protected abstract void injectComponent();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }
}

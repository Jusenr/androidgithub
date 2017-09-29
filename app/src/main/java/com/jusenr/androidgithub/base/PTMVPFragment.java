package com.jusenr.androidgithub.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jusenr.androidlibrary.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by riven_chris on 2017/4/13.
 */

public abstract class PTMVPFragment<P extends BasePresenter> extends PTFragment {

    @Inject
    protected P mPresenter;

    @Override
    public void onViewCreateFinish(View view, @Nullable Bundle savedInstanceState) {
        injectComponent();
    }

    protected abstract void injectComponent();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }
}

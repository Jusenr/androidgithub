package com.jusenr.androidlibrary.base.loading;

import android.support.annotation.UiThread;

import com.jusenr.androidlibrary.base.IView;

/**
 * Created by mingjun on 168/9.
 */
public interface LoadView extends IView {

    @UiThread
    void showLoading();

    @UiThread
    void dismissLoading();
}

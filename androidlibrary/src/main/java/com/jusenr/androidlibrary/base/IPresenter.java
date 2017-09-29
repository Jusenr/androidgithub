package com.jusenr.androidlibrary.base;

/**
 * Created by riven_chris on 2017/4/13.
 */

public interface IPresenter {
    String TAG = IPresenter.class.getSimpleName();

    void subscribe();

    void unSubscribe();

    void onDestroy();
}

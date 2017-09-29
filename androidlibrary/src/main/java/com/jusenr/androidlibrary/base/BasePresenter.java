package com.jusenr.androidlibrary.base;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by riven_chris on 2017/4/12.
 */

public class BasePresenter<V extends IView, M extends IInteractor> implements IPresenter {

    protected V mView;
    protected M mInteractor;
    protected CompositeSubscription subscriptions = new CompositeSubscription();

    public BasePresenter(V view, M interactor) {
        mView = view;
        mInteractor = interactor;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        subscriptions.unsubscribe();
    }

    @Override
    public void onDestroy() {
        unSubscribe();
        mView = null;
        if (mInteractor != null) {
            mInteractor.onDestroy();
            mInteractor = null;
        }
    }
}

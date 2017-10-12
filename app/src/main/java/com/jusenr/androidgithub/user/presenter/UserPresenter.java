package com.jusenr.androidgithub.user.presenter;

import com.jusenr.androidgithub.user.contract.UserContract;
import com.jusenr.androidgithub.user.model.model.UserModel;
import com.jusenr.androidlibrary.base.BasePresenter;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import javax.inject.Inject;

import rx.Subscriber;
import rx.functions.Action0;

@ActivityScope
public class UserPresenter extends BasePresenter<UserContract.View, UserContract.Interactor> {

    @Inject
    public UserPresenter(UserContract.View view, UserContract.Interactor interactor) {
        super(view, interactor);
    }

    public void onUserInfo(final String username) {
        subscriptions.add(mInteractor.getSingleUser(username)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        mView.dismissLoading();
                    }
                })
                .subscribe(new Subscriber<UserModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getUserinfoFailed(-1, e.getMessage());
                    }

                    @Override
                    public void onNext(UserModel model) {
                        mView.getUserinfoResult(model);
                    }
                }));
    }
}
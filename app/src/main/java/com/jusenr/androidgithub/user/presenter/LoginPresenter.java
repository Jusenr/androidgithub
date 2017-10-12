package com.jusenr.androidgithub.user.presenter;

import com.alibaba.fastjson.JSONObject;
import com.jusenr.androidgithub.user.contract.LoginContract;
import com.jusenr.androidgithub.user.model.model.UserModel;
import com.jusenr.androidgithub.utils.AccountHelper;
import com.jusenr.androidlibrary.base.BasePresenter;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import javax.inject.Inject;

import rx.Subscriber;
import rx.functions.Action0;

@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.View, LoginContract.Interactor> {

    @Inject
    public LoginPresenter(LoginContract.View view, LoginContract.Interactor interactor) {
        super(view, interactor);
    }

    public void onLogin(final String username, String password) {
        subscriptions.add(mInteractor.login(username, password)
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
                .subscribe(new Subscriber<JSONObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.loginFailed(-1, e.getMessage());
                    }

                    @Override
                    public void onNext(JSONObject object) {
                        AccountHelper.login(object);
                        AccountHelper.saveUsername(username);
                        onUserInfo();
                    }
                }));
    }

    private void onUserInfo() {
        subscriptions.add(mInteractor.getUserInfo()
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
                .subscribe(new Subscriber<JSONObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.loginFailed(-1, e.getMessage());
                    }

                    @Override
                    public void onNext(JSONObject object) {
                        AccountHelper.login(object);
                        UserModel result = object.toJavaObject(UserModel.class);
                        AccountHelper.saveUserInfo(result);
                        mView.loginResult(result);
                    }
                }));
    }
}
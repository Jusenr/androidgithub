package com.jusenr.androidgithub.user.presenter;

import com.alibaba.fastjson.JSONObject;
import com.jusenr.androidgithub.retrofit.subscriber.Subscriber0;
import com.jusenr.androidgithub.user.contract.LoginContract;
import com.jusenr.androidgithub.user.model.model.UserModel;
import com.jusenr.androidgithub.utils.AccountHelper;
import com.jusenr.androidlibrary.base.BasePresenter;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import javax.inject.Inject;

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
                .subscribe(new Subscriber0<JSONObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(String msg, JSONObject object) {
                        AccountHelper.login(object);
                        AccountHelper.saveUsername(username);
                        onUserInfo();
                    }

                    @Override
                    public void onError(int code, String msg) {
                        mView.loginFailed(code, msg);
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
                .subscribe(new Subscriber0<JSONObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(String msg, JSONObject object) {
                        AccountHelper.login(object);
                        UserModel result = object.toJavaObject(UserModel.class);
                        AccountHelper.saveUserInfo(result);
                        mView.loginResult(result);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        mView.loginFailed(code, msg);
                    }
                }));
    }
}
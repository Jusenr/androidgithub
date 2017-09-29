package com.jusenr.androidgithub.user.presenter;

import com.alibaba.fastjson.JSONObject;
import com.jusenr.androidgithub.user.contract.LoginContract;
import com.jusenr.androidgithub.user.model.model.User;
import com.jusenr.androidlibrary.base.BasePresenter;
import com.jusenr.androidlibrary.di.scope.ActivityScope;
import com.jusenr.toolslibrary.log.logger.Logger;

import javax.inject.Inject;

import rx.Subscriber;

@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.View, LoginContract.Interactor> {

    @Inject
    public LoginPresenter(LoginContract.View view, LoginContract.Interactor interactor) {
        super(view, interactor);
    }

    public void onLogin(String username, String password) {
        subscriptions.add(mInteractor.login(username, password)
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
                        onUserInfo();
                        User result = object.toJavaObject(User.class);
//                        mView.loginResult(result);
                        Logger.i(object.toString());
                    }
                }));
    }

    public void onUserInfo() {
        subscriptions.add(mInteractor.getUserInfo()
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
                        User result = object.toJavaObject(User.class);
                        mView.loginResult(result);

                        Logger.i(object.toString());
                    }
                }));
    }
}
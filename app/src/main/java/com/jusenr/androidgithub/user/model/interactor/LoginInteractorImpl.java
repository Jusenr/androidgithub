package com.jusenr.androidgithub.user.model.interactor;

import android.util.Base64;

import com.alibaba.fastjson.JSONObject;
import com.jusenr.androidgithub.base.BaseApi;
import com.jusenr.androidgithub.retrofit.RxRetrofitComposer;
import com.jusenr.androidgithub.retrofit.api.PassApi;
import com.jusenr.androidgithub.user.contract.LoginContract;
import com.jusenr.androidgithub.user.model.model.CreateAuthorization;
import com.jusenr.androidgithub.utils.AccountHelper;
import com.jusenr.androidlibrary.di.scope.ActivityScope;

import javax.inject.Inject;

import rx.Observable;

@ActivityScope
public class LoginInteractorImpl implements LoginContract.Interactor {

    private PassApi passApi;

    @Inject
    public LoginInteractorImpl(PassApi passApi) {
        this.passApi = passApi;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public Observable<JSONObject> login(String username, String password) {
        CreateAuthorization createAuthorization = new CreateAuthorization();
        createAuthorization.note = BaseApi.NOTE;
        createAuthorization.client_id = BaseApi.CLIENT_ID;
        createAuthorization.client_secret = BaseApi.CLIENT_SECRET;
        createAuthorization.scopes = BaseApi.SCOPES;

        // https://developer.github.com/v3/auth/#basic-authentication
        // https://developer.github.com/v3/oauth/#non-web-application-flow
        String userCredentials = username + ":" + password;
        String basicAuth = "Basic " + new String(Base64.encode(userCredentials.getBytes(), Base64.DEFAULT));
        AccountHelper.saveCurrentUid(basicAuth.trim());

        return passApi.login(basicAuth.trim(), createAuthorization)
                .compose(RxRetrofitComposer.<JSONObject>applySchedulers());
    }

    @Override
    public Observable<JSONObject> getUserInfo() {
        return passApi.getUserInfo(AccountHelper.getCurrentUid(), AccountHelper.getToken())
                .compose(RxRetrofitComposer.<JSONObject>applySchedulers());
    }
}
package com.jusenr.androidgithub.user.contract;

import com.alibaba.fastjson.JSONObject;
import com.jusenr.androidlibrary.base.loading.LoadView;
import com.jusenr.androidgithub.user.model.model.UserModel;
import com.jusenr.androidlibrary.base.IInteractor;

import rx.Observable;

public interface LoginContract {

    interface View extends LoadView {

        void loginResult(UserModel bean);

        void loginFailed(int code, String msg);
    }

    interface Interactor extends IInteractor {

        Observable<JSONObject> login(String username, String password);

        Observable<JSONObject> getUserInfo();
    }
}
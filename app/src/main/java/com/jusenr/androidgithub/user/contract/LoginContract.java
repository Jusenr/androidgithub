package com.jusenr.androidgithub.user.contract;

import com.alibaba.fastjson.JSONObject;
import com.jusenr.androidgithub.user.model.model.User;
import com.jusenr.androidlibrary.base.IInteractor;
import com.jusenr.androidlibrary.base.IView;

import rx.Observable;

public interface LoginContract {

    interface View extends IView {

        void loginResult(User bean);

        void loginFailed(int code, String msg);
    }

    interface Interactor extends IInteractor {

        Observable<JSONObject> login(String username, String password);

        Observable<JSONObject> getUserInfo();
    }
}
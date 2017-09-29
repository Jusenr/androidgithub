package com.jusenr.androidgithub.retrofit.subscriber;

import com.jusenr.androidgithub.retrofit.model.Model1;

/**
 * Created by riven_chris on 2017/4/14.
 */

public abstract class ApiSubscriber1<T> extends ApiSubscriber<Model1<T>> {

    @Override
    public final void onNext(Model1<T> t) {
        int code = t.getHttp_status_code();
        String msg = t.getMsg();
        boolean success = (code == 200);
        onResponse(success, code, msg, t);
    }
}
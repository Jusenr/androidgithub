package com.jusenr.androidgithub.retrofit.subscriber;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonSyntaxException;

import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by riven_chris on 2017/4/14.
 */

abstract class ApiSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public final void onError(Throwable e) {
        e.printStackTrace();
        String message = e.getMessage();
        Log.e("#####", "ApiSubscriber-onError: " + message);
        if (e instanceof SocketTimeoutException) {
            message = "请求超时，请稍后再试";
        } else if (e instanceof JsonSyntaxException) {
            message = "数据解析异常";
        } else if (!TextUtils.isEmpty(message)) {
            if ((message.contains("400")
                    || message.contains("404")
                    || message.contains("500")
                    || message.contains("502")
                    || (message.contains("504") && !message.contains("only-if-cached")))) {
                message = "服务器异常，请稍后再试";
            } else if (message.contains("resolve")
                    || message.contains("connect")
                    || message.contains("timeout")) {
                //Unable to resolve host "test-api-assistant.ptdev.cn": No address associated with hostname
                //failed to connect to test-account-api.ptdev.cn/10.1.11.39 (port 80) after 10000ms
                message = "请求超时，请稍后再试";
            } else {
                message = "您的网络不给力，请稍后重试";
            }
        } else {
            message = "您的网络不给力，请稍后重试";
        }
        onResponse(false, -1, message, null);
        onCompleted();
    }

    final void onResponse(boolean success, int code, String msg, T data) {
        onCompleted();
        if (success) {
            onNext(msg, data);
        } else {
            if (code == 62002) {//单点登录
//                Logger.dSave("SingleSignOn", "code: " + code);
//                AccountHelper.logout();
//                EventBusUtils.post(false, Constants.EventKey.EVENT_FORCE_LOGOUT);
            } else if (code == 62004) {
                onError(code, "验证码错误");
            } else {
                onError(code, msg);
            }
        }
    }

    public abstract void onNext(String msg, T t);

    public abstract void onError(int code, String msg);
}

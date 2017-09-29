package com.jusenr.androidgithub.retrofit.model;

import java.io.Serializable;

/**
 * Created by riven_chris on 2017/4/14.
 */

public class Model1<T> implements Serializable {
    private T data;
    private int http_status_code;
    private String msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getHttp_status_code() {
        return http_status_code;
    }

    public void setHttp_status_code(int http_status_code) {
        this.http_status_code = http_status_code;
    }

    @Override
    public String toString() {
        return "Model1{" +
                "data=" + data +
                ", http_status_code=" + http_status_code +
                ", msg='" + msg + '\'' +
                '}';
    }
}

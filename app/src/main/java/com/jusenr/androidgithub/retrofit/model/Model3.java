package com.jusenr.androidgithub.retrofit.model;

import java.io.Serializable;

/**
 * Created by riven_chris on 2017/4/14.
 */

public class Model3<T> implements Serializable {
    protected int error_code;
    protected T list;
    protected String msg;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Model3{" +
                "error_code=" + error_code +
                ", list=" + list +
                ", msg='" + msg + '\'' +
                '}';
    }
}

package com.jusenr.androidgithub.retrofit.model;

import java.io.Serializable;

/**
 * Created by riven_chris on 2017/4/14.
 */

public class Model2 implements Serializable {

    protected int error_code;
    protected String msg;
    protected String error;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

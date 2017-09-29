package com.jusenr.androidgithub.retrofit;


import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.jusenr.androidgithub.base.BaseApi;
import com.jusenr.androidgithub.utils.AccountHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * 固定参数拼接封装
 * Created by riven_chris on 16/7/5.
 */
public class ParamsBuilder {
    public static final String PARAM_KEY_CLIENT_TYPE = "client_type";       //设备类型(1:ios,2:andriod,3:wp,4:other)
    public static final String PARAM_KEY_PLATFORM_ID = "platform_id";       //平台(putao:1)
    public static final String PARAM_KEY_VERSION = "version";               //版本号1

    public static final String PARAM_KEY_UID = "uid";                       //家长账户的UID
    public static final String PARAM_PARENT_UID = "parent_uid";             //家长账户的UID
    public static final String PARAM_KEY_APP_ID = "appid";                  //平台id
    public static final String PARAM_KEY_TOKEN = "token";                   //登录的token
    public static final String PARAM_KEY_DEVICE_ID = "device_id";           //设备id

    public static final String PARAM_KEY_PUSH_TOKEN = "push_token";         //推送时用的token
    public static final String PARAM_KEY_PUSH_APPID = "push_appid";         //推送时用的appid

    public static final String PARAM_KEY_DATA = "data";                     //参数data
    public static final String PARAM_KEY_UID_CHILDREN = "uid_children";     //孩子id

    public static final String REQUEST_UID = "x:uid";
    public static final String REQUEST_FILENAME = "filename";
    public static final String REQUEST_SHA1 = "sha1";
    public static final String REQUEST_TYPE = "type";
    public static final String REQUEST_APP_ID = "appid";
    public static final String REQUEST_UPLOAD_TOKEN = "uploadToken";
    public static final String REQUEST_FILE = "file";

    private HashMap<String, String> mParams;

    /**
     * 固定参数 appid、device_id
     */
    private ParamsBuilder() {
        mParams = new HashMap<>();
        mParams.put(PARAM_KEY_APP_ID, BaseApi.APP_ID);
        mParams.put(PARAM_KEY_DEVICE_ID, BaseApi.sDeviceId);
    }

    /**
     * 登录/注册
     *
     * @return
     */
    public static ParamsBuilder pass() {
        ParamsBuilder paramsBuilder = new ParamsBuilder();
        paramsBuilder.put(PARAM_KEY_CLIENT_TYPE, BaseApi.PLATFORM_ID);
        paramsBuilder.put(PARAM_KEY_PLATFORM_ID, BaseApi.CLIENT_TYPE);
        paramsBuilder.put(PARAM_KEY_VERSION, BaseApi.sVersion);
        return paramsBuilder;
    }


    /**
     * 获取固定参数 appid、device_id、uid、token
     *
     * @return
     */
    public static ParamsBuilder start() {
        ParamsBuilder paramsBuilder = new ParamsBuilder();
        paramsBuilder.put(PARAM_KEY_UID, AccountHelper.getCurrentUid());
        paramsBuilder.put(PARAM_KEY_TOKEN, AccountHelper.getToken());
        return paramsBuilder;
    }

    public ParamsBuilder put(String k, String v) {
        if (v == null) return this;
        mParams.put(k, v);
        return this;
    }

    public ParamsBuilder put(String k, int v) {
        mParams.put(k, String.valueOf(v));
        return this;
    }

    public ParamsBuilder put(String k, long v) {
        mParams.put(k, String.valueOf(v));
        return this;
    }

    public ParamsBuilder putAll(Map<String, String> map) {
        mParams.putAll(map);
        return this;
    }

    public ParamsBuilder remove(Object k) {
        mParams.remove(k);
        return this;
    }

    public ParamsBuilder mock(boolean mock) {
        if (mock) {
            mParams.clear();
        }
        return this;
    }

    public HashMap<String, String> build() {
        return mParams;
    }

    public static HashMap<String, String> buildByData(HashMap<String, String> map) {
        HashMap<String, String> params = new HashMap<>();
        params.put(PARAM_KEY_DATA, JSON.toJSONString(map));
        Log.i("HttpLog_param", params.toString());
        return params;
    }

    public static HashMap<String, String> buildByDeviceId(HashMap<String, String> map) {
        return buildByDeviceId(map, null);
    }

    public static HashMap<String, String> buildByDeviceId(HashMap<String, String> map, String deviceId) {
        HashMap<String, String> params = new HashMap<>();
        String paramStr = JSON.toJSONString(map);
        params.put(PARAM_KEY_DATA, paramStr);
        String picoDeviceId = TextUtils.isEmpty(deviceId) ? "" : deviceId;
        params.put(PARAM_KEY_DEVICE_ID, picoDeviceId);
        Log.i("HttpLog_param", params.toString());
        return params;
    }

}

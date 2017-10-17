package com.jusenr.androidgithub.retrofit.api;

import com.alibaba.fastjson.JSONObject;
import com.jusenr.androidgithub.base.BaseApi;
import com.jusenr.androidgithub.user.model.model.CreateAuthorization;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 通行证
 * Created by riven_chris on 2017/4/14.
 */

public interface PassApi {

    /**
     * 授权登录
     *
     * @param authorization       授权账号信息
     * @param createAuthorization 固定参数信息
     * @return
     */
    @POST(BaseApi.Url.URL_LOGIN)
    Observable<JSONObject> login(@Header("Authorization") String authorization,
                                 @Body CreateAuthorization createAuthorization);

    /**
     * 获取用户信息
     *
     * @param authorization 授权账号信息
     * @param accessToken   Token
     * @return
     */
    @GET(BaseApi.Url.URL_USER)
    Observable<JSONObject> getUserInfo(@Header("Authorization") String authorization,
                                       @Query("access_token") String accessToken);


}

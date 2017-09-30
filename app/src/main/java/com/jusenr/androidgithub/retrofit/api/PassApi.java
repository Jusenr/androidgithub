package com.jusenr.androidgithub.retrofit.api;

import com.alibaba.fastjson.JSONObject;
import com.jusenr.androidgithub.base.BaseApi;
import com.jusenr.androidgithub.retrofit.model.Model2;
import com.jusenr.androidgithub.user.model.model.CreateAuthorization;
import com.jusenr.androidgithub.utils.PassModel;

import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
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
    Observable<JSONObject> login(@Header("Authorization") String authorization, @Body CreateAuthorization createAuthorization);

    /**
     * 获取用户信息
     *
     * @param authorization 授权账号信息
     * @param accessToken   Token
     * @return
     */
    @GET(BaseApi.Url.URL_USER)
    Observable<JSONObject> getUserInfo(@Header("Authorization") String authorization, @Query("access_token") String accessToken);

    /**
     * 注册
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST(BaseApi.Url.URL_USER)
    Observable<PassModel> register(@FieldMap Map<String, String> map);

    /**
     * 登录验证
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST(BaseApi.Url.URL_LOGIN)
    Observable<JSONObject> login(@FieldMap Map<String, String> map);

    /**
     * 安全登录
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST(BaseApi.Url.URL_SAFELOGIN)
    Observable<JSONObject> safeLogin(@FieldMap Map<String, String> map);

    /**
     * 图形验证码
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST(BaseApi.Url.URL_VERIFICATION)
    Observable<Model2> graphVerify(@FieldMap Map<String, String> map);

    /**
     * 忘记密码
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST(BaseApi.Url.URL_FORGET)
    Observable<Model2> forget(@FieldMap Map<String, String> map);

    /**
     * 手机注册与否检测
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST(BaseApi.Url.URL_CHECKMOBILE)
    Observable<Model2> checkMobile(@FieldMap Map<String, String> map);

    /**
     * 修改密码
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST(BaseApi.Url.URL_CHANGEPASSWD)
    Observable<Model2> changePasswd(@FieldMap Map<String, String> map);

    /**
     * 更新账号token
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST(BaseApi.Url.URL_UPDATETOKEN)
    Observable<PassModel> updateToken(@FieldMap Map<String, String> map);

    /**
     * 设置昵称
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST(BaseApi.Url.URL_SETNICKNAME)
    Observable<Model2> setNickName(@FieldMap Map<String, String> map);

}

package com.jusenr.androidgithub.utils;


import com.jusenr.androidgithub.retrofit.model.Model2;

/**
 * Description:
 * Copyright  : Copyright (c) 2017
 * Email      : jusenr@163.com
 * Author     : Jusenr
 * Date       : 2017/04/18
 * Time       : 11:34.
 */

public class PassModel extends Model2 {

    /**
     * error_code : 0
     * msg : success
     * avatar :  //图像
     * birthday :  //生日
     * expire_time : 1493904702   //过期时间
     * gender : 0   //性别
     * nickname : pt_60011140   //"pt"+uid,(默认)
     * refresh_token : 136e8fad48fa4f5dbb66482159190d55   //刷新用的token
     * token : 83cb0ca9ea8d42a785f936b3014c9b3c   //token(string)
     * tokenID : 1018149368004920320   //tokenID(long)
     * uid : 60011140   //用户id
     */

    private String avatar;
    private String birthday;
    private long expire_time;
    private String gender;
    private String nickname;
    private String refresh_token;
    private String token;
    private long tokenID;
    private String uid;

    private String mobile;//自定义

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public long getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(long expire_time) {
        this.expire_time = expire_time;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getTokenID() {
        return tokenID;
    }

    public void setTokenID(long tokenID) {
        this.tokenID = tokenID;
    }

    @Override
    public String toString() {
        return "PassModel{" +
                "avatar='" + avatar + '\'' +
                ", birthday='" + birthday + '\'' +
                ", expire_time=" + expire_time +
                ", gender='" + gender + '\'' +
                ", nickname='" + nickname + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", token='" + token + '\'' +
                ", tokenID='" + tokenID + '\'' +
                ", uid='" + uid + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}

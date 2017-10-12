package com.jusenr.androidgithub.utils;

import com.alibaba.fastjson.JSONObject;
import com.jusenr.androidgithub.realm.APPRealmMigration;
import com.jusenr.androidgithub.user.model.model.UserModel;
import com.jusenr.androidlibrary.commn.database.DBManager;
import com.jusenr.toolslibrary.utils.PreferenceUtils;
import com.jusenr.toolslibrary.utils.StringUtils;

/**
 * Description: 通行证助手
 * Copyright  : Copyright (c) 2017
 * Email      : jusenr@163.com
 * Author     : Jusenr
 * Date       : 2017/04/13
 * Time       : 17:14.
 */

public class AccountHelper {

    /**
     * 登录
     *
     * @param object
     */
    public static void login(JSONObject object) {
        if (object != null) {
            String note = object.getString("note");
            if (!StringUtils.isEmpty(note)) {
                AccountHelper.saveNote(note);
            }

            String token = object.getString("token");
            if (!StringUtils.isEmpty(token))
                AccountHelper.saveToken(token);

            String hashed_token = object.getString("hashed_token");
            if (!StringUtils.isEmpty(hashed_token))
                PreferenceUtils.save(Constants.SPKey.PREFERENCE_KEY_HASHED_TOKEN, hashed_token);

            String email = object.getString("email");
            if (!StringUtils.isEmpty(email))
                PreferenceUtils.save(Constants.SPKey.PREFERENCE_KEY_EXPIRE_TIME, email);

            String username = getUsername();
            if (!StringUtils.isEmpty(username)) {
                DBManager.initDefaultRealm(username, APPRealmMigration.VERSION, new APPRealmMigration());
            }
//            String mobile = bean.getMobile();
//            if (!StringUtils.isEmpty(mobile))
//                AccountHelper.saveMobile(mobile);
//
//            String nickname = bean.getNickname();
//            if (!StringUtils.isEmpty(nickname))
//                AccountHelper.saveNickname(nickname);
//
//            String gender = bean.getGender();
//            if (!StringUtils.isEmpty(gender))
//                PreferenceUtils.save(Constants.SPKey.PREFERENCE_KEY_GENDER, gender);
//
//            String avatar = bean.getAvatar();
//            if (!StringUtils.isEmpty(avatar))
//                AccountHelper.saveAvatar(avatar);
//
//            String tokenId = String.valueOf(bean.getTokenID());
//            if (!StringUtils.isEmpty(tokenId))
//                AccountHelper.saveTokenId(tokenId);
        }
    }

    /**
     * 是否在登录状态
     *
     * @return
     */
    public static boolean isLogin() {
        return !StringUtils.isEmpty(getCurrentUid()) && !StringUtils.isEmpty(getToken());
    }

    /**
     * 登出
     */
    public static void logout() {
        PreferenceUtils.remove(Constants.SPKey.PREFERENCE_KEY_NOTE);
        PreferenceUtils.remove(Constants.SPKey.PREFERENCE_KEY_TOKEN);
        PreferenceUtils.remove(Constants.SPKey.PREFERENCE_KEY_HASHED_TOKEN);

        PreferenceUtils.remove(Constants.SPKey.PREFERENCE_KEY_TOKEN_ID);
        PreferenceUtils.remove(Constants.SPKey.PREFERENCE_KEY_EXPIRE_TIME);
        PreferenceUtils.remove(Constants.SPKey.PREFERENCE_KEY_NICKNAME);
        PreferenceUtils.remove(Constants.SPKey.PREFERENCE_KEY_AVATAR);
    }

    /**
     * 获取当前登录的Uid
     *
     * @return 当前登录的Uid
     */
    public static String getCurrentUid() {
        return PreferenceUtils.getValue(Constants.SPKey.PREFERENCE_KEY_UID, null);
    }

    /**
     * 保存当前Uid
     *
     * @param uid
     */
    public static void saveCurrentUid(String uid) {
        PreferenceUtils.save(Constants.SPKey.PREFERENCE_KEY_UID, uid);
    }

    /**
     * 获取当前的token
     *
     * @return 当前登录的Uid
     */
    public static String getToken() {
        return PreferenceUtils.getValue(Constants.SPKey.PREFERENCE_KEY_TOKEN, null);
    }

    /**
     * 保存当前Token
     *
     * @param token
     */
    public static void saveToken(String token) {
        PreferenceUtils.save(Constants.SPKey.PREFERENCE_KEY_TOKEN, token);
    }

    /**
     * 获取当前的tokenId
     */
    public static String getTokenId() {
        return PreferenceUtils.getValue(Constants.SPKey.PREFERENCE_KEY_TOKEN_ID, null);
    }

    /**
     * 保存当前TokenId
     */
    public static void saveTokenId(String tokenId) {
        PreferenceUtils.save(Constants.SPKey.PREFERENCE_KEY_TOKEN_ID, tokenId);
    }

    /**
     * 获取当前登录的手机号
     *
     * @return 当前登录的手机号
     */
    public static String getUsername() {
        return PreferenceUtils.getValue(Constants.SPKey.PREFERENCE_KEY_USERNAME, null);
    }

    /**
     * 保存当前登录的手机号
     *
     * @param username
     */
    public static void saveUsername(String username) {
        PreferenceUtils.save(Constants.SPKey.PREFERENCE_KEY_USERNAME, username);
    }

    /**
     * 获取当前用户昵称
     *
     * @return 当前用户昵称
     */
    public static String getNickname() {
        return PreferenceUtils.getValue(Constants.SPKey.PREFERENCE_KEY_NICKNAME, null);
    }

    /**
     * 保存当前用户昵称
     *
     * @param nickname
     */
    public static void saveNickname(String nickname) {
        PreferenceUtils.save(Constants.SPKey.PREFERENCE_KEY_NICKNAME, nickname);
    }

    /**
     * 获取当前用户昵称
     *
     * @return 当前用户昵称
     */
    public static String getNote() {
        return PreferenceUtils.getValue(Constants.SPKey.PREFERENCE_KEY_NOTE, null);
    }

    /**
     * 保存当前用户昵称
     *
     * @param note
     */
    public static void saveNote(String note) {
        PreferenceUtils.save(Constants.SPKey.PREFERENCE_KEY_NOTE, note);
    }

    /**
     * 获取当前用户图像
     *
     * @return 当前用户图像
     */
    public static String getAvatar() {
        return PreferenceUtils.getValue(Constants.SPKey.PREFERENCE_KEY_AVATAR, null);
    }

    /**
     * 保存当前用户图像
     *
     * @param avatar
     */
    public static void saveAvatar(String avatar) {
        PreferenceUtils.save(Constants.SPKey.PREFERENCE_KEY_AVATAR, avatar);
    }

    /**
     * 获取当前用户信息
     *
     * @return 当前用户信息
     */
    public static UserModel getUserInfo() {
        return PreferenceUtils.getValue(Constants.SPKey.PREFERENCE_KEY_USER_INFO, null);
    }

    /**
     * 保存当前用户信息
     *
     * @param userModel
     */
    public static void saveUserInfo(UserModel userModel) {
        PreferenceUtils.save(Constants.SPKey.PREFERENCE_KEY_USER_INFO, userModel);
    }
}

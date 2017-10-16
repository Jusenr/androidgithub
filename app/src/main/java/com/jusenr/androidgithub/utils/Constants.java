package com.jusenr.androidgithub.utils;

/**
 * Created by riven_chris on 2017/4/14.
 */

public class Constants {

    public static class ParamKey {
        public static final String PARAM_PASSWD = "passwd";                 //登陆密码
        public static final String PARAM_ACTION = "action";                 //验证码发送原因(register|forget|changephone|checkoldphone|login)
        public static final String PARAM_NICK_NAME = "nick_name";           //昵称
        public static final String PARAM_SIGN = "sign";                     //签名
        public static final String PARAM_UID = "uid";                       //用户ID
        public static final String PARAM_TOKEN = "token";                   //令牌
        public static final String PARAM_APPID = "appid";                   //平台ID
        public static final String PARAM_DEVICE_ID = "device_id";           //设备id(IMEI)
        public static final String PARAM_CLIENT_TYPE = "client_type";       //设备类型(1:ios,2:andriod,3:wp,4:other)
        public static final String PARAM_VERSION = "version";               //version
        public static final String PARAM_DEVICE_NAME = "device_name";       //设备名称(型号)

        public static final String PARAM_DATA = "data";                     //反馈内容(限制255字符以内)
        public static final String PARAM_CONTENT = "content";               //反馈内容(限制255字符以内)

    }

    public static class BundleKey {
        public static final String BUNDLE_USER_NAME = "extra_user_name";
        public static final String BUNDLE_OWNER = "extra_owner";
        public static final String BUNDLE_REPO_NAME = "extra_repo_name";
        public static final String BUNDLE_README = "extra_readme";
        public static final String BUNDLE_CODE = "extra_code";

        public static final String BUNDLE_URL = "url";//H5-url
        public static final String BUNDLE_TITLE = "title";//H5-标题
    }

    public static class TypeKey {
        public static final String TYPE_UPDATE_CHILD_NICKNAME = "update_child_nickname";//修改孩子昵称
        public static final String TYPE_UPDATE_USER_NICKNAME = "update_user_nickname";//修改用户昵称
    }

    public static class ActionKey {
        public static final String ACTION_REPOS = "com.github.app.ACTION_REPOS";
        public static final String ACTION_STARRED_REPOS = "com.github.app.ACTION_STARRED_REPOS";

        public static final String ACTION_FOLLOWING = "com.github.app.ACTION_FOLLOWING";
        public static final String ACTION_FOLLOWERS = "com.github.app.ACTION_FOLLOWERS";
    }

    public static class SPKey {
        public static final String PREFERENCE_KEY_NOTE = "note";
        public static final String PREFERENCE_KEY_UID = "uid";
        public static final String PREFERENCE_KEY_TOKEN = "token";
        public static final String PREFERENCE_KEY_HASHED_TOKEN = "hashed_token";
        public static final String PREFERENCE_KEY_USERNAME = "username";
        public static final String PREFERENCE_KEY_NICKNAME = "nickname";
        public static final String PREFERENCE_KEY_AVATAR = "avatar";
        public static final String PREFERENCE_KEY_TOKEN_ID = "tokenId";
        public static final String PREFERENCE_KEY_USER_INFO = "user_info";
        public static final String PREFERENCE_KEY_EXPIRE_TIME = "expire_tim";
        public static final String PREFERENCE_KEY_IS_FIRST = "is_first";
    }

    public static class EventKey {
        public static final String EVENT_FORCE_LOGOUT = "event_force_logout"; // 退出登录
        public static final String EVENT_LEARNING_OPEN = "event_learning_open"; //学习模式开关
        public static final String EVENT_CHILD_SIGN_IN = "event_child_sign_in"; //孩子登录平板
        public static final String EVENT_CHILD_SIGN_OUT = "event_child_sign_out"; //孩子退出平板登录
        public static final String APP_UPDATE = "event_app_update"; //版本更新
    }

    public static class Value {
        // page size
        public static final String AUTHOR = "Jusenr";
        public static final int PAGE_SIZE = 30;
        public static final String SORT_BY_STARS = "stars";
        public static final String ORDER_BY_DESC = "desc";
        public static final String UPDATED = "updated";
        public static final String ALL = "all";
        public static final String NEWEST = "newest";

        public static final String SCHEME_HTTP = "http";
        public static final String SCHEME_HTTPS = "https";
    }


}

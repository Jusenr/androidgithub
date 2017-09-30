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
        public static final String PARAM_PLATFORM_ID = "platform_id";       //平台(putao:1)
        public static final String PARAM_REFRESH_TOKEN = "refresh_token";   //刷新用的token
        public static final String PARAM_VERSION = "version";               //version
        public static final String PARAM_DEVICE_NAME = "device_name";       //设备名称(型号)

        public static final String PARAM_DATA = "data";                     //反馈内容(限制255字符以内)
        public static final String PARAM_CONTENT = "content";               //反馈内容(限制255字符以内)


        public static final String PARAM_NICKNAME = "nickname";//昵称
        public static final String PARAM_BIRTHDAY = "birthday";//生日
        public static final String PARAM_GENDER = "gender";//性别
        public static final String PARAM_EXT = "ext";//图片后缀(xxx.png-->png)
        public static final String PARAM_EXTENSION = "extension";//图片后缀(xxx.png-->png)
        public static final String PARAM_HASH = "hash";//图片哈希值
        public static final String PARAM_WIDTH = "width";//图片宽
        public static final String PARAM_HEIGHT = "height";//图片高
        public static final String PARAM_USER_NAME = "user_name";//用户名
        public static final String PARAM_UID_CHILDREN = "uid_children";//孩子cid
    }

    public static class BundleKey {
        public static final String BUNDLE_TYPE = "type";//修改昵称类型 TYPE_UPDATE_CHILD_NICKNAME|TYPE_UPDATE_USER_NICKNAME
        public static final String BUNDLE_CID = "cid";//孩子id
        public static final String BUNDLE_CHILD_ACCOUNT_NAME = "account_name";//孩子账户名称

        public static final String BUNDLE_ME = "me";//当前账号的家长信息
        public static final String BUNDLE_URL = "url";//H5-url
        public static final String BUNDLE_TITLE = "title";//H5-标题
        public static final String BUNDLE_SHOWHEAD = "showhead";//H5-是否显示H5原生头部控件
    }

    public static class TypeKey {
        public static final String TYPE_UPDATE_CHILD_NICKNAME = "update_child_nickname";//修改孩子昵称
        public static final String TYPE_UPDATE_USER_NICKNAME = "update_user_nickname";//修改用户昵称
    }

    public static class ActionKey {
        public static final String ACTION_REGISTER = "register";            //注册
        public static final String ACTION_FORGET = "forget";                //忘记密码
        public static final String ACTION_LOGIN = "login";                  //登录
        public static final String ACTION_CHANGEPHONE = "changephone";
        public static final String ACTION_CHECKOLDPHONE = "checkoldphone";
    }

    public static class SPKey {
        public static final String PREFERENCE_KEY_NOTE = "note";
        public static final String PREFERENCE_KEY_TOKEN = "token";
        public static final String PREFERENCE_KEY_HASHED_TOKEN = "hashed_token";

        public static final String PREFERENCE_KEY_TOKEN_ID = "tokenId";
        public static final String PREFERENCE_KEY_NICKNAME = "nickname";
        public static final String PREFERENCE_KEY_GENDER = "gender";
        public static final String PREFERENCE_KEY_MOBILE = "mobile";
        public static final String PREFERENCE_KEY_EXPIRE_TIME = "expire_tim";
        public static final String PREFERENCE_KEY_AVATAR = "avatar";
        public static final String PREFERENCE_KEY_IS_FIRST = "is_first";
    }

    public static class EventKey {
        public static final String EVENT_FORCE_LOGOUT = "event_force_logout"; // 退出登录
        public static final String EVENT_LEARNING_OPEN = "event_learning_open"; //学习模式开关
        public static final String EVENT_CHILD_SIGN_IN = "event_child_sign_in"; //孩子登录平板
        public static final String EVENT_CHILD_SIGN_OUT = "event_child_sign_out"; //孩子退出平板登录
        public static final String APP_UPDATE = "event_app_update"; //版本更新
        public static final String EVENT_IM_TIMEOUT = "event_im_timeout"; //二级页面IM超时
    }

    public static class Value {
        public static final String REQUEST_ACCEPT = "ACCEPT";
        public static final String REQUEST_REFUSE = "REFUSE";
        public static final String REQUEST_RESET_PWD_ACCEPT = "1";
        public static final String REQUEST_RESET_PWD_REFUSE = "0";
        public static final String SCHEME_HTTP = "http";
        public static final String SCHEME_HTTPS = "https";
    }


}

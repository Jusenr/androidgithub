package com.jusenr.androidgithub.base;

import android.content.Context;

import com.jusenr.toolslibrary.utils.AppUtils;

/**
 * Created by riven_chris on 2017/4/13.
 *
 * @see ...
 * @see Url [http://doc-ptui-apps.ptdev.cn/index.php?act=api&tag=17]
 */

public class BaseApi {

    public static final int HOST_FORMAL = 1;//正式环境
    public static final int HOST_TEST = 2;//测试环境
    public static final int HOST_DEV = 3;//开发环境

    public static int HOST_NOW;//当前环境
    private static String API_LOCALE;//当前app语言环境

    public static final String PLATFORM_ID = "1";//平台id
    public static final String CLIENT_TYPE = "2";//设备类型
    public static final String APP_ID = "6666";

    // client id/secret
    public static final String CLIENT_ID = "83b7d6586738c4a360db";
    public static final String CLIENT_SECRET = "17da79816c7b3444135840fdccb52a3ae24ec788";
    // scopes
    public static final String[] SCOPES = {"user", "repo", "notifications", "gist", "admin:org"};
    public static final String NOTE = "AndroidGitHubApp";
    public static final String AUTHOR_NAME = "Jusenr";

    public static String sVersion;//应用版本号;
    public static String sDeviceId;//设备ID(手机)

    /**
     * 通行证
     */
    public static String PASS_BASE_URL = "";
    /**
     * h5
     */
    public static String H5_BASE_URL = "";
    /**
     * 用户图像
     */
    public static String USER_AVATAR_BASE_URL = "";
    /**
     * 版本更新
     */
    public static String CLOUD_BASE_URL = "";

    /**
     * environment: 1，外网 2，内网测试 3，内网开发
     */
    public static void init(Context context, int environment, String locale) {
        HOST_NOW = environment;
        API_LOCALE = locale;
        switch (environment) {
            case 1:
                PASS_BASE_URL = "https://api.github.com/";

                H5_BASE_URL = "http://h5.putao.com/";
                USER_AVATAR_BASE_URL = "http://account.file.putaocloud.com/file/";
                CLOUD_BASE_URL = "http://api-cloud.putao.com";
                break;
            case 2:
                PASS_BASE_URL = "https://api.github.com/";

                H5_BASE_URL = "http://dev.fe.ptdev.cn/";
                USER_AVATAR_BASE_URL = "http://account.file.dev.putaocloud.com/file/";
                CLOUD_BASE_URL = "http://test-api-cloud.ptdev.cn";
                break;
            case 3:
                PASS_BASE_URL = "https://api.github.com/";

                H5_BASE_URL = "http://dev.fe.ptdev.cn/";
                USER_AVATAR_BASE_URL = "http://account.file.dev.putaocloud.com/file/";
                CLOUD_BASE_URL = "http://dev-api-cloud.ptdev.cn/";
                break;
        }

        sDeviceId = AppUtils.getRealDeviceId(context);
        sVersion = AppUtils.getVersionName(context);
    }

    //生成孩子二维码
    public static final String CREATE_QR_URL = "http://store.putao.com/ad/13";

    public static boolean isInner() {
        return HOST_NOW == HOST_DEV || HOST_NOW == HOST_TEST;
    }

    /*所有的相对URL*/
    public static class Url {
        /**
         * 通行证
         *
         * @see com.jusenr.androidgithub.retrofit.api.PassApi {@link BaseApi#PASS_BASE_URL}.
         */
        //==============================PassApi=======================================//
        public static final String URL_LOGIN = "/authorizations";//登录
        public static final String URL_USER = "/user";//获取用户信息

        public static final String URL_VERIFICATION = "api/verification";//发送图形验证码
        public static final String URL_CHECKMOBILE = "api/checkMobile";//手机注册与否检测
        public static final String URL_SAFESENDMSG = "api/safeSendMsg";//发送验证码安全接口
        public static final String URL_FORGET = "api/forget";//忘记密码(手机)
        public static final String URL_SAFELOGIN = "api/safeLogin";//安全登录
        public static final String URL_CHANGEPASSWD = "api/changePasswd";//修改密码
        public static final String URL_UPDATETOKEN = "api/updateToken";//更新token
        public static final String URL_CHECKTOKEN = "api/checkToken";//验证token
        public static final String URL_GETNICKNAME = "api/getNickName";//获取昵称
        public static final String URL_SETNICKNAME = "api/setNickName";//设置昵称

        //==============================UploadApi=======================================//
        /**
         * 文件服务
         *
         * @see com.putao.ptpad.cn.retrofit.api.UploadApi {@link BaseApi#FILE_BASE_URL}.
         */
        public static final String URL_CHECK_SHA1 = "fileinfo";//校检sha1
        public static final String URL_UPLOAD_FILE = "upload";//上传文件

        //==============================QRCodeApi========================================//
        /**
         * 二维码扫码
         *
         * @see com.putao.ptpad.cn.retrofit.api.QRCodeApi {@link BaseApi#QRCODE_BASE_URL}.
         */
        public static final String URL_QR_SERVER_HANDLER = "handler";//二维码扫码流程

        //==================================H5===========================================//
        /**
         * H5相关
         *
         * @see {@link BaseApi#H5_BASE_URL}.
         */
        public static final String URL_H5_USED_TIME_TODAY = H5_BASE_URL + "ptpad/index.html?";//今日使用
        public static final String URL_H5_STUDY_REPORT = H5_BASE_URL + "pt_report/index.html";//学习报告
        public static final String URL_H5_WHERE_CHILD_QRCODE_1 = H5_BASE_URL + "qr_location/location_index_1.html";//二维码在哪里-协助登录页面
        public static final String URL_H5_WHERE_CHILD_QRCODE_2 = H5_BASE_URL + "qr_location/location_index_2.html";//二维码在哪里-关联孩子页面

        //==================================版本===========================================//

        /**
         * 版本更新相关
         */
        public static final String URL_UPGRADE_CHECK = "version/checkupgrade";

    }
}

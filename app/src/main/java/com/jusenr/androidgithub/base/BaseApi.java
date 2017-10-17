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
     * environment: 1，外网生产 2，内网测试 3，内网开发
     */
    public static void init(Context context, int environment, String locale) {
        HOST_NOW = environment;
        API_LOCALE = locale;
        switch (environment) {
            case 1:
                PASS_BASE_URL = "https://api.github.com/";

                break;
            case 2:
                PASS_BASE_URL = "https://api.github.com/";

                break;
            case 3:
                PASS_BASE_URL = "https://api.github.com/";

                break;
        }

        sDeviceId = AppUtils.getRealDeviceId(context);
        sVersion = AppUtils.getVersionName(context);
    }

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

        /**
         * 通行证
         *
         * @see com.jusenr.androidgithub.retrofit.api.RepoApi {@link BaseApi#PASS_BASE_URL}.
         */
        //==============================RepoApi=======================================//
        public static final String URL_SEARCH_REPOSITORIES = "/search/repositories";//搜索项目
    }
}

package com.example.wanandroidapp.base;

/**
 * @author: ODM
 * @date: 2019/7/13
 */

public class BaseUrl {

    final static String LOGOUT_URL = "https://www.wanandroid.com/user/logout/json";
    final static String REGISTER_URL = "https://www.wanandroid.com/user/register";
    final static String LOGIN_URL = "https://www.wanandroid.com/user/login";
    final static String ARTICLELIST_PATH = "https://www.wanandroid.com/article/list/";
    final static String SEARCH_PATH = "https://www.wanandroid.com/article/query/";
    final static String BANNER_PATH = "https://www.wanandroid.com/banner/json";

    public static String getLogoutUrl() {
        return LOGOUT_URL;
    }

    public static String getRegisterUrl() {
        return REGISTER_URL;
    }

    public static String getLoginUrl() {
        return LOGIN_URL;
    }

    public static String getArticlelistPath() {
        return ARTICLELIST_PATH;
    }

    public static String getSearchPath() {
        return SEARCH_PATH;
    }

    public static String getBannerPath() {
        return BANNER_PATH;
    }
}
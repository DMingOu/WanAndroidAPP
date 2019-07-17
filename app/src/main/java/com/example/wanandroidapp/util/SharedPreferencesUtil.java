package com.example.wanandroidapp.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.wanandroidapp.core.constant.Constants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @author: ODM
 * @date: 2019/7/17
 */
public class SharedPreferencesUtil {


    /**
     * 将登陆用户名和密码存储在SharedPreferences上
     * @param context 上下文
     * @param loginUserName  登录用户名
     * @param loginUserPassword 登录密码
     */
    public static void saveLoginSharedPreferences(Context context,String loginUserName,String loginUserPassword) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.LOGIN,Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor = sharedPreferences.edit();
        editor.putString("loginUserName",loginUserName);
        editor.putString("loginUserPassword",loginUserPassword);
        editor.apply();
    }

    /**
     * 从SharedPreferences上获取到用户账号信息
     * @param context 上下文
     * @return  拼接起来的用户名和密码字符串data
     */
    public static HashMap getLoginSharedPreferences(Context context)  {
        HashMap<String,String> userInfo = new HashMap<>(4);

        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.LOGIN,Context.MODE_PRIVATE);

        String loginUserName = sharedPreferences.getString("loginUserName","");
        String loginUserPassword = sharedPreferences.getString("loginUserPassword","");
        //当用户名与密码为空，即退出登录状态，返回空不含登录信息
        if(loginUserName.equals("") && loginUserPassword.equals("")) {
            userInfo.put("loginUserName","");
            userInfo.put("loginUserPassword" ,"");
        } else {
            userInfo.put("loginUserName",loginUserName);
            userInfo.put("loginUserPassword" ,loginUserPassword);
        }
        return  userInfo;
    }
}


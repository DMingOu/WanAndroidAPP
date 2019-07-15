package com.example.wanandroidapp.app;

import android.app.Application;
import android.content.Context;


/**
 * @author: ODM
 * @date: 2019/7/14
 */
public class WanAndroidApp extends Application {

    private static Context mContext;
    public static  Context getContext(){
        return  mContext;
    }
}

package com.example.wanandroidapp.core.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.wanandroidapp.app.WanAndroidApp;
import com.example.wanandroidapp.core.constant.Constants;

/**
 * @author: ODM
 * @date: 2019/7/14
 */
public class PreferenceManger implements IPreference {

    private SharedPreferences mSharedPrefences;
    PreferenceManger(){
        mSharedPrefences = WanAndroidApp.getContext().getSharedPreferences(Constants.MY_PREFERENCES_KEY, Context.MODE_PRIVATE);
    }

    @Override
    public void setLoginStatus(boolean isLogin) {
            mSharedPrefences.edit().putBoolean(Constants.LOGIN_STATUS,false).apply();
    }

    @Override
    public boolean getLoginStatus() {
        return mSharedPrefences.getBoolean(Constants.LOGIN_STATUS,false);
    }

    @Override
    public void setLoginAccount(String username) {
         mSharedPrefences.edit().putString(Constants.USERNAME,username).apply();
    }

    @Override
    public String getLoginAccount() {
        return mSharedPrefences.getString(Constants.USERNAME , "");
    }
}

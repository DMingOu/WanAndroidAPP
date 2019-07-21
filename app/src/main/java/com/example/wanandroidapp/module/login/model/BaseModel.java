package com.example.wanandroidapp.module.login.model;

import com.example.wanandroidapp.app.WanAndroidApp;
import com.example.wanandroidapp.bean.LoginData;
import com.example.wanandroidapp.core.http.ApiMethods;
import com.example.wanandroidapp.core.http.ObserverOnNextListener;
import com.example.wanandroidapp.core.http.TemplateObserver;
import com.example.wanandroidapp.module.login.contract.Contract;

/**
 * Model层 ---登录
 *
 * @author: ODM
 * @date: 2019/7/13
 */
public class BaseModel implements Contract.BaseModel {

    @Override
    public void getLogin(ObserverOnNextListener<LoginData> listener , String userName, String password) {
        ApiMethods.doLogin(new TemplateObserver<LoginData>(WanAndroidApp.getContext(),listener),userName,password);
            //Post请求登录，然后将请求成功失败的结果返回到P层
    }
}

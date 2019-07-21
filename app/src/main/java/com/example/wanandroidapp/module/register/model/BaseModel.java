package com.example.wanandroidapp.module.register.model;

import com.example.wanandroidapp.app.WanAndroidApp;
import com.example.wanandroidapp.bean.RegisterData;
import com.example.wanandroidapp.core.http.ApiMethods;
import com.example.wanandroidapp.core.http.ObserverOnNextListener;
import com.example.wanandroidapp.core.http.TemplateObserver;
import com.example.wanandroidapp.module.register.contract.Contract;

/**
 * @author: ODM
 * @date: 2019/7/13
 */
public class BaseModel implements Contract.BaseModel {

    @Override
    /*
    *网络请求 --注册 ，将结果回调到 P层
     */
    public void getRegister(ObserverOnNextListener<RegisterData> listener , String userName, String password, String rePassword) {
        ApiMethods.doRegister(new TemplateObserver<RegisterData>(WanAndroidApp.getContext(),listener),userName,password,rePassword);

    }
}

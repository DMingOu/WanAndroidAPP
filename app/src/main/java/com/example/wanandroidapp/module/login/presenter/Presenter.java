package com.example.wanandroidapp.module.login.presenter;

import com.example.wanandroidapp.app.WanAndroidApp;
import com.example.wanandroidapp.base.presenter.BasePresenter;
import com.example.wanandroidapp.bean.LoginData;
import com.example.wanandroidapp.core.http.ObserverOnNextListener;
import com.example.wanandroidapp.module.login.contract.Contract;
import com.example.wanandroidapp.module.login.model.Model;
import com.example.wanandroidapp.module.login.ui.LoginActivity;
import com.example.wanandroidapp.util.SharedPreferencesUtil;
import com.orhanobut.logger.Logger;

/**
 * 登录 --Presenter层
 *
 * @author: ODM
 * @date: 2019/7/13
 */
public class Presenter extends BasePresenter<Contract.View> implements Contract.Presenter{

    private Model loginModel;
    public Presenter(LoginActivity  loginActivity){
        super(loginActivity);
        loginModel = new Model();
    }

    @Override
    public void toLogin(String userName ,String password) {
        ObserverOnNextListener<LoginData> loginListener = new ObserverOnNextListener<LoginData>() {
            @Override
            public void onNext(LoginData loginData) {
                Logger.d("登录ing,错误代码若为0，则为正常登录:  " + loginData.getErrorCode());
                //根据M层返回的结果，Toast提醒+errorMsg，成功的话调用 V 层 的loginSuccess方法
                if (loginData.getErrorCode() == 0) {
                    getView().loginSuccess(loginData.getErrorMsg());
                    //登录成功后将用户名和密码存储在本机上
                    SharedPreferencesUtil.saveLoginSharedPreferences(WanAndroidApp.getContext(),userName,password);
                } else {
                    getView().loginFailure(loginData.getErrorMsg());
                }
            }

            @Override
            public void onComplete() {
            }
        };
        loginModel.getLogin(loginListener,userName,password);
     };

    }





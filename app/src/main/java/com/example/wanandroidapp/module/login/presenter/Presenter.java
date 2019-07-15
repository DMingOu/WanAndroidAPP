package com.example.wanandroidapp.module.login.presenter;

import com.example.wanandroidapp.base.presenter.BasePresenter;
import com.example.wanandroidapp.module.login.contract.Contract;
import com.example.wanandroidapp.module.login.model.Model;
import com.example.wanandroidapp.module.login.ui.LoginActivity;

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
    public void toLogin(String userName ,String password){
        loginModel.getLogin(userName,password);
        //根据M层返回的结果，Toast提醒+errorMsg，成功的话调用 V 层 的loginSuccess方法
    }



}

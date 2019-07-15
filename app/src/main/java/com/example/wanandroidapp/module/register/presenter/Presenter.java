package com.example.wanandroidapp.module.register.presenter;

import com.example.wanandroidapp.base.presenter.BasePresenter;
import com.example.wanandroidapp.module.register.contract.Contract;
import com.example.wanandroidapp.module.register.model.Model;
import com.example.wanandroidapp.module.register.ui.RegisterActivity;

/**
 * @author: ODM
 * @date: 2019/7/13
 */
public class Presenter extends BasePresenter<Contract.View> implements Contract.Presenter {

    private Model registerModel;

    public Presenter(RegisterActivity registerActivity){
        super(registerActivity);
        registerModel = new Model();
    }
    @Override
    public void toRegister(String userName, String password, String rePassword) {
        registerModel.getRegister(userName,password,rePassword);
        //回调接收到结果后，判断 V层是调用成功方法还是失败方法。
    }
}

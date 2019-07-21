package com.example.wanandroidapp.module.register.presenter;

import com.example.wanandroidapp.base.presenter.BasePresenter;
import com.example.wanandroidapp.bean.RegisterData;
import com.example.wanandroidapp.core.http.ObserverOnNextListener;
import com.example.wanandroidapp.module.register.contract.Contract;
import com.example.wanandroidapp.module.register.model.BaseModel;
import com.example.wanandroidapp.module.register.ui.RegisterActivity;
import com.orhanobut.logger.Logger;

/**
 * @author: ODM
 * @date: 2019/7/13
 */
public class Presenter extends BasePresenter<Contract.View> implements Contract.Presenter {

    private BaseModel registerModel;

    public Presenter(RegisterActivity registerActivity){
        super(registerActivity);
        registerModel = new BaseModel();
    }
    @Override
    public void toRegister(String userName, String password, String rePassword) {
        ObserverOnNextListener<RegisterData> registerListener  = new ObserverOnNextListener<RegisterData>() {
            @Override
            public void onNext(RegisterData registerData) {
                Logger.d("注册ing,错误代码若为0，则为正常登录:  " + registerData.getErrorCode());
                if(registerData.getErrorCode() == 0) {
                    getView().registerSuccess(registerData.getErrorMsg());
                } else {
                    getView().registerFailure(registerData.getErrorMsg());
                }
            }
            @Override
            public void onComplete() {
            }
        };
        registerModel.getRegister(registerListener,userName,password,rePassword);
        //回调接收到结果后，判断 V层是调用成功方法还是失败方法。
    }
}

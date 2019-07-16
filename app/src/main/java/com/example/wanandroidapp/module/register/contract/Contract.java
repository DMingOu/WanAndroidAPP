package com.example.wanandroidapp.module.register.contract;

import com.example.wanandroidapp.base.model.IModel;
import com.example.wanandroidapp.base.presenter.IBasePresenter;
import com.example.wanandroidapp.base.view.IBaseView;
import com.example.wanandroidapp.bean.LoginData;
import com.example.wanandroidapp.bean.RegisterData;
import com.example.wanandroidapp.core.http.ObserverOnNextListener;

/**
 * 注册模块--契约类
 *
 * @author: ODM
 * @date: 2019/7/13
 */
public interface Contract {
    interface Model extends IModel {
        public void getRegister(ObserverOnNextListener<RegisterData> listener , String userName , String password , String rePassword);
    }

    interface View extends IBaseView {
        public void  registerSuccess(String errorMsg);

        public void registerFailure(String errorMsg);
    }

    interface Presenter extends IBasePresenter {
        public void toRegister(String userName , String password , String rePassword);
    }
}

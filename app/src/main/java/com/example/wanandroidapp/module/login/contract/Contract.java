package com.example.wanandroidapp.module.login.contract;

import com.example.wanandroidapp.base.model.IModel;
import com.example.wanandroidapp.base.presenter.IBasePresenter;
import com.example.wanandroidapp.base.view.IBaseView;
import com.example.wanandroidapp.bean.ArticleItemData;
import com.example.wanandroidapp.bean.LoginData;
import com.example.wanandroidapp.core.http.ObserverOnNextListener;

/**
 * 登录 -- 契约类
 * @author: ODM
 * @date: 2019/7/13
 */
public interface Contract {
    interface Model extends IModel {
        public void  getLogin(ObserverOnNextListener<LoginData> listener , String userName , String password);
    }

    interface View extends IBaseView {
        public void  loginSuccess(String errorMsg);

        public void loginFailure(String errorMsg);
    }

    interface Presenter extends IBasePresenter {
        public void toLogin(String userName ,String password);
        //登录成功后，要给 V 层 返回一个Toast做提醒
    }
}

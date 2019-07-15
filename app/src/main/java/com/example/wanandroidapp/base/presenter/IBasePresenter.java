package com.example.wanandroidapp.base.presenter;

import com.example.wanandroidapp.base.view.IBaseView;

/**
 * The interface Base presenter.
 *
 * @author: ODM
 * @date: 2019 /7/13
 */
public interface IBasePresenter <V extends IBaseView> {

    /**
     * 判断 presenter 是否与 view 建立联系，防止出现内存泄漏状况
     *
     * @return true:联系已经建立  false:联系已断开
     */
    boolean isAttachView();

    /**
     * 断开 presenter 与 view 之间的联系
     */
    void  detachView();

    /**
     * 设置登录状态
     *
     * @param loginStatus login status
     */
   // void setLoginStatus(boolean loginStatus);

    /**
     * 判断登录状态
     *
     * @return if is login status
     */
    //boolean getLoginStatus();

    /**
     * 获取登录账户
     *
     * @return login account
     */
    //String getLoginAccount();

    /**
     * 设置登录账户
     *
     * @param account account
     */
    //void setLoginAccount(String account);
}

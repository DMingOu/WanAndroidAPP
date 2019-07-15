package com.example.wanandroidapp.base.view;

import android.app.Activity;

/**
 * @author: ODM
 * @date: 2019/7/13
 */
public interface IBaseView {


    /**
     * 获取 Activity 对象
     *
     * @return activity
     */
    <T extends Activity> T getSelfActivity();

    /**
     * 显示正在加载 view
     */
    void showLoading();

    /**
     * 关闭正在加载 view
     */
    void hideLoading();

    /**
     * 显示提示
     *
     * @param msg
     */
    void showToast(String msg);




}

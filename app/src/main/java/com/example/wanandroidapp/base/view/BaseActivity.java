package com.example.wanandroidapp.base.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.SupportActivity;
import android.support.v7.app.AppCompatActivity;

import com.example.wanandroidapp.base.presenter.IBasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author: ODM
 * @date: 2019/7/13
 */
public abstract class BaseActivity <P extends IBasePresenter>  extends AppCompatActivity implements IBaseView {
    private P mPresenter;
    private Unbinder unBinder;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unBinder = ButterKnife.bind(this);
        initToolbar();
        //initView();
    }

    /**
     * 创建 Presenter
     */
    public abstract P onBindPresenter();

    /**
     * 获取 Presenter 对象，在需要获取时才创建Presenter，起到懒加载作用
     */
    public P getPresenter() {
        if (mPresenter == null) {
            mPresenter = onBindPresenter();
        }
        return mPresenter;
    }

    @Override
    public Activity getSelfActivity() {
        return  this;
    }

    @Override
    protected void onDestroy() {
        hideLoading();
        super.onDestroy();
        if (unBinder != null && unBinder != Unbinder.EMPTY) {
            unBinder.unbind();
            unBinder = null;
        }
         //在生命周期结束时，将 presenter 与 view 之间的联系断开，防止出现内存泄露
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化ToolBar
     */
    protected abstract void initToolbar();


    @Override
    public void showLoading(){
        //
    }

    @Override
    public void hideLoading(){
        //
    }

    @Override
    public void showToast(String msg){
        //
    }

}

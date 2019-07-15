package com.example.wanandroidapp.base.presenter;

import com.example.wanandroidapp.base.view.BaseActivity;
import com.example.wanandroidapp.base.view.IBaseView;

import java.lang.ref.WeakReference;

/**
 * @author: ODM
 * @date: 2019/7/13
 */
public abstract class BasePresenter <V extends IBaseView>implements IBasePresenter {

    private V mView;

    //防止 Activity 不执行 onDestory(),采用 弱引用来防止内存泄漏
    private WeakReference<V> mViewRef;

    public BasePresenter(){
    }
    public BasePresenter(V view) {
        attachView(view);
    }

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }
    public  V getView() {
        return  mViewRef.get();
    }

    @Override
    public boolean isAttachView() {
        return  mViewRef != null && mViewRef.get() != null;
    }

    @Override
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}

package com.example.wanandroidapp.base;

import com.example.wanandroidapp.base.view.IBaseView;
import com.orhanobut.logger.Logger;

import io.reactivex.observers.ResourceObserver;

/**
 * 抽象基类-Observer
 * @author: ODM
 * @date: 2019/7/14
 */
public abstract class BaseObserver<T> extends ResourceObserver<BaseResponse<T>> {
    private static final String TAG = "BaseObserver";
    private IBaseView  mView;

    protected BaseObserver(IBaseView view) {
        this.mView = view;
    }

    /**
     * 请求成功的操作
     *
     * @param t the t
     */
    public abstract void onSuccess(T t);

    @Override
    public void onNext(BaseResponse<T> tBaseResponse) {
        if (tBaseResponse.getErrorCode() == BaseResponse.SUCCESS) {
            Logger.d("onSuccess");
            onSuccess(tBaseResponse.getData());
        } else {
            Logger.d("onFailure");
            //待完成操作：请求失败的操作
        }
    }

    @Override
    public void onError(Throwable e) {
            Logger.d("onError",e.toString());
        if (mView == null) {
            return;
        }
    }

    @Override
    public void onComplete() {
        Logger.d("onComplete");
        if (mView == null) {
            return;
        }
        //待完成操作：判断网络状态->若为异常则在View上显示异常信息
    }
}

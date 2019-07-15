package com.example.wanandroidapp.core.http;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author: ODM
 * @date: 2019/7/14
 */
public class TemplateObserver <T> implements Observer<T> {
    private static final String TAG = "TemplateObserver";
    private ObserverOnNextListener listener;
    private Context context;

    public TemplateObserver(Context context, ObserverOnNextListener listener) {
        this.listener = listener;
        this.context = context;
    }

    @Override
    public void onSubscribe(Disposable d) {
        Log.d(TAG, "onSubscribe: ");
        //添加业务处理
    }

    @Override
    public void onNext(T t) {
        listener.onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: ", e);
        //添加业务处理
    }

    @Override
    public void onComplete() {
        listener.onComplete();
    }
}

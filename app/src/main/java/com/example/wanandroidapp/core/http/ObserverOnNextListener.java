package com.example.wanandroidapp.core.http;

/**
 * 监听OnNext方法
 *
 * @author: ODM
 * @date: 2019/7/14
 */
public interface ObserverOnNextListener<T> {
    void onNext(T t);
    void onComplete();
}

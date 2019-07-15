package com.example.wanandroidapp.core.http;

/**
 * @author: ODM
 * @date: 2019/7/14
 */
public interface ObserverOnNextListener<T> {
    void onNext(T t);
    void onComplete();
}

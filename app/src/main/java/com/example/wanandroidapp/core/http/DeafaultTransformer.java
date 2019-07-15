package com.example.wanandroidapp.core.http;

/**
 * @author: ODM
 * @date: 2019/7/14
 */

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Describe: 设置网络 请求线程 和 结果处理线程
 */
public class DeafaultTransformer<T> implements ObservableTransformer<T, T> {
    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).map(new Function<T, T>() {
                    @Override
                    public T apply(T t) throws Exception{
                        return t;
                    }
                });
    }

    public static <T> DeafaultTransformer<T> create() {
        return new DeafaultTransformer<>();
    }
}

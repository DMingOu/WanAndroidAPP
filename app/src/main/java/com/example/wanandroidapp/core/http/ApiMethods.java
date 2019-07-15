package com.example.wanandroidapp.core.http;

import com.example.wanandroidapp.bean.ArticleItemData;
import com.example.wanandroidapp.util.retrofitUtil.RetrofitMonitor;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: ODM
 * @date: 2019/7/14
 */
public class ApiMethods {

    /**
     * 封装线程管理和订阅的过程
     */
    private static void ApiSubscribe(Observable<?> observable, Observer observer) {
//        observable.subscribeOn(Schedulers.io());
//        observable.unsubscribeOn(Schedulers.io());
//        observable.observeOn(AndroidSchedulers.mainThread());
//        observable.subscribe(observer);
        observable.subscribeOn(Schedulers.io())
               /* .unsubscribeOn(Schedulers.io())*/
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    /**
     * 用于获取文章列表的数据
     *
     * @param observer the observer
     * @param curPage  the curpage
     */
    public static void getArticleList(Observer<ArticleItemData> observer , int curPage) {
        ApiSubscribe(RetrofitMonitor.getApiService().getArticleList(curPage),observer);
    }

}

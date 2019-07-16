package com.example.wanandroidapp.core.http;

import com.example.wanandroidapp.bean.ArticleItemData;
import com.example.wanandroidapp.bean.BannerData;
import com.example.wanandroidapp.bean.LoginData;
import com.example.wanandroidapp.bean.RegisterData;
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
                .unsubscribeOn(Schedulers.io())
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


    /**
     * 请求登录
     *
     * @param observer the observer
     * @param userName the user name
     * @param password the password
     */
    public static void  doLogin (Observer<LoginData> observer ,String userName,String password) {
        ApiSubscribe(RetrofitMonitor.getApiService().login(userName,password),observer);
    }

    /**
     * 请求注册
     *
     * @param observer   the observer
     * @param userName   the user name
     * @param password   the password
     * @param rePassword the re password
     */
    public static void  doRegister (Observer<RegisterData> observer , String userName ,String password ,String rePassword) {
        ApiSubscribe((RetrofitMonitor.getApiService().register(userName,password,password)),observer);
    }

    /**
     * 请求搜索关键词的文章
     *
     * @param observer the observer
     * @param curPage  the cur page
     * @param keyword  the keyword
     */
    public static  void  doSearch (Observer<ArticleItemData> observer ,int curPage ,String keyword) {
        ApiSubscribe(RetrofitMonitor.getApiService().getSearchResultList(curPage,keyword),observer);
    }

    /**
     * Gets banner list.
     *
     * @param observer the observer
     */
    public static void getBannerList(Observer<BannerData>  observer) {
        ApiSubscribe(RetrofitMonitor.getApiService().getBannerData(),observer);
    }

}

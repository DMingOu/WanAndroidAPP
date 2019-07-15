package com.example.wanandroidapp.module.article_home.model;

import com.example.wanandroidapp.app.WanAndroidApp;
import com.example.wanandroidapp.bean.ArticleItemData;
import com.example.wanandroidapp.core.http.ApiMethods;
import com.example.wanandroidapp.core.http.ObserverOnNextListener;
import com.example.wanandroidapp.core.http.TemplateObserver;
import com.example.wanandroidapp.module.article_home.contract.ArticleHomeContract;
import com.orhanobut.logger.Logger;

import java.util.List;

import io.reactivex.Observer;

/**
 * @author: ODM
 * @date: 2019/7/13
 */
public class ArticleHomeModel implements ArticleHomeContract.Model {
    private List<ArticleItemData.DataBean.DatasBean>  mArtcileList;

    @Override
    public void getArticleList(ObserverOnNextListener<ArticleItemData.DataBean> listener,int curPage) {
        //此处要获取一个装满文章的文章列表
        ApiMethods.getArticleList(new TemplateObserver<ArticleItemData.DataBean>(WanAndroidApp.getContext(),listener),curPage);
    }

    @Override
    public void getBannerData() {

    }

    @Override
    public void refresh(ObserverOnNextListener<ArticleItemData.DataBean> listener , int curPage) {
        ApiMethods.getArticleList(new TemplateObserver<ArticleItemData.DataBean>(WanAndroidApp.getContext(),listener),curPage);
    }

    @Override
    public void loadMore(ObserverOnNextListener<ArticleItemData.DataBean> listener , int curPage) {
        ApiMethods.getArticleList(new TemplateObserver<ArticleItemData.DataBean>(WanAndroidApp.getContext(),listener),curPage);
        }
        }

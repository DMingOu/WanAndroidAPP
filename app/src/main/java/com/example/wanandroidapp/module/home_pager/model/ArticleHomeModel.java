package com.example.wanandroidapp.module.home_pager.model;

import com.example.wanandroidapp.app.WanAndroidApp;
import com.example.wanandroidapp.bean.ArticleItemData;
import com.example.wanandroidapp.bean.BannerData;
import com.example.wanandroidapp.core.http.ApiMethods;
import com.example.wanandroidapp.core.http.ObserverOnNextListener;
import com.example.wanandroidapp.core.http.TemplateObserver;
import com.example.wanandroidapp.module.home_pager.contract.ArticleHomeContract;

import java.util.List;

/**
 * @author: ODM
 * @date: 2019/7/13
 */
public class ArticleHomeModel implements ArticleHomeContract.Model {
    private List<ArticleItemData.DataBean.DatasBean>  mArtcileList;

    @Override
    public void getArticleList(ObserverOnNextListener<ArticleItemData> listener,int curPage) {
        //此处要获取一个装满文章的文章列表
        ApiMethods.getArticleList(new TemplateObserver<ArticleItemData>(WanAndroidApp.getContext(),listener),curPage);
    }

    @Override
    public void getBannerData(ObserverOnNextListener<BannerData> listener) {
        //网络请求，获取Banner数据
        ApiMethods.getBannerList(new TemplateObserver<BannerData>(WanAndroidApp.getContext(),listener));

    }

    @Override
    public void refresh(ObserverOnNextListener<ArticleItemData> listener , int curPage) {
        ApiMethods.getArticleList(new TemplateObserver<ArticleItemData>(WanAndroidApp.getContext(),listener),curPage);
    }

    @Override
    public void loadMore(ObserverOnNextListener<ArticleItemData> listener , int curPage) {
        ApiMethods.getArticleList(new TemplateObserver<ArticleItemData>(WanAndroidApp.getContext(),listener),curPage);
         }
        }

package com.example.wanandroidapp.module.search_article.model;

import com.example.wanandroidapp.app.WanAndroidApp;
import com.example.wanandroidapp.bean.ArticleItemData;
import com.example.wanandroidapp.core.http.ApiMethods;
import com.example.wanandroidapp.core.http.ObserverOnNextListener;
import com.example.wanandroidapp.core.http.TemplateObserver;
import com.example.wanandroidapp.module.search_article.contract.Contract;

/**
 * Model层---搜索关键词文章
 *
 *
 * @author: ODM
 * @date: 2019/7/13
 */
public class BaseModel implements Contract.BaseModel {
    @Override
    public void getArticleListSearch(ObserverOnNextListener<ArticleItemData> listener, int curPage, String keyword) {
        ApiMethods.doSearch(new TemplateObserver<ArticleItemData>(WanAndroidApp.getContext(),listener),curPage,keyword);
    }
}

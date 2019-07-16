package com.example.wanandroidapp.module.search_article.presenter;

import com.example.wanandroidapp.base.presenter.BasePresenter;
import com.example.wanandroidapp.bean.ArticleItemData;
import com.example.wanandroidapp.core.http.ObserverOnNextListener;
import com.example.wanandroidapp.module.search_article.contract.Contract;
import com.example.wanandroidapp.module.search_article.model.Model;
import com.example.wanandroidapp.module.search_article.ui.SearchActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ODM
 * @date: 2019/7/13
 */
public class Presenter extends BasePresenter<Contract.View> implements Contract.Presenter {
    private Model modelSearch;
    private int currentPage = 0;
    private String currentKeyword = "";
    private List<ArticleItemData.DataBean.DatasBean> mArtcileList = new ArrayList<>();
    public Presenter(SearchActivity searchActivity) {
        super(searchActivity);
        //实例化Model层
        modelSearch = new Model();
    }

    @Override
    public void getArticleListSearch(String keyword) {
        //每次点击搜索键后，请求页码先清零
        if("".equals(currentKeyword)) {
            currentKeyword = keyword;
        } else {
            //搜索关键词更新了
            if (! currentKeyword.equals(keyword)) {
                currentPage = 0;
                mArtcileList.clear();
                getView().getmArticleAdapter().notifyDataSetChanged();
                currentKeyword = keyword;
            }
        }
        //定义listener接受回调
        ObserverOnNextListener<ArticleItemData> searchListener = new ObserverOnNextListener<ArticleItemData>() {
            @Override
            public void onNext(ArticleItemData dataBean) {
                Logger.d("搜索加载" + dataBean.getData().getTotal());
                mArtcileList.addAll(dataBean.getData().getDatas());
            }
            @Override
            public void onComplete() {
                getView().getmArticleAdapter().setData(mArtcileList);
            }
        };
        modelSearch.getArticleListSearch(searchListener,currentPage,keyword);
        currentPage++;
    }
}

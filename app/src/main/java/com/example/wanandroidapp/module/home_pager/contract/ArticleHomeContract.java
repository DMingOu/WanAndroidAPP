package com.example.wanandroidapp.module.home_pager.contract;

import com.example.wanandroidapp.base.model.IBaseModel;
import com.example.wanandroidapp.base.presenter.IBasePresenter;
import com.example.wanandroidapp.base.view.IBaseView;
import com.example.wanandroidapp.bean.ArticleItemData;
import com.example.wanandroidapp.bean.BannerData;
import com.example.wanandroidapp.core.http.ObserverOnNextListener;
import com.example.wanandroidapp.module.home_pager.ui.ArticleListAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * The interface Article home contract.
 *
 * @author: ODM
 * @date: 2019 /7/13
 */
public interface ArticleHomeContract {
    /**
     * The interface Model.
     */
    interface BaseModel extends IBaseModel {
        /**
         *  To Get article list.
         */
        void getArticleList(ObserverOnNextListener<ArticleItemData> listener , int curPage);

        /**
         *  To Get banner data.
         */
        void getBannerData(ObserverOnNextListener<BannerData> listener);

        /**
         * Refresh.
         */
        void refresh (ObserverOnNextListener<ArticleItemData> listener , int curPage);

        /**
         * Load more.
         *
         * @param curPage the cur page
         */
        void loadMore (ObserverOnNextListener<ArticleItemData> listener , int curPage);
    }

    /**
     * The interface View.
     */
    interface View  extends IBaseView {

        /**
         * Show banner data.
         *
         * @param bannerDataList the banner data list
         */
        void showBannerData(List<BannerData.DataBean> bannerDataList);
        void putBannerData(List<String> Urls , List<String> Titles);
         ArticleListAdapter getmArticleAdapter();

         XRecyclerView getxRvArticle();
    }


    /**
     * The interface Presenter.
     */
    interface Presenter extends IBasePresenter {
        /**
         * Gets article list.
         */
        void  getArticleList();

        /**
         * Gets banner data.
         */
        void getBannerData();

        /**
         * Refresh.
         */
        void refresh ();

        /**
         * Load more.
         */
        void loadMore ();
    }


}


package com.example.wanandroidapp.module.search_article.contract;

import com.example.wanandroidapp.base.model.IModel;
import com.example.wanandroidapp.base.presenter.IBasePresenter;
import com.example.wanandroidapp.base.view.IBaseView;
import com.example.wanandroidapp.bean.ArticleItemData;
import com.example.wanandroidapp.core.http.ObserverOnNextListener;
import com.example.wanandroidapp.module.search_article.ui.SearchArticleListAdapter;

/**
 * The interface Contract.
 *
 * @author: ODM
 * @date: 2019 /7/13
 */
public interface Contract {
    /**
     * The interface Model.
     */
    interface Model extends IModel {

        /**
         * Gets article list search.
         *
         * @param listener the listener
         * @param curPage  the cur page
         * @param keyword  the keyword
         */
        void getArticleListSearch(ObserverOnNextListener<ArticleItemData> listener , int curPage ,String keyword);

    }

    /**
     * The interface View.
     */
    interface View  extends IBaseView {
        /**
         * Gets article adapter.
         *
         * @return the article adapter
         */
        SearchArticleListAdapter getmArticleAdapter();
    }

    /**
     * The interface Presenter.
     */
    interface Presenter  extends IBasePresenter {
        /**
         * Gets article list search.
         *
         * @param keyword the keyword
         */
        void getArticleListSearch( String keyword);
    }
}

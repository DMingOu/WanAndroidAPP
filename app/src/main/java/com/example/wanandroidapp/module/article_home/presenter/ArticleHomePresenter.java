package com.example.wanandroidapp.module.article_home.presenter;

import com.example.wanandroidapp.base.presenter.BasePresenter;
import com.example.wanandroidapp.bean.ArticleItemData;
import com.example.wanandroidapp.core.http.ObserverOnNextListener;
import com.example.wanandroidapp.module.article_home.contract.ArticleHomeContract;
import com.example.wanandroidapp.module.article_home.model.ArticleHomeModel;
import com.example.wanandroidapp.module.article_home.ui.ArticleHomeActivity;
import com.example.wanandroidapp.module.article_home.ui.ArticleListAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ODM
 * @date: 2019/7/13
 */
public class ArticleHomePresenter extends BasePresenter<ArticleHomeContract.View> implements ArticleHomeContract.Presenter {
    private ArticleHomeModel articleHomeModel;
    private int currentCurPage = 0;
    private List<ArticleItemData.DataBean.DatasBean>   mArtcileList;
    public ArticleHomePresenter(ArticleHomeActivity view) {
        super(view);
        //实例化Model层
        articleHomeModel = new ArticleHomeModel();

        }

    @Override
    public void  getArticleList() {
        this.mArtcileList = new ArrayList<>();
        //定义listener接受回调
        ObserverOnNextListener<ArticleItemData> listener = new ObserverOnNextListener<ArticleItemData>() {
            @Override
            public void onNext(ArticleItemData dataBean) {
                Logger.d("首次加载" + dataBean.getData().getTotal());
                mArtcileList.addAll(dataBean.getData().getDatas());
            }
            @Override
            public void onComplete() {
                getView().getmArticleAdapter().setData(mArtcileList);
            }
        };
        articleHomeModel.getArticleList(listener,currentCurPage);
        currentCurPage++;
    };

    @Override
    public void getBannerData() {
        articleHomeModel.getBannerData();
    }

    @Override
    public void refresh() {
        mArtcileList.clear();
        currentCurPage = 0;
        //定义listener接受回调
        ObserverOnNextListener<ArticleItemData> listener = new ObserverOnNextListener<ArticleItemData>() {
            @Override
            public void onNext(ArticleItemData dataBean) {
                Logger.d("刷新" );
                mArtcileList.addAll(dataBean.getData().getDatas());
            }
            @Override
            public void onComplete() {
                //刷新适配器里的数据
                getView().getmArticleAdapter().notifyDataSetChanged();
            }
        };
        articleHomeModel.refresh(listener,currentCurPage);
        currentCurPage ++;
    }

    @Override
    public void loadMore() {
        //定义listener接受回调
        ObserverOnNextListener<ArticleItemData> listener = new ObserverOnNextListener<ArticleItemData>() {
            @Override
            public void onNext(ArticleItemData dataBean) {
                Logger.d("加载更多" );
                mArtcileList.addAll(dataBean.getData().getDatas());
            }
            @Override
            public void onComplete() {
                //刷新适配器里的数据
                getView().getmArticleAdapter().setData(mArtcileList);
            }
        };
        articleHomeModel.loadMore(listener,currentCurPage);
        currentCurPage++;
    }
}

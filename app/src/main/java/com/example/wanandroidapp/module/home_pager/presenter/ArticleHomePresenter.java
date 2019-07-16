package com.example.wanandroidapp.module.home_pager.presenter;

import com.example.wanandroidapp.base.presenter.BasePresenter;
import com.example.wanandroidapp.bean.ArticleItemData;
import com.example.wanandroidapp.bean.BannerData;
import com.example.wanandroidapp.core.http.ObserverOnNextListener;
import com.example.wanandroidapp.module.home_pager.contract.ArticleHomeContract;
import com.example.wanandroidapp.module.home_pager.model.ArticleHomeModel;
import com.example.wanandroidapp.module.home_pager.ui.ArticleHomeActivity;
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
    private List<BannerData.DataBean>  mBannerList ;
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
    }

    @Override
    public void getBannerData() {
        this.mBannerList = new ArrayList<>();
        //定义 Banner数据的回调listener处理
        ObserverOnNextListener<BannerData> listener = new ObserverOnNextListener<BannerData>() {
            @Override
            public void onNext(BannerData bannerData) {
                Logger.d("加载Banner  " + bannerData.getData().get(0).getImagePath());
                mBannerList.addAll(bannerData.getData());
                getView().showBannerData(bannerData.getData());
            }

            @Override
            public void onComplete() {

            }
        };
        articleHomeModel.getBannerData(listener);
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

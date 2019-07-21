package com.example.wanandroidapp.module.home_pager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.wanandroidapp.R;
import com.example.wanandroidapp.app.WanAndroidApp;
import com.example.wanandroidapp.base.presenter.IBasePresenter;
import com.example.wanandroidapp.base.view.BaseActivity;
import com.example.wanandroidapp.bean.ArticleItemData;
import com.example.wanandroidapp.bean.BannerData;
import com.example.wanandroidapp.module.ReadActivity;
import com.example.wanandroidapp.module.home_pager.contract.ArticleHomeContract;
import com.example.wanandroidapp.module.home_pager.presenter.ArticleHomePresenter;
import com.example.wanandroidapp.module.search_article.ui.SearchActivity;
import com.example.wanandroidapp.module.user.UserActivity;
import com.example.wanandroidapp.util.GlideImageLoader;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleHomeActivity<P extends IBasePresenter> extends BaseActivity<ArticleHomePresenter> implements ArticleHomeContract.View {

    @BindView(R.id.tool_bar_home)
    Toolbar toolBarHome;
    @BindView(R.id.appbarlayout_main)
    AppBarLayout appbarlayoutMain;
    @BindView(R.id.rv_item_article)
    XRecyclerView xRvArticle;
    @BindView(R.id.fb_updown)
    FloatingActionButton fbUpdown;

    private List<ArticleItemData.DataBean.DatasBean> articleList = new ArrayList<>();
    private List<String> urls = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private ArticleListAdapter mArticleAdapter;
    private Banner mBanner;
    private View mView;

    @Override
    public ArticleHomePresenter onBindPresenter() {
        //P层构造函数传参一个view进去
        return new ArticleHomePresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_ariticle);
        ButterKnife.bind(this);
        //通知P层去获取文章列表数据
        mArticleAdapter = new ArticleListAdapter(articleList);
            mView = View.inflate(this, R.layout.view_banner, null);
        getPresenter().getArticleList();
        getPresenter().getBannerData();
        initView();
        initToolbar();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (xRvArticle != null) {
            xRvArticle.destroy();
            xRvArticle = null;
        }
    }

    @Override
    protected void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRvArticle.setLayoutManager(layoutManager);

        xRvArticle.setAdapter(mArticleAdapter);
        xRvArticle.setRefreshProgressStyle(ProgressStyle.TriangleSkewSpin);
        xRvArticle.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
        //设置列表文章的点击事件--跳转网页
        mArticleAdapter.setRecyclerViewOnItemClickListener(new ArticleListAdapter.ItemClickListener() {
            @Override
            public void onArticleItemClick(View itemView, int position) {
                Intent intent = new Intent(ArticleHomeActivity.this, ReadActivity.class);
                intent.putExtra("url", mArticleAdapter.getmArticleList().get(position).getLink());
                intent.putExtra("title", mArticleAdapter.getmArticleList().get(position).getTitle());
                startActivity(intent);
            }
        });
        xRvArticle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //refresh data here
                getPresenter().refresh();
            }

            @Override
            public void onLoadMore() {
                // load more data here
                getPresenter().loadMore();
            }
        });
        fbUpdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xRvArticle.scrollToPosition(0);
            }
        });
    }

    public void initBanner() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_ariticle;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(toolBarHome);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_user_toolbar_32);
        }

    }

    /**
     * 给标题栏加载menu菜单布局
     *
     * @param menu 菜单布局
     * @return 状态
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_main, menu);
        return true;
    }

    /**
     * 处理标题栏各个按钮的点击事件
     *
     * @param item 按钮子项
     * @return 状态
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            //左上角按钮的实际id，但是用R.id.home 会找不到
            case 16908332:
                Intent intentUser = new Intent();
                intentUser.setClass(ArticleHomeActivity.this, UserActivity.class);
                startActivity(intentUser);
                break;
            case R.id.item_toolbar_search:
                Intent intentSearch = new Intent();
                intentSearch.setClass(ArticleHomeActivity.this, SearchActivity.class);
                startActivity(intentSearch);
                break;
            default:
                Logger.d("标题栏按钮点击事件出错");
                break;
        }
        return true;
    }


    @Override
    public ArticleListAdapter getmArticleAdapter() {
        return this.mArticleAdapter;
    }

    @Override
    public void showBannerData(List<BannerData.DataBean> bannerDataList) {
//        for (BannerData.DataBean dataBean : bannerDataList) {
//
//            Logger.d(dataBean.getImagePath());
//            urls.add(dataBean.getImagePath());
//            titles.add(dataBean.getTitle());
//        }
        //Banner初始化
        LinearLayout mHeaderGroup = (LinearLayout) getLayoutInflater().inflate(R.layout.view_banner, null);
        mBanner = mHeaderGroup.findViewById(R.id.head_banner);
        mHeaderGroup.removeView(mBanner);
        //设置banner样式,特定样式会需要设置标题进去
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合,urls是已经获取到图片地址的字符串列表
        mBanner.setImages(urls);
        mBanner.setBannerTitles(titles);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Accordion);
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(ArticleHomeActivity.this, ReadActivity.class);
                intent.putExtra("url", bannerDataList.get(position).getUrl());
                intent.putExtra("title", bannerDataList.get(position).getTitle());
                startActivity(intent);
            }
        });
        mBanner.start();
        mArticleAdapter.setHeaderView(mBanner);
    }

    @Override
    public void putBannerData(List<String> Urls , List<String> Titles){
        this.urls = Urls;
        this.titles = Titles;

    }
    @Override
    public XRecyclerView getxRvArticle() {
        return xRvArticle;
    }
}

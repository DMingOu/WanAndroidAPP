package com.example.wanandroidapp.module.article_home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.wanandroidapp.R;
import com.example.wanandroidapp.base.presenter.BasePresenter;
import com.example.wanandroidapp.base.presenter.IBasePresenter;
import com.example.wanandroidapp.base.view.BaseActivity;
import com.example.wanandroidapp.bean.ArticleItemData;
import com.example.wanandroidapp.bean.BannerData;
import com.example.wanandroidapp.module.ReadActivity;
import com.example.wanandroidapp.module.article_home.contract.ArticleHomeContract;
import com.example.wanandroidapp.module.article_home.presenter.ArticleHomePresenter;
import com.example.wanandroidapp.module.search.ui.SearchActivity;
import com.example.wanandroidapp.module.user.UserActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ArticleHomeActivity <P extends IBasePresenter> extends BaseActivity<ArticleHomePresenter> implements ArticleHomeContract.View {

    @BindView(R.id.tool_bar_home)
    Toolbar toolBarHome;
    @BindView(R.id.appbarlayout_main)
    AppBarLayout appbarlayoutMain;
    @BindView(R.id.rv_item_article)
    XRecyclerView xRvArticle;

    private List<ArticleItemData.DataBean.DatasBean> articleList = new ArrayList<>();
    private ArticleListAdapter mArticleAdapter;

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
        getPresenter().getArticleList();
        initView();
        initToolbar();
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

        mArticleAdapter.setRecyclerViewOnItemClickListener(new ArticleListAdapter.ItemClickLitener() {
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
                xRvArticle.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                // load more data here
                getPresenter().loadMore();
                xRvArticle.loadMoreComplete();
            }
        });
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
                    Logger.d("标题栏按钮点击事件出错");break;
        }
        return true;
    }

    @Override
    public void showArticleList(List<ArticleItemData.DataBean.DatasBean> mArticleList , boolean isRefresh) {
//        //把这个list 放进 adapter ，让它显示出来
//        //加载文章状态--Adapter增加
//        if (! isRefresh  ) {
//            articleList.addAll(mArticleList);
//
//            mArticleAdapter = new ArticleListAdapter(articleList);
//            initView();
//            //xRvArticle.notifyAll();
//        } else {
//            //刷新状态--Adapter恢复成一页
//            mArticleAdapter.notifyItemRangeRemoved(0,mArticleAdapter.getItemCount());
//            articleList.clear();
//            articleList.addAll(mArticleList);
//            xRvArticle.notifyAll();
//        }
    }


    @Override
    public ArticleListAdapter getmArticleAdapter() {
        return this.mArticleAdapter;
    }


    @Override
    public void showBannerData(List<BannerData> bannerDataList) {

    }
}

package com.example.wanandroidapp.module.home_pager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
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
import com.example.wanandroidapp.util.GlideUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;
import com.zhengsr.viewpagerlib.anim.MzTransformer;
import com.zhengsr.viewpagerlib.bean.PageBean;
import com.zhengsr.viewpagerlib.callback.PageHelperListener;
import com.zhengsr.viewpagerlib.indicator.ZoomIndicator;
import com.zhengsr.viewpagerlib.view.BannerViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleHomeActivity <P extends IBasePresenter> extends BaseActivity<ArticleHomePresenter> implements ArticleHomeContract.View {

    @BindView(R.id.tool_bar_home)
    Toolbar toolBarHome;
    @BindView(R.id.appbarlayout_main)
    AppBarLayout appbarlayoutMain;
    @BindView(R.id.rv_item_article)
    XRecyclerView xRvArticle;

    private List<ArticleItemData.DataBean.DatasBean> articleList = new ArrayList<>();
    private List<BannerData.DataBean>  mBannerList = new ArrayList<>();
    private ArticleListAdapter mArticleAdapter;
    private ZoomIndicator zoomIndicator = new ZoomIndicator(this,null);
    private BannerViewPager bannerViewPager = new BannerViewPager(this);

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
        //getPresenter().getBannerData();
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

    public void initBanner(){

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
    public ArticleListAdapter getmArticleAdapter() {
        return this.mArticleAdapter;
    }


    @Override
    public void showBannerData(List<BannerData.DataBean> bannerDataList) {
        mBannerList.addAll(bannerDataList);
        PageBean bean = new PageBean.Builder<BannerData.DataBean>()
                .setDataObjects(mBannerList)
                .setIndicator(zoomIndicator)
                .builder();
        //设置banner轮播动画为仿魅族动画
        bannerViewPager.setPageTransformer(false,new MzTransformer());
        bannerViewPager.setPageListener(bean, R.layout.item_banner, new PageHelperListener() {
            @Override
            public void getItemView(View view, Object data) {
                //加载banner子项图片
                View  mView = View.inflate(WanAndroidApp.getContext(),R.layout.activity_home_banner ,null);
                ImageView imageView =(ImageView) mView.findViewById(R.id.banner_icon);
                //ImageView  imageView = view.findViewById(R.id.banner_icon);
                BannerData.DataBean  bannerBean = (BannerData.DataBean) data;
                RequestOptions options = new RequestOptions()
                        .placeholder(R.mipmap.loading);
                GlideUtil.load(ArticleHomeActivity.this,bannerBean.getImagePath(),imageView,options);
                //设置banner的标题
                TextView textView = (TextView) mView.findViewById(R.id.banner_text);
              //  TextView textView = view.findViewById(R.id.banner_text);
                textView.setText(bannerBean.getTitle());
                //设置banner的点击事件
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ArticleHomeActivity.this, ReadActivity.class);
                        intent.putExtra("url",bannerBean.getUrl() );
                        intent.putExtra("title",bannerBean.getTitle() );
                        startActivity(intent);
                    }
                });
            }
        });
        //将banner 头布局添加进 XRecyclerView
        View header =   LayoutInflater.from(this).inflate(R.layout.activity_home_banner, (ViewGroup)findViewById(android.R.id.content),false);
        xRvArticle.addHeaderView(header);
    }
}

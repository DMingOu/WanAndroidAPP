package com.example.wanandroidapp.module.search_article.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wanandroidapp.R;
import com.example.wanandroidapp.base.presenter.IBasePresenter;
import com.example.wanandroidapp.base.view.BaseActivity;
import com.example.wanandroidapp.bean.ArticleItemData;
import com.example.wanandroidapp.module.ReadActivity;
import com.example.wanandroidapp.module.search_article.contract.Contract;
import com.example.wanandroidapp.module.search_article.presenter.Presenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity<P extends IBasePresenter> extends BaseActivity<Presenter> implements Contract.View {

    @BindView(R.id.tool_bar_search)
    Toolbar toolBarSearch;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv_article_search)
    XRecyclerView xRvArticleSearch;
    @BindView(R.id.tv_noResult)
    TextView tvNoResult;
    private List<ArticleItemData.DataBean.DatasBean> articleList = new ArrayList<>();
    private SearchArticleListAdapter searchArticleListAdapter;
    private String keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_article);
        ButterKnife.bind(this);
        searchArticleListAdapter = new SearchArticleListAdapter(articleList);
        initView();
        initToolbar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (xRvArticleSearch != null) {
            xRvArticleSearch.destroy();
            xRvArticleSearch = null;
        }
    }

    @Override
    public Presenter onBindPresenter() {
        return new Presenter(this);
    }

    @Override
    protected void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRvArticleSearch.setLayoutManager(layoutManager);
        xRvArticleSearch.setAdapter(searchArticleListAdapter);

        searchArticleListAdapter.setRecyclerViewOnItemClickListener(new SearchArticleListAdapter.ItemClickLitener() {
            @Override
            public void onArticleItemClick(View itemView, int position) {
                Intent intent = new Intent(SearchActivity.this, ReadActivity.class);
                intent.putExtra("url", searchArticleListAdapter.getmArticleList().get(position).getLink());
                intent.putExtra("title", searchArticleListAdapter.getmArticleList().get(position).getTitle());
                startActivity(intent);
            }
        });
        xRvArticleSearch.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //refresh data here
                xRvArticleSearch.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                // load more data here
                getPresenter().getArticleListSearch(etSearch.getText().toString());
                xRvArticleSearch.loadMoreComplete();
            }
        });
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //点击搜索的时候隐藏软键盘
                    hideKeyboard(etSearch);
                    keyword = etSearch.getText().toString();
                    articleList.clear();
                    // 搜索的操作--网络请求数据
                    getPresenter().getArticleListSearch(keyword);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_article;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(toolBarSearch);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_toolbar_32);
            actionBar.setTitle("搜索");
        }
    }

    @Override
    public void searchSuccess(List<ArticleItemData.DataBean.DatasBean> mArtcileList) {
        tvNoResult.setVisibility(View.GONE);
        etSearch.setVisibility(View.VISIBLE);
        xRvArticleSearch.setVisibility(View.VISIBLE);
        searchArticleListAdapter.setData(mArtcileList);
    }

    @Override
    public void searchFailure() {
        tvNoResult.setVisibility(View.VISIBLE);
        etSearch.setVisibility(View.VISIBLE);
        xRvArticleSearch.setVisibility(View.GONE);
    }

    /**
     * 隐藏软键盘
     *
     * @param view ：一般是EditText使用
     */
    public void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 点击返回键做了处理
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
        }
        return true;
    }

    @Override
    public SearchArticleListAdapter getmArticleAdapter() {
        return this.searchArticleListAdapter;
    }


}

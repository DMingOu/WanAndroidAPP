package com.example.wanandroidapp.module.user.history.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wanandroidapp.R;
import com.example.wanandroidapp.base.presenter.IBasePresenter;
import com.example.wanandroidapp.base.view.BaseActivity;
import com.example.wanandroidapp.bean.HistoryArticleData;
import com.example.wanandroidapp.module.ReadActivity;
import com.example.wanandroidapp.module.user.history.contract.HistoryContract;
import com.example.wanandroidapp.module.user.history.presenter.HistoryPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity<P extends IBasePresenter> extends BaseActivity<HistoryPresenter> implements HistoryContract.View {

    @BindView(R.id.tool_bar_history)
    Toolbar toolBarHistory;
    @BindView(R.id.xrv_article_history)
    XRecyclerView xrvArticleHistory;
    HistoryAdapter historyAdapter;
    @BindView(R.id.iv_noRecord)
    ImageView ivNoRecord;
    @BindView(R.id.tv_noRecord)
    TextView tvNoRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        initView();
        initToolbar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (xrvArticleHistory != null) {
            xrvArticleHistory.destroy();
            xrvArticleHistory = null;
        }
    }

    @Override
    public HistoryPresenter onBindPresenter() {
        return new HistoryPresenter(this);
    }

    @Override
    protected void initView() {
        historyAdapter = new HistoryAdapter(new ArrayList<>());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrvArticleHistory.setLayoutManager(layoutManager);
        xrvArticleHistory.setAdapter(historyAdapter);
        getPresenter().getHistoryData();
        historyAdapter.setRecyclerViewOnItemClickListener(new HistoryAdapter.ItemClickLitener() {
            @Override
            public void onArticleItemClick(View itemView, int position) {
                Intent intent = new Intent(HistoryActivity.this , ReadActivity.class);
                intent.putExtra("url" ,historyAdapter.getmHistoryDataList().get(position).getUrl());
                intent.putExtra("title" ,historyAdapter.getmHistoryDataList().get(position).getTitle());
                startActivity(intent);
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_history;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(toolBarHistory);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("本机文章阅读记录");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_toolbar_32);
        }
    }

    @Override
    public void showHistoryData(List<HistoryArticleData> list, boolean existRead) {
        if (existRead) {
            Logger.d("历史记录List大小:   " + list.size());
            historyAdapter.setData(list);
            ivNoRecord.setVisibility(View.GONE);
            tvNoRecord.setVisibility(View.GONE);
            xrvArticleHistory.setVisibility(View.VISIBLE);
        } else {
            ivNoRecord.setVisibility(View.VISIBLE);
            tvNoRecord.setVisibility(View.VISIBLE);
            xrvArticleHistory.setVisibility(View.GONE);
        }
    }


    /**
     * 标题栏返回键做了处理
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
}

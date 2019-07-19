package com.example.wanandroidapp.module.user.history.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoSession;
import com.example.wanandroidapp.R;
import com.example.wanandroidapp.app.WanAndroidApp;
import com.example.wanandroidapp.bean.HistoryArticleData;
import com.example.wanandroidapp.module.home_pager.ui.ArticleListAdapter;
import com.example.wanandroidapp.util.TimeUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: ODM
 * @date: 2019/7/17
 */
public class HistoryAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<HistoryArticleData>  mHistoryDataList = new ArrayList<>();
    private ItemClickLitener mItemClickListener;
    DaoSession daoSession = ((WanAndroidApp)WanAndroidApp.getContext()).getDaoSession();
    QueryBuilder<HistoryArticleData> qb = daoSession.queryBuilder(HistoryArticleData.class);

    public HistoryAdapter(List<HistoryArticleData> list) {
        this.mHistoryDataList = list;
    }
    public HistoryAdapter(){}

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        private CardView cvHistory;
        @BindView(R.id.cv_tv_history_author) TextView tvAuthor;
        @BindView(R.id.cv_tv_history_time) TextView tvTime;
        @BindView(R.id.cv_tv_history_title) TextView tvTitle;
        public HistoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            cvHistory = (CardView) itemView;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null) {
            mContext = parent.getContext();
        }
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_history,parent,false);

        return new HistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //让最新的阅读记录出现在最前面
        int pos;
        if(getItemCount() > 0) {
            pos = getItemCount() - position - 1;
        } else {
            pos = 0;
        }
        if(holder instanceof HistoryViewHolder) {
            HistoryArticleData itemData = mHistoryDataList.get(pos);
            ((HistoryViewHolder) holder).tvTitle.setText(Html.fromHtml(itemData.getTitle()));
            ((HistoryViewHolder) holder).tvAuthor.setText(itemData.getAuthor());
            ((HistoryViewHolder) holder).tvTime.setText("上次阅读时间:"+ itemData.getLastTime() );
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更新这篇文章再次阅读的时间
                if(holder instanceof  HistoryViewHolder) {
                    ((HistoryViewHolder) holder).tvTime.setText(TimeUtil.showCurrentTime(System.currentTimeMillis()));
                }
                HistoryArticleData  itemHistory = mHistoryDataList.get(pos);
                itemHistory.setLastTime(TimeUtil.showCurrentTime(System.currentTimeMillis()));
                Logger.d(" item标题"+ itemHistory.getTitle()+"   作者" + itemHistory.getAuthor()+"   time "+itemHistory.getLastTime());
                daoSession.update(itemHistory);
                if (mItemClickListener != null) {
                    mItemClickListener.onArticleItemClick(v, pos);
                }
            }

        });
    }

    @Override
    public int getItemCount() {
        return mHistoryDataList.size();
    }

    public List<HistoryArticleData> getmHistoryDataList() {
        return mHistoryDataList;
    }

    public void setData (List<HistoryArticleData> List) {
        mHistoryDataList.addAll(List);
        notifyDataSetChanged();
    }

    /**
     * 设置适配器的监听事件并初始化
     * @param onArticleItemClickListener
     */
    public void setRecyclerViewOnItemClickListener(ItemClickLitener onArticleItemClickListener) {
        this.mItemClickListener = onArticleItemClickListener;
    }

    /**
     * 定义点击事件的接口
     */
    public interface ItemClickLitener {
        void onArticleItemClick(View itemView, int position);
    }
}

package com.example.wanandroidapp.module.home_pager.ui;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoSession;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.HistoryArticleDataDao;
import com.example.wanandroidapp.R;
import com.example.wanandroidapp.app.WanAndroidApp;
import com.example.wanandroidapp.bean.ArticleItemData;
import com.example.wanandroidapp.bean.HistoryArticleData;
import com.example.wanandroidapp.util.TimeUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The type Article list adapter.
 *
 * @author: ODM
 * @date: 2019 /7/13
 */
public class ArticleListAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ArticleItemData.DataBean.DatasBean> mArticleList = new ArrayList<>();
    private DaoSession daoSession = ((WanAndroidApp)(WanAndroidApp.getContext())).getDaoSession();
    private  View mHeadView;
    private ArticleItemData.DataBean.DatasBean   itemData;
    private ItemClickListener mItemClickListener;
    private static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_NORMAL = 1;
    public ArticleListAdapter(List <ArticleItemData.DataBean.DatasBean> ArticleList) {
        mArticleList = ArticleList;
    }

    public ArticleListAdapter(){

    }


    public List<ArticleItemData.DataBean.DatasBean> getmArticleList() {
        return mArticleList;
    }

    public  static  class ItemArticleViewHolder extends RecyclerView.ViewHolder{
        private CardView cvItemArticle;
        @BindView(R.id.cv_tv_author) TextView tvAuthor;
        @BindView(R.id.cv_tv_time) TextView tvTime;
        @BindView(R.id.cv_tv_title) TextView tvTitle;
        @BindView(R.id.cv_tv_superChapterName) TextView tvSChapterName;
        public ItemArticleViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
            cvItemArticle = (CardView) itemView;

        }
    }

    //顶部加载布局Holder--轮播图
    public class HeaderViewHolder extends RecyclerView.ViewHolder{

        private HeaderViewHolder(View view) {
            super(view);
            return;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }

        if (viewType == ITEM_TYPE_HEADER) {
            return new HeaderViewHolder(mHeadView);
        } else {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_article, parent, false);
            return new ItemArticleViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemCount() <= 1) {
            return;
        }
        if(holder instanceof  HeaderViewHolder) {
            return;
        }
        if(holder instanceof  ItemArticleViewHolder){
            if(mHeadView != null) {
                itemData = mArticleList.get(position - 1);
                ((ItemArticleViewHolder) holder).cvItemArticle.setTag(position -1);
            } else {
                itemData = mArticleList.get(position );
                ((ItemArticleViewHolder) holder).cvItemArticle.setTag(position );
            }
            ((ItemArticleViewHolder) holder).tvTitle.setText(Html.fromHtml(itemData.getTitle()));
            ((ItemArticleViewHolder) holder).tvTime.setText(itemData.getNiceDate());
            String authoreInfo = "作者:  " + itemData.getAuthor();
            ((ItemArticleViewHolder) holder).tvAuthor.setText(authoreInfo);
            if (itemData.getSuperChapterName().equals("")) {
                itemData.setSuperChapterName("开发者");
            }
            ((ItemArticleViewHolder) holder).tvSChapterName.setText(itemData.getSuperChapterName());
            //设置Tag 方便进行点击事件数据的处理

        }
        QueryBuilder<HistoryArticleData> qb = daoSession.queryBuilder(HistoryArticleData.class);
        QueryBuilder<HistoryArticleData> historyArticleDataQueryBuilder = qb.where(HistoryArticleDataDao.Properties.Id
                .eq(itemData.getId())).orderAsc(HistoryArticleDataDao.Properties.Id);
        List<HistoryArticleData> list = historyArticleDataQueryBuilder.list();
        if (holder instanceof ItemArticleViewHolder) {
            //数据库有相同的标题（读过的)，设置为灰色，否则设置为黑色
            if(list.size() != 0){
                //灰色
                ((ItemArticleViewHolder) holder).tvTitle.setTextColor(Color.parseColor("#999999"));
            }  else {
                //黑色
                ((ItemArticleViewHolder) holder).tvTitle.setTextColor(Color.parseColor("#000000"));
            }
        }
        //若文章已被点击，则被设为灰色已读
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mHeadView != null){
                    itemData = mArticleList.get(position - 1);
                } else {
                    itemData = mArticleList.get(position);
                }
                itemData.setClicked(true);
                //将点击阅读的文章数据存入数据库,更新本次阅读的时间
                    HistoryArticleData history = new HistoryArticleData();
                    history.setAuthor(itemData.getAuthor());
                    history.setId(itemData.getId());
                    history.setUrl(itemData.getLink());
                    history.setTitle(itemData.getTitle());
                    history.setLastTime(TimeUtil.showCurrentTime(System.currentTimeMillis()));
                    daoSession.insertOrReplace(history);
                ((ItemArticleViewHolder) holder).tvTitle.setTextColor(Color.parseColor("#999999"));//灰色
                if (mItemClickListener != null) {
                    //注意这里使用getTag方法获取数据
                    mItemClickListener.onArticleItemClick(view, (Integer) view.getTag());
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return  mArticleList.size() + 1;
    }

    //根据不同位置判断view的种类，返回不同的值
    @Override
    public int getItemViewType(int position) {
        if(position == 0 && mHeadView != null) {
            return ITEM_TYPE_HEADER;
        }else {
            return ITEM_TYPE_NORMAL;
        }
    }

    public void setData(List<ArticleItemData.DataBean.DatasBean> mData) {
        this.mArticleList = mData;
        notifyDataSetChanged();//通知更新
    }

    /**
     * 设置适配器的监听事件并初始化
     * @param onArticleItemClickListener
     */
    public void setRecyclerViewOnItemClickListener(ItemClickListener onArticleItemClickListener) {
        this.mItemClickListener = onArticleItemClickListener;
    }

    /**
     * 定义点击事件的接口
     */
    public interface ItemClickListener {
        void onArticleItemClick(View itemView, int position);
    }


    /**
     * 插入顶部头布局
     * @param headerView
     */
    public void setHeaderView(View headerView) {
        mHeadView = headerView;
        notifyItemInserted(0);
        notifyDataSetChanged();
    }
}

package com.example.wanandroidapp.module.search_article.ui;

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

import com.example.wanandroidapp.R;
import com.example.wanandroidapp.bean.ArticleItemData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: ODM
 * @date: 2019/7/13
 */
public class SearchArticleListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;


    private List<ArticleItemData.DataBean.DatasBean> mArticleList = new ArrayList<>();

    private ArticleItemData Data;
    private ArticleItemData.DataBean.DatasBean   itemData;
    private SearchArticleListAdapter.ItemClickLitener mItemClickListener;

    public SearchArticleListAdapter(List <ArticleItemData.DataBean.DatasBean> ArticleList) {
        mArticleList = ArticleList;
    }

    public SearchArticleListAdapter(){

    }


    public List<ArticleItemData.DataBean.DatasBean> getmArticleList() {
        return mArticleList;
    }

    public  static  class ItemArticleViewHolder extends RecyclerView.ViewHolder{
        private CardView cvItemArticle;
        @BindView(R.id.cv_tv_author)
        TextView tvAuthor;
        @BindView(R.id.cv_tv_time) TextView tvTime;
        @BindView(R.id.cv_tv_title) TextView tvTitle;
        @BindView(R.id.cv_tv_superChapterName) TextView tvSChapterName;
        public ItemArticleViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
            cvItemArticle = (CardView) itemView;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null) {
            mContext = parent.getContext();
        }
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_article,parent,false);
        return new SearchArticleListAdapter.ItemArticleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof SearchArticleListAdapter.ItemArticleViewHolder){
            itemData = mArticleList.get(position);
            ((SearchArticleListAdapter.ItemArticleViewHolder) holder).tvTitle.setText(Html.fromHtml(itemData.getTitle()));
            ((SearchArticleListAdapter.ItemArticleViewHolder) holder).tvTime.setText(itemData.getNiceDate());
            String authoreInfo = "作者:  " + itemData.getAuthor();
            ((SearchArticleListAdapter.ItemArticleViewHolder) holder).tvAuthor.setText(authoreInfo);
            if ("".equals(itemData.getSuperChapterName())) {
                itemData.setSuperChapterName("开发者");
            }
            ((SearchArticleListAdapter.ItemArticleViewHolder) holder).tvSChapterName.setText(itemData.getSuperChapterName());
            //设置Tag 方便进行点击事件数据的处理
            ((SearchArticleListAdapter.ItemArticleViewHolder) holder).cvItemArticle.setTag(position);
        }
        //若文章已被点击，则被设为灰色已读
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int position = holder.getLayoutPosition();
                itemData.setClicked(true);
                if (holder instanceof SearchArticleListAdapter.ItemArticleViewHolder) {
                    ((SearchArticleListAdapter.ItemArticleViewHolder) holder).tvTitle.setTextColor(Color.parseColor("#999999"));//灰色
                }
                if (mItemClickListener != null) {
                    //注意这里使用getTag方法获取数据
                    mItemClickListener.onArticleItemClick(view, (Integer) view.getTag());
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return  mArticleList.size();
    }

    public void setData(List<ArticleItemData.DataBean.DatasBean> mData) {
        this.mArticleList = mData;
        notifyDataSetChanged();//通知更新
    }

    /**
     * 设置适配器的监听事件并初始化
     *
     * @param onArticleItemClickListener
     */
    public void setRecyclerViewOnItemClickListener(SearchArticleListAdapter.ItemClickLitener onArticleItemClickListener) {
        this.mItemClickListener = onArticleItemClickListener;
    }

    /**
     * 定义点击事件的接口
     */
    public interface ItemClickLitener{
        void onArticleItemClick(View itemView , int position);

    }
}

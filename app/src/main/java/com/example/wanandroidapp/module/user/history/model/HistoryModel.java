package com.example.wanandroidapp.module.user.history.model;

import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoSession;
import com.example.wanandroidapp.app.WanAndroidApp;
import com.example.wanandroidapp.bean.HistoryArticleData;
import com.example.wanandroidapp.module.user.history.contract.HistoryContract;
import com.orhanobut.logger.Logger;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * @author: ODM
 * @date: 2019/7/17
 */
public class HistoryModel implements HistoryContract.Model {


    @Override
    public List<HistoryArticleData> getHistoryData() {
        DaoSession daoSession = ((WanAndroidApp)WanAndroidApp.getContext()).getDaoSession();
        QueryBuilder<HistoryArticleData> qb = daoSession.queryBuilder(HistoryArticleData.class);
        List<HistoryArticleData>  list = qb.list();
        Logger.d("List size :" + list.size());
        return list;
    }
}

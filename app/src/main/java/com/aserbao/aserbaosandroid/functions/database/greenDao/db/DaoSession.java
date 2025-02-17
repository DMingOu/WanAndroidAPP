package com.aserbao.aserbaosandroid.functions.database.greenDao.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.wanandroidapp.bean.HistoryArticleData;

import com.aserbao.aserbaosandroid.functions.database.greenDao.db.HistoryArticleDataDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig historyArticleDataDaoConfig;

    private final HistoryArticleDataDao historyArticleDataDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        historyArticleDataDaoConfig = daoConfigMap.get(HistoryArticleDataDao.class).clone();
        historyArticleDataDaoConfig.initIdentityScope(type);

        historyArticleDataDao = new HistoryArticleDataDao(historyArticleDataDaoConfig, this);

        registerDao(HistoryArticleData.class, historyArticleDataDao);
    }
    
    public void clear() {
        historyArticleDataDaoConfig.clearIdentityScope();
    }

    public HistoryArticleDataDao getHistoryArticleDataDao() {
        return historyArticleDataDao;
    }

}

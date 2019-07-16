package com.example.wanandroidapp.app;

import android.app.Application;
import android.content.Context;
import org.greenrobot.greendao.*;
import android.database.sqlite.SQLiteDatabase;

import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoMaster;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoSession;


/**
 * @author: ODM
 * @date: 2019/7/14
 */
public class WanAndroidApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext =getApplicationContext();
        initGreenDao();
    }

    public static  Context getContext(){
        return  mContext;
    }
    /**
     * 初始化GreenDao,直接在Application中进行初始化操作
     */
    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "aserbao.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private DaoSession daoSession;
    public  DaoSession getDaoSession() {
        return daoSession;
    }


}

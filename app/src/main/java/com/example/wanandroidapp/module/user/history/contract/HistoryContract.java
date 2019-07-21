package com.example.wanandroidapp.module.user.history.contract;

import com.example.wanandroidapp.base.model.IBaseModel;
import com.example.wanandroidapp.base.presenter.IBasePresenter;
import com.example.wanandroidapp.base.view.IBaseView;
import com.example.wanandroidapp.bean.HistoryArticleData;

import java.util.List;

/**
 * The interface History contract.
 *
 * @author: ODM
 * @date: 2019 /7/17
 */
public interface HistoryContract {
    /**
     * The interface Model.
     */
    interface BaseModel extends IBaseModel {
        /**
         * Gets history data.
         *
         * @return the history data
         */
        List<HistoryArticleData> getHistoryData();
    }

    /**
     * The interface View.
     */
    interface View extends IBaseView {
        /**
         * Show history data.
         *
         * @param list      the list
         * @param existRecord the none read
         */
        void showHistoryData(List<HistoryArticleData> list , boolean  existRecord);
    }

    /**
     * The interface Presenter.
     */
    interface Presenter extends IBasePresenter {
        /**
         * Gets history data.
         */
        void  getHistoryData();
    }
}

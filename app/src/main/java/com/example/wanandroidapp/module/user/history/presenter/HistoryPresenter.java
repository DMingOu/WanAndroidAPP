package com.example.wanandroidapp.module.user.history.presenter;

import com.example.wanandroidapp.base.presenter.BasePresenter;
import com.example.wanandroidapp.bean.HistoryArticleData;
import com.example.wanandroidapp.module.user.history.contract.HistoryContract;
import com.example.wanandroidapp.module.user.history.model.HistoryBaseModel;
import com.example.wanandroidapp.module.user.history.ui.HistoryActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ODM
 * @date: 2019/7/17
 */
public class HistoryPresenter extends BasePresenter<HistoryContract.View> implements HistoryContract.Presenter {

    private HistoryBaseModel historyModel;
    private List<HistoryArticleData>  historyList = new ArrayList<>();

    public HistoryPresenter(HistoryActivity view){
        super(view);
        historyModel = new HistoryBaseModel();
    }

    @Override
    public void getHistoryData() {
        historyList = historyModel.getHistoryData();
        if (historyList.size() != 0) {
            getView().showHistoryData(historyList ,true);
        } else {
            getView().showHistoryData(historyList,false);
        }
    }
}

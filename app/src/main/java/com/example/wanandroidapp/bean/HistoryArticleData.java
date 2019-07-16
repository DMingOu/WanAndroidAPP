package com.example.wanandroidapp.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author: ODM
 * @date: 2019/7/16
 */
@Entity
public class HistoryArticleData {

    private  String title;
    private String  url;
    private  String lastTime;
    private  int id;
    private  String author;
    @Generated(hash = 105706023)
    public HistoryArticleData(String title, String url, String lastTime, int id,
            String author) {
        this.title = title;
        this.url = url;
        this.lastTime = lastTime;
        this.id = id;
        this.author = author;
    }
    @Generated(hash = 1325427137)
    public HistoryArticleData() {
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getLastTime() {
        return this.lastTime;
    }
    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

}

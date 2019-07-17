package com.example.wanandroidapp.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * @author: ODM
 * @date: 2019/7/16
 */
@Entity

public class HistoryArticleData {
    @Id(autoincrement = true)
    Long ID;
    @Unique
    private  String title;
    private String  url;
    private  String lastTime;
    private  int id;
    private  String author;
    @Generated(hash = 579887706)
    public HistoryArticleData(Long ID, String title, String url, String lastTime,
            int id, String author) {
        this.ID = ID;
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
    public Long getID() {
        return this.ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }

}

package com.example.wanandroidapp.bean;

import java.util.List;

/**
 * @author: ODM
 * @date: 2019/7/13
 */
public class LoginData {

    /**
     * data : {"admin":false,"chapterTops":[],"collectIds":[1726,8530,1792,1793,8655,8646,5091,8438,3329,1364],"email":"","icon":"","id":22832,"nickname":"758502274@qq.com","password":"","token":"","type":0,"username":"758502274@qq.com"}
     * errorCode : 0
     * errorMsg :
     */

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class DataBean {
        /**
         * admin : false
         * chapterTops : []
         * collectIds : [1726,8530,1792,1793,8655,8646,5091,8438,3329,1364]
         * email :
         * icon :
         * id : 22832
         * nickname : 758502274@qq.com
         * password :
         * token :
         * type : 0
         * username : 758502274@qq.com
         */

        private String icon;
        private int id;
        private String nickname;
        private String username;
        private List<?> chapterTops;
        private List<Integer> collectIds;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<?> getChapterTops() {
            return chapterTops;
        }

        public void setChapterTops(List<?> chapterTops) {
            this.chapterTops = chapterTops;
        }

        public List<Integer> getCollectIds() {
            return collectIds;
        }

        public void setCollectIds(List<Integer> collectIds) {
            this.collectIds = collectIds;
        }
    }
}

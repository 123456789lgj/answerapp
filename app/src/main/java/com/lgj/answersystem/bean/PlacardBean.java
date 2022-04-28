package com.lgj.answersystem.bean;

import java.io.Serializable;
import java.util.List;

public class PlacardBean implements Serializable {

    /**
     * code : 1
     * message : 操作成功
     * data : [{"id":1,"userId":10,"username":null,"title":"kk","imageList":null,"description":"lll","noticeType":1,"checkStatus":null,"gmtCreate":"2019-12-21 20:30:21","gmtModified":"2019-12-21 20:30:21"},{"id":2,"userId":10,"username":null,"title":"eer","imageList":"http://haoshangtong2518-1300600680.cos.ap-beijing.myqcloud.com/20191223111958246.jpg","description":"opopkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk","noticeType":1,"checkStatus":null,"gmtCreate":"2019-12-23 11:20:28","gmtModified":"2019-12-25 17:59:04"},{"id":3,"userId":18,"username":"23412341234","title":"eer","imageList":"llll","description":"lklk","noticeType":0,"checkStatus":2,"gmtCreate":"2019-12-23 12:07:24","gmtModified":"2020-02-09 17:09:28"},{"id":4,"userId":10,"username":null,"title":"ooo","imageList":null,"description":"lll","noticeType":1,"checkStatus":null,"gmtCreate":"2020-02-07 13:08:55","gmtModified":"2020-02-07 13:08:55"},{"id":5,"userId":10,"username":null,"title":"ppo","imageList":null,"description":"oo","noticeType":1,"checkStatus":null,"gmtCreate":"2020-02-07 13:10:51","gmtModified":"2020-02-07 13:10:51"},{"id":6,"userId":10,"username":null,"title":"ss","imageList":"http://haoshangtong2518-1300600680.cos.ap-beijing.myqcloud.com/20200208130131921.jpg","description":null,"noticeType":1,"checkStatus":null,"gmtCreate":"2020-02-08 13:01:34","gmtModified":"2020-02-08 13:01:34"},{"id":7,"userId":10,"username":null,"title":null,"imageList":"http://haoshangtong2518-1300600680.cos.ap-beijing.myqcloud.com/20200208130237398.jpg","description":null,"noticeType":1,"checkStatus":null,"gmtCreate":"2020-02-08 13:02:40","gmtModified":"2020-02-08 13:02:40"},{"id":8,"userId":10,"username":null,"title":"aa","imageList":"http://haoshangtong2518-1300600680.cos.ap-beijing.myqcloud.com/20200208132209325.jpg","description":"aaa","noticeType":1,"checkStatus":null,"gmtCreate":"2020-02-08 13:04:27","gmtModified":"2020-02-08 13:22:12"},{"id":9,"userId":10,"username":null,"title":"rrr","imageList":"http://haoshangtong2518-1300600680.cos.ap-beijing.myqcloud.com/20200208132317479.jpg","description":"ff","noticeType":1,"checkStatus":null,"gmtCreate":"2020-02-08 13:44:55","gmtModified":"2020-02-08 13:44:55"},{"id":10,"userId":10,"username":null,"title":"rrr","imageList":"http://haoshangtong2518-1300600680.cos.ap-beijing.myqcloud.com/20200208132317479.jpg","description":"ff","noticeType":1,"checkStatus":null,"gmtCreate":"2020-02-08 13:44:55","gmtModified":"2020-02-08 13:44:55"},{"id":12,"userId":10,"username":null,"title":null,"imageList":",,,,","description":null,"noticeType":1,"checkStatus":null,"gmtCreate":"2020-02-08 13:55:56","gmtModified":"2020-02-08 13:55:56"},{"id":13,"userId":10,"username":null,"title":"ss","imageList":",,,,","description":null,"noticeType":1,"checkStatus":null,"gmtCreate":"2020-02-08 13:59:21","gmtModified":"2020-02-08 13:59:21"},{"id":14,"userId":10,"username":null,"title":null,"imageList":null,"description":null,"noticeType":null,"checkStatus":null,"gmtCreate":"2020-02-08 14:21:44","gmtModified":"2020-02-08 14:21:44"},{"id":15,"userId":10,"username":null,"title":"","imageList":",,,,","description":"","noticeType":null,"checkStatus":null,"gmtCreate":"2020-02-08 14:26:19","gmtModified":"2020-02-08 14:26:19"},{"id":16,"userId":10,"username":null,"title":"","imageList":",,,,","description":"","noticeType":null,"checkStatus":null,"gmtCreate":"2020-02-08 14:27:35","gmtModified":"2020-02-08 14:27:35"},{"id":17,"userId":10,"username":null,"title":"","imageList":",,,,","description":"","noticeType":null,"checkStatus":null,"gmtCreate":"2020-02-08 14:29:01","gmtModified":"2020-02-08 14:29:01"},{"id":18,"userId":10,"username":null,"title":"gtg","imageList":null,"description":"bbf","noticeType":1,"checkStatus":null,"gmtCreate":"2020-02-08 14:42:20","gmtModified":"2020-02-08 14:42:52"},{"id":19,"userId":10,"username":null,"title":"yy","imageList":"http://haoshangtong2518-1300600680.cos.ap-beijing.myqcloud.com/20200208144943178.png,null,null,null,null","description":"f","noticeType":1,"checkStatus":null,"gmtCreate":"2020-02-08 14:49:45","gmtModified":"2020-02-08 14:49:45"},{"id":20,"userId":10,"username":null,"title":"ggh","imageList":"http://haoshangtong2518-1300600680.cos.ap-beijing.myqcloud.com/20200208145211464.jpg,http://haoshangtong2518-1300600680.cos.ap-beijing.myqcloud.com/20200208145214929.jpg,http://haoshangtong2518-1300600680.cos.ap-beijing.myqcloud.com/20200208145219824.jpg,http://haoshangtong2518-1300600680.cos.ap-beijing.myqcloud.com/20200208145223217.jpg,http://haoshangtong2518-1300600680.cos.ap-beijing.myqcloud.com/20200208145226805.jpg","description":"ttr","noticeType":1,"checkStatus":null,"gmtCreate":"2020-02-08 14:52:31","gmtModified":"2020-02-08 14:52:31"},{"id":21,"userId":41,"username":null,"title":"hhh","imageList":"null,null,null,null,null","description":"2020-02-07 18:36:31","noticeType":0,"checkStatus":1,"gmtCreate":"2020-02-09 11:40:48","gmtModified":"2020-02-09 13:03:20"},{"id":22,"userId":10,"username":null,"title":"ertyu","imageList":"null,null,null,null,null","description":null,"noticeType":1,"checkStatus":null,"gmtCreate":"2020-02-09 12:17:45","gmtModified":"2020-02-09 12:17:45"},{"id":24,"userId":25,"username":null,"title":"问题","imageList":"null,null,null,null,null","description":"holiday你咯","noticeType":0,"checkStatus":0,"gmtCreate":"2020-02-09 13:51:01","gmtModified":"2020-02-09 13:51:01"},{"id":25,"userId":10,"username":null,"title":"ll","imageList":"http://haoshangtong2518-1300600680.cos.ap-beijing.myqcloud.com/20200209171935433.jpg,null,null,null,null","description":",","noticeType":1,"checkStatus":null,"gmtCreate":"2020-02-09 17:19:40","gmtModified":"2020-02-09 17:19:40"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * userId : 10
         * username : null
         * title : kk
         * imageList : null
         * description : lll
         * noticeType : 1
         * checkStatus : null
         * gmtCreate : 2019-12-21 20:30:21
         * gmtModified : 2019-12-21 20:30:21
         */

        private int id;
        private int userId;
        private Object username;
        private String title;
        private Object imageList;
        private String description;
        private int noticeType;
        private Object checkStatus;
        private String gmtCreate;
        private String gmtModified;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public Object getUsername() {
            return username;
        }

        public void setUsername(Object username) {
            this.username = username;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getImageList() {
            return imageList;
        }

        public void setImageList(Object imageList) {
            this.imageList = imageList;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getNoticeType() {
            return noticeType;
        }

        public void setNoticeType(int noticeType) {
            this.noticeType = noticeType;
        }

        public Object getCheckStatus() {
            return checkStatus;
        }

        public void setCheckStatus(Object checkStatus) {
            this.checkStatus = checkStatus;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public String getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(String gmtModified) {
            this.gmtModified = gmtModified;
        }
    }
}

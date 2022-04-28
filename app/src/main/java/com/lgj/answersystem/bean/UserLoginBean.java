package com.lgj.answersystem.bean;

public class UserLoginBean {
    /**
     * code : 1
     * message : 操作成功
     * data : {"tokenHead":"Bearer","token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMzEyOTU5MzIzNiIsImNyZWF0ZWQiOjE1NzQwMDM4MDU0NzgsImV4cCI6MjE3ODgwMzgwNX0.Dn0YWIKlLbGXqjWDwvd4LgwAl0V2InRDmD3BafrGRfnVC4BvPSi_C-o95I5Au5e2dvEgEZ9dh7DSPAEJ_NymJA"}
     */

    private int code;
    private String message;
    private DataBean data;


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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * tokenHead : Bearer
         * token : eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMzEyOTU5MzIzNiIsImNyZWF0ZWQiOjE1NzQwMDM4MDU0NzgsImV4cCI6MjE3ODgwMzgwNX0.Dn0YWIKlLbGXqjWDwvd4LgwAl0V2InRDmD3BafrGRfnVC4BvPSi_C-o95I5Au5e2dvEgEZ9dh7DSPAEJ_NymJA
         */

        private String tokenHead;
        private String token;
        private int  userId;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getTokenHead() {
            return tokenHead;
        }

        public void setTokenHead(String tokenHead) {
            this.tokenHead = tokenHead;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}

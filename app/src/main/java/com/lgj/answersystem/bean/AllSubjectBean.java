package com.lgj.answersystem.bean;

import java.util.List;

public class AllSubjectBean {

    /**
     * code : 1
     * message : 操作成功
     * data : [{"id":9,"imageUrl":null,"professionName":"轮机技术","subjectList":[{"id":15,"subjectName":"主推力装置 ","professionId":9,"professionName":"轮机技术","useType":0,"deviceId":null,"useStatus":null,"activationTime":null,"expirationTime":null},{"id":14,"subjectName":"轮机盖伦","professionId":9,"professionName":"轮机技术","useType":0,"deviceId":null,"useStatus":null,"activationTime":null,"expirationTime":null}]},{"id":8,"imageUrl":null,"professionName":"6y65","subjectList":[{"id":13,"subjectName":"rgt","professionId":8,"professionName":"6y65","useType":0,"deviceId":null,"useStatus":null,"activationTime":null,"expirationTime":null}]},{"id":7,"imageUrl":null,"professionName":"dff","subjectList":[{"id":12,"subjectName":"fb","professionId":7,"professionName":"dff","useType":0,"deviceId":null,"useStatus":null,"activationTime":null,"expirationTime":null}]}]
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
         * id : 9
         * imageUrl : null
         * professionName : 轮机技术
         * subjectList : [{"id":15,"subjectName":"主推力装置 ","professionId":9,"professionName":"轮机技术","useType":0,"deviceId":null,"useStatus":null,"activationTime":null,"expirationTime":null},{"id":14,"subjectName":"轮机盖伦","professionId":9,"professionName":"轮机技术","useType":0,"deviceId":null,"useStatus":null,"activationTime":null,"expirationTime":null}]
         */

        private int id;
        private Object imageUrl;
        private String professionName;
        private List<SubjectListBean> subjectList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(Object imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getProfessionName() {
            return professionName;
        }

        public void setProfessionName(String professionName) {
            this.professionName = professionName;
        }

        public List<SubjectListBean> getSubjectList() {
            return subjectList;
        }

        public void setSubjectList(List<SubjectListBean> subjectList) {
            this.subjectList = subjectList;
        }

        public static class SubjectListBean {
            /**
             * id : 15
             * subjectName : 主推力装置
             * professionId : 9
             * professionName : 轮机技术
             * useType : 0
             * deviceId : null
             * useStatus : null
             * activationTime : null
             * expirationTime : null
             */

            private int id;
            private String subjectName;
            private int professionId;
            private String professionName;
            private int useType;
            private Object deviceId;
            private Object useStatus;
            private Object activationTime;
            private Object expirationTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSubjectName() {
                return subjectName;
            }

            public void setSubjectName(String subjectName) {
                this.subjectName = subjectName;
            }

            public int getProfessionId() {
                return professionId;
            }

            public void setProfessionId(int professionId) {
                this.professionId = professionId;
            }

            public String getProfessionName() {
                return professionName;
            }

            public void setProfessionName(String professionName) {
                this.professionName = professionName;
            }

            public int getUseType() {
                return useType;
            }

            public void setUseType(int useType) {
                this.useType = useType;
            }

            public Object getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(Object deviceId) {
                this.deviceId = deviceId;
            }

            public Object getUseStatus() {
                return useStatus;
            }

            public void setUseStatus(Object useStatus) {
                this.useStatus = useStatus;
            }

            public Object getActivationTime() {
                return activationTime;
            }

            public void setActivationTime(Object activationTime) {
                this.activationTime = activationTime;
            }

            public Object getExpirationTime() {
                return expirationTime;
            }

            public void setExpirationTime(Object expirationTime) {
                this.expirationTime = expirationTime;
            }
        }
    }
}

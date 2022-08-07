package com.lgj.answersystem.bean;


import java.util.List;

public class QuestionBean {

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
         * id : 799
         * readId : null
         * questionTitle : 判断是否存在碰撞危险时，考虑的因素是来船方位的变化情况，该方位是指______。
         * fileUrl : null
         * fileType : null
         * optionA : 罗经方位
         * optionAUrl : null
         * optionB : 相对方位
         * optionBUrl : null
         * optionC : 舷角
         * optionCUrl : null
         * optionD : 真方位
         * optionDUrl : null
         * optionE :
         * optionEUrl : null
         * answer : A
         * parse :
         * typeName : null
         * questionType : 0
         * score : 5
         * difficult : 3
         * creator : null
         * professionId : 9
         * professionName : 轮机技术
         * subjectId : 15
         * subjectName : 主推力装置
         * bigCategoryName : 机械做功
         * bigCategoryId : 37
         * smallCategoryName : 机械动能守恒定律
         * smallCategoryId : 308
         * paperType : 0
         * gmtCreate : 2021-12-26 15:21:59
         * gmtModified : 2021-12-26 15:21:59
         * file : null
         */

        private int id;
        private Object readId;
        private String questionTitle;
        private Object fileUrl;
        private Object fileType;
        private String optionA;
        private String optionAUrl;
        private String optionB;
        private String optionBUrl;
        private String optionC;
        private String optionCUrl;
        private String optionD;
        private String optionDUrl;
        private String optionE;
        private String optionEUrl;
        private String answer;
        private String parse;
        private Object typeName;
        private int questionType;
        private int score;
        private int difficult;
        private Object creator;
        private int professionId;
        private String professionName;
        private int subjectId;
        private String subjectName;
        private String bigCategoryName;
        private int bigCategoryId;
        private String smallCategoryName;
        private int smallCategoryId;
        private int paperType;
        private String gmtCreate;
        private String gmtModified;
        private Object file;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getReadId() {
            return readId;
        }

        public void setReadId(Object readId) {
            this.readId = readId;
        }

        public String getQuestionTitle() {
            return questionTitle;
        }

        public void setQuestionTitle(String questionTitle) {
            this.questionTitle = questionTitle;
        }

        public Object getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(Object fileUrl) {
            this.fileUrl = fileUrl;
        }

        public Object getFileType() {
            return fileType;
        }

        public void setFileType(Object fileType) {
            this.fileType = fileType;
        }

        public String getOptionA() {
            return optionA;
        }

        public void setOptionA(String optionA) {
            this.optionA = optionA;
        }

        public String getOptionAUrl() {
            return optionAUrl;
        }

        public void setOptionAUrl(String optionAUrl) {
            this.optionAUrl = optionAUrl;
        }

        public String getOptionB() {
            return optionB;
        }

        public void setOptionB(String optionB) {
            this.optionB = optionB;
        }

        public String getOptionBUrl() {
            return optionBUrl;
        }

        public void setOptionBUrl(String optionBUrl) {
            this.optionBUrl = optionBUrl;
        }

        public String getOptionC() {
            return optionC;
        }

        public void setOptionC(String optionC) {
            this.optionC = optionC;
        }

        public String getOptionCUrl() {
            return optionCUrl;
        }

        public void setOptionCUrl(String optionCUrl) {
            this.optionCUrl = optionCUrl;
        }

        public String getOptionD() {
            return optionD;
        }

        public void setOptionD(String optionD) {
            this.optionD = optionD;
        }

        public String getOptionDUrl() {
            return optionDUrl;
        }

        public void setOptionDUrl(String optionDUrl) {
            this.optionDUrl = optionDUrl;
        }

        public String getOptionE() {
            return optionE;
        }

        public void setOptionE(String optionE) {
            this.optionE = optionE;
        }

        public String getOptionEUrl() {
            return optionEUrl;
        }

        public void setOptionEUrl(String optionEUrl) {
            this.optionEUrl = optionEUrl;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getParse() {
            return parse;
        }

        public void setParse(String parse) {
            this.parse = parse;
        }

        public Object getTypeName() {
            return typeName;
        }

        public void setTypeName(Object typeName) {
            this.typeName = typeName;
        }

        public int getQuestionType() {
            return questionType;
        }

        public void setQuestionType(int questionType) {
            this.questionType = questionType;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getDifficult() {
            return difficult;
        }

        public void setDifficult(int difficult) {
            this.difficult = difficult;
        }

        public Object getCreator() {
            return creator;
        }

        public void setCreator(Object creator) {
            this.creator = creator;
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

        public int getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(int subjectId) {
            this.subjectId = subjectId;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public String getBigCategoryName() {
            return bigCategoryName;
        }

        public void setBigCategoryName(String bigCategoryName) {
            this.bigCategoryName = bigCategoryName;
        }

        public int getBigCategoryId() {
            return bigCategoryId;
        }

        public void setBigCategoryId(int bigCategoryId) {
            this.bigCategoryId = bigCategoryId;
        }

        public String getSmallCategoryName() {
            return smallCategoryName;
        }

        public void setSmallCategoryName(String smallCategoryName) {
            this.smallCategoryName = smallCategoryName;
        }

        public int getSmallCategoryId() {
            return smallCategoryId;
        }

        public void setSmallCategoryId(int smallCategoryId) {
            this.smallCategoryId = smallCategoryId;
        }

        public int getPaperType() {
            return paperType;
        }

        public void setPaperType(int paperType) {
            this.paperType = paperType;
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

        public Object getFile() {
            return file;
        }

        public void setFile(Object file) {
            this.file = file;
        }
    }
}
package com.lgj.answersystem.bean;

import java.util.List;

public class ChapterBean {

    /**
     * code : 1
     * message : 操作成功
     * data : {"chapterList":[{"id":37,"bigCategoryName":"机械做功","professionId":9,"professionName":null,"subjectId":15,"subjectName":"主推力装置 ","paperType":0,"smallList":[{"id":308,"smallCategoryName":"机械动能守恒定律","creator":1,"professionId":9,"professionName":null,"subjectId":15,"subjectName":"主推力装置 ","bigCategoryName":"机械做功","bigCategoryId":37,"paperType":null,"score":null,"difficult":null,"gmtCreate":"2021-12-24 20:05:36","gmtModified":"2021-12-24 20:05:36"},{"id":309,"smallCategoryName":"阅读理解题","creator":1,"professionId":9,"professionName":null,"subjectId":15,"subjectName":"主推力装置 ","bigCategoryName":"机械做功","bigCategoryId":37,"paperType":null,"score":null,"difficult":null,"gmtCreate":"2022-05-26 03:01:49","gmtModified":"2022-05-26 03:01:49"}]}],"testExamList":[],"realExamList":[]}
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
        private List<ChapterListBean> chapterList;
        private List<?> testExamList;
        private List<?> realExamList;

        public List<ChapterListBean> getChapterList() {
            return chapterList;
        }

        public void setChapterList(List<ChapterListBean> chapterList) {
            this.chapterList = chapterList;
        }

        public List<?> getTestExamList() {
            return testExamList;
        }

        public void setTestExamList(List<?> testExamList) {
            this.testExamList = testExamList;
        }

        public List<?> getRealExamList() {
            return realExamList;
        }

        public void setRealExamList(List<?> realExamList) {
            this.realExamList = realExamList;
        }

        public static class ChapterListBean {
            /**
             * id : 37
             * bigCategoryName : 机械做功
             * professionId : 9
             * professionName : null
             * subjectId : 15
             * subjectName : 主推力装置
             * paperType : 0
             * smallList : [{"id":308,"smallCategoryName":"机械动能守恒定律","creator":1,"professionId":9,"professionName":null,"subjectId":15,"subjectName":"主推力装置 ","bigCategoryName":"机械做功","bigCategoryId":37,"paperType":null,"score":null,"difficult":null,"gmtCreate":"2021-12-24 20:05:36","gmtModified":"2021-12-24 20:05:36"},{"id":309,"smallCategoryName":"阅读理解题","creator":1,"professionId":9,"professionName":null,"subjectId":15,"subjectName":"主推力装置 ","bigCategoryName":"机械做功","bigCategoryId":37,"paperType":null,"score":null,"difficult":null,"gmtCreate":"2022-05-26 03:01:49","gmtModified":"2022-05-26 03:01:49"}]
             */

            private int id;
            private String bigCategoryName;
            private int professionId;
            private Object professionName;
            private int subjectId;
            private String subjectName;
            private int paperType;
            private List<SmallListBean> smallList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getBigCategoryName() {
                return bigCategoryName;
            }

            public void setBigCategoryName(String bigCategoryName) {
                this.bigCategoryName = bigCategoryName;
            }

            public int getProfessionId() {
                return professionId;
            }

            public void setProfessionId(int professionId) {
                this.professionId = professionId;
            }

            public Object getProfessionName() {
                return professionName;
            }

            public void setProfessionName(Object professionName) {
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

            public int getPaperType() {
                return paperType;
            }

            public void setPaperType(int paperType) {
                this.paperType = paperType;
            }

            public List<SmallListBean> getSmallList() {
                return smallList;
            }

            public void setSmallList(List<SmallListBean> smallList) {
                this.smallList = smallList;
            }

            public static class SmallListBean {
                /**
                 * id : 308
                 * smallCategoryName : 机械动能守恒定律
                 * creator : 1
                 * professionId : 9
                 * professionName : null
                 * subjectId : 15
                 * subjectName : 主推力装置
                 * bigCategoryName : 机械做功
                 * bigCategoryId : 37
                 * paperType : null
                 * score : null
                 * difficult : null
                 * gmtCreate : 2021-12-24 20:05:36
                 * gmtModified : 2021-12-24 20:05:36
                 */

                private int id;
                private String smallCategoryName;
                private int creator;
                private int professionId;
                private Object professionName;
                private int subjectId;
                private String subjectName;
                private String bigCategoryName;
                private int bigCategoryId;
                private Object paperType;
                private Object score;
                private Object difficult;
                private String gmtCreate;
                private String gmtModified;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getSmallCategoryName() {
                    return smallCategoryName;
                }

                public void setSmallCategoryName(String smallCategoryName) {
                    this.smallCategoryName = smallCategoryName;
                }

                public int getCreator() {
                    return creator;
                }

                public void setCreator(int creator) {
                    this.creator = creator;
                }

                public int getProfessionId() {
                    return professionId;
                }

                public void setProfessionId(int professionId) {
                    this.professionId = professionId;
                }

                public Object getProfessionName() {
                    return professionName;
                }

                public void setProfessionName(Object professionName) {
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

                public Object getPaperType() {
                    return paperType;
                }

                public void setPaperType(Object paperType) {
                    this.paperType = paperType;
                }

                public Object getScore() {
                    return score;
                }

                public void setScore(Object score) {
                    this.score = score;
                }

                public Object getDifficult() {
                    return difficult;
                }

                public void setDifficult(Object difficult) {
                    this.difficult = difficult;
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
    }
}

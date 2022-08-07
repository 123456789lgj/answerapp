package com.lgj.answersystem.bean;

import com.lgj.answersystem.answer.OptionStatus;

import retrofit2.http.OPTIONS;

// 选项的Javabean对象
public class QuestionOptionBean {
    public OptionStatus optionStatus = OptionStatus.NORMAL;
    public String optionA;// 选项名称
    public String description;// 选项描述
    public String imageUrl;
    public String answer;//正确答案
    public boolean chooseThisQuestion;//表示是否选择该题，true
    public int isRight = -1;// 1 表示正确，0 表示错误，统计该题是否正确
    public QuestionOptionBean(String optionA, String description, String imageUrl, String answer) {
        this.optionA = optionA;
        this.description = description;
        this.imageUrl = imageUrl;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuestionOptionBean{" +
                "optionStatus=" + optionStatus +
                ", optionA='" + optionA + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", answer='" + answer + '\'' +
                ", chooseThisQuestion=" + chooseThisQuestion +
                ", isRight=" + isRight +
                '}';
    }
}

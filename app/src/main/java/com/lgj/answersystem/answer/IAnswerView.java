package com.lgj.answersystem.answer;

import com.lgj.answersystem.bean.QuestionBean;

import java.util.List;

public interface IAnswerView {
    void showDialog();

    void dismissDialog();

     void fillQuestionData(List<QuestionBean.DataBean> data);
}

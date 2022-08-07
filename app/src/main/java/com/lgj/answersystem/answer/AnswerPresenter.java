package com.lgj.answersystem.answer;

import androidx.fragment.app.FragmentActivity;

import com.lgj.answersystem.bean.QuestionBean;

import java.util.List;

public class AnswerPresenter implements IQuestionBankCompleteListener<QuestionBean.DataBean> {
    private int mSubjectId;
    private int mSmallId;
    private final AnswerModel mAnswerModel;
    private IAnswerView mAnswerView;

    public AnswerPresenter(IAnswerView answerView, int subjectId, int smallId) {
        this.mSubjectId = subjectId;
        this.mSmallId = smallId;
        this.mAnswerView = answerView;
        mAnswerModel = new AnswerModel(this, mSubjectId, mSmallId);
        System.out.println("lgj AnswerPresenter 222222222");
    }


    public void getDataFromPresenter() {
        System.out.println("lgj AnswerPresenter 4444444");
        mAnswerModel.getServiceData();
    }


    @Override
    public void onComplete(List<QuestionBean.DataBean> data) {
        mAnswerView.fillQuestionData(data);
    }

    @Override
    public void showLoadingDialog() {
        mAnswerView.showDialog();
    }

    @Override
    public void dismissLoadingDialog() {
        mAnswerView.dismissDialog();
    }
}

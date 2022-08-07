package com.lgj.answersystem.answer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.lgj.answersystem.bean.QuestionBean;

import java.util.ArrayList;
import java.util.List;
// AnswerPagerAdapter AnswerFragment AnswerActivity 这些都是View层
public class AnswerPagerAdapter extends FragmentStateAdapter implements IAnswerView {
    private List<QuestionBean.DataBean> mDataList;
    private final AnswerPresenter answerPresenter;
    private AnswerActivity mAnswerActivity;

    public AnswerPagerAdapter(@NonNull FragmentActivity answerActivity, int subjectId, int smallId) {
        super(answerActivity);
        this.mAnswerActivity = (AnswerActivity) answerActivity;
        System.out.println("lgj AnswerPagerAdapter 111111111");
        // AnswerPresenter presenter层
        answerPresenter = new AnswerPresenter(this, subjectId, smallId);
    }

    public void initData(){
        answerPresenter.getDataFromPresenter();
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        System.out.println("lgj AnswerPagerAdapter createFragment");
        QuestionBean.DataBean dataBean = mDataList.get(position);
        AnswerFragment answerFragment = new AnswerFragment(dataBean, position,mDataList.size());
        // 也可以通过bundle传递数据
//        answerFragment.setArguments();
        return answerFragment;
    }

    @Override
    public void fillQuestionData(List<QuestionBean.DataBean> data) {
        this.mDataList = data;
        System.out.println("lgj mDataList :" + mDataList.size());
        mAnswerActivity.setAdapter(data);
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public void showDialog() {
        mAnswerActivity.showLoading("加载时间可能较长，请稍等...");
    }

    @Override
    public void dismissDialog() {
        mAnswerActivity.dismissLoading();
    }


}

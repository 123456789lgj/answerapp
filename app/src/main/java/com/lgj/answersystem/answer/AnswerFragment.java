package com.lgj.answersystem.answer;


import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgj.answersystem.R;
import com.lgj.answersystem.bean.QuestionBean;
import com.lgj.answersystem.bean.QuestionOptionBean;
import com.lgj.answersystem.bean.RecordDoneQuestionBean;
import com.lgj.answersystem.fragment.BaseFragment;
import com.lgj.answersystem.util.OtherUtils;

import java.util.ArrayList;

public class AnswerFragment extends BaseFragment implements IUpdateRightAndErrorCount, View.OnClickListener {
    private QuestionBean.DataBean mDataBan;
    private int mCurrentPosition;
    private TextView mTvQuestionType;
    private TextView mTvNumberPager;
    private TextView mTvQuestionTitle;
    private TextView mTvCheckAnalysis;
    private TextView mTvRightOption;
    private TextView mTvMyAnswerOption;
    private RecyclerView mRecyclerViewOption;
    private LinearLayout mAnalysis;//查看解析
    private ImageView mIvArrowDown;//查看解析的箭头
    private RelativeLayout mAnalysisContent;//解析内容
    private int mTotalSize;
    private OptionRecyclerAdapter mOptionRecyclerAdapter;
    String A, B, C, D, E, AUrl, BUrl, CUrl, DUrl, EUrl, answer;//正确答案
    private int mQuestionType = -1;//题型

    public AnswerFragment(QuestionBean.DataBean dataBean, int position, int size) {
        this.mDataBan = dataBean;
        this.mCurrentPosition = position + 1;
        this.mTotalSize = size;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("lgj : AnswerFragment onCreate position :" + mCurrentPosition + " --- " + this);
    }

    @Override
    public View initView() {
        View view = View.inflate(getContext(), R.layout.answer_fragment, null);
        mTvNumberPager = view.findViewById(R.id.tvNumberPager);
        mTvQuestionTitle = view.findViewById(R.id.tvQuestionTitle);
        mRecyclerViewOption = view.findViewById(R.id.recyclerViewOption);
        mTvQuestionType = view.findViewById(R.id.tvQuestionType);
        mAnalysis = view.findViewById(R.id.llAnalysis);
        mIvArrowDown = view.findViewById(R.id.ivArrowDown);
        mTvCheckAnalysis = view.findViewById(R.id.tvCheckAnalysis);
        mTvRightOption = view.findViewById(R.id.tvRightOption);
        mTvMyAnswerOption = view.findViewById(R.id.tvMyAnswerOption);
        mAnalysisContent = view.findViewById(R.id.rlAnalysisContent);
        mAnalysis.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mDataBan == null) {
            return;
        }
        // 题型
        mQuestionType = mDataBan.getQuestionType();
        setQuestionType();
        // 题目
        String questionTitle = mDataBan.getQuestionTitle();
        if (questionTitle.startsWith("[1-9]")) {

        }
        mTvQuestionTitle.setText(mCurrentPosition + "、" + questionTitle);
        // 设置当前的到那一页了
        String content = "<font color=\"#2699fb\">" + mCurrentPosition + "</font>" + "/" + mTotalSize;
        mTvNumberPager.setText(Html.fromHtml(content));
        // 组装数据
        assemblingData();
        mOptionRecyclerAdapter = new OptionRecyclerAdapter(mOptionsList, mQuestionType, mCurrentPosition - 1);
        mOptionRecyclerAdapter.setUpdateRightAndErrorCount(this);
        mRecyclerViewOption.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewOption.setAdapter(mOptionRecyclerAdapter);

    }

    private ArrayList<QuestionOptionBean> mOptionsList = new ArrayList<>();

    private void assemblingData() {
        answer = mDataBan.getAnswer();
        A = mDataBan.getOptionA();
        B = mDataBan.getOptionB();
        C = mDataBan.getOptionC();
        D = mDataBan.getOptionD();
        E = mDataBan.getOptionE();
        AUrl = mDataBan.getOptionAUrl();
        BUrl = mDataBan.getOptionBUrl();
        CUrl = mDataBan.getOptionCUrl();
        DUrl = mDataBan.getOptionDUrl();
        EUrl = mDataBan.getOptionEUrl();
        switch (mQuestionType) {
            case 0:
            case 1:
            case 4:
                QuestionOptionBean a = new QuestionOptionBean("A", A, AUrl, answer);
                QuestionOptionBean b = new QuestionOptionBean("B", B, BUrl, answer);
                QuestionOptionBean c = new QuestionOptionBean("C", C, CUrl, answer);
                QuestionOptionBean d = new QuestionOptionBean("D", D, DUrl, answer);
                mOptionsList.add(a);
                mOptionsList.add(b);
                mOptionsList.add(c);
                mOptionsList.add(d);
                if (!TextUtils.isEmpty(E)) {
                    QuestionOptionBean e = new QuestionOptionBean("E", E, EUrl, answer);
                    mOptionsList.add(e);
                }
                break;
        }
    }

    private void setQuestionType() {
        String msgType = "";
        switch (mQuestionType) {
            case 0://单选
                msgType = "单选题";
                break;
            case 1://多选
                msgType = "多选题";
                break;
            case 2://判断
                msgType = "判断题";
                break;
            case 3://简答题
                msgType = "简答题";
                break;
            case 4://阅读理解
                msgType = "阅读理解";
                break;
            case 5://填空题
                msgType = "填空题";
                break;
            default:
                break;
        }

        mTvQuestionType.setText(msgType);
    }

    @Override
    public void onDestroy() {
        System.out.println("lgj AnswerFragment onDestroy :" + mCurrentPosition + " --- " + this);
        super.onDestroy();
    }

    //
    @Override
    public void updateRightCount() {
        ((AnswerActivity) activity).updateRightCount();
    }

    @Override
    public void updateErrorCount() {
        ((AnswerActivity) activity).updateErrorCount();
    }

    @Override
    public void showAnalysis(boolean isShow, String chooseOption) {
        ObjectAnimator animator;
        if (isShow) {
            mAnalysisContent.setVisibility(View.VISIBLE);
            mTvCheckAnalysis.setText("隐藏解析");
            animator = ObjectAnimator.ofFloat(mIvArrowDown, "rotation", 0.0F, -180F);//设置先顺时针180度旋转
        } else {
            mAnalysisContent.setVisibility(View.GONE);
            mTvCheckAnalysis.setText("查看解析");
            //构造ObjectAnimator对象的方法
            animator = ObjectAnimator.ofFloat(mIvArrowDown, "rotation", -180.0F, 0);//设置先顺时针180度旋转
        }
        animator.setDuration(300);//设置旋转时间
        animator.start();//开始执行动画（顺时针旋转动画）
        mTvMyAnswerOption.setText(chooseOption);
        mTvRightOption.setText(answer);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llAnalysis:
                RecordDoneQuestionBean currentRecord = RecordUserDoneQuestion.getInstance().getCurrentRecord(mCurrentPosition -1);
                if (currentRecord == null) {
                    showAnalysis(!mAnalysisContent.isShown(), "");
                }else {
                    int chooseOption = currentRecord.chooseOption;
                    showAnalysis(!mAnalysisContent.isShown(), OtherUtils.indexTransformString(chooseOption));
                }
                break;
        }
    }


}

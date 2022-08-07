package com.lgj.answersystem.answer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lgj.answersystem.MyApplication;
import com.lgj.answersystem.R;
import com.lgj.answersystem.bean.QuestionOptionBean;
import com.lgj.answersystem.bean.RecordDoneQuestionBean;
import com.lgj.answersystem.util.OtherUtils;
import com.lgj.answersystem.util.ToastUtils;

import java.util.ArrayList;

public class OptionRecyclerAdapter extends RecyclerView.Adapter<OptionRecyclerAdapter.MyHolder> implements View.OnClickListener {

    private ArrayList<QuestionOptionBean> mOptionsList;
    private int mCurrentPosition;

    private int mQuestionType = -1;
    private boolean isDone = false;// 判断该题是否已经做过
    private RecordDoneQuestionBean mRrecordBean;
    IUpdateRightAndErrorCount mUpdateRightAndErrorCount;

    public void setUpdateRightAndErrorCount(IUpdateRightAndErrorCount updateRightAndErrorCount) {
        this.mUpdateRightAndErrorCount = updateRightAndErrorCount;
    }

    public OptionRecyclerAdapter(ArrayList<QuestionOptionBean> optionsList, int questionType, int currentPosition) {
        this.mOptionsList = optionsList;
        this.mQuestionType = questionType;
        this.mCurrentPosition = currentPosition;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.option_item_recycler_adapter, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        System.out.println("lgj onBindViewHolder " + position);
        QuestionOptionBean questionOptionBean = mOptionsList.get(position);
        RecordDoneQuestionBean currentRecord = RecordUserDoneQuestion.getInstance().getCurrentRecord(mCurrentPosition);
        // 如果获取当前做的题目有缓存，说明之前已经做过
        if (currentRecord != null) {
            isDone = true;
            // 此选项表示用户选择的那个
            if (position == currentRecord.chooseOption) {
                if (currentRecord.isRight) {
                    questionOptionBean.optionStatus = OptionStatus.RIGHT;// 用户选择正确
                } else {
                    questionOptionBean.optionStatus = OptionStatus.ERROR;// 用户选择错误
                }
            }
            if (position == currentRecord.rightOption) {
                questionOptionBean.optionStatus = OptionStatus.RIGHT;// 给用户呈现正确答案
            }

        }
        switch (questionOptionBean.optionStatus) {
            case ERROR:
                holder.tvOption.setText("");
                holder.tvOption.setBackgroundResource(R.mipmap.error);
                holder.tvDescription.setTextColor(MyApplication.mApplicationContext.getColor(R.color.error_text_color));
                break;
            case RIGHT:
                holder.tvOption.setText("");
                holder.tvOption.setBackgroundResource(R.mipmap.right);
                holder.tvDescription.setTextColor(MyApplication.mApplicationContext.getColor(R.color.right_text_color));
                break;
            case NORMAL:
                holder.tvOption.setText(questionOptionBean.optionA);
                break;
        }
        // 设置选项描述
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(position);
        holder.tvDescription.setText(questionOptionBean.description);

    }

    @Override
    public int getItemCount() {
        return mOptionsList.size();
    }

    @Override
    public void onClick(View v) {
        if (isDone) {
            ToastUtils.showShort("当前题目已做，请做下道题");
            return;
        }
        int position = (int) v.getTag();
        if (mQuestionType == 0) {//单选
            for (int i = 0; i < mOptionsList.size(); i++) {
                QuestionOptionBean questionOptionBean = mOptionsList.get(i);
                System.out.println("lgj : i :" + i + " questionOptionBean :" +questionOptionBean);
                if (i == position) {//找到对应点击的选项
                    mRrecordBean = new RecordDoneQuestionBean();
                    mRrecordBean.chooseOption = i;// 记录用户选择的哪项，A，B,C,D
                    if (questionOptionBean.answer.contains(",")) {// 放置正确选项中有 "," 号
                        questionOptionBean.answer = questionOptionBean.answer.replace(",","");
                    }
                    int rightAnswerOption = getRight(questionOptionBean.answer);
                    mRrecordBean.rightOption = rightAnswerOption;//记录该题目的正确答案，记录的是索引
                    if (questionOptionBean.answer.equalsIgnoreCase(questionOptionBean.optionA)) {
                        questionOptionBean.isRight = 1;// 1表示正确
                        questionOptionBean.optionStatus = OptionStatus.RIGHT;// 答题正确
                        mRrecordBean.isRight = true;// 记录答题正确
                        RecordUserDoneQuestion.getInstance().recordOptionStatus(mCurrentPosition, OptionStatus.RIGHT);
                        // 统计答题正确的数量
                        RecordUserDoneQuestion.getInstance().RIGHT_COUNT++;
                        // 更新正确的答题正确的数量
                        mUpdateRightAndErrorCount.updateRightCount();
                    } else {
                        questionOptionBean.isRight = 0;// 0表示错误
                        questionOptionBean.optionStatus = OptionStatus.ERROR;// 答题错误
                        mRrecordBean.isRight = false;// 记录答题错误
                        // 记录该题
                        RecordUserDoneQuestion.getInstance().recordOptionStatus(mCurrentPosition, OptionStatus.ERROR);
                        // 统计答题错误的数量
                        RecordUserDoneQuestion.getInstance().ERROR_COUNT++;
                        // 更新答题错误的数量
                        mUpdateRightAndErrorCount.updateErrorCount();
                    }
                    questionOptionBean.chooseThisQuestion = true; //表示该题已经作答
                    RecordUserDoneQuestion.getInstance().record(mCurrentPosition, mRrecordBean);// 记录答题

                } else {
                    if (questionOptionBean.answer.equalsIgnoreCase(questionOptionBean.optionA)) {
                        questionOptionBean.optionStatus = OptionStatus.RIGHT;// 找出正确的答案
                    } else {
                        questionOptionBean.optionStatus = OptionStatus.NORMAL;// 正常显示
                    }
                    questionOptionBean.chooseThisQuestion = false;
                }
            }
            isDone = true;
        }
        notifyDataSetChanged();
        updateAnalysis();
    }

    private void updateAnalysis() {
        RecordDoneQuestionBean currentRecord = RecordUserDoneQuestion.getInstance().getCurrentRecord(mCurrentPosition);
        if (currentRecord == null) {
            mUpdateRightAndErrorCount.showAnalysis(true, "");
        }else {
            mUpdateRightAndErrorCount.showAnalysis(true, OtherUtils.indexTransformString(currentRecord.chooseOption));
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvOption;
        TextView tvDescription;
        RelativeLayout rlOption;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            rlOption = (RelativeLayout) itemView;
            tvOption = itemView.findViewById(R.id.tvOptionA);
            tvDescription = itemView.findViewById(R.id.tvDescription);

        }
    }

    // 获取正确答案的索引
    private int getRight(String answer) {
        switch (answer) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            case "E":
                return 4;
        }
        return -1;
    }
}

package com.lgj.answersystem.fragment;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lgj.answersystem.MainActivity;
import com.lgj.answersystem.MyApplication;
import com.lgj.answersystem.R;
import com.lgj.answersystem.bean.AllSubjectBean;
import com.lgj.answersystem.network.RetrofitUtil;
import com.lgj.answersystem.util.OtherUtils;
import com.lgj.answersystem.widget.ActivationSubjectsDialog;
import com.lgj.answersystem.widget.MyFlowLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionBankFragment extends BaseFragment {
    List<AllSubjectBean.DataBean> mAllSubjectData;
    MainActivity mMainActivity;
    LinearLayout mLeftLinearLayout;
    LinearLayout mRightLinearLayout;
    @Override
    public View initView() {
        View view = View.inflate(activity, R.layout.fragment_question_bank, null);
        mLeftLinearLayout = view.findViewById(R.id.leftLinearLayout);
        mRightLinearLayout = view.findViewById(R.id.rightLinearLayout);
        return view;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void getNetWorkData() {
        if (mAllSubjectData != null) {
            // 说明已经请求过一次了，不必再次请求
            System.out.println("QuestionBankFragment is not null");
            return;
        }
        Activity activity = getActivity();
        if (activity instanceof MainActivity) {
            mMainActivity = (MainActivity)activity;
            mMainActivity.showLoading("加载中...");
        }

        RetrofitUtil.getApiService().AllListSubjectList().enqueue(new Callback<AllSubjectBean>() {
            @Override
            public void onResponse(Call<AllSubjectBean> call, Response<AllSubjectBean> response) {
                if (response != null) {
                    if (response.body().getCode() == 1){
                        mAllSubjectData = response.body().getData();
                        setData(mAllSubjectData);
                    }
                } else {

                }
                if (mMainActivity != null) {
                    mMainActivity.dismissLoading();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (mMainActivity != null) {
                    mMainActivity.dismissLoading();
                }
            }
        });
    }

    private ArrayList<TextView> mSubjectTextList = new ArrayList<>();

    private void setData(List<AllSubjectBean.DataBean> mAllSubjectData) {
        if (mAllSubjectData == null) {
            return;
        }
        // 清空所有的text
        mSubjectTextList.clear();
        for (int i = 0; i < mAllSubjectData.size(); i++) {
            MyFlowLayout myFlowLayout = new MyFlowLayout(getContext());
            myFlowLayout.setPadding(0, OtherUtils.dip2px(6),0,0);
            AllSubjectBean.DataBean dataBean = mAllSubjectData.get(i);
            String professionName = dataBean.getProfessionName();
            List<AllSubjectBean.DataBean.SubjectListBean> subjectList = dataBean.getSubjectList();
            for (int j = 0; j < subjectList.size(); j++) {
                TextView textView = new TextView(getContext());
                textView.setText(subjectList.get(j).getSubjectName());
                textView.setTextSize(14);
                textView.setTextColor(OtherUtils.getColor(R.color.text_press));
                textView.setGravity(Gravity.CENTER);
                textView.setPadding(OtherUtils.dip2px(10),OtherUtils.dip2px(6),OtherUtils.dip2px(10),OtherUtils.dip2px(6));
                textView.setBackgroundResource(R.drawable.shape_subject_text_bg);
                textView.setTag(subjectList.get(j));
                mSubjectTextList.add(textView);
                // 科目点击事件的监听
                textView.setOnClickListener(mSubjectClickListener);
                myFlowLayout.addView(textView);
            }
            mRightLinearLayout.addView(myFlowLayout);
            View rightLineView = new View(getContext());
            rightLineView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,1));
            rightLineView.setBackgroundColor(R.color.gb_gray);
            mRightLinearLayout.addView(rightLineView);
//            上面添加右侧的横线
            int width = OtherUtils.getWindowWidth();
            width = (width) / 4;
            // 计算myFlowLayout的高度
            int measureSpec = View.MeasureSpec.makeMeasureSpec(width * 3, View.MeasureSpec.EXACTLY);
            myFlowLayout.measure(measureSpec, View.MeasureSpec.UNSPECIFIED);
            int measuredHeight = myFlowLayout.getMeasuredHeight() + 1;
            System.out.println("MainActivity measuredHeight :" + measuredHeight);
            RelativeLayout rl = new RelativeLayout(getContext());
            RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, measuredHeight);
            rl.setLayoutParams(relativeLayoutParams);


            TextView textView = new TextView(getContext());
            textView.setEnabled(false);
            textView.setText(professionName);
            textView.setTextSize(14);
            textView.setTextColor(OtherUtils.getColor(R.color.text_press));
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundResource(R.drawable.shape_subject_text_bg);
            textView.setPadding(OtherUtils.dip2px(10), OtherUtils.dip2px(6), OtherUtils.dip2px(10), OtherUtils.dip2px(6));
            RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            textLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            textView.setLayoutParams(textLayoutParams);


            View leftLineView = new View(getContext());
            RelativeLayout.LayoutParams leftLineViewLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
            leftLineViewLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            leftLineView.setLayoutParams(leftLineViewLayoutParams);
            // setBackgroundColor(R.color.gb_gray) 这种设置的颜色比较深一点，OtherUtils.getColor()
            leftLineView.setBackgroundColor(R.color.gb_gray);
            rl.addView(leftLineView);
            rl.addView(textView);
            mLeftLinearLayout.addView(rl);
        }

    }

    private View.OnClickListener mSubjectClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //
            for (int i = 0; i < mSubjectTextList.size(); i++) {
                TextView textView = mSubjectTextList.get(i);
                textView.setTextColor(OtherUtils.getColor(R.color.text_press));
                textView.setBackgroundResource(R.drawable.shape_subject_text_bg);
            }

            TextView textView = (TextView) view;
            textView.setBackgroundResource(R.drawable.shape_subject_text_select);
            textView.setTextColor(OtherUtils.getColor(R.color.white));
            Object tag = view.getTag();
            if (tag instanceof AllSubjectBean.DataBean.SubjectListBean) {
                AllSubjectBean.DataBean.SubjectListBean subjectBean = (AllSubjectBean.DataBean.SubjectListBean) tag;
                ActivationSubjectsDialog subjectsDialog = new ActivationSubjectsDialog(getContext(), subjectBean.getSubjectName(), subjectBean.getId());
                subjectsDialog.show();
            }

        }
    };
}

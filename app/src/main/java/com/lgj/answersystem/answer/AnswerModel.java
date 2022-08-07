package com.lgj.answersystem.answer;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.lgj.answersystem.MyApplication;
import com.lgj.answersystem.bean.QuestionBean;
import com.lgj.answersystem.network.RetrofitUtil;
import com.lgj.answersystem.util.NetworkUtils;
import com.lgj.answersystem.util.OtherUtils;
import com.lgj.answersystem.util.PrefUtils;
import com.lgj.answersystem.util.ToastUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
// 获取数据层，model层
public class AnswerModel {
    public static String TAG = "lgj AnswerModel";
    private int mSubjectId;
    private int mSmallId;
    public IQuestionBankCompleteListener mQuestionBankCompleteListener;

    public AnswerModel(IQuestionBankCompleteListener questionBankCompleteListener, int subjectId, int smallId) {
        this.mQuestionBankCompleteListener = questionBankCompleteListener;
        this.mSubjectId = subjectId;
        this.mSmallId = smallId;
        System.out.println("lgj AnswerModel 3333333333");
    }

    // 从服务器获取数据
    public void getServiceData() {
        boolean network_available = NetworkUtils.isNetworkAvailable();
        System.out.println("lgj AnswerModel 5555555555");
        if (mQuestionBankCompleteListener == null) {
            return;
        }
        if (network_available) {
            String json = PrefUtils.getString(MyApplication.mApplicationContext, mSubjectId + "_question_key", "");
            System.out.println("lgj : json :" + json);
            if (!TextUtils.isEmpty(json)) {
                QuestionBean questionBean = new Gson().fromJson(json, QuestionBean.class);
                parseData(questionBean);
            }else {
                getTitleListData();
            }
//             getTitleListData();
        } else {

        }
    }

    private void getTitleListData() {
//            deviceId    fb69f4d7113a4bac
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("lgj mSubjectId : " + mSubjectId + " OtherUtils.getDeviceId() " + OtherUtils.getDeviceId());
            mQuestionBankCompleteListener.showLoadingDialog();
            RetrofitUtil.getApiService().getQuestionList(mSubjectId, OtherUtils.getDeviceId())
                    .enqueue(new Callback<QuestionBean>() {
                        @Override
                        public void onResponse(Call<QuestionBean> call, Response<QuestionBean> response) {
                            mQuestionBankCompleteListener.dismissLoadingDialog();
                            System.out.println("lgj 访问网络时间 end Time : " + (System.currentTimeMillis() - startTime));
                            if (response.body() != null && response.body().getCode() == 1) {
                                Gson gson = new Gson();
                                String jsonStr = gson.toJson(response.body());
                                System.out.println("response jsonStr :" + jsonStr);
                                long startTime2 = System.currentTimeMillis();
                                PrefUtils.putString(MyApplication.mApplicationContext, mSubjectId + "_question_key", jsonStr);
                                System.out.println("end 写入sp里面的时间 Time : " + (System.currentTimeMillis() - startTime2));
                                parseData(response.body());
                            } else {
                                ToastUtils.showShort(response.body().getMessage());
                            }
                        }

                        @Override
                        public void onFailure(Call<QuestionBean> call, Throwable t) {
                            mQuestionBankCompleteListener.dismissLoadingDialog();
                            ToastUtils.showShort(TAG, "获取所有科目数的数据失败");
                        }
                    });
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    private void parseData(QuestionBean questionBean) {
        if (questionBean != null && questionBean.getData() != null && questionBean.getData().size() > 0) {
            List<QuestionBean.DataBean> data = questionBean.getData();
            mQuestionBankCompleteListener.onComplete(data);
        }
    }
}

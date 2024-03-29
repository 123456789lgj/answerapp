package com.lgj.answersystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lgj.answersystem.bean.ActivationBean;
import com.lgj.answersystem.network.RetrofitUtil;
import com.lgj.answersystem.util.ActivityUtils;
import com.lgj.answersystem.util.OtherUtils;
import com.lgj.answersystem.util.RequestUtils;
import com.lgj.answersystem.util.ToastUtils;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivationActivity extends BaseActivity implements View.OnClickListener {
    TextView mSubjectText;
    TextView mBtnActivate;
    EditText mEtActivateCode;
    private String mSubjectName;
    private int mSubjectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activation);
        findTitle("科目激活");
        mSubjectText = findViewById(R.id.tv_subject_text);
        mBtnActivate = findViewById(R.id.tv_activate);
        mEtActivateCode = findViewById(R.id.et_activate_code);
        mSubjectText = findViewById(R.id.tv_subject_text);
        if (mIntent != null) {
            mSubjectName = mIntent.getStringExtra("subjectName");
            mSubjectId = mIntent.getIntExtra("subjectId", -1);
            System.out.println("lgj mSubjectId :" + mSubjectId);
            mSubjectText.setText(Html.fromHtml("你将要激活的科目是：<font color='#FF0000'>" + "<big>" + mSubjectName + "</big></font> "));
        }
        mBtnActivate.setOnClickListener(this);
        mEtActivateCode.setText("x2c6zo91vand");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_activate:
                activiteCode();
                break;
        }

    }

    private void activiteCode() {
        String activateCode = mEtActivateCode.getText().toString().trim();
        if (TextUtils.isEmpty(activateCode)) {
            ToastUtils.showShort("请输入激活码");
            return;
        }
        activateCode = "ux768kqnv261";// 轮机盖伦
        activateCode = "x2c6zo91vand";//主推力装置
        HashMap<String, Object> param = new HashMap<>();
        param.put("subjectId", mSubjectId);
        param.put("activationCodeName", activateCode);
        param.put("deviceId", OtherUtils.getDeviceId());
        RetrofitUtil.getApiService().useActivationCode(RequestUtils.getRequestBody(param))
                .enqueue(new Callback<ActivationBean>() {
                    @Override
                    public void onResponse(Call<ActivationBean> call, Response<ActivationBean> response) {
                        if (response.body() != null && response.body().getCode() == 1) {
                            ToastUtils.showShort(getString(R.string.has_send_sus));
                            Intent intent = new Intent(ActivationActivity.this, QuestionBankCenter.class);
                            intent.putExtra("subjectId", mSubjectId);
                            intent.putExtra("subjectName", mSubjectName);
                            ActivationActivity.this.startActivity(intent);
                        } else {
                            if (response.body() == null) {
                                return;
                            }
                            String message = response.body().getMessage();
                            System.out.println("useActivationCode message :" + message);
                            ToastUtils.showShort((getString(R.string.send_sms_error)));
                        }
                    }

                    @Override
                    public void onFailure(Call<ActivationBean> call, Throwable t) {
                        ToastUtils.showShort((getString(R.string.send_sms_error)));
                    }
                });
    }
}
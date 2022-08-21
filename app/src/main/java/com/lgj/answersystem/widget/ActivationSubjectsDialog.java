package com.lgj.answersystem.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.lgj.answersystem.ActivationActivity;
import com.lgj.answersystem.ChapterActivity;
import com.lgj.answersystem.QuestionBankCenter;
import com.lgj.answersystem.R;
import com.lgj.answersystem.answer.AnswerActivity;


/**
 * Created by lxz on 2018/12/12.
 * 修改 mac
 */

public class ActivationSubjectsDialog implements View.OnClickListener {
    private final AlertDialog mDialog;
    private Context mContext;
    private String mSubjectName;
    private int mSubjectId;

    public ActivationSubjectsDialog(Context context, String subjectName, int subjectId) {
        this.mContext = context;
        this.mSubjectName = subjectName;
        this.mSubjectId = subjectId;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_subject_activation, null);
        view.findViewById(R.id.btnActivation).setOnClickListener(this);
        mDialog = new AlertDialog.Builder(context).setView(view).create();
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnActivation:
                Intent intent = new Intent(mContext, AnswerActivity.class);
//                Intent intent = new Intent(mContext, ActivationActivity.class);
                intent.putExtra("subjectId",mSubjectId);
                intent.putExtra("subjectName",mSubjectName);
                mContext.startActivity(intent);
                mDialog.dismiss();
                break;
        }
    }
    public ActivationSubjectsDialog show() {
        mDialog.show();
        return this;
    }
}

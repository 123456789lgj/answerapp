package com.lgj.answersystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class QuestionBankCenter extends BaseActivity implements View.OnClickListener {
    private String mSubjectName;
    private int mSubjectId;
    private LinearLayout mChapterTrain;
    private LinearLayout mChapterTest;
    private LinearLayout mReviewCollect;
    private LinearLayout mReviewError;
    private LinearLayout mOneKeyUpdate;
    private LinearLayout mSubjectUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_bank_center);
        Intent intent = getIntent();
        if (intent != null) {
            mSubjectId = intent.getIntExtra("subjectId", -1);
            mSubjectName = intent.getStringExtra("subjectName");
            findTitle(mSubjectName);
        }
        mChapterTrain = findViewById(R.id.ll_chapter_train);
        mChapterTest = findViewById(R.id.ll_chapter_test);
        mReviewCollect = findViewById(R.id.ll_review_collect);
        mReviewError = findViewById(R.id.ll_review_error);
        mOneKeyUpdate = findViewById(R.id.ll_one_key_update);
        mSubjectUpdate = findViewById(R.id.ll_subject_update);
        mChapterTrain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // 章节练习
            case R.id.ll_chapter_train:
                Intent intent = new Intent(this, ChapterActivity.class);
                intent.putExtra("subjectId",mSubjectId);
                intent.putExtra("subjectName",mSubjectName);
                startActivity(intent);
                break;
        }
    }
}
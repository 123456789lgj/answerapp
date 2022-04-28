package com.lgj.answersystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lgj.answersystem.util.DialogLoading;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void finish() {
        super.finish();
    }

    //    处理返回箭头和设置标题文字
    public void findTitle(String headTitle) {
        findViewById(R.id.backArrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(headTitle);
    }

    public void showLoading(String message) {
        DialogLoading.getInstance().showLoading(this, message);
    }

    public void dismissLoading() {
        DialogLoading.getInstance().dismissLoading();
    }
}

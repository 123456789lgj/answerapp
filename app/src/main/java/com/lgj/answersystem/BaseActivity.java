package com.lgj.answersystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lgj.answersystem.util.DialogLoading;

public class BaseActivity extends AppCompatActivity {

    protected Intent mIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIntent = getIntent();
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

    // 让标题下移
    public void setStatusBarDown() {
        RelativeLayout rvTitle = findViewById(R.id.rlTitle);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rvTitle.getLayoutParams();
        // 这个高度应该是布局文件写的高度
        System.out.println("lgj layoutParams.height :" + layoutParams.height);
        int statusBarHeight = getStatusBarHeight();
        layoutParams.height = statusBarHeight + layoutParams.height;
        rvTitle.setPadding(0,statusBarHeight,0,0);
        // 控制 文字垂直居中显示，这块虽然在布局文件里面已经指定了垂直居中，可加载出来之后还是不行
        rvTitle.setVerticalGravity(Gravity.CENTER_VERTICAL);
        rvTitle.setLayoutParams(layoutParams);
        System.out.println("lgj layoutParams.height 最终:" + layoutParams.height);
    }
    //获取状态栏高度
//     <dimen name="navigation_bar_height">48dp</dimen>
//    <dimen name="status_bar_height">24dp</dimen>
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier(
                "status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}

package com.lgj.myflowdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RelativeLayoutActivity extends AppCompatActivity {
    LinearLayout mLeftLinearLayout;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_layout);
        mLeftLinearLayout = findViewById(R.id.leftLinearLayout);
        RelativeLayout rl = new RelativeLayout(this);
        LinearLayout.LayoutParams relativeLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500);
        rl.setLayoutParams(relativeLayoutParams);


        TextView textView = new TextView(this);
        textView.setText("学习");
        textView.setTextSize(14);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundResource(R.drawable.shap_dialog_content_bg);
        textView.setPadding(dip2px(10), dip2px(5), dip2px(10), dip2px(5));
        RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        textLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        textView.setLayoutParams(textLayoutParams);

        View view2 = new View(this);
        RelativeLayout.LayoutParams viewLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        viewLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        view2.setLayoutParams(viewLayoutParams);
        view2.setBackgroundColor(R.color.gb_gray);
        rl.addView(view2);
        rl.addView(textView);
        mLeftLinearLayout.addView(rl);

    }

    public int dip2px(int dip) {
        //屏幕密度
        float density = getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }
}
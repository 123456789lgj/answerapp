package com.lgj.myflowdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    LinearLayout mLeftLinearLayout;
    LinearLayout mRightLinearLayout;
    LinearLayout mLl;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLeftLinearLayout = findViewById(R.id.leftLinearLayout);
        mRightLinearLayout = findViewById(R.id.rightLinearLayout);
        mLl = findViewById(R.id.ll);
        initData();

        for (Map.Entry<String, List<String>> entry : mHashMap.entrySet()) {
            MyFlowLayout myFlowLayout = new MyFlowLayout(this);
            myFlowLayout.setPadding(0,dip2px(6),0,0);
            List<String> list = entry.getValue();
            for (int i = 0; i < list.size(); i++) {
                TextView textView = new TextView(this);
                textView.setText(list.get(i));
                textView.setTextSize(14);
                textView.setGravity(Gravity.CENTER);
                textView.setPadding(dip2px(10),dip2px(6),dip2px(10),dip2px(6));
                textView.setBackgroundResource(R.drawable.shap_dialog_content_bg);
                myFlowLayout.addView(textView);
            }
            mRightLinearLayout.addView(myFlowLayout);
            View view = new View(this);
            view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,1));
            view.setBackgroundColor(R.color.gb_gray);
            mRightLinearLayout.addView(view);
//            上面添加右侧的横线
//================================================================================================================
            WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();
            width = (width) / 4;
            int measureSpec = View.MeasureSpec.makeMeasureSpec(width * 3, View.MeasureSpec.EXACTLY);
            myFlowLayout.measure(measureSpec, View.MeasureSpec.UNSPECIFIED);
            int measuredHeight = myFlowLayout.getMeasuredHeight() + 1;
            System.out.println("MainActivity measuredHeight :" + measuredHeight);
            RelativeLayout rl = new RelativeLayout(this);
//            LinearLayout.LayoutParams relativeLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, measuredHeight);
            RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, measuredHeight);
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

//            WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//            int width = wm.getDefaultDisplay().getWidth();
//            width = (width) / 4;
//            int measureSpec = View.MeasureSpec.makeMeasureSpec(width * 3, View.MeasureSpec.AT_MOST);
//            myFlowLayout.measure(measureSpec, View.MeasureSpec.UNSPECIFIED);
//
//            System.out.println("measuredHeight :" + measuredHeight);
//            TextView textView = new TextView(this);
//            textView.setText("学习");
//            textView.setTextSize(14);
//            textView.setGravity(Gravity.CENTER);
//            textView.setBackgroundResource(R.drawable.shap_dialog_content_bg);
//            textView.setPadding(dip2px(10),dip2px(5),dip2px(10),dip2px(5));
//            textView.measure(0,0);
//            int textMeasureSpecHeight = textView.getMeasuredHeight();
//            System.out.println("measuredHeight textMeasureSpecHeight:" + textMeasureSpecHeight);
//
//
//            LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,measuredHeight);
//            LinearLayout shipei = new LinearLayout(this);
//            shipei.setOrientation(LinearLayout.VERTICAL);
//            shipei.setLayoutParams(layoutParams);
//            int chazhi =  measuredHeight - textMeasureSpecHeight - 1;
//            System.out.println("measuredHeight myFlowLayout.getPaddingTop():" + myFlowLayout.getPaddingTop());
//            System.out.println("measuredHeight chazhi:" + chazhi);
//
//            layoutParams.topMargin = chazhi / 2;
//            layoutParams.bottomMargin = chazhi / 2;
//            textView.setLayoutParams(textLayoutParams);
//            shipei.addView(textView);
//            mLeftLinearLayout.addView(shipei);
//
//            View view2 = new View(this);
//            view2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,1));
//            view2.setBackgroundColor(R.color.gb_gray);
//            shipei.addView(view2);

        }

//        MyFlowLayout myFlowLayout = findViewById(R.id.myFlowLayout);
//        MyFlowLayout myFlowLayout2 = findViewById(R.id.myFlowLayout2);
//        for (int i = 0; i < 5; i++) {
//            TextView textView = new TextView(this);
//            textView.setText("好好学习");
//            textView.setTextSize(14);
//            textView.setGravity(Gravity.CENTER);
//            textView.setPadding(10, 20, 10, 20);
//            textView.setBackgroundResource(R.drawable.shap_dialog_content_bg);
//            myFlowLayout.addView(textView);
//        }
//        for (int i = 0; i < 5; i++) {
//            TextView textView = new TextView(this);
//            textView.setText("天天向上");
//            textView.setTextSize(14);
//            textView.setGravity(Gravity.CENTER);
//            textView.setPadding(10, 20, 10, 20);
//            textView.setBackgroundResource(R.drawable.shap_dialog_content_bg);
//            myFlowLayout2.addView(textView);
//        }

    }

    HashMap<String, List<String>> mHashMap = new HashMap<>();

    private void initData() {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("主推力装置");
        list1.add("轮机盖伦");
        mHashMap.put("轮机技术", list1);
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("主推力装置2");
        list2.add("轮机盖伦2");
        mHashMap.put("轮机技术2fsdf", list2);
        ArrayList<String> list3 = new ArrayList<>();
        list3.add("主推力装置3f");
        list3.add("轮机盖伦3");
        mHashMap.put("轮机技术3fad", list3);
    }
    public int dip2px(int dip) {
        //屏幕密度
        float density = getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }

}

package com.lgj.answersystem.answer;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.SlidingDrawer;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import com.lgj.answersystem.BaseActivity;
import com.lgj.answersystem.R;
import com.lgj.answersystem.bean.QuestionBean;

import java.util.List;

public class AnswerActivity extends BaseActivity {
    public static String TAG = "lgj AnswerActivity";
    private SlidingDrawer mSlidingDrawer;
    private View mBackground;
    private ViewPager2 mViewPager;
    // private String mSubjectName;
    private int mSubjectId;
    private int mSmallId;
    private AnswerPagerAdapter mAnswerPagerAdapter;
    private SlidingDrawerController mSlidingController;

    //    https://blog.csdn.net/u014418171/article/details/81223681  沉浸式状态栏的讲解
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 让整个Activity的界面，可以显示在状态栏的下面,
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_answer);
        if (mIntent != null) {
            mSubjectId = mIntent.getIntExtra("subjectId", -1);
            mSmallId = mIntent.getIntExtra("smallId", -1);
        }
//        mSubjectId = 15;// 打桩数据
        System.out.println(TAG + " mSubjectId :" + mSubjectId);
        System.out.println(TAG + " mSmallId :" + mSmallId);
        setStatusBarDown();
        mSlidingDrawer = findViewById(R.id.slidingDrawer);
        mViewPager = findViewById(R.id.viewPager2);
        findTitle("答题界面");
        mBackground = findViewById(R.id.background);
        mBackground.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7F000000")));
        mSlidingController = new SlidingDrawerController(this, mSlidingDrawer);
        mSlidingController.setHeight();
        mAnswerPagerAdapter = new AnswerPagerAdapter(this, mSubjectId, mSmallId);
        mAnswerPagerAdapter.initData();
        mViewPager.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {

            }
        });
//        mViewPager.setOffscreenPageLimit(1);

        System.out.println("lgj :AnswerActivity  onCreate end");

    }

    public void updateRightCount() {
        mSlidingController.updateRightCount();
    }

    public void updateErrorCount() {
        mSlidingController.updateErrorCount();
    }


    // 控制背景是否显示
    public void isSetTransparentBackground(int isTransparent) {
        mBackground.setVisibility(isTransparent);
        int currentItem = mViewPager.getCurrentItem();
        OptionStatus cardStatus = RecordUserDoneQuestion.getInstance().getCardStatus(currentItem);
        System.out.println("lgj isSetTransparentBackground cardStatus :"
                + cardStatus + " currentItem :" + currentItem + " isTransparent:" + isTransparent);
        if (isTransparent == View.VISIBLE) {// Visible 是0
            // 判断当前题目有没有答
            if (cardStatus == null) {
                RecordUserDoneQuestion.getInstance().recordOptionStatus(currentItem, OptionStatus.CURRENT);
            }
        } else {
            if (cardStatus == OptionStatus.CURRENT) {
                RecordUserDoneQuestion.getInstance().recordOptionStatus(currentItem, null);
            }
        }
    }

    // 等待数据加载完成，填充数据
    public void setAdapter(List<QuestionBean.DataBean> data) {
        mViewPager.setAdapter(mAnswerPagerAdapter);
        System.out.println("lgj :AnswerActivity  setAdapter end");
        // 加载完成之后，就知道 抽屉里面有多少内容
        mSlidingController.setAdapter(data.size());
    }

    @Override
    protected void onDestroy() {
        // 清空用户已经做过的数据
        RecordUserDoneQuestion.getInstance().clearRecord();
        System.out.println("lgj AnswerActivity onDestroy...");
        super.onDestroy();
    }

    public void setTargetPosition(int position) {
        // true 表示要有过渡动画
        mViewPager.setCurrentItem(position, true);
    }

    public void clearCurrentStatus() {
        int currentItem = mViewPager.getCurrentItem();
        System.out.println();
        OptionStatus cardStatus = RecordUserDoneQuestion.getInstance().getCardStatus(currentItem);
        switch (cardStatus) {
            case NORMAL:
            case CURRENT:
                RecordUserDoneQuestion.getInstance().recordOptionStatus(currentItem, null);
                break;
        }
    }
}
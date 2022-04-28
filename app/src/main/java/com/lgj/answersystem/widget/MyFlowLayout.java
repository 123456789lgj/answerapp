package com.lgj.answersystem.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.lgj.answersystem.util.OtherUtils;

import java.util.ArrayList;


/**
 *
 * 1、确定哪些元素属于第一行，哪些元素属于第二行。。将所有的行对象存储在lineList里面去
 * 2、根据lineList里面的内容来计算出MyFlowLayout的高度
 * 3、将行对象里面的view对象，进行位置确定，让他们从左到右一字排开
 * 4、细节的修改
 *      onMeasure会被执行多次
 *      别忘了最后一行
 *      每一行的右边需要对齐,需要进行行里面每一个View对象的重新测量
 *      考虑padding的情况
 *      当一行中元素高度不一致的情况之下，这一行的高度应该以最高的元素为主,其他的元素居中显示
 */
public class MyFlowLayout extends ViewGroup {
    public MyFlowLayout(Context context){
        this(context,null);
    }

    public MyFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int r, int b) {
        int top2 = getPaddingTop() ;
        int left2 = getPaddingBottom();
        System.out.println("top :" + top + " 地址值 ：" + this.toString() + " left :" + left);
        System.out.println("top :2  : " + top2 + " 地址值 ：" + this.toString() + " left :2  :" + left2);
        for (int i = 0; i < lineList.size() ; i++) {
            LineView lineView = lineList.get(i);
            lineView.reMeasure();
            lineView.layout(left2,top2);
            top2 = top2 + lineView.lineHeight + mVerticalSpacing;
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        reset();
        // 获取父控件的宽度
        int withSize = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingBottom();
        int childCount = getChildCount();
        mCurrentLineView = new LineView();
        for (int i = 0; i < childCount; i++) {
            // 获取孩子
            View childView = getChildAt(i);
            // 限制孩子的宽度最多不能超过父控件的宽度
            int widthMeasure = MeasureSpec.makeMeasureSpec(withSize, MeasureSpec.AT_MOST);
            // 手动测量孩子的大小，高度不做任何限制
            childView.measure(widthMeasure,0);
            int measuredWidth = childView.getMeasuredWidth();
            mUsed = mUsed + measuredWidth;
            if (mUsed < withSize) {
                // 表示剩余空间足够
                mUsed = mUsed + mHorizontalSpacing;
                mCurrentLineView.addLineView(childView);
            }else {
                // 剩余空间不够需要换行
                newLine();
                // 新的一行
                mCurrentLineView.addLineView(childView);
                mUsed = mUsed + measuredWidth + mHorizontalSpacing;
            }
        }
        // 加上最后一行
        if (!lineList.contains(mCurrentLineView)) {
            lineList.add(mCurrentLineView);
        }
        int totalHeight = 0;
        for (int i = 0; i < lineList.size(); i++) {
            LineView lineView = lineList.get(i);
            totalHeight = totalHeight + lineView.lineHeight + mVerticalSpacing;
        }
        // 多加了最后一个高度
        totalHeight = totalHeight - mVerticalSpacing;
        // 最后的高度，需要加上padding的高度
        totalHeight = totalHeight + getPaddingBottom() + getPaddingTop();
        int minHeight = OtherUtils.getWindowHeight() / 4;
        if (totalHeight < minHeight) {
            totalHeight = minHeight;
        }
        System.out.println("measuredHeight minHeight:"+minHeight);
        //重新生成MeasureSpec
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(totalHeight, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void reset() {
        mCurrentLineView = new LineView();
        lineList.clear();
        mUsed = 0;
    }

    private void newLine() {
        lineList.add(mCurrentLineView);
        mCurrentLineView = new LineView();
        mUsed = 0;
    }
    // 存放每行的View
    private LineView mCurrentLineView;
    // 存放多少行
    private ArrayList<LineView> lineList = new ArrayList<>();
    private int mUsed;
    private int mHorizontalSpacing = OtherUtils.dip2px(5);
    private int mVerticalSpacing = OtherUtils.dip2px(5);

    class LineView {
        public int lineHeight;
        private ArrayList<View> lineViews = new ArrayList<>();
        public void addLineView(View childView) {
            if (!lineViews.contains(childView)) {
                lineViews.add(childView);
                int measuredHeight = childView.getMeasuredHeight();
                if (lineHeight < measuredHeight){
                    lineHeight = measuredHeight;
                }
            }
        }
        // 重新测量高度
        public void reMeasure() {
            int lineWidth = 0;
            for (int i = 0; i < lineViews.size(); i++) {
                View view = lineViews.get(i);
                lineWidth = lineWidth + view.getMeasuredWidth() + mHorizontalSpacing;
            }
            lineWidth = lineWidth - mHorizontalSpacing;// 每一行的宽度
            //获取view剩余的宽度
            int surplusSpacing = getMeasuredWidth() - getPaddingRight() - getPaddingLeft() - lineWidth;
            if (surplusSpacing > 0) {
                int splitSpacing = 0;
                if (lineViews.size() != 0){
                    splitSpacing = surplusSpacing/ lineViews.size();
                }
                for (int i = 0; i < lineViews.size(); i++) {
                    View childView = lineViews.get(i);
                    int childWidth = childView.getMeasuredWidth();
                    int childHeight = childView.getMeasuredHeight();
                    childWidth = childWidth + splitSpacing;
                    int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
                    int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
                    childView.measure(childWidthMeasureSpec,childHeightMeasureSpec);
                }
            }

        }

        public void layout(int left, int top) {
            for (int i = 0; i < lineViews.size(); i++) {
                View childView = lineViews.get(i);
                int measuredWidth = childView.getMeasuredWidth();
                int measuredHeight = childView.getMeasuredHeight();
                childView.layout(left,top,left + measuredWidth,top + measuredHeight);
//                int topOffset = lineHeight/2 - childView.getMeasuredHeight()/2;
//                childView.layout(left,top+topOffset,left + childView.getMeasuredWidth(),top+topOffset+childView.getMeasuredHeight());
                left = left +  childView.getMeasuredWidth() + mHorizontalSpacing;
                System.out.println("left  :" + left + " top :");
            }
        }
    }
}

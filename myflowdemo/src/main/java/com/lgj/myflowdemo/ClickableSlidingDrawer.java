package com.lgj.myflowdemo;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SlidingDrawer;

public class ClickableSlidingDrawer extends SlidingDrawer {
    private ViewGroup mHandleLayout;
    private final Rect mHitRect = new Rect();

    public ClickableSlidingDrawer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ClickableSlidingDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        View handle = getHandle();

        if (handle instanceof ViewGroup) {
            mHandleLayout = (ViewGroup) handle;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (mHandleLayout != null) {
            int childCount = mHandleLayout.getChildCount();
            System.out.println("lgj onInterceptTouchEvent event.getX() :" + event.getX() + " count :" + childCount);
            System.out.println("lgj onInterceptTouchEvent event.getY() :" + event.getY());
            System.out.println("lgj onInterceptTouchEvent mHandleLayout.getX() :" + mHandleLayout.getX());
            System.out.println("lgj onInterceptTouchEvent mHandleLayout.getY() :" + mHandleLayout.getY());
            int handleClickX = (int) (event.getX() - mHandleLayout.getX());
            int handleClickY = (int) (event.getY() - mHandleLayout.getY());
            System.out.println("lgj onInterceptTouchEvent handleClickX :" + handleClickX + "  ,handleClickY :" + handleClickY);
            Rect hitRect = mHitRect;

            for (int i = 0; i < mTouchableIds.length; i++) {
                View childView = findViewById(mTouchableIds[i]);
                childView.getHitRect(hitRect);
                System.out.println("lgj onInterceptTouchEvent hitRect 左:" + hitRect.left + " 上 :"
                        + hitRect.top + " 右:" + hitRect.right + " 下:" + hitRect.bottom);
                if (hitRect.contains(handleClickX, handleClickY)) {
                    return false;
                }
            }
        }

        return super.onInterceptTouchEvent(event);
    }
    private int mHandleId = 0;                //抽屉行为控件ID
    private int[] mTouchableIds = null;    //Handle 部分其他控件ID


    public void setHandleId(int ctlHandle) {
        mHandleId = ctlHandle;
    }

    public void setTouchableIds(int[] ints) {
        mTouchableIds = ints;
    }
}
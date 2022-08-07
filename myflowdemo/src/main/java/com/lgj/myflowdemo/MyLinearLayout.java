package com.lgj.myflowdemo;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class MyLinearLayout extends LinearLayout {
    private ViewGroup mHandleLayout;

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        View handle = getChildAt(0);

        if (handle instanceof ViewGroup) {
            mHandleLayout = (ViewGroup) handle;
        }
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private final Rect mHitRect = new Rect();

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        int childCount = mHandleLayout.getChildCount();
        System.out.println("lgj : x :" + x + " , y :" + y);
        int x2 = (int) mHandleLayout.getX();
        int y2 = (int) mHandleLayout.getY();
        System.out.println("lgj : x2 :" + x2 + " , y2 :" + y2);
        int handleClickX = (int) (ev.getX() - mHandleLayout.getX());
        int handleClickY = (int) (ev.getY() - mHandleLayout.getY());
        System.out.println("lgj : handleClickX :" + handleClickX + " , handleClickY :" + handleClickY);
        Rect hitRect = mHitRect;
        for (int i = 0; i < childCount; i++) {
            View view = mHandleLayout.getChildAt(i);
            view.getHitRect(hitRect);
            System.out.println("lgj hitRect i :" + i + "左:" + hitRect.left + " 上 :"
                    + hitRect.top + " 右:" + hitRect.right + " 下:" + hitRect.bottom);
        }
        return super.onInterceptTouchEvent(ev);
    }
}

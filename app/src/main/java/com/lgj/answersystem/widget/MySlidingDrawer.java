package com.lgj.answersystem.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SlidingDrawer;


public class MySlidingDrawer extends SlidingDrawer {

    private ViewGroup mHandleLayout;
    private final Rect mHitRect = new Rect();
    private int mHandleId = 0;                //抽屉行为控件ID
    private int[] mTouchableIds = null;    //Handle 部分其他控件ID

    public void setHandleId(int ctlHandle) {
        mHandleId = ctlHandle;
    }

    public void setTouchableIds(int[] ints) {
        mTouchableIds = ints;
    }

    public MySlidingDrawer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MySlidingDrawer(Context context, AttributeSet attrs) {
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
            int handleClickX = (int) (event.getX() - mHandleLayout.getX());
            int handleClickY = (int) (event.getY() - mHandleLayout.getY());
            Rect hitRect = mHitRect;
            for (int i = 0; i < mTouchableIds.length; i++) {
                View childView = findViewById(mTouchableIds[i]);
                childView.getHitRect(hitRect);
                // 表示交给子view处理事件，不让SlidingDrawer处理
                if (hitRect.contains(handleClickX, handleClickY)) {
                    return false;
                }
            }
        }
        return super.onInterceptTouchEvent(event);
    }


}

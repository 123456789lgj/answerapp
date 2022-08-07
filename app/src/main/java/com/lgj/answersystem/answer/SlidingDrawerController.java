package com.lgj.answersystem.answer;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgj.answersystem.MyApplication;
import com.lgj.answersystem.R;
import com.lgj.answersystem.util.OtherUtils;
import com.lgj.answersystem.widget.MySlidingDrawer;

public class SlidingDrawerController implements SlidingDrawer.OnDrawerOpenListener, SlidingDrawer.OnDrawerCloseListener, View.OnClickListener {
    private AnswerActivity mAnswerActivity;
    private MySlidingDrawer mSlidingDrawer;
    private final RecyclerView mAnswerCardRecycler;
    private AnswerCardAdapter mAnswerCardAdapter;
    private TextView mTvRightCount;
    private TextView mTvErrorCount;
    private final RelativeLayout mAnswerCard;
    private final RelativeLayout mSetting;
    private final RelativeLayout mCollect;

    public SlidingDrawerController(AnswerActivity answerActivity, SlidingDrawer slidingDrawer) {
        this.mAnswerActivity = answerActivity;
        this.mSlidingDrawer = (MySlidingDrawer) slidingDrawer;
        mAnswerCardRecycler = mSlidingDrawer.findViewById(R.id.answerCardRecycler);
        mAnswerCard = mSlidingDrawer.findViewById(R.id.rlAnswerCard);
        mSetting = mSlidingDrawer.findViewById(R.id.rlSetting);
        mTvRightCount = mSlidingDrawer.findViewById(R.id.tvRightCount);
        mTvErrorCount = mSlidingDrawer.findViewById(R.id.tvErrorCount);
        mCollect = mSlidingDrawer.findViewById(R.id.rlCollect);
        mSlidingDrawer.setOnDrawerOpenListener(this);
        mSlidingDrawer.setOnDrawerCloseListener(this);
        mAnswerCard.setOnClickListener(this);
        mSetting.setOnClickListener(this);
        mCollect.setOnClickListener(this);
        mSlidingDrawer.findViewById(R.id.rlRight).setOnClickListener(this);
        mSlidingDrawer.findViewById(R.id.rlError).setOnClickListener(this);
        mSlidingDrawer.setHandleId(R.id.rlAnswerCard);
        // SlidingDrawer把手事件自行处理
        mSlidingDrawer.setTouchableIds(new int[]{R.id.rlSetting, R.id.rlCollect,R.id.rlRight,R.id.rlError});
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlSetting:
                System.out.println("lgj SlidingDrawerController rlSetting ...");
                break;
            case R.id.rlCollect:
                System.out.println("lgj SlidingDrawerController rlCollect ...");
                break;
        }
    }

    public void setHeight() {
        // 设置高度为屏幕高度的 3/5
        ViewGroup.LayoutParams params = mSlidingDrawer.getLayoutParams();
        params.height = OtherUtils.getWindowHeight() / 5 * 3;
        mSlidingDrawer.setLayoutParams(params);
    }

    @Override
    public void onDrawerOpened() {
        if (mSlidingDrawer.isOpened()) {
            mAnswerActivity.isSetTransparentBackground(View.VISIBLE);
            mAnswerCardAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDrawerClosed() {
        if (!mSlidingDrawer.isOpened()) {
            mAnswerActivity.isSetTransparentBackground(View.GONE);
        }
    }

    public void setAdapter(int totalSize) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mAnswerActivity, 5);
        mAnswerCardRecycler.setLayoutManager(gridLayoutManager);
        mAnswerCardAdapter = new AnswerCardAdapter(totalSize);
        mAnswerCardRecycler.setAdapter(mAnswerCardAdapter);

    }

    public void updateRightCount() {
        mTvRightCount.setText(RecordUserDoneQuestion.getInstance().RIGHT_COUNT + "");
    }

    public void updateErrorCount() {
        mTvErrorCount.setText(RecordUserDoneQuestion.getInstance().ERROR_COUNT + "");
    }


    class AnswerCardAdapter extends RecyclerView.Adapter<AnswerCardAdapter.AnswerCardHolder> implements View.OnClickListener {
        int totalSize;

        public AnswerCardAdapter(int totalSize) {
            this.totalSize = totalSize;
        }

        @NonNull
        @Override
        public AnswerCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView textView = new TextView(parent.getContext());
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(MyApplication.mApplicationContext.getColor(R.color.textC));
            textView.setBackgroundResource(R.drawable.answer_card_normal);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(OtherUtils.dip2px(50), OtherUtils.dip2px(50));
            params.topMargin = OtherUtils.dip2px(10);
            params.leftMargin = OtherUtils.dip2px(10);
            params.rightMargin = OtherUtils.dip2px(10);
            params.bottomMargin = OtherUtils.dip2px(10);
            textView.setLayoutParams(params);
            return new AnswerCardHolder(textView);
        }

        @Override
        public void onBindViewHolder(@NonNull AnswerCardHolder holder, int position) {
            OptionStatus cardStatus = RecordUserDoneQuestion.getInstance().getCardStatus(position);
            System.out.println("lgj position :" + position + " cardStatus :" + cardStatus);
            holder.textView.setTag(position);
            holder.textView.setOnClickListener(this);
            if (cardStatus == null) {
                holder.textView.setBackgroundResource(R.drawable.answer_card_normal);
                holder.textView.setText(position + 1 + "");
                holder.textView.setTextColor(OtherUtils.getColor(R.color.textC));
                return;
            }
            switch (cardStatus) {
                case NORMAL:
                    holder.textView.setBackgroundResource(R.drawable.answer_card_normal);
                    holder.textView.setTextColor(OtherUtils.getColor(R.color.textC));
                    break;
                case RIGHT:
                    holder.textView.setBackgroundResource(R.drawable.answer_card_right);
                    holder.textView.setTextColor(OtherUtils.getColor(R.color.right_text_color));
                    break;
                case ERROR:
                    holder.textView.setBackgroundResource(R.drawable.answer_card_error);
                    holder.textView.setTextColor(OtherUtils.getColor(R.color.error_text_color));
                    break;
                case CURRENT:
                    holder.textView.setBackgroundResource(R.drawable.answer_card_current);
                    holder.textView.setTextColor(OtherUtils.getColor(R.color.text_press));
                    break;
            }
            holder.textView.setText(position + 1 + "");
        }

        @Override
        public int getItemCount() {
            return totalSize;
        }

        @Override
        public void onClick(View v) {
            // 在题目切换之前，先把当前的current状态清除，避免切换之后，在弹起SlidingDrawer之后，出现好多当前指示
            mAnswerActivity.clearCurrentStatus();
            int position = (int) v.getTag();
            System.out.println("lgj : onClick... position :" + position);
            mAnswerActivity.setTargetPosition(position);
            // 使ViewPager移动到目标位置，关闭SlidingDrawer
            mSlidingDrawer.close();
        }

        public class AnswerCardHolder extends RecyclerView.ViewHolder {
            TextView textView;

            public AnswerCardHolder(@NonNull View itemView) {
                super(itemView);
                textView = (TextView) itemView;
            }
        }
    }
}

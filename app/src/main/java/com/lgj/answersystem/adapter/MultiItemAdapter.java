package com.lgj.answersystem.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.lgj.answersystem.AnswerActivity;
import com.lgj.answersystem.R;
import com.lgj.answersystem.bean.BigBean;
import com.lgj.answersystem.util.ActivityUtils;

import java.util.List;

public class MultiItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int ITEM_FIRST_LEVEL = 1;
    public static final int ITEM_SECOND_LEVEL = 2;
    private int mSubjectId;
    private Context mContext;

    public MultiItemAdapter(List<MultiItemEntity> data, int subjectId, Context context) {
        super(data);
        addItemType(ITEM_FIRST_LEVEL, R.layout.recycler_adapter_expand_one);
        addItemType(ITEM_SECOND_LEVEL, R.layout.recycler_adapter_expand_second);
        this.mSubjectId = subjectId;
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case ITEM_FIRST_LEVEL:
                BigBean bigBean = (BigBean) item;
                String bigCategoryName = bigBean.getBigCategoryName();
                helper.setText(R.id.tvOne, bigCategoryName);
                helper.itemView.setOnClickListener(v -> {
                    int pos = helper.getAdapterPosition();
                    if (bigBean.isExpanded()) {
                        collapse(pos);
                    } else {
                        expand(pos);
                    }
                });
                break;
            case ITEM_SECOND_LEVEL:
                BigBean.SmallBean smallBean = (BigBean.SmallBean) item;
                String smallCategoryName = smallBean.getSmallCategoryName();
                helper.setText(R.id.tvSecond, smallCategoryName);
                helper.itemView.setOnClickListener(v -> {
                    int pos = helper.getAdapterPosition();
                    Intent intent = new Intent(mContext, AnswerActivity.class);
                    intent.putExtra("subjectId", mSubjectId);
                    intent.putExtra("smallId", smallBean.getId());
                    System.out.println("lgj pos :" + pos + " smallId :" + smallBean.getId());
                    mContext.startActivity(intent);
                });
                break;
        }
    }
}

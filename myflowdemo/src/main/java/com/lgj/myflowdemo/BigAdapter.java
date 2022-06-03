package com.lgj.myflowdemo;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class BigAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int ITEM_FIRST_LEVEL = 1;
    public static final int ITEM_SECOND_LEVEL = 2;
    public BigAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(ITEM_FIRST_LEVEL,R.layout.recycler_adapter_expand_one);
        addItemType(ITEM_SECOND_LEVEL,R.layout.recycler_adapter_expand_second);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case ITEM_FIRST_LEVEL:
                BigBean bigBean = (BigBean)item;
                String bigCategoryName = bigBean.getBigCategoryName();
                helper.setText(R.id.tvOne,bigCategoryName);
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
                helper.setText(R.id.tvSecond,smallCategoryName);
                break;
        }
    }
}

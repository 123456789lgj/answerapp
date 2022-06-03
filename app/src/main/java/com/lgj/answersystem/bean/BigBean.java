package com.lgj.answersystem.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;

import static com.lgj.answersystem.adapter.MultiItemAdapter.ITEM_FIRST_LEVEL;
import static com.lgj.answersystem.adapter.MultiItemAdapter.ITEM_SECOND_LEVEL;


public class BigBean extends AbstractExpandableItem<BigBean.SmallBean> implements MultiItemEntity {
    private String bigCategoryName;
    private int id;
    private ArrayList mSmallList;

    public ArrayList getSmallList() {
        return mSmallList;
    }
    public String getBigCategoryName() {
        return bigCategoryName;
    }

    public void setBigCategoryName(String bigCategoryName) {
        this.bigCategoryName = bigCategoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addSmallBean(SmallBean smallBean) {
        if (mSmallList == null) {
            mSmallList = new ArrayList();
        }
        mSmallList.add(smallBean);
    }

    @Override
    public void addSubItem(BigBean.SmallBean subItem) {
        if (mSubItems == null) {
            mSubItems = new ArrayList<>();
        }
        mSubItems.add(subItem);
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return ITEM_FIRST_LEVEL;
    }

    public static class SmallBean  implements MultiItemEntity{
        private String smallCategoryName;
        private int id;

        public String getSmallCategoryName() {
            return smallCategoryName;
        }

        public void setSmallCategoryName(String smallCategoryName) {
            this.smallCategoryName = smallCategoryName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public int getItemType() {
            return ITEM_SECOND_LEVEL;
        }
    }
}

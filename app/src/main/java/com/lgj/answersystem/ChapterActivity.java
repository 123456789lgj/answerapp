package com.lgj.answersystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.Gson;
import com.lgj.answersystem.adapter.MultiItemAdapter;
import com.lgj.answersystem.bean.BigBean;
import com.lgj.answersystem.bean.ChapterBean;
import com.lgj.answersystem.network.RetrofitUtil;
import com.lgj.answersystem.util.ToastUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChapterActivity extends BaseActivity {
    private String mSubjectName;
    private int mSubjectId;
    private ArrayList<MultiItemEntity> mChapterList;
    private RecyclerView mMultiItemRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        mMultiItemRecyclerView = findViewById(R.id.multiItemRecyclerView);
        Intent intent = getIntent();
        if (intent != null) {
            mSubjectId = intent.getIntExtra("subjectId", -1);
            mSubjectName = intent.getStringExtra("subjectName");
        }
        mSubjectName = "主推力装置";
        mSubjectId = 15;
        findTitle(mSubjectName);
        System.out.println("lgj mSubjectName :" + mSubjectName + " intent :" + intent);
        getChatPterList();
    }


    private void getChatPterList() {
        try {
            showLoading("加载中...");
            RetrofitUtil.getApiService().allChapter(mSubjectId)
                    .enqueue(new Callback<ChapterBean>() {
                        @Override
                        public void onResponse(Call<ChapterBean> call, Response<ChapterBean> response) {
                            dismissLoading();
                            if (response.body() != null && response.body().getCode() == 1) {
                                parseData(response.body());
                            } else {
                                ToastUtils.showShort(response.body().getMessage());
                            }

                        }

                        @Override
                        public void onFailure(Call<ChapterBean> call, Throwable t) {
                            dismissLoading();
                            System.out.println("AllChapterBean : " + t.toString());
                            System.out.println("AllChapterBean 2: " + t.getMessage());
                            ToastUtils.showShort("获取专业列表及其下的科目列表失败");
                        }
                    });
        } catch (RuntimeException e) {
            e.printStackTrace();

        }
    }

    private void parseData(ChapterBean response) {
        ToastUtils.showShort("获取专业列表及旗下的科目列表成功");
        ChapterBean.DataBean allChapterBean = response.getData();
        List<ChapterBean.DataBean.ChapterListBean> dataChapterList = allChapterBean.getChapterList();
        System.out.println("lgj dataChapterList :" + dataChapterList);
//        List<?> realList = allChapterBean.getRealExamList();
//        List<?> testList = allChapterBean.getTestExamList();
        mChapterList = new ArrayList<>();
        if (dataChapterList != null && dataChapterList.size() > 0) {
            for (int i = 0; i < dataChapterList.size(); i++) {
                ChapterBean.DataBean.ChapterListBean chapterListBean = dataChapterList.get(i);
                String bigCategoryName = chapterListBean.getBigCategoryName();
                BigBean bigBean = new BigBean();
                bigBean.setBigCategoryName(bigCategoryName);
                List<ChapterBean.DataBean.ChapterListBean.SmallListBean> smallList = chapterListBean.getSmallList();
                for (int j = 0; j < smallList.size(); j++) {
                    ChapterBean.DataBean.ChapterListBean.SmallListBean smallListBean = smallList.get(i);
                    BigBean.SmallBean smallBean = new BigBean.SmallBean();
                    smallBean.setId(smallListBean.getId());
                    smallBean.setSmallCategoryName(smallListBean.getSmallCategoryName());
                    // 这句代码是关键，这个可以有整个列表
                    bigBean.addSubItem(smallBean);
                }
                mChapterList.add(bigBean);
            }
        }
        if (mChapterList.size() > 0) {
            MultiItemAdapter multiItemAdapter = new MultiItemAdapter(mChapterList, mSubjectId, this);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
            mMultiItemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mMultiItemRecyclerView.setAdapter(multiItemAdapter);
        }

    }
}
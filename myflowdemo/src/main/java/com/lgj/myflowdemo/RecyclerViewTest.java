package com.lgj.myflowdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;

public class RecyclerViewTest extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<MultiItemEntity> mChapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test);
        recyclerView = findViewById(R.id.recyclerView);
        mChapterList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            BigBean bigBean = new BigBean();
            bigBean.setBigCategoryName("你好 : " + i);
            for (int j = 0; j < 4; j++) {
                BigBean.SmallBean smallBean = new BigBean.SmallBean();
                smallBean.setId(j);
                smallBean.setSmallCategoryName("我非常好" + j);
//                bigBean.addSmallBean(smallBean);
                bigBean.addSubItem(smallBean);
            }
            mChapterList.add(bigBean);
        }
        BigAdapter bigAdapter = new BigAdapter(mChapterList);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(bigAdapter);
    }
}
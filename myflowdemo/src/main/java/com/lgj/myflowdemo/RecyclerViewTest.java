package com.lgj.myflowdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;

public class RecyclerViewTest extends AppCompatActivity {

    private RecyclerView recyclerView;
//    private ArrayList<MultiItemEntity> mChapterList;
    private ArrayList<String> mChapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test);
        recyclerView = findViewById(R.id.recyclerView);
        mChapterList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mChapterList.add("你好 :" + i);
        }
//        for (int i = 0; i < 4; i++) {
//            BigBean bigBean = new BigBean();
//            bigBean.setBigCategoryName("你好 : " + i);
//            for (int j = 0; j < 4; j++) {
//                BigBean.SmallBean smallBean = new BigBean.SmallBean();
//                smallBean.setId(j);
//                smallBean.setSmallCategoryName("我非常好" + j);
////                bigBean.addSmallBean(smallBean);
//                bigBean.addSubItem(smallBean);
//            }
//            mChapterList.add(bigBean);
//        }
//        BigAdapter bigAdapter = new BigAdapter(mChapterList);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter());
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> implements View.OnClickListener {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView textView = new TextView(parent.getContext());
            textView.setGravity(Gravity.CENTER);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200,100);
//            params.topMargin = OtherUtils.dip2px(10);
//            params.leftMargin = OtherUtils.dip2px(10);
//            params.rightMargin = OtherUtils.dip2px(10);
//            params.bottomMargin = OtherUtils.dip2px(10);
            textView.setLayoutParams(params);
            return new MyViewHolder(textView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.textView.setText(mChapterList.get(position));
            holder.textView.setTag(position);
            holder.textView.setClickable(true);
            holder.textView.setOnClickListener(this);
        }

        @Override
        public int getItemCount() {
            return mChapterList.size();
        }

        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            System.out.println("lgj : onClick... position :" + position);
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
}
package com.lgj.answersystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.lgj.answersystem.bean.PlacardBean2;
import com.lgj.answersystem.fragment.BaseFragment;
import com.lgj.answersystem.fragment.FragmentFactory;
import com.lgj.answersystem.network.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    public static String TAG = " lgj :" + MainActivity.class;
    private RadioGroup mRadioGroup;
    private FrameLayout mMainFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRadioGroup = findViewById(R.id.rgRadioGroup);
        mMainFrameLayout = findViewById(R.id.mainFrameLayout);
        initFragment();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                Log.i(TAG, "checkId = " + checkId);
                switch (checkId) {
                    case R.id.rbHome:
                        switchFragment(0);
                        break;
                    case R.id.rbQuestionBank:
                        switchFragment(1);
                        break;
                    case R.id.rbMy:
                        switchFragment(2);
                        break;
                }
            }
        });
        mRadioGroup.check(R.id.rbHome);

//        RetrofitUtil.getApiService().getPlacard().enqueue(new Callback<PlacardBean2>() {
//            @Override
//            public void onResponse(Call<PlacardBean2> call, Response<PlacardBean2> response) {
//                System.out.println("lgj : " + response.body().getMessage());
//            }
//
//            @Override
//            public void onFailure(Call<PlacardBean2> call, Throwable t) {
//                System.out.println("lgj : " + t.getMessage() + " cause : " + t.getCause());
//            }
//        });
    }

    //初始化所有的Fragment
    private void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.mainFrameLayout, FragmentFactory.getFragment(0));
        transaction.add(R.id.mainFrameLayout, FragmentFactory.getFragment(1));
        transaction.add(R.id.mainFrameLayout, FragmentFactory.getFragment(2));
        transaction.commit();
    }

    //显示选中的Fragment
    private void switchFragment(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        // 要显示的fragment
        BaseFragment fragment = FragmentFactory.getFragment(position);
        //当切换Fragment的时候，先把所有的Fragment隐藏起来
        transaction.hide(FragmentFactory.getFragment(0));
        transaction.hide(FragmentFactory.getFragment(1));
        transaction.hide(FragmentFactory.getFragment(2));
        transaction.show(fragment);


        if (position == 1) {
            // 表示这块是进入题库，需要获取题库的科目内容
            fragment.getNetWorkData();
        }
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FragmentFactory.sSavedFragment.clear();
    }
}
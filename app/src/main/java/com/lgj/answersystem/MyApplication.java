package com.lgj.answersystem;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {


    public static Context mApplicationContext;

    public static Context getInstance() {
        return mApplicationContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = getApplicationContext();
    }
}

package com.lgj.answersystem.util;

import android.widget.Toast;

import com.lgj.answersystem.MyApplication;

public class ToastUtils {
    public static void showShort(String msg) {
        System.out.println("msg :" + msg);
        Toast.makeText(MyApplication.mApplicationContext, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(String tag, String msg) {
        Toast.makeText(MyApplication.mApplicationContext, tag + " : " +msg, Toast.LENGTH_SHORT).show();
    }
}

package com.lgj.answersystem.util;

import android.widget.Toast;

import com.lgj.answersystem.MyApplication;

public class ToastUtils {
    public static void showShort(String msg) {
        Toast.makeText(MyApplication.mApplicationContext, msg, Toast.LENGTH_SHORT).show();
    }
}

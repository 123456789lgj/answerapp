package com.lgj.answersystem.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lgj.answersystem.MyApplication;

public class ActivityUtils {
    public static void startActivity(@NonNull final Class<? extends Activity> clz,@NonNull Context context) {
        context.startActivity(new Intent(MyApplication.mApplicationContext, clz));
    }

}

package com.lgj.lgjglide;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

// 单例
public class Glide {
    private static volatile Glide glide;
    // requestManagerRetriever 也是单例 ，因为Glide是单例，requestManagerRetriever是在glide的构造方法中创建的
    private final RequestManagerRetriever requestManagerRetriever;
    public Glide(Context applicationContext) {
        this.requestManagerRetriever = new RequestManagerRetriever();
    }

    @NonNull
    public static RequestManager with(@NonNull Context context) {
        return getRetriever(context).get(context);
    }
    @NonNull
    public static RequestManager with(@NonNull Activity activity) {
        return getRetriever(activity).get(activity);
    }


    @NonNull
    public static RequestManager with(@NonNull FragmentActivity activity) {
        return getRetriever(activity).get(activity);
    }


    @NonNull
    public static RequestManager with(@NonNull Fragment fragment) {// android x的fragment
        return getRetriever(fragment.getContext()).get(fragment);
    }

    @SuppressWarnings("deprecation")
    @Deprecated
    @NonNull
    public static RequestManager with(@NonNull android.app.Fragment fragment) {
        return getRetriever(fragment.getActivity()).get(fragment);
    }

    @NonNull
    private static RequestManagerRetriever getRetriever(@Nullable Context context) {
        return Glide.get(context).getRequestManagerRetriever();
    }

    public static Glide get(Context applicationContext) {
        if (glide == null) {
            synchronized (Glide.class) {
                if (glide == null) {
                    glide = new Glide(applicationContext);
                }
            }
        }
        return glide;
    }

    @NonNull
    public RequestManagerRetriever getRequestManagerRetriever() {
        return requestManagerRetriever;
    }
}

package com.lgj.lgjglide;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Preconditions;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.lgj.lgjglide.binding.ApplicationLifecycle;
import com.lgj.lgjglide.binding.RequestManagerFragment;
import com.lgj.lgjglide.binding.SupportRequestManagerFragment;
import com.lgj.lgjglide.utils.Util;

import java.util.HashMap;
import java.util.Map;

// 专门管理requestManager的大管家
public class RequestManagerRetriever implements Handler.Callback {
    @VisibleForTesting
    static final String FRAGMENT_TAG = "com.bumptech.glide.manager";
    private static final int ID_REMOVE_FRAGMENT_MANAGER = 1;
    private static final int ID_REMOVE_SUPPORT_FRAGMENT_MANAGER = 2;

    private volatile RequestManager applicationManager;
    private final Handler handler;

    public RequestManagerRetriever() {
        handler = new Handler(Looper.getMainLooper(), this );
    }
    @VisibleForTesting
    final Map<android.app.FragmentManager, RequestManagerFragment> pendingRequestManagerFragments =
            new HashMap<>();

    /** Pending adds for SupportRequestManagerFragments. */
    @VisibleForTesting
    final Map<FragmentManager, SupportRequestManagerFragment> pendingSupportRequestManagerFragments =
            new HashMap<>();

    @NonNull
    public RequestManager get(@NonNull Context context) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        } else if (Util.isOnMainThread() && !(context instanceof Application)) {
            if (context instanceof FragmentActivity) {
                return get((FragmentActivity) context);
            } else if (context instanceof Activity) {
                return get((Activity) context);
            } else if (context instanceof ContextWrapper
                    && ((ContextWrapper) context).getBaseContext().getApplicationContext() != null) {
                return get(((ContextWrapper) context).getBaseContext());
            }
        }

        return getApplicationManager(context);
    }

    @NonNull
    public RequestManager get(@NonNull FragmentActivity activity) {
        if (Util.isOnBackgroundThread()) {
            return get(activity.getApplicationContext());
        } else {
            Util.assertNotDestroyed(activity);
            FragmentManager fm = activity.getSupportFragmentManager();
            return supportFragmentGet(activity, fm);
        }
    }

    @NonNull
    private RequestManager supportFragmentGet(
            @NonNull Context context,
            @NonNull FragmentManager fm) {
        // 从FragmentManager中获取空白的fragment  这块保证一个activity只创建一次fragment
        SupportRequestManagerFragment current =
                getSupportRequestManagerFragment(fm);
        // 从空白的fragment中获取 RequestManager
        RequestManager requestManager = current.getRequestManager();
        // 保证一个空白的fragment对应一个 requestManager
        if (requestManager == null) {
            Glide glide = Glide.get(context);
            // requestManager 和 lifecycle 关联
            requestManager = new RequestManager(glide, current.getGlideLifecycle(), context);
            // 空白的fragment和 requestManager关联起来
            current.setRequestManager(requestManager);
        }
        return requestManager;
    }

    @NonNull
    private SupportRequestManagerFragment getSupportRequestManagerFragment(
            @NonNull final FragmentManager fm) {
        // 先是从 FragmentManager中获取 Fragment
        SupportRequestManagerFragment current =
                (SupportRequestManagerFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (current == null) {
            // 再从 集合中获取Fragment
            current = pendingSupportRequestManagerFragments.get(fm);
            if (current == null) {
                // 创建空白的fragment
                current = new SupportRequestManagerFragment();
                // 保存空白的fragment到集合中
                pendingSupportRequestManagerFragments.put(fm, current);
                // 这个提交是异步的，所以需要一个集合在保存下fragment，保证一个activity只有一个fragment实例对象
                fm.beginTransaction().add(current, FRAGMENT_TAG).commitAllowingStateLoss();
                // fragment肯定已经提交那么需要把fragment从集合中移除
                handler.obtainMessage(ID_REMOVE_SUPPORT_FRAGMENT_MANAGER, fm).sendToTarget();
            }
        }
        return current;
    }

    @NonNull
    public RequestManager get(@NonNull Fragment fragment) {
        if (Util.isOnBackgroundThread()) {
            return get(fragment.getContext().getApplicationContext());
        } else {
            FragmentManager fm = fragment.getChildFragmentManager();
            return supportFragmentGet(fragment.getContext(), fm);
        }
    }

    @SuppressWarnings("deprecation")
    @NonNull
    public RequestManager get(@NonNull Activity activity) {
        if (Util.isOnBackgroundThread()) {
            return get(activity.getApplicationContext());
        } else {
            Util.assertNotDestroyed(activity);
            android.app.FragmentManager fm = activity.getFragmentManager();
            return fragmentGet(activity, fm);
        }
    }

    @NonNull
    private RequestManager fragmentGet(
            @NonNull Context context,
            @NonNull android.app.FragmentManager fm) {
        RequestManagerFragment current = getRequestManagerFragment(fm);
        RequestManager requestManager = current.getRequestManager();
        if (requestManager == null) {
            Glide glide = Glide.get(context);
            requestManager = new RequestManager(glide, current.getGlideLifecycle(), context);
            current.setRequestManager(requestManager);
        }
        return requestManager;
    }

    @NonNull
    private RequestManagerFragment getRequestManagerFragment(
            @NonNull final android.app.FragmentManager fm) {
        RequestManagerFragment current = (RequestManagerFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (current == null) {
            current = pendingRequestManagerFragments.get(fm);
            if (current == null) {
                current = new RequestManagerFragment();
                pendingRequestManagerFragments.put(fm, current);
                fm.beginTransaction().add(current, FRAGMENT_TAG).commitAllowingStateLoss();
                handler.obtainMessage(ID_REMOVE_FRAGMENT_MANAGER, fm).sendToTarget();
            }
        }
        return current;
    }


    @SuppressWarnings("deprecation")
    @Deprecated
    @NonNull
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public RequestManager get(@NonNull android.app.Fragment fragment) { // 这个过时了，以后用的都是Androidx的fragment
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException(
                    "You cannot start a load on a fragment before it is attached");
        }
        if (Util.isOnBackgroundThread() || Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return get(fragment.getActivity().getApplicationContext());
        } else {
            android.app.FragmentManager fm = fragment.getChildFragmentManager();
            return fragmentGet(fragment.getActivity(), fm);
        }
    }

    // application 作用域
    @NonNull
    private RequestManager getApplicationManager(@NonNull Context context) {
        if (applicationManager == null) {
            synchronized (this) {
                if (applicationManager == null) {
                    Glide glide = Glide.get(context.getApplicationContext());
                    applicationManager = new RequestManager(glide, new ApplicationLifecycle(), context.getApplicationContext());
                }
            }
        }
        return applicationManager;
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        switch (message.what) {
            case ID_REMOVE_FRAGMENT_MANAGER:
                android.app.FragmentManager fm = (android.app.FragmentManager) message.obj;
                pendingRequestManagerFragments.remove(fm);
                break;
            case ID_REMOVE_SUPPORT_FRAGMENT_MANAGER:
                FragmentManager supportFm = (FragmentManager) message.obj;
                pendingSupportRequestManagerFragments.remove(supportFm);
                break;
        }
        return false;
    }
}

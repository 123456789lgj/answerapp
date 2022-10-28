package com.lgj.lgjglide.binding;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lgj.lgjglide.RequestManager;

// android x 的fragment 监听生命周期的变化
public class SupportRequestManagerFragment extends Fragment {
    private final ActivityFragmentLifecycle lifecycle;
    private RequestManager requestManager;

    public SupportRequestManagerFragment() {
        // fragment 和 lifecycle 进行关联
        this(new ActivityFragmentLifecycle());
    }

    public SupportRequestManagerFragment(@NonNull ActivityFragmentLifecycle lifecycle) {
        this.lifecycle = lifecycle;

    }

    public void setRequestManager(@NonNull RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    @NonNull
    public ActivityFragmentLifecycle getGlideLifecycle() {
        return lifecycle;
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycle.onStartAction();
    }

    @Override
    public void onStop() {
        super.onStop();
        lifecycle.onStopAction();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifecycle.onDestroyAction();
    }

    @Nullable
    public RequestManager getRequestManager() {
        return requestManager;
    }
}

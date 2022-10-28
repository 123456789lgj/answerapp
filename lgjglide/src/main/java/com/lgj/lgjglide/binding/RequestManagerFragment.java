package com.lgj.lgjglide.binding;

import android.annotation.SuppressLint;
import android.app.Fragment;

import androidx.annotation.NonNull;

import com.lgj.lgjglide.RequestManager;
import com.lgj.lgjglide.binding.inter.Lifecycle;

public class RequestManagerFragment extends Fragment {
    private final ActivityFragmentLifecycle lifecycle;
    private RequestManager requestManager;

    public RequestManagerFragment() {
        this(new ActivityFragmentLifecycle());
    }

    @SuppressLint("ValidFragment")
    public RequestManagerFragment(@NonNull ActivityFragmentLifecycle lifecycle) {
        this.lifecycle = lifecycle;

    }

    public void setRequestManager(@NonNull RequestManager requestManager) {
        this.requestManager = requestManager;
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

    public RequestManager getRequestManager() {
        return requestManager;
    }

    public Lifecycle getGlideLifecycle() {
        return lifecycle;
    }
}

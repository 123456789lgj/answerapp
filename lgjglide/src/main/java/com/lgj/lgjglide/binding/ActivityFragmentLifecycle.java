package com.lgj.lgjglide.binding;

import androidx.annotation.NonNull;
import com.lgj.lgjglide.utils.Util;
import com.lgj.lgjglide.binding.inter.Lifecycle;
import com.lgj.lgjglide.binding.inter.LifecycleListener;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

// 非application作用域，只有它可以搞一个空白的fragment
class ActivityFragmentLifecycle implements Lifecycle {
    private final Set<LifecycleListener> lifecycleListeners =
            Collections.newSetFromMap(new WeakHashMap<LifecycleListener, Boolean>());
    private boolean isStarted;// 启动的标记
    private boolean isDestroyed;// 销毁的标记

    @Override
    public void addListener(@NonNull LifecycleListener listener) {
        lifecycleListeners.add(listener);
        if (isDestroyed) {
            listener.onDestroy();
        } else if (isStarted) {
            listener.onStart();
        } else {
            listener.onStop(); // 首次启动会默认执行onStop，
        }
    }

    @Override
    public void removeListener(@NonNull LifecycleListener listener) {
        lifecycleListeners.remove(listener);
    }

    public void onStartAction() {
        isStarted = true;
        for (LifecycleListener lifecycleListener : lifecycleListeners) {
            lifecycleListener.onStart();
        }
    }

    public void onStopAction() {
        isStarted = false;
        for (LifecycleListener lifecycleListener : lifecycleListeners) {
            lifecycleListener.onStop();
        }
    }

    public void onDestroyAction() {
        isDestroyed = true;
        for (LifecycleListener lifecycleListener : lifecycleListeners) {
            lifecycleListener.onDestroy();
        }
    }
}

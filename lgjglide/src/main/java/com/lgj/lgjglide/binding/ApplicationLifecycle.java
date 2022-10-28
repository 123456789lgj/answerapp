package com.lgj.lgjglide.binding;

import androidx.annotation.NonNull;

import com.lgj.lgjglide.binding.inter.Lifecycle;
import com.lgj.lgjglide.binding.inter.LifecycleListener;


// application 作用域
public class ApplicationLifecycle implements Lifecycle {

    @Override
    public void addListener(@NonNull LifecycleListener listener) {
        // APP 起来了，只能APP起来的时候执行 onStart
        listener.onStart();
    }

    @Override
    public void removeListener(@NonNull LifecycleListener listener) {
        // Do nothing.
    }
}

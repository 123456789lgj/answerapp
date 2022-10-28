package com.lgj.lgjglide;

import android.content.Context;

import com.lgj.lgjglide.binding.ApplicationLifecycle;
import com.lgj.lgjglide.binding.inter.Lifecycle;
import com.lgj.lgjglide.binding.inter.LifecycleListener;

public class RequestManager implements LifecycleListener {
    private Lifecycle lifecycle;
    public RequestManager(Glide glide, Lifecycle lifecycle, Context applicationContext) {
        this.lifecycle = lifecycle;
        // 将 requestManager 加入到 lifecycle中
        this.lifecycle.addListener(this);
    }

    // Activity/fragment 可见时恢复请求，onStart
    @Override
    public void onStart() {
        System.out.println("lgj 执行生命周期业务 onStart，运行队列 全部执行，等待队列全部清空 ......" + this);
    }

    // Activity/fragment 不可见时暂停请求，执行onStop
    @Override
    public void onStop() {
        System.out.println("lgj 执行生命周期业务 onStop，运行队列 全部停止，把任务加到等待队列中去 ......");
    }

    @Override
    public void onDestroy() {
        System.out.println("lgj 执行生命周期业务 onDestroy，自己负责移除自己绑定的生命周期监听，释放操作......");
        this.lifecycle.removeListener(this);
    }
}

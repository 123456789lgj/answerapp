package com.lgj.myflowdemo.dagger.presenter_id;

import com.lgj.myflowdemo.dagger.DatabaseModule;
import com.lgj.myflowdemo.dagger.HttpModule;
import com.lgj.myflowdemo.dagger.Test;
import com.lgj.myflowdemo.dagger.Test2;
import com.lgj.myflowdemo.dagger.scope.UserScope;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;

/*
 * 这就是一个组件，用于注入对象到其他组件中去
 * */
// @Singleton 不同的Component的上，Scope不能相同 (两个不同的Component上不能同时用@Singleton)
// 有Scope不能依赖没有Scope的Component
// 没有Scope的不能依赖有Scope的Component
//  上面这两种完全是不同的条件
@UserScope
@Component(modules = {PresenterModule.class})
public interface PresenterComponent {
    // Component 是不能直接注入到同一个类里面的，
    // 下面这种方式，MyComponent组件已经注入，那么PresenterComponent就不能直接这么注入
    // 使用依赖关系，就不能用这种方式了
//    void injectTest(Test test);
//
//    void injectTest(Test2 test2);

    public PresenterObject providerDatabaseObject();
}

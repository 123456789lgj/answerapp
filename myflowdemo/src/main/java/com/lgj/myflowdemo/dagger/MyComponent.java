package com.lgj.myflowdemo.dagger;

import com.lgj.myflowdemo.dagger.object.DatabaseObject;
import com.lgj.myflowdemo.dagger.presenter_id.PresenterComponent;
import com.lgj.myflowdemo.dagger.scope.AppScope;

import javax.inject.Singleton;

import dagger.Component;

/*
 * 这就是一个组件，用于注入对象到其他组件中去
 * */

@AppScope
@Component(modules = {HttpModule.class, DatabaseModule.class}
// 需要依赖其他组件的话，就加 dependencies
        , dependencies = {PresenterComponent.class}
)
public interface MyComponent {
    // 参数不能用 多态、object、泛型 T等
    void injectTest(Test test);

    void injectTest2(Test2 test2);
}

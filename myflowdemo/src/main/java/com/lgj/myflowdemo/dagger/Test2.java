
package com.lgj.myflowdemo.dagger;

import com.lgj.myflowdemo.dagger.object.HttpObject;
import com.lgj.myflowdemo.dagger.presenter_id.PresenterObject;

import javax.inject.Inject;

public class Test2 {
    // 局部单例模块
    @Inject
    HttpObject httpObject;

    @Inject
    PresenterObject presenterObject;


    private MyComponent myComponent;
    public Test2(MyComponent myComponent) {
        this.myComponent = myComponent;
    }

    public void zhuru(){
        // 因为这块又重新创建了一个 DaggerMyComponent对象和之前的不一样
//        DaggerMyComponent.builder()
//                .httpModule(new HttpModule())
//                .build()
//                .injectTest(this);

        myComponent.injectTest2(this);
        System.out.println("lgj test2 httpObject :" + httpObject.hashCode());
        System.out.println("lgj test2 presenterObject :" + presenterObject.hashCode());
    }
}

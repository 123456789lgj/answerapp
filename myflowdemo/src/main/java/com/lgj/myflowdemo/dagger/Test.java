package com.lgj.myflowdemo.dagger;

import com.lgj.myflowdemo.dagger.object.DatabaseObject;
import com.lgj.myflowdemo.dagger.object.HttpObject;
import com.lgj.myflowdemo.dagger.presenter_id.DaggerPresenterComponent;
import com.lgj.myflowdemo.dagger.presenter_id.PresenterComponent;
import com.lgj.myflowdemo.dagger.presenter_id.PresenterModule;
import com.lgj.myflowdemo.dagger.presenter_id.PresenterObject;

import javax.inject.Inject;

public class Test {
    @Inject
    HttpObject httpObject;

    @Inject
    HttpObject httpObject2;

    @Inject
    DatabaseObject databaseObject;

    @Inject
    PresenterObject presenterObject;

    public static void main(String[] args) {
        new Test().zhuru();
    }

    public void zhuru() {
//        DaggerMyComponent.create().injectTest(this);
        MyComponent myComponent = DaggerMyComponent.builder()
                .httpModule(new HttpModule())
                .databaseModule(new DatabaseModule())
                .presenterComponent(
                        DaggerPresenterComponent
                                .builder()
                                .presenterModule(new PresenterModule())
                                .build())
                .build();
//        DaggerPresenterComponent
//                .builder()
//                .presenterModule(new PresenterModule())
//                .build()
//        就等于  DaggerPresenterComponent.create();


        myComponent.injectTest(this);


        // 在开发过程中，我们有可能需要让 HttpObject做成单例
        System.out.println("lgj httpObject :" + httpObject.hashCode());
        System.out.println("lgj httpObject2 :" + httpObject2.hashCode());
        System.out.println("lgj databaseObject :" + databaseObject.hashCode());
        System.out.println("lgj presenterObject :" + presenterObject.hashCode());
        // 单例的话，就需要让 提供的module上加上@Single，和对应的组件上也加上@Single
        System.out.println("----------------------------------------------------");
        new Test2(myComponent).zhuru();

        // 如果需要全局单例的话，需要把组件创建提升到Application级别
    }
}

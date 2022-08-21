package com.lgj.myflowdemo.dagger;


import com.lgj.myflowdemo.dagger.object.DatabaseObject;
import com.lgj.myflowdemo.dagger.object.HttpObject;
import com.lgj.myflowdemo.dagger.scope.AppScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/*
* 用于提供对象
* */
@AppScope
@Module
public class HttpModule {

    @AppScope
    @Provides
    public HttpObject providerHttpObject(){
        return new HttpObject();
    }
}

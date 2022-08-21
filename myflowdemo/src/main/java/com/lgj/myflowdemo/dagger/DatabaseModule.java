package com.lgj.myflowdemo.dagger;

import com.lgj.myflowdemo.dagger.object.DatabaseObject;

import dagger.Module;
import dagger.Provides;

/*
* 用于提供对象
* */
@Module
public class DatabaseModule {
    @Provides
    public DatabaseObject providerDatabaseObject(){
        return new DatabaseObject();
    }
}

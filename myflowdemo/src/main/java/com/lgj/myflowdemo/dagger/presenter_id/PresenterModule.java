package com.lgj.myflowdemo.dagger.presenter_id;

import com.lgj.myflowdemo.dagger.scope.UserScope;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/*
 * 用于提供对象
 * */
@UserScope
@Module
public class PresenterModule {
    @UserScope
    @Provides
    public PresenterObject providerDatabaseObject() {
        return new PresenterObject();
    }
}

package com.lightheart.sphr.patient.di.module;

import android.content.Context;

import com.lightheart.sphr.patient.app.DHApplication;
import com.lightheart.sphr.patient.di.scope.ContextLife;
import com.lightheart.sphr.patient.di.scope.PerApp;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {
    private DHApplication mApplication;

    public ApplicationModule(DHApplication application) {
        mApplication = application;
    }

    @Provides
    @PerApp
    @ContextLife("Application")
    public Context provideApplicationContext() {
        return mApplication.getApplicationContext();
    }
}

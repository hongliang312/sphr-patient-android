package com.lightheart.sphr.patient.app;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.lightheart.sphr.patient.di.component.ApplicationComponent;
import com.lightheart.sphr.patient.di.component.DaggerApplicationComponent;
import com.lightheart.sphr.patient.di.module.ApplicationModule;

/**
 * Created by fucp on 2018-4-10.
 * Description :
 */

public class DHApplication extends Application {

    private ApplicationComponent mApplicationComponent;
    private static DHApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initApplicationComponent();
        Utils.init(this);
    }

    /**
     * 初始化ApplicationComponent
     */
    private void initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    public static DHApplication getInstance() {
        return mInstance;
    }

}

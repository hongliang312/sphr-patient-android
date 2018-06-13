package com.lightheart.sphr.patient.di.component;

import android.app.Activity;
import android.content.Context;

import com.lightheart.sphr.patient.di.module.ActivityModule;
import com.lightheart.sphr.patient.di.scope.ContextLife;
import com.lightheart.sphr.patient.di.scope.PerActivity;
import com.lightheart.sphr.patient.ui.main.ui.MainActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(MainActivity activity);

}

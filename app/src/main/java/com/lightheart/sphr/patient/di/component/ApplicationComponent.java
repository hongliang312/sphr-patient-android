package com.lightheart.sphr.patient.di.component;

import android.content.Context;

import com.lightheart.sphr.patient.di.module.ApplicationModule;
import com.lightheart.sphr.patient.di.scope.ContextLife;
import com.lightheart.sphr.patient.di.scope.PerApp;

import dagger.Component;


@PerApp
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    @ContextLife("Application")
    Context getApplication();
}
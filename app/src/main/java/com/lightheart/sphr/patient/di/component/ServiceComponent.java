package com.lightheart.sphr.patient.di.component;

import android.content.Context;

import com.lightheart.sphr.patient.di.module.ServiceModule;
import com.lightheart.sphr.patient.di.scope.ContextLife;
import com.lightheart.sphr.patient.di.scope.PerService;

import dagger.Component;

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {
    @ContextLife("Service")
    Context getServiceContext();

    @ContextLife("Application")
    Context getApplicationContext();
}

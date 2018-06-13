package com.lightheart.sphr.patient.di.component;

import android.app.Activity;
import android.content.Context;

import com.lightheart.sphr.patient.di.module.FragmentModule;
import com.lightheart.sphr.patient.di.scope.ContextLife;
import com.lightheart.sphr.patient.di.scope.PerFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

//    void inject(HomeFragment fragment);

//    void inject(ContractFragment fragment);

//    void inject(MyFragment fragment);

}

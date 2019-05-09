package com.giussep.ricardo.doctus.root;

import com.facebook.stetho.Stetho;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class ApplicationClass extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().application(this).build();
    }
}

package com.giussep.ricardo.doctus.root;

import com.giussep.ricardo.doctus.crearTip.CrearTipActivity;
import com.giussep.ricardo.doctus.crearTip.CrearTipModule;
import com.giussep.ricardo.doctus.main.MainActivity;
import com.giussep.ricardo.doctus.main.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector(modules = CrearTipModule.class)
    abstract CrearTipActivity crearTipActivity();
}

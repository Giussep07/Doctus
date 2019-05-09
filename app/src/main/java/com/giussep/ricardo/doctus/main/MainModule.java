package com.giussep.ricardo.doctus.main;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MainModule {

    @Binds
    abstract MainContracts.Presenter providePresenter(MainPresenter mainPresenter);
}

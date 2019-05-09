package com.giussep.ricardo.doctus.crearTip;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class CrearTipModule {

    @Binds
    abstract CrearTipContracts.Presenter providePresenter(CrearTipPresenter presenter);
}

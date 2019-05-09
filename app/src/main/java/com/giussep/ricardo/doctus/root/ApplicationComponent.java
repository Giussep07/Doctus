package com.giussep.ricardo.doctus.root;

import android.app.Application;

import com.giussep.ricardo.doctus.dataSource.DoctusDatabaseModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class,
        DoctusDatabaseModule.class
})
public interface ApplicationComponent extends AndroidInjector<ApplicationClass> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        ApplicationComponent.Builder application(Application application);

        ApplicationComponent build();
    }

}

package com.giussep.ricardo.doctus.dataSource;

import android.app.Application;

import com.giussep.ricardo.doctus.dataSource.tip.TipDao;
import com.giussep.ricardo.doctus.dataSource.tip.TipDataSource;
import com.giussep.ricardo.doctus.dataSource.tip.TipLocalDataSource;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class DoctusDatabaseModule {

    @Singleton
    @Provides
    static DoctusDatabase provideDb(Application context) {
        return Room.databaseBuilder(context, DoctusDatabase.class, "DoctusPrueba")
                .build();
    }

    @Singleton
    @Binds
    abstract TipDataSource provideTipLocalDataSource(TipLocalDataSource tipLocalDataSource);

    @Singleton
    @Provides
    static TipDao provideTipDao(DoctusDatabase database) {
        return database.getTipDao();
    }
}

package com.giussep.ricardo.doctus.dataSource;

import com.giussep.ricardo.doctus.dataSource.tip.TipDao;
import com.giussep.ricardo.doctus.models.Tip;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Tip.class, version = 1, exportSchema = false)
public abstract class DoctusDatabase extends RoomDatabase {

    abstract TipDao getTipDao();
}

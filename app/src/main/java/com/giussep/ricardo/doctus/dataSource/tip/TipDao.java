package com.giussep.ricardo.doctus.dataSource.tip;

import com.giussep.ricardo.doctus.models.Tip;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Completable;
import io.reactivex.Maybe;

@Dao
public interface TipDao {

    @Insert
    Completable insertTip(Tip tip);

    @Update
    Completable updateTip(Tip tip);

    @Delete
    Completable deleteTip(Tip tip);

    @Query("SELECT * FROM Tip")
    Maybe<List<Tip>> getAllTips();
}

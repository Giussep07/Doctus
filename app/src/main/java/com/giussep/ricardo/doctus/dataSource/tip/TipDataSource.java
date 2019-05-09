package com.giussep.ricardo.doctus.dataSource.tip;

import com.giussep.ricardo.doctus.models.Tip;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;

public interface TipDataSource {

    Completable insertTip(Tip tip);

    Completable updateTip(Tip tip);

    Completable deleteTip(Tip tip);

    Maybe<List<Tip>> getAllTips();
}

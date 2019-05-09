package com.giussep.ricardo.doctus.dataSource.tip;

import com.giussep.ricardo.doctus.models.Tip;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;

public class TipLocalDataSource implements TipDataSource {

    private TipDao dao;

    @Inject
    public TipLocalDataSource(TipDao dao) {
        this.dao = dao;
    }

    @Override
    public Completable insertTip(Tip tip) {
        return dao.insertTip(tip);
    }

    @Override
    public Completable updateTip(Tip tip) {
        return dao.updateTip(tip);
    }

    @Override
    public Completable deleteTip(Tip tip) {
        return dao.deleteTip(tip);
    }

    @Override
    public Maybe<List<Tip>> getAllTips() {
        return dao.getAllTips();
    }
}

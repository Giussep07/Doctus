package com.giussep.ricardo.doctus.main;

import com.giussep.ricardo.doctus.dataSource.tip.TipDataSource;
import com.giussep.ricardo.doctus.models.Tip;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContracts.Presenter {

    private MainContracts.View view;
    private TipDataSource dataSource;

    @Inject
    public MainPresenter(TipDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void setView(MainContracts.View view) {
        this.view = view;
    }

    @Override
    public void getTips() {
        if (view != null) {

            view.showDialogProgress();

            CompositeDisposable compositeDisposable = new CompositeDisposable();

            compositeDisposable.add(dataSource.getAllTips()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(tips -> {
                        view.hideDialogProgress();
                        view.loadTips(tips);
                    }, throwable -> {
                        view.hideDialogProgress();
                        view.showErrorLoadingTips();
                    }));

        }
    }

    @Override
    public void crearTipPressed() {
        if (view != null)
            view.openCrearTipActivity();
    }

    @Override
    public void eliminarTip(Tip tip, int position) {
        if (view != null) {

            CompositeDisposable disposable = new CompositeDisposable();

            view.showDialogProgress();

            disposable.add(dataSource.deleteTip(tip)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        view.hideDialogProgress();
                        view.showTipDeletedSuccessfully(position);
                    }, throwable -> {
                        view.hideDialogProgress();
                        view.showErrorDeletingTips();
                    }));
        }
    }
}

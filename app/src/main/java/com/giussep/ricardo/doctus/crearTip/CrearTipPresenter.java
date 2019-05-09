package com.giussep.ricardo.doctus.crearTip;

import com.giussep.ricardo.doctus.R;
import com.giussep.ricardo.doctus.dataSource.tip.TipDataSource;
import com.giussep.ricardo.doctus.models.Tip;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CrearTipPresenter implements CrearTipContracts.Presenter {

    private CrearTipContracts.View view;
    private TipDataSource dataSource;

    @Inject
    public CrearTipPresenter(TipDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void setView(CrearTipContracts.View view) {
        this.view = view;
    }

    @Override
    public void crearTipPressed() {
        if (view != null) {

            view.resetTextInputErrors();

            String fecha = view.getFecha();
            String titulo = view.getTitulo();
            String descripcion = view.getDescripcion();

            if (fecha.trim().equals("")) {
                view.showTextInputEmpty(R.id.textInputLayout_fecha);
            } else if (titulo.trim().equals("")) {
                view.showTextInputEmpty(R.id.textInputLayout_titulo);
            } else if (descripcion.trim().equals("")) {
                view.showTextInputEmpty(R.id.textInputLayout_descripcion);
            } else {

                view.showDialogProgress();

                Tip tip = new Tip();
                tip.setFecha(fecha);
                tip.setTitulo(titulo);
                tip.setDescripcion(descripcion);

                CompositeDisposable disposable = new CompositeDisposable();

                disposable.add(dataSource.insertTip(tip)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> {
                                    view.hideDialogProgress();
                                    view.showTipCreatedSuccessfully();
                                    view.enableUIElements();
                                    view.resetForm();
                                },
                                throwable -> {
                                    view.hideDialogProgress();
                                    view.showErrorCreatingTip();
                                    view.enableUIElements();
                                }
                        ));
            }

        }
    }

    @Override
    public void loadTip(Tip tip) {
        if (view != null)
            view.loadTipInfo(tip);
    }

    @Override
    public void editarTipPressed(Tip tipEditar) {
        if (view != null) {

            view.resetTextInputErrors();

            String fecha = view.getFecha();
            String titulo = view.getTitulo();
            String descripcion = view.getDescripcion();

            if (fecha.trim().equals("")) {
                view.showTextInputEmpty(R.id.textInputLayout_fecha);
            } else if (titulo.trim().equals("")) {
                view.showTextInputEmpty(R.id.textInputLayout_titulo);
            } else if (descripcion.trim().equals("")) {
                view.showTextInputEmpty(R.id.textInputLayout_descripcion);
            } else {

                view.showDialogProgress();

                Tip tip = new Tip();
                tip.setId(tipEditar.getId());
                tip.setFecha(fecha);
                tip.setTitulo(titulo);
                tip.setDescripcion(descripcion);

                CompositeDisposable disposable = new CompositeDisposable();

                disposable.add(dataSource.updateTip(tip)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> {
                                    view.hideDialogProgress();
                                    view.showTipUpdatedSuccessfully();
                                    view.enableUIElements();
                                    view.resetForm();
                                },
                                throwable -> {
                                    view.hideDialogProgress();
                                    view.showErrorUpdatingTip();
                                    view.enableUIElements();
                                }
                        ));
            }

        }

    }
}

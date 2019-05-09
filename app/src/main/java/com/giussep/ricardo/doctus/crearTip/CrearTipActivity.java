package com.giussep.ricardo.doctus.crearTip;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.giussep.ricardo.doctus.R;
import com.giussep.ricardo.doctus.dialogs.DatePickerDialogFragment;
import com.giussep.ricardo.doctus.models.Tip;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import dagger.android.support.DaggerAppCompatActivity;

public class CrearTipActivity extends DaggerAppCompatActivity implements CrearTipContracts.View, DatePickerDialogFragment.OnDatePicker {

    public static final String TIP_EDITAR = "TIP_EDITAR";
    @Inject
    CrearTipContracts.Presenter presenter;

    @BindView(R.id.textInputEditText_fecha)
    TextInputEditText textInputEditTextFecha;
    @BindView(R.id.textInputLayout_fecha)
    TextInputLayout textInputLayoutFecha;
    @BindView(R.id.textInputEditText_titulo)
    TextInputEditText textInputEditTextTitulo;
    @BindView(R.id.textInputLayout_titulo)
    TextInputLayout textInputLayoutTitulo;
    @BindView(R.id.textInputEditText_descripcion)
    TextInputEditText textInputEditTextDescripcion;
    @BindView(R.id.textInputLayout_descripcion)
    TextInputLayout textInputLayoutDescripcion;
    @BindView(R.id.button_crear)
    MaterialButton buttonCrear;
    @BindView(R.id.button_editar)
    MaterialButton buttonEditar;

    private ProgressDialog progressDialog;
    private String fecha;
    private Tip tipEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tip);
        ButterKnife.bind(this);

        presenter.setView(this);

        if (getIntent().getExtras() != null) {

            Bundle bundle = getIntent().getExtras();

            if (bundle.containsKey(TIP_EDITAR)) {

                Tip tip = (Tip) bundle.getSerializable(TIP_EDITAR);

                presenter.loadTip(tip);

                buttonCrear.setVisibility(View.GONE);
                buttonEditar.setVisibility(View.VISIBLE);
            }
        }
    }

    @OnClick({R.id.button_crear, R.id.button_editar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_crear:
                presenter.crearTipPressed();
                break;
            case R.id.button_editar:
                presenter.editarTipPressed(tipEditar);
                break;
        }
    }

    @OnTouch(R.id.textInputEditText_fecha)
    public boolean actionOnEditText(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
            datePickerDialogFragment.show(getSupportFragmentManager(), "datepicker");
        }

        return true;
    }

    //region Implementation DatePickerDialogFragment.OnDatePicker

    @Override
    public void dateSelected(String date) {
        textInputLayoutFecha.setFocusable(false);
        textInputEditTextFecha.setText(date);

        this.fecha = date;
    }

    //endregion

    //region Implementation CrearTipContracts.View

    @Override
    public void showDialogProgress() {
        progressDialog = new ProgressDialog(this);

        progressDialog.setIndeterminate(true);
        progressDialog.setTitle(R.string.app_name);
        progressDialog.setMessage(getString(R.string.loading_tips));
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        progressDialog.show();
    }

    @Override
    public void hideDialogProgress() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public String getFecha() {
        return fecha == null ? "" : fecha;
    }

    @Override
    public String getTitulo() {
        return textInputEditTextTitulo.getText().toString();
    }

    @Override
    public String getDescripcion() {
        return textInputEditTextDescripcion.getText().toString();
    }

    @Override
    public void enableUIElements() {
        textInputLayoutFecha.setEnabled(true);
        textInputLayoutTitulo.setEnabled(true);
        textInputLayoutDescripcion.setEnabled(true);
        buttonCrear.setEnabled(true);
    }

    @Override
    public void disableUIElements() {
        textInputLayoutFecha.setEnabled(false);
        textInputLayoutTitulo.setEnabled(false);
        textInputLayoutDescripcion.setEnabled(false);
        buttonCrear.setEnabled(false);
    }

    @Override
    public void showTipCreatedSuccessfully() {
        Toast.makeText(this, R.string.tip_created_successfully, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showTipUpdatedSuccessfully() {
        Toast.makeText(this, R.string.tip_updated_successfully, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showErrorCreatingTip() {
        Toast.makeText(this, R.string.error_creating_tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorUpdatingTip() {
        Toast.makeText(this, R.string.error_updating_tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void resetForm() {
        textInputEditTextFecha.setText("");
        textInputEditTextTitulo.setText("");
        textInputEditTextDescripcion.setText("");
    }

    @Override
    public void showTextInputEmpty(int textInputLayout) {
        ((TextInputLayout) findViewById(textInputLayout)).setError(getString(R.string.textinput_empty));
    }

    @Override
    public void resetTextInputErrors() {
        textInputLayoutFecha.setError(null);
        textInputLayoutTitulo.setError(null);
        textInputLayoutDescripcion.setError(null);
    }

    @Override
    public void loadTipInfo(Tip tip) {
        tipEditar = tip;

        fecha = tip.getFecha();

        textInputEditTextFecha.setText(tip.getFecha());
        textInputEditTextTitulo.setText(tip.getTitulo());
        textInputEditTextDescripcion.setText(tip.getDescripcion());

    }

    //endregion
}

package com.giussep.ricardo.doctus.crearTip;

import com.giussep.ricardo.doctus.models.Tip;

public interface CrearTipContracts {

    public interface View {

        void showDialogProgress();

        void hideDialogProgress();

        String getFecha();

        String getTitulo();

        String getDescripcion();

        void enableUIElements();

        void disableUIElements();

        void showTipCreatedSuccessfully();

        void showTipUpdatedSuccessfully();

        void showErrorCreatingTip();

        void showErrorUpdatingTip();

        void resetForm();

        void showTextInputEmpty(int textInputLayout);

        void resetTextInputErrors();

        void loadTipInfo(Tip tip);
    }

    public interface Presenter {

        void setView(View view);

        void crearTipPressed();

        void loadTip(Tip tip);

        void editarTipPressed(Tip tipEditar);
    }
}

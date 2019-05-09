package com.giussep.ricardo.doctus.main;

import com.giussep.ricardo.doctus.models.Tip;

import java.util.List;

public interface MainContracts {

    public interface View {
        void showDialogProgress();

        void hideDialogProgress();

        void loadTips(List<Tip> tipList);

        void showErrorLoadingTips();

        void openCrearTipActivity();

        void showTipDeletedSuccessfully(int position);

        void showErrorDeletingTips();
    }

    public interface Presenter {

        void setView(View view);

        void getTips();

        void crearTipPressed();

        void eliminarTip(Tip tip, int position);
    }

}

package com.proyecto.sistemas.mediccityempresa.presentation.activities.detalle_medico_firestore;

import com.proyecto.sistemas.mediccityempresa.data.entities.Medico;

public interface IMedicoDetailFirestoreContract {
    interface IView{
        void showError(String errorMsg);
        void showProgressBar();
        void hideProgressBar();
        void getPostDetailSuccess(Medico medico);
    }
    interface IPresenter{
        void attachView(IView view);
        void detachView();
        boolean isViewAttached();
        void getMedico(String id);
    }
}

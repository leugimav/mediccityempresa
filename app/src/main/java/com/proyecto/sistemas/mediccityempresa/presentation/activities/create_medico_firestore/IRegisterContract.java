package com.proyecto.sistemas.mediccityempresa.presentation.activities.create_medico_firestore;

public interface IRegisterContract {

    interface IView {
        void showError(String errorMsg);
        void showProgressDialog();
        void hideProgressDialog();
        void goToMenu(String id);
        void onSuccessCreate(); ///////////////////

    }


    interface IPresenter {
        void attachView(IView view);
        void detachView();
        boolean isViewAttached();
        void register(String username, String password , String nombres, String apellidos,String celuar,String nrocolegiatura,String espPrincipal,String espSecundaria,String centroEstudios,String finalizaEstudios,String detalles,String latitud,String longitud,String tipo);

    }
}

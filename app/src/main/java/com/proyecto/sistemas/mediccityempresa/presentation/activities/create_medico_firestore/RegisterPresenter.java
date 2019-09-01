package com.proyecto.sistemas.mediccityempresa.presentation.activities.create_medico_firestore;

import android.text.TextUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.proyecto.sistemas.mediccityempresa.data.entities.Medico;
import com.proyecto.sistemas.mediccityempresa.domain.create_medico_firestore_interactor.ICreateMedicoFirestoreInteractor;

import javax.inject.Inject;

public class RegisterPresenter implements IRegisterContract.IPresenter {

    @Inject
    ICreateMedicoFirestoreInteractor interactor;

    private IRegisterContract.IView view;
    private final FirebaseAuth firebaseAuth;

    @Inject
    public RegisterPresenter(FirebaseAuth firebaseAuth)
    {
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    public void attachView(IRegisterContract.IView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void register(String username, String password, String nombres, String apellidos, String celular, String nrocolegiatura, String espPrincipal, String espSecundaria, String centroEstudios, String finalizaEstudios, String detalles, String latitud, String longitud, String tipo)
    {

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            if(isViewAttached()) view.showError("Debe completar los datos del Usuario y Password");
        }

        else
        {
            if(isViewAttached()) view.showProgressDialog();
            firebaseAuth.createUserWithEmailAndPassword(username,password)
                    .addOnCompleteListener(task -> {
                        if(isViewAttached()){
                                //view.hideProgressDialog();
                            if(task.isSuccessful()){
                                //Se realiza el registro en la B.D
                                if(TextUtils.isEmpty(apellidos) || TextUtils.isEmpty(celular) ||  TextUtils.isEmpty(nrocolegiatura) || TextUtils.isEmpty(espPrincipal) || TextUtils.isEmpty(espSecundaria) || TextUtils.isEmpty(centroEstudios) || TextUtils.isEmpty(finalizaEstudios) || TextUtils.isEmpty(detalles) || TextUtils.isEmpty(latitud) || TextUtils.isEmpty(tipo))
                                {
                                    if(isViewAttached()) view.showError("Debe completar todos los datos");
                                    return; ///////////////////////Validar
                                }
                                createMedico(username,password,nombres,apellidos,celular,nrocolegiatura,espPrincipal,espSecundaria,centroEstudios,finalizaEstudios,detalles,latitud,longitud,tipo);
                                view.onSuccessCreate();
                                view.goToMenu(firebaseAuth.getCurrentUser().getUid());
                            } else {
                                view.showError("Ocurrio un error");
                            }
                            view.hideProgressDialog();
                        }
                    }).addOnFailureListener(e -> {
                if(isViewAttached()){
                    view.hideProgressDialog();
                    view.showError(e.getMessage());
                }
            });


        }
    }

    private void createMedico(String username, String password , String nombres, String apellidos,String celular,String nrocolegiatura,String espPrincipal,String espSecundaria,String centroEstudios,String finalizaEstudios,String detalles,String latitud,String longitud,String tipo)
    {
        Medico medico = new Medico();
        medico.setUid(firebaseAuth.getUid());
        medico.setCorreo(username);
        medico.setNombres(nombres);
        medico.setApellidos(apellidos);
        medico.setCelular(celular);
        medico.setNroColegiatura(nrocolegiatura);
        medico.setEspPrincipal(espPrincipal);
        medico.setEspSecundaria(espSecundaria);
        medico.setCentroEstudios(centroEstudios);
        medico.setFinalizaEstudios(finalizaEstudios);
        medico.setDetalles(detalles);
        medico.setLatitud(latitud);
        medico.setLongitud(longitud);
        medico.setTipo(tipo);

        interactor.createMedico(medico, task -> {
            if(isViewAttached()) {
                view.hideProgressDialog();
                if (task.isSuccessful()) {
                    view.onSuccessCreate();
                } else {
                    view.showError(task.getException().getMessage());
                }
            }
        });

    }
}

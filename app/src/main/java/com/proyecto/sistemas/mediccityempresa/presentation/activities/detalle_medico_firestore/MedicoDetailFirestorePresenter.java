package com.proyecto.sistemas.mediccityempresa.presentation.activities.detalle_medico_firestore;

import com.proyecto.sistemas.mediccityempresa.data.entities.Medico;
import com.proyecto.sistemas.mediccityempresa.data.repository.IMedicoFirestoreRepository;
import com.proyecto.sistemas.mediccityempresa.domain.medico_detail_firestore_interactor.IMedicoDetailFirestoreInteractor;

import javax.inject.Inject;

public class MedicoDetailFirestorePresenter implements IMedicoDetailFirestoreContract.IPresenter {

    IMedicoDetailFirestoreContract.IView view;

    @Inject
    IMedicoDetailFirestoreInteractor interactor;

    @Inject
    public MedicoDetailFirestorePresenter(){

    }

    @Override
    public void attachView(IMedicoDetailFirestoreContract.IView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void getMedico(String id) {
        if(id == null || id.isEmpty()){
            view.showError("No hay id de Medico");
            return;
        }
        view.showProgressBar();
        interactor.getMedico(id, task -> {
            if(isViewAttached()) {
                view.hideProgressBar();
                if (task.isSuccessful()) {
                    Medico medico = task.getResult().toObject(Medico.class);
                    view.getPostDetailSuccess(medico);
                } else {
                    view.showError(task.getException().getMessage());
                }
            }
        });
    }
}

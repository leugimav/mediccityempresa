package com.proyecto.sistemas.mediccityempresa.domain.create_medico_firestore_interactor;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentReference;
import com.proyecto.sistemas.mediccityempresa.data.entities.Medico;
import com.proyecto.sistemas.mediccityempresa.data.repository.IMedicoFirestoreRepository;

import javax.inject.Inject;

public class CreateMedicoFirestoreInteractorImpl implements ICreateMedicoFirestoreInteractor {

    private final IMedicoFirestoreRepository repository;

    @Inject
    public CreateMedicoFirestoreInteractorImpl(IMedicoFirestoreRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public void createMedico(Medico medico, OnCompleteListener<DocumentReference> onComplete) {
        repository.registrarMedico(medico, onComplete);
    }



}

package com.proyecto.sistemas.mediccityempresa.domain.create_post_firestore;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentReference;
import com.proyecto.sistemas.mediccityempresa.data.entities.Medico;
import com.proyecto.sistemas.mediccityempresa.data.repository.IMedicoFirestoreRepository;

import javax.inject.Inject;

public class CreateMedicoFirestoreInteractorImpl implements ICreateMedicoFirestoreInteractor {

    private final IMedicoFirestoreRepository repository;

    @Override
    public void createMedico(Medico medico, OnCompleteListener<DocumentReference> onComplete) {

    }

    @Inject
    public CreateMedicoFirestoreInteractorImpl(IMedicoFirestoreRepository repository)
    {
        this.repository = repository;
    }

}

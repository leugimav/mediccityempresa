package com.proyecto.sistemas.mediccityempresa.domain.medico_firestore_interactor;

import com.google.firebase.firestore.Query;
import com.proyecto.sistemas.mediccityempresa.data.repository.IMedicoFirestoreRepository;

import javax.inject.Inject;

public class MedicoFirestoreInteractorImpl implements IMedicoFirestoreInteractor {
    private final IMedicoFirestoreRepository repository;

    @Inject
    public MedicoFirestoreInteractorImpl(IMedicoFirestoreRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public Query getAllMedico() {
        return repository.getAllMedicos();
    }
}

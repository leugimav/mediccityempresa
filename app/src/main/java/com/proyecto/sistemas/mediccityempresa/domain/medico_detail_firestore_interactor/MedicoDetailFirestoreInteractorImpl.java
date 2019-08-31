package com.proyecto.sistemas.mediccityempresa.domain.medico_detail_firestore_interactor;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.proyecto.sistemas.mediccityempresa.data.repository.IMedicoFirestoreRepository;

import javax.inject.Inject;

public class MedicoDetailFirestoreInteractorImpl implements IMedicoDetailFirestoreInteractor {
    private final IMedicoFirestoreRepository repository;

    @Inject
    public MedicoDetailFirestoreInteractorImpl(IMedicoFirestoreRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public void getMedico(String uid, OnCompleteListener<DocumentSnapshot> onComplete) {
        repository.getMedico(uid,onComplete);
    }
}

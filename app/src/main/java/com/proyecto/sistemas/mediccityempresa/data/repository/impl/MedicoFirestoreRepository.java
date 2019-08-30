package com.proyecto.sistemas.mediccityempresa.data.repository.impl;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.proyecto.sistemas.mediccityempresa.data.entities.Medico;
import com.proyecto.sistemas.mediccityempresa.data.repository.IMedicoFirestoreRepository;
import com.proyecto.sistemas.mediccityempresa.presentation.utils.FirestoreConstants;

import javax.inject.Inject;

public class MedicoFirestoreRepository implements IMedicoFirestoreRepository {

    private final FirebaseFirestore firestore;

    @Inject
    public MedicoFirestoreRepository(FirebaseFirestore firestore) {
        this.firestore = firestore;
    }


    @Override
    public void getMedico(String uid, OnCompleteListener<DocumentSnapshot> onComplete) {
        firestore.collection(FirestoreConstants.COLLECTION_POSTS).document(uid).get().addOnCompleteListener(onComplete);
    }

    @Override
    public void registrarMedico(Medico medico, OnCompleteListener<DocumentReference> onComplete) {
        firestore.collection(FirestoreConstants.COLLECTION_POSTS).add(medico).addOnCompleteListener(onComplete);
    }

    @Override
    public Query getAllMedicos() {
        return firestore.collection(FirestoreConstants.COLLECTION_POSTS);
    }
}

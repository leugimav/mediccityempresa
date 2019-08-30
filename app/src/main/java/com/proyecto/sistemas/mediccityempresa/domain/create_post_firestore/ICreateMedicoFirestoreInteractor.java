package com.proyecto.sistemas.mediccityempresa.domain.create_post_firestore;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentReference;
import com.proyecto.sistemas.mediccityempresa.data.entities.Medico;

public interface ICreateMedicoFirestoreInteractor {

    void createMedico(Medico medico, OnCompleteListener<DocumentReference> onComplete);
}

package com.proyecto.sistemas.mediccityempresa.domain.medico_detail_firestore_interactor;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentSnapshot;

public interface IMedicoDetailFirestoreInteractor {
    void getMedico(String uid, OnCompleteListener<DocumentSnapshot> onComplete);
}

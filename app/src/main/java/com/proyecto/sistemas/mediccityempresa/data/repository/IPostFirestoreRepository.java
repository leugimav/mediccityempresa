package com.proyecto.sistemas.mediccityempresa.data.repository;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.proyecto.sistemas.mediccityempresa.data.entities.Medico;

public interface IPostFirestoreRepository {


    void getMedico(String uid,  OnCompleteListener<DocumentSnapshot> onComplete);
    void registrarMedico(Medico medico,OnCompleteListener<DocumentReference> onComplete);
    Query getAllMedicos();
}

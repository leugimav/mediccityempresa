package com.proyecto.sistemas.mediccityempresa.di.modules;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.proyecto.sistemas.mediccityempresa.data.repository.IPostFirestoreRepository;
import com.proyecto.sistemas.mediccityempresa.data.repository.impl.PostFirestoreRepositoryImpl;

import javax.inject.Singleton;

import dagger.Provides;

public class ApplicationModule {

    private Context mContext;

    public ApplicationModule(Context context) {
        this.mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

    @Provides @Singleton
    IPostFirestoreRepository providePostFirestoreRepository(PostFirestoreRepositoryImpl postFirestoreRepositoryImpl){
        return postFirestoreRepositoryImpl;
    }

    @Provides @Singleton
    FirebaseAuth provideFirebaseAuth(){
        return FirebaseAuth.getInstance();
    }

    @Provides @Singleton
    FirebaseFirestore provideFirestore(){ return FirebaseFirestore.getInstance(); }

}

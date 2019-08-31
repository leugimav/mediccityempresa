package com.proyecto.sistemas.mediccityempresa.di.modules;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.proyecto.sistemas.mediccityempresa.data.repository.IMedicoFirestoreRepository;
import com.proyecto.sistemas.mediccityempresa.data.repository.impl.MedicoFirestoreRepositoryImp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
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
    IMedicoFirestoreRepository providePostsFirestoreInteractorprovidePostFirestoreRepository(MedicoFirestoreRepositoryImp postFirestoreRepositoryImpl){
        return postFirestoreRepositoryImpl;
    }

    @Provides @Singleton
    FirebaseAuth provideFirebaseAuth(){
        return FirebaseAuth.getInstance();
    }

    @Provides @Singleton
    FirebaseFirestore provideFirestore(){ return FirebaseFirestore.getInstance(); }

}

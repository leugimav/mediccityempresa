package com.proyecto.sistemas.mediccityempresa.di.modules;

import com.proyecto.sistemas.mediccityempresa.data.repository.IMedicoFirestoreRepository;
import com.proyecto.sistemas.mediccityempresa.di.scope.PerActivity;
import com.proyecto.sistemas.mediccityempresa.domain.create_medico_firestore_interactor.CreateMedicoFirestoreInteractorImpl;
import com.proyecto.sistemas.mediccityempresa.domain.create_medico_firestore_interactor.ICreateMedicoFirestoreInteractor;
import com.proyecto.sistemas.mediccityempresa.domain.medico_detail_firestore_interactor.IMedicoDetailFirestoreInteractor;
import com.proyecto.sistemas.mediccityempresa.domain.medico_detail_firestore_interactor.MedicoDetailFirestoreInteractorImpl;
import com.proyecto.sistemas.mediccityempresa.domain.medico_firestore_interactor.IMedicoFirestoreInteractor;
import com.proyecto.sistemas.mediccityempresa.domain.medico_firestore_interactor.MedicoFirestoreInteractorImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {


    //Creacion Medico
    @PerActivity
    @Provides
    ICreateMedicoFirestoreInteractor provideCreatePostFirestoreInteractor(IMedicoFirestoreRepository repository){
        return new CreateMedicoFirestoreInteractorImpl(repository);
    }

    //Obtener Detalle Medico
    @PerActivity
    @Provides
    IMedicoDetailFirestoreInteractor providePostDetailFirestoreInteractor(IMedicoFirestoreRepository repository){
        return new MedicoDetailFirestoreInteractorImpl(repository);
    }

    //Lista Medico
    @PerActivity
    @Provides
    IMedicoFirestoreInteractor providePostsFirestoreInteractor(IMedicoFirestoreRepository repository){
        return new MedicoFirestoreInteractorImpl(repository);
    }

}

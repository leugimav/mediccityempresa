package com.proyecto.sistemas.mediccityempresa.di.modules;

import com.proyecto.sistemas.mediccityempresa.data.repository.IMedicoFirestoreRepository;
import com.proyecto.sistemas.mediccityempresa.di.scope.PerActivity;
import com.proyecto.sistemas.mediccityempresa.domain.create_post_firestore.CreateMedicoFirestoreInteractorImpl;
import com.proyecto.sistemas.mediccityempresa.domain.create_post_firestore.ICreateMedicoFirestoreInteractor;

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
    IPostDetailFirestoreInteractor providePostDetailFirestoreInteractor(IMedicoFirestoreRepository repository){
        return new PostDetailFirestoreInteractorImpl(repository);
    }

    //Lista Medico
    @PerActivity
    @Provides
    IPostsFirestoreInteractor providePostsFirestoreInteractor(IMedicoFirestoreRepository repository){
        return new PostFirestoreInteractorImpl(repository);
    }

}

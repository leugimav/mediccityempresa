package com.proyecto.sistemas.mediccityempresa.di.components;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.proyecto.sistemas.mediccityempresa.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Context exposeContext();
    FirebaseAuth exposeFirebaseAuth();
    FirebaseFirestore exposeFirestore();
}

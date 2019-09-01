package com.proyecto.sistemas.mediccityempresa.di.components;

import com.proyecto.sistemas.mediccityempresa.presentation.activities.detalle_medico_firestore.VisualizarDatos;
import com.proyecto.sistemas.mediccityempresa.di.modules.PresentationModule;
import com.proyecto.sistemas.mediccityempresa.di.scope.PerActivity;
import com.proyecto.sistemas.mediccityempresa.presentation.activities.create_medico_firestore.AgregarCuentaMedico_2;
import com.proyecto.sistemas.mediccityempresa.presentation.activities.login.MainActivity;

import dagger.Component;

@PerActivity
@Component(modules = PresentationModule.class,dependencies = ApplicationComponent.class)
public interface PresentationComponent {
    void inject(MainActivity loginActivity);
    void inject(AgregarCuentaMedico_2 agregarCuentaMedico_2);
    void inject(VisualizarDatos visualizarDatos);
}

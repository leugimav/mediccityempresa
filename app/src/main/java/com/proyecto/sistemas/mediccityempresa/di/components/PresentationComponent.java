package com.proyecto.sistemas.mediccityempresa.di.components;

import com.proyecto.sistemas.mediccityempresa.di.modules.PresentationModule;
import com.proyecto.sistemas.mediccityempresa.di.scope.PerActivity;

import dagger.Component;

@PerActivity
@Component(modules = PresentationModule.class,dependencies = ApplicationComponent.class)
public interface PresentationComponent {
}

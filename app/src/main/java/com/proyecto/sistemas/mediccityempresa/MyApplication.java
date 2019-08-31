package com.proyecto.sistemas.mediccityempresa;

import android.app.Application;

import com.proyecto.sistemas.mediccityempresa.di.components.ApplicationComponent;
import com.proyecto.sistemas.mediccityempresa.di.components.DaggerApplicationComponent;
import com.proyecto.sistemas.mediccityempresa.di.modules.ApplicationModule;


public class MyApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        inicializarApplicationComponent();
    }

    void inicializarApplicationComponent(){
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}

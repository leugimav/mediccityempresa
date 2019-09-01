package com.proyecto.sistemas.mediccityempresa.presentation.activities.detalle_medico_firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.proyecto.sistemas.mediccityempresa.R;
import com.proyecto.sistemas.mediccityempresa.base.BaseActivity;
import com.proyecto.sistemas.mediccityempresa.data.entities.Medico;
import com.proyecto.sistemas.mediccityempresa.di.components.DaggerPresentationComponent;
import com.proyecto.sistemas.mediccityempresa.di.modules.PresentationModule;
import com.proyecto.sistemas.mediccityempresa.domain.medico_detail_firestore_interactor.IMedicoDetailFirestoreInteractor;
import com.proyecto.sistemas.mediccityempresa.presentation.activities.login.Principal;

import javax.inject.Inject;

public class VisualizarDatos extends BaseActivity implements IMedicoDetailFirestoreContract.IView {

    EditText txtNombres;
    EditText txtApellidos;
    EditText txtColegiatura;
    EditText txtEspPrincipal;
    EditText txtEspSecundaria;
    EditText txtCentroEstudios;
    EditText txtUbicacion;
    Button btnCerrarSesion;
    String UUID;
    private FirebaseAuth firebaseSignOut;

    @Inject
    MedicoDetailFirestorePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseSignOut = FirebaseAuth.getInstance();
        UUID = firebaseSignOut.getCurrentUser().getUid();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_visualizar_datos;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        presenter.attachView(this);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        txtNombres = findViewById(R.id.txtNombres);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtColegiatura = findViewById(R.id.txtColegiatura);
        txtEspPrincipal = findViewById(R.id.txtEspPrincipal);
        txtEspSecundaria = findViewById(R.id.txtEspSecundaria);
        txtCentroEstudios = findViewById(R.id.txtCentroEstudios);
        txtUbicacion = findViewById(R.id.txtUbicacion);
        btnCerrarSesion =  findViewById(R.id.btnCerrarSesion);

        presenter.getMedico(getIntent().getStringExtra("medico_id"));

        setListeners();
    }


    @Override
    protected void resolveDaggerDependency() {
        DaggerPresentationComponent.builder()
                .applicationComponent(getApplicationComponent())
                .presentationModule(new PresentationModule())
                .build().inject(this);
    }


    @Override
    public void showError(String errorMsg) {
        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        showDialog("Cargando Datos");
    }

    @Override
    public void hideProgressBar() {
        hideDialog();
    }

    @Override
    public void getPostDetailSuccess(Medico medico) {
        txtNombres.setText(medico.getNombres());
        txtApellidos.setText(medico.getApellidos());
        txtColegiatura.setText(medico.getNroColegiatura());
        txtEspPrincipal.setText(medico.getEspPrincipal());
        txtEspSecundaria.setText(medico.getEspSecundaria());
        txtCentroEstudios.setText(medico.getCentroEstudios());
        txtUbicacion.setText("Latitud : " + medico.getLatitud() + "Longitud : " + medico.getLongitud());

    }


    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onDetachedFromWindow() {
        presenter.detachView();
        super.onDetachedFromWindow();
    }

    private void setListeners(){
        btnCerrarSesion.setOnClickListener(v -> {
            firebaseSignOut.signOut();
            startActivity(new Intent(VisualizarDatos.this, Principal.class));
            finish();

        });
    }
/*
    @Override
    protected void onStart() {

        super.onStart();

            UUID = firebaseSignOut.getCurrentUser().getUid();


    }*/
}

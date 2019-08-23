package com.proyecto.sistemas.mediccityempresa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class VisualizarDatos extends AppCompatActivity implements View.OnClickListener{

    private Button btnCerrarSesion;
    private FirebaseAuth firebaseSignOut;

    private EditText txtNombres;
    private EditText txtCentroEstudios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_datos);
        firebaseSignOut = FirebaseAuth.getInstance();

        btnCerrarSesion = (Button)findViewById(R.id.btnCerrarSesion);
        btnCerrarSesion.setOnClickListener(this);

        txtNombres = findViewById(R.id.txtNombres);
        txtCentroEstudios = findViewById(R.id.txtCentroEstudios);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnCerrarSesion:
                firebaseSignOut.signOut();
                startActivity(new Intent(VisualizarDatos.this,Principal.class));
                finish();
                break;

        }
    }

    
}

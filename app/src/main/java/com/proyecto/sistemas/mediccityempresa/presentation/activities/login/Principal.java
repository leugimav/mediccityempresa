package com.proyecto.sistemas.mediccityempresa.presentation.activities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.proyecto.sistemas.mediccityempresa.presentation.activities.create_medico_firestore.AgregarCuentaMedico;
import com.proyecto.sistemas.mediccityempresa.R;
import com.proyecto.sistemas.mediccityempresa.presentation.activities.detalle_medico_firestore.VisualizarDatos;

public class Principal extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Button btnNuevaCuenta;
    private Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        getWindow().setBackgroundDrawable(null);

        btnNuevaCuenta =(Button)findViewById(R.id.btnNuevaCuenta);
        btnIngresar = (Button)findViewById(R.id.btnIngresar);


        btnIngresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);
                finish();
            }
        });


        btnNuevaCuenta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AgregarCuentaMedico.class);

                startActivity(intent);
                finish();
            }
        });

        //Inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {

        super.onStart();
/*
        if(firebaseAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(Principal.this, VisualizarDatos.class));
            finish();

        }
*/
    }
}

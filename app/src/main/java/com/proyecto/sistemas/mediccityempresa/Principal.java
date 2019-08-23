package com.proyecto.sistemas.mediccityempresa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.proyecto.sistemas.mediccityempresa.R;

public class Principal extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    Button btnNuevaCuenta;
    Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

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

        if(firebaseAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(Principal.this,VisualizarDatos.class));
            finish();

        }

    }
}

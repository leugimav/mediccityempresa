package com.proyecto.sistemas.mediccityempresa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.proyecto.sistemas.mediccityempresa.presentation.activities.login.Principal;

public class VisualizarDatos extends AppCompatActivity implements View.OnClickListener{

    private Button btnCerrarSesion;
    private EditText txtNombres;
    private EditText txtCentroEstudios;

    private FirebaseAuth firebaseSignOut;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_datos);

        //
        firebaseSignOut = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        btnCerrarSesion = (Button)findViewById(R.id.btnCerrarSesion);
        btnCerrarSesion.setOnClickListener(this);

        txtNombres = findViewById(R.id.txtNombres);
        txtCentroEstudios = findViewById(R.id.txtCentroEstudios);

        getInformacionDatos();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnCerrarSesion:
                firebaseSignOut.signOut();
                startActivity(new Intent(VisualizarDatos.this, Principal.class));
                finish();
                break;

        }
    }

    private void getInformacionDatos()
    {
        String id = firebaseSignOut.getCurrentUser().getUid();
        databaseReference.child("Medico").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    String nombres = dataSnapshot.child("nombres").getValue().toString();
                    String centroestudios = dataSnapshot.child("centroEstudios").getValue().toString();

                    txtNombres.setText(nombres);
                    txtCentroEstudios.setText(centroestudios);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}

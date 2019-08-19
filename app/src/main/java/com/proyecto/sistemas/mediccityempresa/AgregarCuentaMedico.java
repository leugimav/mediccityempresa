package com.proyecto.sistemas.mediccityempresa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyecto.sistemas.mediccityempresa.R;

import java.util.UUID;

public class AgregarCuentaMedico extends AppCompatActivity {
    Button btnSiguiente;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;
    private EditText txtNombres;
    private EditText txtApellidos;
    private EditText txtCelular;
    private EditText txtNroColegiatura;
    private EditText txtCorreo;
    private EditText txtClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cuenta_medico);

        //Inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //Se referencia los Views
        txtCorreo = (EditText)findViewById(R.id.txtCorreo);
        txtClave = (EditText)findViewById(R.id.txtClave);
        progressDialog = new ProgressDialog(this);
        txtNombres = (EditText)findViewById(R.id.txtNombres);
        txtApellidos = (EditText)findViewById(R.id.txtApellidos);
        txtCelular = (EditText)findViewById(R.id.txtCelular);
        txtNroColegiatura = (EditText)findViewById(R.id.txtNroColegiatura);

        inicializarFirebaseDatabase();

        btnSiguiente = (Button)findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registrarCuenta();
                /*
                Intent intent = new Intent(getApplicationContext(), AgregarCuentaMedico_2.class);

                startActivity(intent);
                finish();
                */
            }
        });


    }

    private void inicializarFirebaseDatabase()
    {
        //FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

    private void registrarCuenta() {
        //Se obtiene el Email y Contraseña
        String Email = txtCorreo.getText().toString();
        String Password = txtClave.getText().toString();

        if(TextUtils.isEmpty(Email)){
            Toast.makeText(this,"Ingresar Email Válido",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(Password)){
            Toast.makeText(this,"Ingresar Password Válido",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Realizando Registro ...");
        progressDialog.show();

        //Creando Usuario
        firebaseAuth.createUserWithEmailAndPassword(Email,Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(AgregarCuentaMedico.this,"Se Registró el Usuario con el Email: "+ txtCorreo.getText(),Toast.LENGTH_LONG).show();
                        }else{

                            if (task.getException() instanceof FirebaseAuthUserCollisionException)
                            {//si se presenta una colisión
                                Toast.makeText(AgregarCuentaMedico.this, "El Usuario ya se encuentra registrado", Toast.LENGTH_SHORT).show();
                            } else {
                                //Toast.makeText(MainActivity.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                                Toast.makeText(AgregarCuentaMedico.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                Log.e("Activity","Problemas Error: " + task.getException().getMessage());

                            }

                        }
                        progressDialog.dismiss();
                    }
                });


        Medico medico = new Medico();
        medico.setUid(UUID.randomUUID().toString());
        medico.setNombres(txtNombres.getText().toString());
        medico.setApellidos(txtApellidos.getText().toString());
        medico.setCelular(txtCelular.getText().toString());
        medico.setNroColegiatura(txtNroColegiatura.getText().toString());
        databaseReference.child("Medico").child(medico.getUid()).setValue(medico);
        Toast.makeText(this,"Agregado",Toast.LENGTH_LONG).show();
    }
}

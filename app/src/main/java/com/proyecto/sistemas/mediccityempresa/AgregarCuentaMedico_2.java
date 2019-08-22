package com.proyecto.sistemas.mediccityempresa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AgregarCuentaMedico_2 extends AppCompatActivity implements View.OnClickListener{

    private Button btnRegistrarMedico;
    private FirebaseAuth firebaseAuth;
   // private FirebaseDatabase firebaseDatabase;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;

    private String correo,clave,nombres,apellidos,celular,colegiatura;
    private EditText txtEspPrincipal;
    private EditText txtEspSecundaria;
    private EditText txtCentroEstudios;
    private EditText txtFinalizaEstudios;
    private EditText txtDetalles;
    private String Latitud; //EditText
    private String Longitud; //EditText

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cuenta_medico_2);

        //Inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //Se captura los datos enviados desde AgregarCuentaMedico.java
        correo = getIntent().getExtras().getString("correo");
        clave = getIntent().getExtras().getString("clave");
        nombres = getIntent().getExtras().getString("nombres");
        apellidos = getIntent().getExtras().getString("apellidos");
        celular = getIntent().getExtras().getString("celular");
        colegiatura = getIntent().getExtras().getString("colegiatura");

        //
        progressDialog = new ProgressDialog(this);

        //
        txtEspPrincipal = findViewById(R.id.txtEspPrincipal);
        txtEspSecundaria = findViewById(R.id.txtEspSecundaria);
        txtCentroEstudios = findViewById(R.id.txtCentroEstudios);
        txtFinalizaEstudios = findViewById(R.id.txtFinalizaEstudios);
        txtDetalles =  findViewById(R.id.txtDetalles);
        btnRegistrarMedico = findViewById(R.id.btnRegistrarMedico);
        btnRegistrarMedico.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnRegistrarMedico :
                registrarMedico();
                break;

        }
    }


    public void registrarMedico()
    {

        progressDialog.setMessage("Validando Usuario ...");
        progressDialog.show();

        //Creando Usuario
        firebaseAuth.createUserWithEmailAndPassword(correo,clave)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            //Toast.makeText(AgregarCuentaMedico_2.this,"Se Registró el Usuario con el Email: "+ correo,Toast.LENGTH_LONG).show();
                            //Se obtiene el Id de Autenticación del Usuario
                            String id = firebaseAuth.getUid();

                            Medico medico = new Medico();
                            //medico.setUid(UUID.randomUUID().toString());
                            //medico.setNombres(txtNombres.getText().toString());
                            //medico.setApellidos(txtApellidos.getText().toString());
                            //medico.setCelular(txtCelular.getText().toString());
                            //medico.setNroColegiatura(txtNroColegiatura.getText().toString());

                            medico.setUid(id);
                            medico.setCorreo(correo);
                            medico.setNombres(nombres);
                            medico.setApellidos(apellidos);
                            medico.setCelular(celular);
                            medico.setNroColegiatura(colegiatura);
                            medico.setEspPrincipal(txtEspPrincipal.getText().toString());
                            medico.setEspSecundaria(txtEspSecundaria.getText().toString());
                            medico.setCentroEstudios(txtCentroEstudios.getText().toString());
                            medico.setFinalizaEstudios(txtFinalizaEstudios.getText().toString());
                            medico.setDetalles(txtDetalles.getText().toString());
                            medico.setLatitud("15.152471");
                            medico.setLongitud("20.145475");
                            medico.setTipo("M"); //Medico

                            databaseReference.child("Medico").child(id).setValue(medico).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> taskdb) {
                                    if(taskdb.isSuccessful())
                                    {

                                        Toast.makeText(AgregarCuentaMedico_2.this,"Se Registró el Usuario con el Email: "+ correo,Toast.LENGTH_LONG).show();
                                        //LimpiarTextos();
                                        startActivity(new Intent(AgregarCuentaMedico_2.this,Principal.class));
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(AgregarCuentaMedico_2.this,"Hubo problemas al realizar el Registro",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        }else{

                            if (task.getException() instanceof FirebaseAuthUserCollisionException)
                            {//si se presenta una colisión
                                Toast.makeText(AgregarCuentaMedico_2.this, "El Usuario ya se encuentra registrado", Toast.LENGTH_SHORT).show();
                            } else {
                                //Toast.makeText(MainActivity.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                                Toast.makeText(AgregarCuentaMedico_2.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                Log.e("Activity","Problemas Error: " + task.getException().getMessage());

                            }

                        }
                        progressDialog.dismiss();
                    }
                });
    }


}

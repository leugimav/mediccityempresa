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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.proyecto.sistemas.mediccityempresa.R;

public class MainActivity extends AppCompatActivity {

    Button btnIngresar;
    Button btnRecuperarClave;
    private FirebaseAuth firebaseAuth;
    private EditText txtEmail;
    private EditText txtPassword;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //Se referencia los Views
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        progressDialog = new ProgressDialog(this);


        btnIngresar =(Button)findViewById(R.id.btnIngresar);
        btnRecuperarClave = (Button)findViewById(R.id.btnRecuperarClave);

        btnIngresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                ingresarCuenta();
                /*
                Intent intent = new Intent(getApplicationContext(), UbicacionDoctores.class);

                startActivity(intent);
                finish();
                */
            }
        });

        btnRecuperarClave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(getApplicationContext(), RecuperarContrasena.class);

                startActivity(intent);
                finish();
            }
        });


    }

    private void ingresarCuenta()
    {
        //Se obtiene el Email y Contraseña
        String Email = txtEmail.getText().toString();
        String Password = txtPassword.getText().toString();

        if(TextUtils.isEmpty(Email)){
            Toast.makeText(this,"Ingresar Email Válido",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(Password)){
            Toast.makeText(this,"Ingresar Password Válido",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Validando Usuario ...");
        progressDialog.show();

        //Creando Usuario
        firebaseAuth.signInWithEmailAndPassword(Email,Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(MainActivity.this,"Accesos ingresados correctos"+ txtEmail.getText(),Toast.LENGTH_LONG).show();
                        }else{

                            if (task.getException() instanceof FirebaseAuthUserCollisionException)
                            {//si se presenta una colisión
                                Toast.makeText(MainActivity.this, "El Usuario ya se encuentra registrado", Toast.LENGTH_SHORT).show();
                            } else {
                                //Toast.makeText(MainActivity.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                                Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                Log.e("Activity","Problemas Error: " + task.getException().getMessage());

                            }

                        }
                        progressDialog.dismiss();
                    }
                });

    }
    }




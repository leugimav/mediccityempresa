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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnIngresar;
    Button btnRecuperarClave;
    private FirebaseAuth firebaseAuth;
    private EditText txtEmail;
    private EditText txtPassword;
    private ProgressDialog progressDialog;

    private String Email;
    private String Password;

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

        btnIngresar.setOnClickListener(this);
        btnRecuperarClave.setOnClickListener(this);
        /*
        btnIngresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                ingresarCuenta();

                //Intent intent = new Intent(getApplicationContext(), UbicacionDoctores.class);

                //startActivity(intent);
                //finish();

            }
        });
        */

        /*btnRecuperarClave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(getApplicationContext(), RecuperarContrasena.class);

                startActivity(intent);
                finish();
            }
        //});
        */

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnIngresar:
                ingresarCuenta();
                break;

            case R.id.btnRecuperarClave:
                recuperarClave();
                break;

        }
    }
    /*
    @Override
    protected void onStart() {
        super.onStart();

        if(firebaseAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(MainActivity.this,VisualizarDatos.class));
            finish();

        }

    }


*/
    private void ingresarCuenta()
    {
        //Se obtiene el Email y Contraseña
        Email = txtEmail.getText().toString();
        Password = txtPassword.getText().toString();

        if(!TextUtils.isEmpty(Email) && !TextUtils.isEmpty(Password)  ){
            LoginUsuario();
        }
        else
        {
            Toast.makeText(this,"Completar los Datos del Usuario y Password",Toast.LENGTH_LONG).show();
            return;
        }


    }

    private void LoginUsuario()
    {

        progressDialog.setMessage("Validando Usuario ...");
        progressDialog.show();

        //Creando Usuario
        firebaseAuth.signInWithEmailAndPassword(Email,Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(MainActivity.this,VisualizarDatos.class));
                            //startActivity(new Intent(MainActivity.this,UbicacionDoctores.class));
                            finish();
                            //Toast.makeText(MainActivity.this,"Accesos ingresados correctos"+ txtEmail.getText(),Toast.LENGTH_LONG).show();
                        }else{

                            Toast.makeText(MainActivity.this,"No se pudo inicar sesión - " + task.getException().getMessage(),Toast.LENGTH_LONG).show();

                            }

                        }

                });
        progressDialog.dismiss();
    }

    private void recuperarClave()
    {
        startActivity(new Intent(MainActivity.this,RecuperarContrasena.class));

    }

}




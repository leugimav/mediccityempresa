package com.proyecto.sistemas.mediccityempresa.presentation.activities.create_medico_firestore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyecto.sistemas.mediccityempresa.R;

public class AgregarCuentaMedico extends AppCompatActivity implements View.OnClickListener {
    private Button btnSiguiente;
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

        //Se referencia los Views
        txtCorreo = (EditText)findViewById(R.id.txtCorreo);
        txtClave = (EditText)findViewById(R.id.txtClave);
        progressDialog = new ProgressDialog(this);
        txtNombres = (EditText)findViewById(R.id.txtNombres);
        txtApellidos = (EditText)findViewById(R.id.txtApellidos);
        txtCelular = (EditText)findViewById(R.id.txtCelular);
        txtNroColegiatura = (EditText)findViewById(R.id.txtNroColegiatura);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnSiguiente:

                //Se realiza la validación de los datos ingresados
                if(!TextUtils.isEmpty(txtCorreo.getText().toString()) && !TextUtils.isEmpty(txtClave.getText().toString()) &&
                        !TextUtils.isEmpty(txtClave.getText().toString()) && !TextUtils.isEmpty(txtNombres.getText().toString()) &&
                        !TextUtils.isEmpty(txtApellidos.getText().toString()) && !TextUtils.isEmpty(txtCelular.getText().toString()) &&
                        !TextUtils.isEmpty(txtNroColegiatura.getText().toString()))
                {
                    Intent intent = new Intent(getApplicationContext(), AgregarCuentaMedico_2.class);
                    intent.putExtra("correo", txtCorreo.getText().toString());
                    intent.putExtra("clave",  txtClave.getText().toString());
                    intent.putExtra("nombres", txtNombres.getText().toString());
                    intent.putExtra("apellidos", txtApellidos.getText().toString());
                    intent.putExtra("celular", txtCelular.getText().toString());
                    intent.putExtra("colegiatura", txtNroColegiatura.getText().toString());
                    startActivity(intent);
                }
                else
                {
                    if(txtClave.getText().toString().length() < 6)
                    {
                        Toast.makeText(this, "La Clave debe tener al menos 6 caracteres", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(this, "Debe Ingresar Todos los Datos Completos", Toast.LENGTH_LONG).show();
                    }
                }

            break;

        }

    }


}

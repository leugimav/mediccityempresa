package com.proyecto.sistemas.mediccityempresa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.proyecto.sistemas.mediccityempresa.R;

public class RecuperarContrasena extends AppCompatActivity {

    private EditText txtRecuperarClave;
    private Button btnRecuperarClave;
    private String email;
    private FirebaseAuth firebaseRec;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena);

        txtRecuperarClave = (EditText)findViewById(R.id.txtRecuperarClave);
        btnRecuperarClave =  (Button)findViewById(R.id.btnRecuperarClave);

        firebaseRec = FirebaseAuth.getInstance();

        progress = new ProgressDialog(this);

        btnRecuperarClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = txtRecuperarClave.getText().toString();
                if(!email.isEmpty())
                {
                    progress.setMessage("Espere un momento ..");
                    progress.setCanceledOnTouchOutside(false);
                    progress.show();
                    ResetearClave();
                }
                else
                {
                    Toast.makeText(RecuperarContrasena.this,"Debe ingresar el correo",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void ResetearClave()
    {
        firebaseRec.setLanguageCode("es");
        firebaseRec.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(RecuperarContrasena.this,"Se ha enviado un correo para reestablecer contraseña",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(RecuperarContrasena.this,"No se pudo enviar el correo para reestrablecer contraseña",Toast.LENGTH_SHORT).show();
                }
                progress.dismiss();
            }
        });
    }

}

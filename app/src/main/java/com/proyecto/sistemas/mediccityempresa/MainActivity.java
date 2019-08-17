package com.proyecto.sistemas.mediccityempresa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.proyecto.sistemas.mediccityempresa.R;

public class MainActivity extends AppCompatActivity {

    Button btnIngresar;
    Button btnRecuperarClave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIngresar =(Button)findViewById(R.id.btnIngresar);
        btnRecuperarClave = (Button)findViewById(R.id.btnRecuperarClave);

        btnIngresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), UbicacionDoctores.class);

                startActivity(intent);
                finish();
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
}

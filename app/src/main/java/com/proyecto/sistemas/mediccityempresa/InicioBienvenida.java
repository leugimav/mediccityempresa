package com.proyecto.sistemas.mediccityempresa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class InicioBienvenida extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_inicio_bienvenida);

        /*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(InicioBienvenida.this,MainActivity.class);
                startActivity(intent);
            }
        },3000);*/


        //Intent intent = new Intent(this,MainActivity.class);
        Intent intent = new Intent(this,Principal.class);
        startActivity(intent);
        finish();
    }
}

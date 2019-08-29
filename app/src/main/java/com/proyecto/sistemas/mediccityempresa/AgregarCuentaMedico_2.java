package com.proyecto.sistemas.mediccityempresa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyecto.sistemas.mediccityempresa.data.entities.Medico;


public class AgregarCuentaMedico_2 extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    //Firebase
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;

    //Permisos
    private FusedLocationProviderClient fusedLocationProviderClient;

    //Mapa
    GoogleMap mMap;
    MapView ubicacionDoctor;
    LatLng latitudLongitud;

    //
    private String correo, clave, nombres, apellidos, celular, colegiatura;
    private EditText txtEspPrincipal;
    private EditText txtEspSecundaria;
    private EditText txtCentroEstudios;
    private EditText txtFinalizaEstudios;
    private EditText txtDetalles;
    private String Latitud; //EditText
    private String Longitud; //EditText

    private Button btnRegistrarMedico;

    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS;

    @Override
    protected void onResume() {
        super.onResume();
        ubicacionDoctor.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ubicacionDoctor.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ubicacionDoctor.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cuenta_medico_2);

        //Inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //Inicializamos Mapa - Location
        ubicacionDoctor = (MapView)findViewById(R.id.mapView);
        ubicacionDoctor.onCreate(savedInstanceState);


        //googleMap = ubicacionDoctor.getMapAsync(this);
        ubicacionDoctor.getMapAsync(this);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        //UbicacionActual();


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
        txtDetalles = findViewById(R.id.txtDetalles);
        btnRegistrarMedico = findViewById(R.id.btnRegistrarMedico);
        btnRegistrarMedico.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegistrarMedico:
                registrarMedico();
                break;

        }
    }


    public void registrarMedico() {

        progressDialog.setMessage("Validando Usuario ...");
        progressDialog.show();

        //Creando Usuario
        firebaseAuth.createUserWithEmailAndPassword(correo, clave)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

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
                            medico.setLatitud(Latitud);
                            medico.setLongitud(Longitud);
                            medico.setTipo("M"); //Medico

                            databaseReference.child("Medico").child(id).setValue(medico).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> taskdb) {
                                    if (taskdb.isSuccessful()) {

                                        Toast.makeText(AgregarCuentaMedico_2.this, "Se Registró el Usuario con el Email: " + correo, Toast.LENGTH_LONG).show();
                                        //LimpiarTextos();
                                        //startActivity(new Intent(AgregarCuentaMedico_2.this, Principal.class));
                                        startActivity(new Intent(AgregarCuentaMedico_2.this, VisualizarDatos.class));
                                        finish();
                                    } else {
                                        Toast.makeText(AgregarCuentaMedico_2.this, "Hubo problemas al realizar el Registro", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        } else {

                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                                Toast.makeText(AgregarCuentaMedico_2.this, "El Usuario ya se encuentra registrado", Toast.LENGTH_SHORT).show();
                            } else {
                                //Toast.makeText(MainActivity.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                                Toast.makeText(AgregarCuentaMedico_2.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                Log.e("Activity", "Problemas Error: " + task.getException().getMessage());

                            }

                        }
                        progressDialog.dismiss();
                    }
                });
    }


    public void UbicacionActual() {

        Log.e("Entrando ,,,,,,,, :" , "XXXXXXXXXXXXXX");

        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) &&
                (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED)) {

            ActivityCompat.requestPermissions(AgregarCuentaMedico_2.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            //Log.e("No hay permiso" , "No hay permiso.....");
            return;


        }

        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            latitudLongitud = new LatLng(location.getLatitude(),location.getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latitudLongitud, 15));
                            mMap.addMarker(new MarkerOptions()
                                    .title("Mi Ubicación")
                                    .position(latitudLongitud));

                          //  mMap.moveCamera(CameraUpdateFactory.newLatLng(latitudLongitud));
                            Log.e("Latitud :" , + location.getLatitude() + " Longitud : " + location.getLongitude());

                        }
                    }

                });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        //mMap = googleMap;
        //if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        //        != PackageManager.PERMISSION_GRANTED ) {
        //    return;
        //}

        //mMap.setMyLocationEnabled(true);
        //LatLng referenciaLatLng= new LatLng(-12.117895, -77.025463);
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(referenciaLatLng, 15));//18

        mMap = googleMap;

        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) &&
                (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED)) {

            ActivityCompat.requestPermissions(AgregarCuentaMedico_2.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            //Log.e("No hay permiso" , "No hay permiso.....");
            return;


        }

        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            latitudLongitud = new LatLng(location.getLatitude(),location.getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latitudLongitud, 15));
                            mMap.addMarker(new MarkerOptions()
                                    .title("Mi Ubicación")
                                    .position(latitudLongitud)
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                            //  mMap.moveCamera(CameraUpdateFactory.newLatLng(latitudLongitud));
                            Latitud =  Double.toString(location.getLatitude()) ;
                            Longitud = Double.toString(location.getLongitude());
                            Log.e("Latitud :" , + location.getLatitude() + " Longitud : " + location.getLongitude());

                        }
                    }

                });

    }



}


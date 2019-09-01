package com.proyecto.sistemas.mediccityempresa.presentation.activities.create_medico_firestore;

import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.proyecto.sistemas.mediccityempresa.R;
import com.proyecto.sistemas.mediccityempresa.presentation.activities.detalle_medico_firestore.VisualizarDatos;
import com.proyecto.sistemas.mediccityempresa.base.BaseActivity;
import com.proyecto.sistemas.mediccityempresa.di.components.DaggerPresentationComponent;
import com.proyecto.sistemas.mediccityempresa.di.modules.PresentationModule;

import javax.inject.Inject;


public class AgregarCuentaMedico_2 extends BaseActivity implements IRegisterContract.IView, OnMapReadyCallback {

    //Firebase
    //private FirebaseAuth firebaseAuth;
    //private ProgressDialog progressDialog;
    //private DatabaseReference databaseReference;

    //Permisos
    //private FusedLocationProviderClient fusedLocationProviderClient;

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
    private Button btnRegistrarMedico;
    private String Latitud;
    private String Longitud;
    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS;

    //Permisos
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Inject
    RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_agregar_cuenta_medico_2;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        presenter.attachView(this);
        getWindow().setBackgroundDrawable(null);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        txtEspPrincipal = findViewById(R.id.txtEspPrincipal);
        txtEspSecundaria = findViewById(R.id.txtEspSecundaria);
        txtCentroEstudios = findViewById(R.id.txtCentroEstudios);
        txtFinalizaEstudios = findViewById(R.id.txtFinalizaEstudios);
        txtDetalles = findViewById(R.id.txtDetalles);
        btnRegistrarMedico = findViewById(R.id.btnRegistrarMedico);

        //Inicializamos Mapa - Location
        ubicacionDoctor = (MapView)findViewById(R.id.mapView);
        ubicacionDoctor.onCreate(savedInstanceState);
        ubicacionDoctor.getMapAsync(this);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        //Se captura los datos enviados desde AgregarCuentaMedico.java
        correo = getIntent().getExtras().getString("correo");
        clave = getIntent().getExtras().getString("clave");
        nombres = getIntent().getExtras().getString("nombres");
        apellidos = getIntent().getExtras().getString("apellidos");
        celular = getIntent().getExtras().getString("celular");
        colegiatura = getIntent().getExtras().getString("colegiatura");


        setListeners();
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerPresentationComponent.builder()
                .applicationComponent(getApplicationComponent())
                .presentationModule(new PresentationModule())
                .build().inject(this);
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog() {
        showDialog("Cargando");
    }

    @Override
    public void hideProgressDialog() {
        hideDialog();
    }

    @Override
    public void goToMenu(String uid) {
        Intent intent = new Intent(getApplicationContext() , VisualizarDatos.class);
        intent.putExtra("medico_id",uid);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //presenter.detachView();
        ubicacionDoctor.onDestroy();
    }

    @Override
    public void onDetachedFromWindow() {
        presenter.detachView();
        super.onDetachedFromWindow();
    }

    private void setListeners(){
        btnRegistrarMedico.setOnClickListener(v -> {
            String espPrincipal = txtEspPrincipal.getText().toString();
            String espSecundaria = txtEspSecundaria.getText().toString();
            String centroEstudios = txtCentroEstudios.getText().toString();
            String finalizaEstudios = txtFinalizaEstudios.getText().toString();
            String detalles = txtDetalles.getText().toString();
            String latitud = Latitud;
            String longitud = Longitud;
            String tipo = "M";
            presenter.register(correo,clave,nombres,apellidos,celular,colegiatura,espPrincipal,espSecundaria,centroEstudios,finalizaEstudios,detalles,latitud,longitud,tipo);
        });
    }





    @Override
    public void onSuccessCreate() {
        Toast.makeText(getApplicationContext(), "Se registró el Usuario", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ubicacionDoctor.onResume();
    }



    @Override
    protected void onPause() {
        super.onPause();
        ubicacionDoctor.onPause();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {


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
                                    .title(" Latitud : " + location.getLatitude() + " Longitud :" + location.getLongitude())
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


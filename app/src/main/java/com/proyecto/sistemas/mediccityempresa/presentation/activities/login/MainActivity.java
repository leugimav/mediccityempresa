package com.proyecto.sistemas.mediccityempresa.presentation.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.proyecto.sistemas.mediccityempresa.R;
import com.proyecto.sistemas.mediccityempresa.RecuperarContrasena;
import com.proyecto.sistemas.mediccityempresa.presentation.activities.detalle_medico_firestore.VisualizarDatos;
import com.proyecto.sistemas.mediccityempresa.base.BaseActivity;
import com.proyecto.sistemas.mediccityempresa.di.components.DaggerPresentationComponent;
import com.proyecto.sistemas.mediccityempresa.di.modules.PresentationModule;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements ILoginContract.IView {

    private Button btnIngresar;
    private Button btnRecuperarClave;
    private EditText txtEmail;
    private EditText txtPassword;

    @Inject
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.LoginTheme);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }


    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent)
    {
        super.onViewReady(savedInstanceState, intent);
        presenter.attachView(this);
        presenter.checkUserLogged();
        getWindow().setBackgroundDrawable(null);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnRecuperarClave = findViewById(R.id.btnRecuperarClave);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
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
    public void goToMenu() {

        Intent intent = new Intent(getApplicationContext() , VisualizarDatos.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }


    @Override
    public void onDetachedFromWindow() {
        presenter.detachView();
        super.onDetachedFromWindow();
    }

    private void setListeners() {
        btnIngresar.setOnClickListener(v -> {
            String username = txtEmail.getText().toString();
            String password = txtPassword.getText().toString();
            presenter.login(username,password);
        });
        btnRecuperarClave.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RecuperarContrasena.class);
            startActivity(intent);
        });
    }
}




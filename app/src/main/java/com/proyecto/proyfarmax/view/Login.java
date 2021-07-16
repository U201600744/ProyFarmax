package com.proyecto.proyfarmax.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyecto.proyfarmax.R;
import com.proyecto.proyfarmax.interfaces.LoginPresenter;
import com.proyecto.proyfarmax.presenter.LoginPresenterImpl;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText txtEmail, txtPassword;
    private LoginPresenter loginPresenter;
    private Button btnRecuperar,btnNuevoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        loginPresenter = new LoginPresenterImpl(this, firebaseAuth, databaseReference);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        btnRecuperar = findViewById(R.id.btnRecuperar);
        btnRecuperar.setOnClickListener(this);
        btnNuevoUsuario = findViewById(R.id.btnNuevoUsuario);
        btnNuevoUsuario.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                String email = txtEmail.getText().toString().trim();
                String pass = txtPassword.getText().toString().trim();
                loginPresenter.signInUser(email, pass);
                break;
            case R.id.btnRecuperar:
                loginPresenter.enviarRecuperarPassword();
            case R.id.btnNuevoUsuario:
                loginPresenter.enviarNuevoUsuario();
        }
    }
}
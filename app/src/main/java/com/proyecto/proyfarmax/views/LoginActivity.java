package com.proyecto.proyfarmax.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.proyecto.proyfarmax.R;

public class LoginActivity extends AppCompatActivity {


    Context mContext;
    EditText username, pass;
    Button login, register, recover;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        mAuth = FirebaseAuth.getInstance();
        {
            username = findViewById(R.id.txtUser);
            pass = findViewById(R.id.txtContrasena);
            login = findViewById(R.id.btnIngresar);
            register = findViewById(R.id.btnNuevoUsuario);
            recover = findViewById(R.id.btnOlvidarContraseña);
        }

        if (existSession()){
            Toast.makeText(mContext, "Ya existe sesion", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = "correo@correo.com";
                String password = "123456";
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(mContext, "Login Exitoso", Toast.LENGTH_SHORT).show();

                            Toast.makeText(mContext, ""+mAuth.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(mContext, "No existe Usuario Reg", Toast.LENGTH_SHORT).show();
                            crearusuario(email, password);
                        }
                    }
                });
            }
        });



    }

    private void crearusuario(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(mContext, "USuario Creado Correctamente", Toast.LENGTH_SHORT).show();
                    Toast.makeText(mContext, ""+mAuth.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean existSession() {
        boolean exist = false;
        if (mAuth.getCurrentUser() != null) {
            exist = true;
        }
        return exist;
    }
}
package com.proyecto.proyfarmax.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
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
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.proyecto.proyfarmax.R;
import com.proyecto.proyfarmax.WelcomeActivity;

public class LoginAdminActivity extends AppCompatActivity {

    private EditText txtUserAdmin;
    private EditText txtPassAdmin;
    private Button btnLoginAdmin, btnOlvidarPassAdmin;
    private String correo = "";
    private String pass = "";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        mAuth = FirebaseAuth.getInstance();

        txtUserAdmin = findViewById(R.id.txtUserAdmin);
        txtPassAdmin = findViewById(R.id.txtPassAdmin);
        btnLoginAdmin = findViewById(R.id.btnLoginAdmin);
        btnLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correo = txtUserAdmin.getText().toString();
                pass = txtPassAdmin.getText().toString();

                if (!correo.isEmpty() && !pass.isEmpty()){
                    inicioSesion();
                }else {
                    AlertDialog.Builder ven = new AlertDialog.Builder(LoginAdminActivity.this);
                    ven.setTitle("Mensaje Informativo");
                    ven.setMessage("Debe ingresar Correo y Contraseña");
                    ven.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(LoginAdminActivity.this, LoginAdminActivity.class);
                            startActivity(intent);
                        }
                    });
                    ven.create().show();
                }
            }
        });

        btnOlvidarPassAdmin = findViewById(R.id.btnOlvidarPassAdmin);
        btnOlvidarPassAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginAdminActivity.this, ResetContrasenaActivity.class));
            }
        });
    }

    private void inicioSesion() {
        mAuth.signInWithEmailAndPassword(correo, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(LoginAdminActivity.this, HomeAdminActivity.class));
                    finish();
                }else {
                    AlertDialog.Builder ven = new AlertDialog.Builder(LoginAdminActivity.this);
                    ven.setTitle("Mensaje Informativo");
                    ven.setMessage("No se pudo iniciar sesión.\n"+
                            "\nVerifique los datos ingresados y vuelva a intentarlo");
                    ven.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(LoginAdminActivity.this, LoginAdminActivity.class);
                            startActivity(intent);
                        }
                    });
                    ven.create().show();
                }
            }
        });
    }
}
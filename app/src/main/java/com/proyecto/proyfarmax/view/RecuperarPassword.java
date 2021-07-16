package com.proyecto.proyfarmax.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.proyecto.proyfarmax.R;
import com.proyecto.proyfarmax.interfaces.LoginView;

public class RecuperarPassword extends AppCompatActivity {
    private EditText txtEmailRecup;
    private Button btnEnviarEmail;
    private String email = "";
    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_password);
        asignarReferencias();
    }

    private void asignarReferencias() {
        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);
        txtEmailRecup = findViewById(R.id.txtEmailRecup);
        btnEnviarEmail = findViewById(R.id.btnEnviarEmail);
        btnEnviarEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = txtEmailRecup.getText().toString();
                if (!email.isEmpty()) {
                    mDialog.setMessage("Espere un momento...");
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                    resetPassword();
                } else {
                    Toast.makeText(RecuperarPassword.this, "Debe ingresar el Email", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void resetPassword() {
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RecuperarPassword.this, "Se ha enviado un correo para restablecer su contraseña", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RecuperarPassword.this, "No se pudo enviar el correo para restablecer su contraseña", Toast.LENGTH_SHORT).show();
                }
                mDialog.dismiss();
                Intent intent = new Intent(RecuperarPassword.this, Login.class);
                RecuperarPassword.this.startActivity(intent);
            }
        });
    }
}
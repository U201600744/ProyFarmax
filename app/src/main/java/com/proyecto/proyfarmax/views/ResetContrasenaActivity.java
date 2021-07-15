package com.proyecto.proyfarmax.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
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

public class ResetContrasenaActivity extends AppCompatActivity {

    private EditText txtCorreoReset;
    private Button btnReset;
    private String correo = "";
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_contrasena);

        txtCorreoReset = findViewById(R.id.txtCorreoReset);
        btnReset = findViewById(R.id.btnReset);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correo = txtCorreoReset.getText().toString();
                if (!correo.isEmpty()){
                    progressDialog.setMessage("Enviando correo");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    resetPass();
                    AlertDialog.Builder ven = new AlertDialog.Builder(ResetContrasenaActivity.this);
                    ven.setTitle("Mensaje Informativo");
                    ven.setMessage("Se envió el correo para cambio de contraseña");
                    ven.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(ResetContrasenaActivity.this, LoginAdminActivity.class);
                            startActivity(intent);
                        }
                    });
                    progressDialog.dismiss();
                    ven.create().show();
                }else{
                    AlertDialog.Builder ven = new AlertDialog.Builder(ResetContrasenaActivity.this);
                    ven.setTitle("Mensaje Informativo");
                    ven.setMessage("Debe ingresar el correo");
                    ven.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(ResetContrasenaActivity.this, ResetContrasenaActivity.class);
                            startActivity(intent);
                        }
                    });
                    progressDialog.dismiss();
                    ven.create().show();
                }
            }
        });
    }

    private void resetPass() {
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(correo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    AlertDialog.Builder ven = new AlertDialog.Builder(ResetContrasenaActivity.this);
                    ven.setTitle("Mensaje Informativo");
                    ven.setMessage("Se envió el correo para reestablecer la contraseña");
                    ven.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(ResetContrasenaActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                    ven.create().show();
                }else {
                    AlertDialog.Builder ven = new AlertDialog.Builder(ResetContrasenaActivity.this);
                    ven.setTitle("Mensaje Informativo");
                    ven.setMessage("No se pudo enviar el correo para reestablecer la contraseña.\n"+
                            "\nVerifique el correo ingresado e intentelo más tarde");
                    ven.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(ResetContrasenaActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                    ven.create().show();
                }
            }
        });
    }
}
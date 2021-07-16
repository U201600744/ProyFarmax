package com.proyecto.proyfarmax.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyecto.proyfarmax.R;
import com.proyecto.proyfarmax.RegistrarUsuarioActivity;
import com.proyecto.proyfarmax.WelcomeActivity;

public class LoginActivity extends AppCompatActivity {


    // Context mContext;
    EditText username, pass;
    Button login, register, reset;
    TextView txtAdmin;

    String email = "";
    String password = "";

    FirebaseAuth mAuth;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // mContext = this;
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        username = findViewById(R.id.txtUserAdmin);
        pass = findViewById(R.id.txtPassAdmin);
        login = findViewById(R.id.btnLoginAdmin);
        register = findViewById(R.id.btnNuevoUsuario);
        reset = findViewById(R.id.btnOlvidarPassAdmin);
        txtAdmin = findViewById(R.id.txtAdmin);


        // if (existSession()){
        //     Toast.makeText(mContext, "Ya existe sesion", Toast.LENGTH_SHORT).show();



            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(LoginActivity.this, RegistrarUsuarioActivity.class));
                    finish();
                }
            });

       // if (existSession()){
       //     Toast.makeText(mContext, "Ya existe sesion", Toast.LENGTH_SHORT).show();
        //    mAuth.signOut();
        // }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String email = "correo@correo.com";
                //String password = "123456";

                email = username.getText().toString();
                password = pass.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    loginUser();
                } else {
                    Toast.makeText(LoginActivity.this, "complete los campos", Toast.LENGTH_SHORT).show();
                }

                //  mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                //     @Override
                //    public void onComplete(@NonNull Task<AuthResult> task) {
                //         if (task.isSuccessful()){
                //   Toast.makeText(mContext, "Login Exitoso", Toast.LENGTH_SHORT).show();

                //   Toast.makeText(mContext, ""+mAuth.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
                //     } else {

                //    Toast.makeText(mContext, "No existe Usuario Reg", Toast.LENGTH_SHORT).show();
                //    crearusuario(email, password);

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetContrasenaActivity.class));
            }
        });

        txtAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, LoginAdminActivity.class));
            }
        });
    }

    private void loginUser() {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "No se pudo iniciar sesion y que compruebe los datos", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private void crearusuario(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //  Toast.makeText(mContext, "USuario Creado Correctamente", Toast.LENGTH_SHORT).show();
                    //  Toast.makeText(mContext, ""+mAuth.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
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
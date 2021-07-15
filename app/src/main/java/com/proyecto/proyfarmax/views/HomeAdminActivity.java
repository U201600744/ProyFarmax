package com.proyecto.proyfarmax.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.proyecto.proyfarmax.R;

public class HomeAdminActivity extends AppCompatActivity {

    private Button btnCerrarSesionAdmin, btnNuevoAdmin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        mAuth = FirebaseAuth.getInstance();
        btnCerrarSesionAdmin = findViewById(R.id.btnCerrarSesionAdmin);
        btnCerrarSesionAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(HomeAdminActivity.this, LoginAdminActivity.class));
                finish();
            }
        });

        btnNuevoAdmin = findViewById(R.id.btnNuevoAdmin);
        btnNuevoAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAdminActivity.this, RegistrarUsuAdminActivity.class));
                finish();
            }
        });


    }
}
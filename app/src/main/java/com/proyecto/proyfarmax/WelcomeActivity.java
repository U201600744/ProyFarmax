package com.proyecto.proyfarmax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.proyecto.proyfarmax.views.LoginActivity;
import com.proyecto.proyfarmax.views.LoginAdminActivity;
import com.proyecto.proyfarmax.views.MainActivity;
import com.proyecto.proyfarmax.views.ResetContrasenaActivity;
import com.proyecto.proyfarmax.views.UpdateUserActivity;
import com.proyecto.proyfarmax.views.UpdateUserActivity;

public class WelcomeActivity extends AppCompatActivity {
    Button btnsingOut, btnActualizarDP, btnSeleccionarProd, btnEditarPersonas;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mAuth = FirebaseAuth.getInstance();
        btnEditarPersonas = (Button) findViewById(R.id.btnActualizarDP);
        btnEditarPersonas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( WelcomeActivity.this, UpdateUserActivity.class));
            }
        });


        btnsingOut = (Button)findViewById(R.id.btnsingOut);
        btnsingOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity( new Intent( WelcomeActivity.this, LoginActivity.class));
                finish();
            }
        });

        btnActualizarDP = findViewById(R.id.btnActualizarDP);
        btnActualizarDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, UpdateUserActivity.class));
            }
        });

        btnSeleccionarProd = findViewById(R.id.btnSeleccionarProd);
        btnSeleccionarProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
        });
    }
}
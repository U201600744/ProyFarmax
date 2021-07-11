package com.proyecto.proyfarmax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.proyecto.proyfarmax.views.LoginActivity;

public class WelcomeActivity extends AppCompatActivity {
    Button btnsingOut;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mAuth = FirebaseAuth.getInstance();

        btnsingOut = (Button)findViewById(R.id.btnsingOut);

        btnsingOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity( new Intent( WelcomeActivity.this, LoginActivity.class));
                finish();
            }
        });

    }
}
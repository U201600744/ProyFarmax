package com.proyecto.proyfarmax.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.proyecto.proyfarmax.AdaptadorUsuario;
import com.proyecto.proyfarmax.R;
import com.proyecto.proyfarmax.RegistrarUsuarioActivity;
import com.proyecto.proyfarmax.entities.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioActivity extends AppCompatActivity {
    RecyclerView recyclerUsuario;
    FloatingActionButton btnNuevo;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private List<Usuario> listaUsuario = new ArrayList<>();
    AdaptadorUsuario adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuario_main);
        asignarReferencia();
        inicializarFirebase();
        listarDatos();
    }

    private void listarDatos(){
        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                listaUsuario.clear();
                for(DataSnapshot item: snapshot.getChildren()){
                    Usuario u = item.getValue(Usuario.class);
                    listaUsuario.add(u);
                }
                adaptador = new AdaptadorUsuario(UsuarioActivity.this,listaUsuario);
                recyclerUsuario.setAdapter(adaptador);
                recyclerUsuario.setLayoutManager(new LinearLayoutManager(UsuarioActivity.this));
            }
            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
            }
        });
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void asignarReferencia() {
        recyclerUsuario = findViewById(R.id.recyclerUsuario);
            btnNuevo = findViewById(R.id.btnNuevo);
            btnNuevo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UsuarioActivity.this,RegistrarUsuarioActivity.class);
                    startActivity(intent);

                    //startActivity(new Intent(UsuarioActivity.this, RegistrarUsuarioActivity2.class));
                }
            });
    }
}

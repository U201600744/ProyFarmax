package com.proyecto.proyfarmax.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import com.proyecto.proyfarmax.AdaptadorProducto;
import com.proyecto.proyfarmax.ItemProducto;
import com.proyecto.proyfarmax.R;
import com.proyecto.proyfarmax.entities.Producto;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton btnNuevo;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    AdaptadorProducto adaptadorProducto;
    ArrayList<Producto> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
        inicializarFirebase();
        listarDatos();
    }

    private void asignarReferencias(){
        btnNuevo = findViewById(R.id.btnNuevo);
        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrarActivity.class);
                startActivity(intent);
            }
        });
    }

    private void listarDatos(){
        recyclerView = findViewById(R.id.itemProducto);
        databaseReference = FirebaseDatabase.getInstance().getReference("Producto");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adaptadorProducto = new AdaptadorProducto(this,list);
        recyclerView.setAdapter(adaptadorProducto);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Producto Producto = dataSnapshot.getValue(Producto.class);
                    list.add(Producto);
                }
                adaptadorProducto.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

}
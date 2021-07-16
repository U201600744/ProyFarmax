package com.proyecto.proyfarmax.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.proyecto.proyfarmax.R;
import com.proyecto.proyfarmax.entity.Producto;

import java.util.ArrayList;
import java.util.List;

public class ListaProductos extends AppCompatActivity {

    RecyclerView recyclerProductos;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView lblCantidad;
    ImageButton btnCarrito;
    List<Producto> listaProducto = new ArrayList<>();
    List<Producto> carroCompras = new ArrayList<>();
    AdaptadorListaProducto adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);
        asignarReferencias();
        inicializarFirebase();
        listarDatos();
    }


    private void listarDatos() {

        databaseReference.child("Producto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaProducto.clear();
                for (DataSnapshot item :snapshot.getChildren()){
                    Producto producto = item.getValue(Producto.class);
                    listaProducto.add(producto);
                }
                //adaptador = new ArrayAdapter<Persona>(MainActivity.this,android.R.layout.simple_list_item_1,listaPersona);
                //lstPersonas.setAdapter(adaptador);
                adaptador = new AdaptadorListaProducto(ListaProductos.this,lblCantidad,btnCarrito,listaProducto,carroCompras);
                recyclerProductos.setAdapter(adaptador);
                recyclerProductos.setLayoutManager(new LinearLayoutManager(ListaProductos.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void asignarReferencias() {
        btnCarrito = findViewById(R.id.btnCarrito);
        recyclerProductos = findViewById(R.id.recyclerCarrito);
        lblCantidad = findViewById(R.id.lblCantidad);
        recyclerProductos.setLayoutManager(new GridLayoutManager(ListaProductos.this,1));
        btnCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaProductos.this, CarritoCompras.class);
                startActivity(intent);
            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}
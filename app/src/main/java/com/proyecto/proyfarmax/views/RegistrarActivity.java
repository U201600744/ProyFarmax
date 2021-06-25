package com.proyecto.proyfarmax.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyecto.proyfarmax.R;
import com.proyecto.proyfarmax.entities.Producto;

import java.util.UUID;

public class RegistrarActivity extends AppCompatActivity {

    EditText txtNombreProducto, txtStock, txtPrecio, txtDetalle;
    Button btnRegistrar;
    String id;
    boolean registra = true;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String nombreProducto, stock, precio, detalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        asignarReferencias();
        recibirDatos();
        inicializarFirebase();
    }

    private void recibirDatos() {
        if(getIntent().hasExtra("id")) {
            registra = false;
            id = getIntent().getStringExtra("id");
            nombreProducto =getIntent().getStringExtra("nombre");
            stock =getIntent().getStringExtra("stock");
            //  precio = Double.parseDouble(getIntent().getStringExtra("precio"));
            detalle =getIntent().getStringExtra("detalle");

            txtNombreProducto.setText(nombreProducto);
            txtStock.setText(stock);
            txtPrecio.setText(precio);
            txtDetalle.setText(detalle);


        }
    }

    private void asignarReferencias(){
        txtNombreProducto = findViewById(R.id.txtNombreProducto);
        txtStock = findViewById(R.id.txtStock);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtDetalle = findViewById(R.id.txtDetalle);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(registra == true){
                    registrar();
                }else{
                    modificar();
                }
            }
        });

    }
    private void registrar(){
        nombreProducto = txtNombreProducto.getText().toString();
        stock = txtStock.getText().toString();
        precio = txtPrecio.getText().toString();
        detalle = txtDetalle.getText().toString();
        Producto p = new Producto();
        p.setId(UUID.randomUUID().toString());
        p.setNombreProducto(nombreProducto);
        p.setStock(Integer.parseInt(stock));
        p.setPrecio(Double.parseDouble(precio));
        p.setDetalle(detalle);

        databaseReference.child("Producto").child(p.getId()).setValue(p);
        AlertDialog.Builder ventana = new AlertDialog.Builder(RegistrarActivity.this);
        ventana.setTitle("Mensaje informativo");
        ventana.setMessage("Producto Agregado");
        ventana.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Intent intent = new Intent(RegistrarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ventana.create().show();
    }
    private void modificar(){
        Producto p = new Producto();
        p.setId(UUID.randomUUID().toString());
        nombreProducto = txtNombreProducto.getText().toString();
        stock = txtStock.getText().toString();
        precio = txtPrecio.getText().toString();
        detalle = txtDetalle.getText().toString();
        //p.setId(UUID.randomUUID().toString());
        p.setNombreProducto(nombreProducto);
        p.setStock(Integer.parseInt(stock));
        //p.setPrecio(Double.parseDouble(precio));
        p.setDetalle(detalle);

        databaseReference.child("Producto").child(p.getId()).setValue(p);
        AlertDialog.Builder ventana = new AlertDialog.Builder(RegistrarActivity.this);
        ventana.setTitle("Mensaje informativo");
        ventana.setMessage("Producto Actualizado");
        ventana.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Intent intent = new Intent(RegistrarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ventana.create().show();
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

}
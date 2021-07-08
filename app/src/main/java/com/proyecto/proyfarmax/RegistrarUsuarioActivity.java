package com.proyecto.proyfarmax;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyecto.proyfarmax.entities.Usuario;
import com.proyecto.proyfarmax.views.UsuarioActivity;

import java.util.UUID;

public class RegistrarUsuarioActivity extends AppCompatActivity {

    EditText txttipodocumento, txtnumerodocumento, txtnombre, txtapellido, txtcorreo, txtcelular, txtcontrasena, txtdireccion, txtdepartamento, txtprovincia, txtdistrito;
    Button btnregistrar;
    boolean registra = true;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String tipodocumento, numerodocumento, nombre, apellido, correo, celular, contrasena, direccion, departamento, provincia, distrito, id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrarusuario);
        asignarReferencias();
        inicializarFirebase();
        verificaRegistrarActulizar();


    }

    private void verificaRegistrarActulizar() {
        if (getIntent().hasExtra("id")) {
            registra = false;
            id = getIntent().getStringExtra("id");
            txttipodocumento.setText(getIntent().getStringExtra("tipodocumento"));
            txtnumerodocumento.setText(getIntent().getStringExtra("numerodocumento"));
            txtnombre.setText(getIntent().getStringExtra("nombre"));
            txtapellido.setText(getIntent().getStringExtra("apellido"));
            txtcorreo.setText(getIntent().getStringExtra("correo"));
            txtcelular.setText(getIntent().getStringExtra("celular"));
            txtcontrasena.setText(getIntent().getStringExtra("contrasena"));
            txtdireccion.setText(getIntent().getStringExtra("direccion"));
            txtdepartamento.setText(getIntent().getStringExtra("departamento"));
            txtprovincia.setText(getIntent().getStringExtra("provincia"));
            txtdistrito.setText(getIntent().getStringExtra("distrito"));


        }
    }

    private void asignarReferencias() {
        txttipodocumento = findViewById(R.id.txttipodocumento);
        txtnumerodocumento = findViewById(R.id.txtnumerodocumento);
        txtnombre = findViewById(R.id.txtnombre);
        txtapellido = findViewById(R.id.txtapellido);
        txtcorreo = findViewById(R.id.txtcorreo);
        txtcelular = findViewById(R.id.txtcelular);
        txtcontrasena = findViewById(R.id.txtcontrasena);
        txtdireccion = findViewById(R.id.txtdireccion);
        txtdepartamento = findViewById(R.id.txtdepartamento);
        txtprovincia = findViewById(R.id.txtprovincia);
        txtdistrito = findViewById(R.id.txtdistrito);
        btnregistrar = findViewById(R.id.btnactulizar);
        btnregistrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (validarCampos()) {
                    if (registra == true) {
                        registrar();
                    } else {
                        actualizar();
                    }
                }
            }
        });
    }

    private void actualizar() {
        Usuario u = new Usuario();
        u.setId(id);
        tipodocumento = txttipodocumento.getText().toString();
        numerodocumento = txtnumerodocumento.getText().toString();
        nombre = txtnombre.getText().toString();
        apellido = txtapellido.getText().toString();
        correo = txtcorreo.getText().toString();
        celular = txtcelular.getText().toString();
        contrasena = txtcontrasena.getText().toString();
        direccion = txtdireccion.getText().toString();
        departamento = txtdepartamento.getText().toString();
        provincia = txtprovincia.getText().toString();
        distrito = txtdistrito.getText().toString();

        u.setTipodocumento(tipodocumento);
        u.setNumerodocumento(numerodocumento);
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setCorreo(correo);
        u.setCelular((celular));
        u.setContrasena(contrasena);
        u.setDireccion(direccion);
        u.setDepartamento(departamento);
        u.setProvincia(provincia);
        u.setDistrito(distrito);

        databaseReference.child("Usuario").child(u.getId()).setValue(u);
        AlertDialog.Builder ventana = new AlertDialog.Builder(RegistrarUsuarioActivity.this);
        ventana.setTitle("Mensaje Informativo");
        ventana.setMessage("Usuario Actualizado");
        ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(RegistrarUsuarioActivity.this, UsuarioActivity.class);
                startActivity(intent);
            }
        });
        ventana.create().show();


    }


    private void registrar() {
        tipodocumento = txttipodocumento.getText().toString();
        numerodocumento = txtnumerodocumento.getText().toString();
        nombre = txtnombre.getText().toString();
        apellido = txtapellido.getText().toString();
        correo = txtcorreo.getText().toString();
        celular = txtcelular.getText().toString();
        contrasena = txtcontrasena.getText().toString();
        direccion = txtdireccion.getText().toString();
        departamento = txtdepartamento.getText().toString();
        provincia = txtprovincia.getText().toString();
        distrito = txtdistrito.getText().toString();
        Usuario u = new Usuario();
        u.setId(UUID.randomUUID().toString());
        u.setTipodocumento(tipodocumento);
        u.setNumerodocumento(numerodocumento);
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setCorreo(correo);
        u.setCelular((celular));
        u.setContrasena(contrasena);
        u.setDireccion(direccion);
        u.setDepartamento(departamento);
        u.setProvincia(provincia);
        u.setDistrito(distrito);
        databaseReference.child("Usuario").child(u.getId()).setValue(u);
        AlertDialog.Builder ventana = new AlertDialog.Builder(RegistrarUsuarioActivity.this);
        ventana.setTitle("Mensaje Informativo");
        ventana.setMessage("Usuario agregado");
        ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(RegistrarUsuarioActivity.this, UsuarioActivity.class);
                startActivity(intent);
            }
        });
        ventana.create().show();


    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public boolean validarCampos() {
        boolean r = true;
        tipodocumento = txttipodocumento.getText().toString();
        numerodocumento = txtnumerodocumento.getText().toString();
        nombre = txtnombre.getText().toString();
        apellido = txtapellido.getText().toString();
        correo = txtcorreo.getText().toString();
        celular = txtcelular.getText().toString();
        contrasena = txtcontrasena.getText().toString();
        direccion = txtdireccion.getText().toString();
        departamento = txtdepartamento.getText().toString();
        provincia = txtprovincia.getText().toString();
        distrito = txtdistrito.getText().toString();


        if (tipodocumento.equals("")) {
            txttipodocumento.setError("Tipo de Documento Obligatorio");
            r = false;
        }

        if (numerodocumento.equals("")) {
            txtnumerodocumento.setError("Numero de Documento Obligatorio");
            r = false;
        }

        if (nombre.equals("")) {
            txtnombre.setError("Nombre Obligatorio");
            r = false;
        }

        if (apellido.equals("")) {
            txtapellido.setError("Apellido Obligatorio");
            r = false;
        }
        if (correo.equals("")) {
            txtcorreo.setError("Correo Obligatorio");
            r = false;
        }
        if (celular.equals("")) {
            txtcelular.setError("Celular Obligatorio");
            r = false;
        }
        if (contrasena.equals("")) {
            txtcontrasena.setError("Contraseña Obligatorio");
            r = false;
        }
        if (direccion.equals("")) {
            txtdireccion.setError("Direccion Obligatorio");
            r = false;
        }
        if (departamento.equals("")) {
            txtdepartamento.setError("Departamento Obligatorio");
            r = false;
        }
        if (provincia.equals("")) {
            txtprovincia.setError("Provincia Obligatorio");
            r = false;
        }
        if (distrito.equals("")) {
            txtdistrito.setError("Distrito Obligatorio");
            r = false;
        }

        return r;
    }

}
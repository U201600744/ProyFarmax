package com.proyecto.proyfarmax.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyecto.proyfarmax.R;
import com.proyecto.proyfarmax.entity.Usuario;
import com.proyecto.proyfarmax.util.DatePickerFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class RegistrarUsuario_2 extends AppCompatActivity {
    private EditText txtNacimiento, txtPass1, txtPass2;
    private Switch swtNotificaciones;
    private CheckBox chkTermino, chkOferta;
    private Button btnRegistrarUsuario;
    private Usuario usuario;
    private String fechaNac, pass1, pass2;
    private Boolean termino, oferta, notificacion;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario2);
        asignarReferencias();
        recibirDatos();
        inicializarFirebase();

    }

    private void recibirDatos() {
        Intent i = getIntent();
        usuario = (Usuario) i.getSerializableExtra("usuario");
    }

    private void asignarReferencias() {
        txtNacimiento = findViewById(R.id.txtNacimiento);
        txtPass1 = findViewById(R.id.txtPassUsuario);
        txtPass2 = findViewById(R.id.txtPassUsuarioRep);
        chkTermino = findViewById(R.id.chkTermino);
        chkOferta = findViewById(R.id.chkOferta);
        swtNotificaciones = findViewById(R.id.swtNotificaciones);
        btnRegistrarUsuario = findViewById(R.id.btnRegistrarUsuario);
        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validar()) {
                    usuario.setFechaNacimiento(fechaNac);
                    usuario.setPassword(pass1);
                    usuario.setTermino(termino);
                    usuario.setEnvioOferta(oferta);
                    usuario.setNotificacion(notificacion);
                    registrar(usuario);

                }
            }
        });

        txtNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.txtNacimiento:
                        showDatePickerDialog();
                        break;
                }
            }
        });
    }

    private void registrar(Usuario usuario) {
        usuario.setIdUsuario(UUID.randomUUID().toString());
        databaseReference.child("Usuario").child(usuario.getIdUsuario()).setValue(usuario);
        AlertDialog.Builder ventana = new AlertDialog.Builder(RegistrarUsuario_2.this);
        ventana.setTitle("Mensaje Informativo");
        ventana.setMessage("Usuario creado satisfactoriamente");
        ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(RegistrarUsuario_2.this,Login.class);
                startActivity(intent);
            }
        });
        ventana.create().show();
    }

    private boolean validar() {
        fechaNac = txtNacimiento.getText().toString();
        pass1 = txtPass1.getText().toString();
        pass2 = txtPass2.getText().toString();
        termino = chkTermino.isChecked();
        oferta = chkOferta.isChecked();
        notificacion = swtNotificaciones.isChecked();
        return true;
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month + 1) + " / " + year;
                txtNacimiento.setText(selectedDate);
            }
        });

        newFragment.show(RegistrarUsuario_2.this.getSupportFragmentManager(), "datePicker");
    }
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}
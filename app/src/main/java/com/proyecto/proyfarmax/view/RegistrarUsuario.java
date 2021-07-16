package com.proyecto.proyfarmax.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proyecto.proyfarmax.R;
import com.proyecto.proyfarmax.entity.Usuario;

import java.io.Serializable;

public class RegistrarUsuario extends AppCompatActivity implements Serializable{
    private FloatingActionButton btnNext1;
    Spinner spnTipoDocumento;
    EditText txtDni, txtNombres, txtPaterno, txtMaterno;
    RadioButton rbMaculino, rbFemenino;
    String dni, nombres, apePaterno, apeMaterno, tipoDocumento;
    Boolean masculino, femenino;
    RadioGroup rbGenero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        asignarReferencias();
    }

    private void asignarReferencias() {
        txtDni = findViewById(R.id.txtDni);
        txtNombres = findViewById(R.id.txtNombres);
        txtPaterno = findViewById(R.id.txtPaterno);
        txtMaterno = findViewById(R.id.txtMaterno);
        rbMaculino = findViewById(R.id.rbMaculino);
        rbFemenino = findViewById(R.id.rbFemenino);
        rbGenero = findViewById(R.id.rbGenero);
        btnNext1 = findViewById(R.id.btnNext1);
        spnTipoDocumento = findViewById(R.id.spnTipoDocumento);
        String[] lstDocumentos = {"Seleccione", "DNI", "Carnet de Extrangeria", "Pasaporte"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(RegistrarUsuario.this, android.R.layout.simple_spinner_dropdown_item, lstDocumentos);
        spnTipoDocumento.setAdapter(adapter);
        btnNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validar()) {
                    Usuario usuario = new Usuario();
                    usuario.setIdTipoDocumento(tipoDocumento);
                    usuario.setNumDocumento(dni);
                    usuario.setNombres(nombres);
                    usuario.setApelPaterno(apePaterno);
                    usuario.setApelMaterno(apeMaterno);
                    if (rbMaculino.isChecked()) {
                        usuario.setGenero("M");
                    } else {
                        usuario.setGenero("F");
                    }
                    Intent intent = new Intent(RegistrarUsuario.this, RegistrarUsuario_1.class);
                    intent.putExtra("usuario",usuario);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validar() {
        boolean resultado = true;
        tipoDocumento = spnTipoDocumento.getSelectedItem().toString();
        dni = txtDni.getText().toString();
        nombres = txtNombres.getText().toString();
        apePaterno = txtPaterno.getText().toString();
        apeMaterno = txtMaterno.getText().toString();
        masculino = rbMaculino.isChecked();
        femenino = rbFemenino.isChecked();
        if(tipoDocumento.equals("Seleccione")){
            ((TextView)spnTipoDocumento.getSelectedView()).setError("Tipo de documento obligatorio");
            resultado = false;
        }
        if (dni.isEmpty()) {
            txtDni.setError("Dni obligatorio");
            resultado = false;
        }
        if (nombres.isEmpty()) {
            txtNombres.setError("Nombres obligatorio");
            resultado = false;
        }
        if (apePaterno.isEmpty()) {
            txtPaterno.setError("Apellido paterno obligatorio");
            resultado = false;
        }
        if (apeMaterno.isEmpty()) {
            txtMaterno.setError("Apellido materno obligatorio");
            resultado = false;
        }
        if (!masculino && !femenino) {
            rbMaculino.setError("Género obligatorio");
            rbFemenino.setError("Género obligatorio");
            resultado = false;
        }
        return resultado;
    }
}
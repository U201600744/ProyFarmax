package com.proyecto.proyfarmax.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.security.FileIntegrityManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proyecto.proyfarmax.R;
import com.proyecto.proyfarmax.entity.Usuario;

public class RegistrarUsuario_1 extends AppCompatActivity {
    private FloatingActionButton btnNext2;
    private Spinner spnDepartamento, spnProvincia, spnDistrito;
    private EditText txtDireccion, txtEmailUsuario, txtCelular;
    private String departamento, provincia, distrito, direccion, email, celular;
    private Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario1);
        asignarReferencias();
        recibirDatos();
    }

    private void recibirDatos() {
        Intent i = getIntent();
        usuario = (Usuario) i.getSerializableExtra("usuario");

    }

    private void asignarReferencias() {
        btnNext2 = findViewById(R.id.btnNext2);
        spnDepartamento = findViewById(R.id.spnDepartamento);
        String[] lstDepartamentos = {"Seleccione", "Amazonas", "Ancash", "Apurimac", "Arequipa", "Ayacucho", "Cajamarca", "Callao", "Cusco", "Huancavelica", "Huanuco", "Ica",
                "Junín", "La Libertad", "Lambayeque", "Lima", "Loreto", "Madre de Dios", "Moquegua", "Pasco", "Piura", "Puno", "San Martín", "Tacna", "Tumbes", "Ucayali"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(RegistrarUsuario_1.this, android.R.layout.simple_spinner_dropdown_item, lstDepartamentos);
        spnDepartamento.setAdapter(adapter);

        spnProvincia = findViewById(R.id.spnProvincia);
        String[] lstProvincia = {"Seleccione", "LIMA", "Callao"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(RegistrarUsuario_1.this, android.R.layout.simple_spinner_dropdown_item, lstProvincia);
        spnProvincia.setAdapter(adapter1);

        spnDistrito = findViewById(R.id.spnDistrito);
        String[] lstDistrito = {"Seleccione", "San Isidro", "La Victoria", "Pueblo Libre"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(RegistrarUsuario_1.this, android.R.layout.simple_spinner_dropdown_item, lstDistrito);
        spnDistrito.setAdapter(adapter2);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtEmailUsuario = findViewById(R.id.txtEmailUsuario);
        txtCelular = findViewById(R.id.txtCelular);
        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validar()) {
                    usuario.setIdDepartamento(departamento);
                    usuario.setIdProvincia(provincia);
                    usuario.setIdDistrito(distrito);
                    usuario.setDireccion(direccion);
                    usuario.setEmail(email);
                    usuario.setNumCelular(celular);
                    Intent intent = new Intent(RegistrarUsuario_1.this, RegistrarUsuario_2.class);
                    intent.putExtra("usuario",usuario);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validar() {
        Boolean resultado = true;
        departamento = spnDepartamento.getSelectedItem().toString();
        provincia = spnProvincia.getSelectedItem().toString();
        distrito = spnDistrito.getSelectedItem().toString();
        direccion = txtDireccion.getText().toString();
        email = txtEmailUsuario.getText().toString();
        celular = txtCelular.getText().toString();
        if (departamento.isEmpty()) {
            ((TextView) spnDepartamento.getSelectedView()).setError("Departamento obligatorio");
            resultado = false;
        }

        return resultado;
    }
}
package com.proyecto.proyfarmax.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyecto.proyfarmax.R;

import java.util.HashMap;
import java.util.Map;

public class RegistrarUsuAdminActivity extends AppCompatActivity {

    private TextView txtTipDocAdmin, txtNumDocAdmin, txtNomAdmin, txtApeAdmin, txtCorreoAdmin, txtCelAdmin, txtPassNewAdmin, txtDirAdmin;
    private TextView txtDepAdmin, txtProvAdmin, txtDisAdmin;
    private Button btnNewAdmin;
    private String tipdoc="", numdoc="", nombre ="", ape = "", correo = "", cel="", pass = "", dire="", dep="", prov="", dist="";
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usu_admin);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        txtTipDocAdmin = findViewById(R.id.txtTipDoc);
        txtNumDocAdmin = findViewById(R.id.txtNumDoc);
        txtNomAdmin = findViewById(R.id.txtNom);
        txtApeAdmin = findViewById(R.id.txtApe);
        txtCorreoAdmin = findViewById(R.id.txtCorreo);
        txtCelAdmin = findViewById(R.id.txtCel);
        txtPassNewAdmin = findViewById(R.id.txtPassNewAdmin);
        txtDirAdmin = findViewById(R.id.txtDir);
        txtDepAdmin = findViewById(R.id.txtDep);
        txtProvAdmin = findViewById(R.id.txtProv);
        txtDisAdmin = findViewById(R.id.txtDis);
        btnNewAdmin = findViewById(R.id.btnActualizarUser);
        btnNewAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipdoc = txtTipDocAdmin.getText().toString();
                numdoc = txtNumDocAdmin.getText().toString();
                nombre = txtNomAdmin.getText().toString();
                ape = txtApeAdmin.getText().toString();
                correo = txtCorreoAdmin.getText().toString();
                cel = txtCelAdmin.getText().toString();
                pass = txtPassNewAdmin.getText().toString();
                dire = txtDirAdmin.getText().toString();
                dep = txtDepAdmin.getText().toString();
                prov = txtProvAdmin.getText().toString();
                dist = txtDisAdmin.getText().toString();

                if (!tipdoc.isEmpty() || !numdoc.isEmpty() || !nombre.isEmpty() || !ape.isEmpty() || !correo.isEmpty() ||
                    !cel.isEmpty() || !pass.isEmpty() || !dire.isEmpty() || !dire.isEmpty() || !dep.isEmpty() ||
                    !prov.isEmpty() || !dist.isEmpty()){
                    if (pass.length() >= 10){
                        registrarAdmin();
                        AlertDialog.Builder ven = new AlertDialog.Builder(RegistrarUsuAdminActivity.this);
                        ven.setTitle("Mensaje Informativo");
                        ven.setMessage("Se registraron los datos correctamente");
                        ven.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(RegistrarUsuAdminActivity.this, HomeAdminActivity.class);
                                startActivity(intent);
                            }
                        });
                        ven.create().show();
                    }else{
                        AlertDialog.Builder ven = new AlertDialog.Builder(RegistrarUsuAdminActivity.this);
                        ven.setTitle("Mensaje Informativo");
                        ven.setMessage("La contraseña debe tener mínimo 10 caracteres");
                        ven.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(RegistrarUsuAdminActivity.this, RegistrarUsuAdminActivity.class);
                                startActivity(intent);
                            }
                        });
                        ven.create().show();
                    }
                }else {
                    AlertDialog.Builder ven = new AlertDialog.Builder(RegistrarUsuAdminActivity.this);
                    ven.setTitle("Mensaje Informativo");
                    ven.setMessage("Debe ingresar todos los datos");
                    ven.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(RegistrarUsuAdminActivity.this, HomeAdminActivity.class);
                            startActivity(intent);
                        }
                    });
                    ven.create().show();
                }
            }
        });
    }

    private void registrarAdmin() {
        mAuth.createUserWithEmailAndPassword(correo, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Map<String, Object> map = new HashMap<>();
                    map.put("tipo documento", tipdoc);
                    map.put("numero documento", numdoc);
                    map.put("nombre", nombre);
                    map.put("apellido", ape);
                    map.put("correo", correo);
                    map.put("celular", cel);
                    //map.put("contraseña", pass);//no enviar
                    map.put("direccion", dire);
                    map.put("departamento", dep);
                    map.put("provincia", prov);
                    map.put("distrito", dist);

                    String id = mAuth.getCurrentUser().getUid();
                    databaseReference.child("Administrador").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                startActivity(new Intent(RegistrarUsuAdminActivity.this, HomeAdminActivity.class));
                                finish();
                            }else {
                                AlertDialog.Builder ven = new AlertDialog.Builder(RegistrarUsuAdminActivity.this);
                                ven.setTitle("Mensaje Informativo");
                                ven.setMessage("Hubo un inconveniente al grabar los datos\n"+
                                        "\nVuelva a intentarlo en unos minutos");
                                ven.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(RegistrarUsuAdminActivity.this, HomeAdminActivity.class);
                                        startActivity(intent);
                                    }
                                });
                                ven.create().show();
                            }
                        }
                    });
                }else {
                    AlertDialog.Builder ven = new AlertDialog.Builder(RegistrarUsuAdminActivity.this);
                    ven.setTitle("Mensaje Informativo");
                    ven.setMessage("No se pudo registrar al usuario.\n"+
                            "\nVerifique los datos ingresados y vuelva a intentarlo");
                    //ven.setMessage("Debe ingresar todos los datos");
                    ven.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(RegistrarUsuAdminActivity.this, HomeAdminActivity.class);
                            startActivity(intent);
                        }
                    });
                    ven.create().show();
                }
            }
        });
    }
}
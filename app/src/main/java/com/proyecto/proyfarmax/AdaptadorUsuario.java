package com.proyecto.proyfarmax;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyecto.proyfarmax.entities.Usuario;
import com.proyecto.proyfarmax.views.UpdateUserActivity;
import com.proyecto.proyfarmax.views.UsuarioActivity;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorUsuario extends RecyclerView.Adapter<AdaptadorUsuario.vistaHolder> {

    private Context context;
    private List<Usuario> listaUsuario = new ArrayList<>();

    public AdaptadorUsuario(Context context, List<Usuario> lista) {
        this.context = context;
        this.listaUsuario = lista;
    }

    @NonNull
    @Override
    public AdaptadorUsuario.vistaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.listausuarios, parent, false);
        return new vistaHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorUsuario.vistaHolder holder, int position) {
        holder.filanombre.setText(listaUsuario.get(position).getNombre() + " " + listaUsuario.get(position).getApellido());
        holder.filacelular.setText(listaUsuario.get(position).getCelular() + "");
        holder.filadireccion.setText(listaUsuario.get(position).getDireccion() + "");
        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(context, ""+listaUsuario.get(position).getNombre(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, UpdateUserActivity.class);
                intent.putExtra("id", listaUsuario.get(position).getId());
                intent.putExtra("tipodocumento", listaUsuario.get(position).getTipodocumento());
                intent.putExtra("numerodocumento", listaUsuario.get(position).getNumerodocumento());
                intent.putExtra("nombre", listaUsuario.get(position).getNombre());
                intent.putExtra("apellido", listaUsuario.get(position).getApellido());
                intent.putExtra("correo", listaUsuario.get(position).getCorreo());
                intent.putExtra("celular", listaUsuario.get(position).getCelular());
                intent.putExtra("contrasena", listaUsuario.get(position).getContrasena());
                intent.putExtra("direccion", listaUsuario.get(position).getDireccion());
                intent.putExtra("departamento", listaUsuario.get(position).getDepartamento());
                intent.putExtra("provincia", listaUsuario.get(position).getProvincia());
                intent.putExtra("distrito", listaUsuario.get(position).getDistrito());
                context.startActivity(intent);


            }
        });

        //Eliminar

        holder.btnElimnar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminar(listaUsuario.get(position).getId(), listaUsuario.get(position).getNombre());

            }
        });
    }

    private void eliminar(String id, String nombre) {
        AlertDialog.Builder ventana = new AlertDialog.Builder(context);
        ventana.setTitle("Confirmacion");
        ventana.setMessage("Desea elimnar al Usuario " + nombre);
        ventana.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseDatabase firebaseDatabase;
                DatabaseReference databaseReference;
                FirebaseApp.initializeApp(context);
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference();


                databaseReference.child("Usuario").child(id).removeValue();

            }
        });
        ventana.setNegativeButton("No", null);
        ventana.create().show();

    }

    @Override
    public int getItemCount() {
        return listaUsuario.size();
    }

    public class vistaHolder extends RecyclerView.ViewHolder {
        TextView filanombre, filacelular, filadireccion;
        ImageButton btnEditar, btnElimnar;

        public vistaHolder(@NonNull View itemView) {
            super(itemView);
            filanombre = itemView.findViewById(R.id.filanombre);
            filacelular = itemView.findViewById(R.id.filacelular);
            filadireccion = itemView.findViewById(R.id.filadireccion);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnElimnar = itemView.findViewById(R.id.btnEliminar);


        }
    }
}

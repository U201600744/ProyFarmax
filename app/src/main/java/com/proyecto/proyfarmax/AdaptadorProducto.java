package com.proyecto.proyfarmax;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyecto.proyfarmax.entities.Producto;
import com.proyecto.proyfarmax.views.MainActivity;

import java.util.ArrayList;

public class AdaptadorProducto extends RecyclerView.Adapter<AdaptadorProducto.MyViewHolder> {

    Context context;
    ArrayList<Producto> list;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public AdaptadorProducto(Context context, ArrayList<Producto> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorProducto.MyViewHolder holder, int position) {
        //inicializarFirebase();

        //Producto producto = list.get(position);
        holder.tvNombreProducto.setText(list.get(position).getNombreProducto()+"");
        holder.tvStock.setText(list.get(position).getStock()+"");
        holder.tvPrecio.setText(list.get(position).getPrecio()+"");
        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ventana = new AlertDialog.Builder(context);
                ventana.setTitle("¿Desea eliminar?");
                ventana.setMessage("¿Desea eliminar el producto "+list.get(position).getNombreProducto()+"?");
                ventana.setPositiveButton("si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Producto p = new Producto();
                        p.setId(list.get(position).getId());
                        inicializarFirebase();
                        databaseReference.child("Producto").child(p.getId()).removeValue();
                        AlertDialog.Builder v2 = new AlertDialog.Builder(context);
                        v2.setTitle("Mensaje de Confirmación");
                        v2.setMessage("Se eliminó el producto de manera correcta");
                        v2.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(context, MainActivity.class);
                                context.startActivity(intent);
                            }
                        });
                        v2.create().show();
                    }
                });
                ventana.setNegativeButton("No",null);
                ventana.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvNombreProducto, tvStock, tvPrecio;
        ImageButton btnEliminar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombreProducto = itemView.findViewById(R.id.tvNombreProducto);
            tvStock = itemView.findViewById(R.id.tvStock);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(AdaptadorProducto.this.context);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}

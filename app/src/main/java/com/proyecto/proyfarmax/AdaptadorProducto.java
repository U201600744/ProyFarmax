package com.proyecto.proyfarmax;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.proyfarmax.entities.Producto;
import com.proyecto.proyfarmax.views.EditarActivity;

import java.util.ArrayList;


public class AdaptadorProducto extends RecyclerView.Adapter<AdaptadorProducto.MyViewHolder> {

    Context context;
    ArrayList<Producto> list;


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

        //Producto producto = list.get(position);
        holder.tvNombreProducto.setText(list.get(position).getNombreProducto()+"");
        holder.tvStock.setText(list.get(position).getStock()+"");
        holder.tvPrecio.setText(list.get(position).getPrecio()+"");
        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditarActivity.class);
                intent.putExtra("id",list.get(position).getId()+"");
                intent.putExtra("nombre",list.get(position).getNombreProducto()+"");
                intent.putExtra("stock",list.get(position).getStock()+"");
                intent.putExtra("precio",list.get(position).getPrecio()+"");
                intent.putExtra("detalles",list.get(position).getDetalle()+"");
                context.startActivity(intent);

            }

            
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvNombreProducto, tvStock, tvPrecio;
        ImageButton btnEditar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombreProducto = itemView.findViewById(R.id.tvNombreProducto);
            tvStock = itemView.findViewById(R.id.tvStock);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            btnEditar = itemView.findViewById(R.id.btnEditar);
        }
    }
}

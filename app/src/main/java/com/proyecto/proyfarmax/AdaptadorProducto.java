package com.proyecto.proyfarmax;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.proyfarmax.entities.Producto;

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

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvNombreProducto, tvStock, tvPrecio;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombreProducto = itemView.findViewById(R.id.tvNombreProducto);
            tvStock = itemView.findViewById(R.id.tvStock);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
        }
    }
}

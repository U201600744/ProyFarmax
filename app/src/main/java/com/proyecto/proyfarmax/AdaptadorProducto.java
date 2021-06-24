package com.proyecto.proyfarmax;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorProducto extends RecyclerView.Adapter<AdaptadorProducto.MyViewHolder> {

    Context context;
    ArrayList<ItemProducto> list;

    public AdaptadorProducto(Context context, ArrayList<ItemProducto> list) {
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

        ItemProducto itemProducto = list.get(position);
        holder.item_nombreProducto.setText(itemProducto.getItem_nombreProducto());
        holder.item_stock.setText(itemProducto.getItem_stock());
        holder.item_precio.setText(itemProducto.getItem_precio());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView item_nombreProducto, item_stock, item_precio;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_nombreProducto = itemView.findViewById(R.id.tvNombreProducto);
            item_stock = itemView.findViewById(R.id.tvStock);
            item_precio = itemView.findViewById(R.id.tvPrecio);
        }
    }
}

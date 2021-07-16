package com.proyecto.proyfarmax.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.proyecto.proyfarmax.R;
import com.proyecto.proyfarmax.entity.Producto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdaptadorListaProducto  extends RecyclerView.Adapter<AdaptadorListaProducto.MyViewHolder> {
    private Context context;
    private TextView lblCantidad;
    private ImageButton btnCarrito;
    private List<Producto> listaProductos = new ArrayList<>();
    private List<Producto> carroCompras = new ArrayList<>();
    int cantidad;

    public AdaptadorListaProducto(Context context, TextView lblCantidad, ImageButton btnCarrito, List<Producto> listaProductos, List<Producto> carroCompras) {
        this.context = context;
        this.lblCantidad = lblCantidad;
        this.btnCarrito = btnCarrito;
        this.listaProductos = listaProductos;
        this.carroCompras = carroCompras;
    }



    @Override
    public AdaptadorListaProducto.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.fila,parent,false);
        return new MyViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull  AdaptadorListaProducto.MyViewHolder holder, final int position) {

        holder.lblNombre.setText(listaProductos.get(position).getNombreProducto()+"");
        holder.lblPrecio.setText(listaProductos.get(position).getPrecio()+"");
        Glide.with(context).load(listaProductos.get(position).getFoto()).into(holder.imgItem);
        holder.chkAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(holder.chkAdd.isChecked() == true ){
                        lblCantidad.setText(""+(Integer.parseInt(lblCantidad.getText().toString())+1));
                        carroCompras.add(listaProductos.get(position));
                }else  if(holder.chkAdd.isChecked() == false ){
                    lblCantidad.setText(""+(Integer.parseInt(lblCantidad.getText().toString())-1));
                    carroCompras.remove(listaProductos.get(position));
                }
            }
        });
                    btnCarrito.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context,CarritoCompras.class);
                            intent.putExtra("CarroCompras", (Serializable) carroCompras);
                            context.startActivity(intent);
                        }
                    });

        }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgItem;
        TextView lblNombre,lblPrecio;
        CheckBox chkAdd;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.imgItem);
            lblNombre = itemView.findViewById(R.id.lblNombre);
            lblPrecio = itemView.findViewById(R.id.lblCantProd);
            chkAdd = itemView.findViewById(R.id.chkAdd);
        }
    }
}

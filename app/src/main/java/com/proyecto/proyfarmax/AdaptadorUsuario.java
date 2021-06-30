package com.proyecto.proyfarmax;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.proyfarmax.entities.Usuario;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorUsuario extends RecyclerView.Adapter<AdaptadorUsuario.vistaHolder> {

    private Context context;
    private List<Usuario> listaUsuario = new ArrayList<>();

    public AdaptadorUsuario(Context context, List<Usuario> lista){
        this.context = context;
        this.listaUsuario = lista;
    }

    @NonNull
    @Override
    public AdaptadorUsuario.vistaHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.listausuarios,parent,false);
        return new vistaHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull  AdaptadorUsuario.vistaHolder holder, int position) {
        holder.filanombre.setText(listaUsuario.get(position).getNombre()+" "+listaUsuario.get(position).getApellido());
        holder.filacelular.setText(listaUsuario.get(position).getCelular()+"");
        holder.filadireccion.setText(listaUsuario.get(position).getDireccion()+"");

    }

    @Override
    public int getItemCount() {
        return listaUsuario.size();
    }

    public class vistaHolder extends RecyclerView.ViewHolder {
        TextView filanombre, filacelular, filadireccion;
        public vistaHolder(@NonNull View itemView) {
            super(itemView);
            filanombre = itemView.findViewById(R.id.filanombre);
            filacelular = itemView.findViewById(R.id.filacelular);
            filadireccion = itemView.findViewById(R.id.filadireccion);

        }
    }
}

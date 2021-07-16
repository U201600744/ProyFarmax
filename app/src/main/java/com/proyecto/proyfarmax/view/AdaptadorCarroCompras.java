package com.proyecto.proyfarmax.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.proyfarmax.R;
import com.proyecto.proyfarmax.entity.Producto;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorCarroCompras extends RecyclerView.Adapter<AdaptadorCarroCompras.MyViewHolder> {

    private Context context;
    private TextView lblNomProd,lblPrecioProd;
    private TextView txtSubtotal,txtIgv,txtTotal;
    private ImageButton btnComprar;
    private List<Producto> carroCompras = new ArrayList<>();
    double subTotal,total;
    double Igv = 1.18;


    public AdaptadorCarroCompras(Context context, TextView txtSubtotal, TextView txtIgv, TextView txtTotal, ImageButton btnComprar, List<Producto> carroCompras) {
        this.context = context;
        this.txtSubtotal = txtSubtotal;
        this.txtIgv = txtIgv;
        this.txtTotal = txtTotal;
        this.btnComprar = btnComprar;
        this.carroCompras = carroCompras;

        for( int i =0;i<carroCompras.size();i++){
            subTotal = subTotal + Double.parseDouble(""+carroCompras.get(i).getPrecio());
        }
        txtSubtotal.setText(""+subTotal);
        txtIgv.setText(""+Igv);
        txtTotal.setText(""+(subTotal*Igv));
    }

    @Override
    public AdaptadorCarroCompras.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.filacarrito, parent, false);
        return new AdaptadorCarroCompras.MyViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorCarroCompras.MyViewHolder holder, final int position) {
        holder.lblNomProd.setText(carroCompras.get(position).getNombreProducto());
        holder.lblPrecioProd.setText(carroCompras.get(position).getPrecio()+"");
    }

    @Override
    public int getItemCount() {
        return carroCompras.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lblNomProd, lblPrecioProd;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lblNomProd = itemView.findViewById(R.id.lblNomProd);
            lblPrecioProd = itemView.findViewById(R.id.lblPrecioProd);
        }
    }
}


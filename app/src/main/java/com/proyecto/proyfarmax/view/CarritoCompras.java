package com.proyecto.proyfarmax.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.proyecto.proyfarmax.R;
import com.proyecto.proyfarmax.entity.Producto;

import java.util.List;

public class CarritoCompras extends AppCompatActivity {

    private List<Producto> carroCompras;
    AdaptadorCarroCompras adaptadorCarroCompras;
    RecyclerView recyclerCarrito;
    TextView txtSubtotal,txtIgv,txtTotal;
    ImageButton btnComprar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_compras);
        obtenerCarroCompras();
        asignarReferencias();
    }

    private void asignarReferencias() {
        txtSubtotal = findViewById(R.id.txtSubtotal);
        txtIgv = findViewById(R.id.txtIgv);
        txtTotal = findViewById(R.id.txtTotal);
        recyclerCarrito = findViewById(R.id.recyclerCarrito);
        recyclerCarrito.setLayoutManager(new GridLayoutManager(CarritoCompras.this,1));
        adaptadorCarroCompras = new AdaptadorCarroCompras(CarritoCompras.this,txtSubtotal,txtIgv,txtTotal,btnComprar,carroCompras);
        recyclerCarrito.setAdapter(adaptadorCarroCompras);
    }

    private void obtenerCarroCompras(){

        carroCompras = (List<Producto>) getIntent().getSerializableExtra("CarroCompras");

    }
}
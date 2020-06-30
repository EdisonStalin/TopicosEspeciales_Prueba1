package com.example.tiendaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView tvnombre, tvcantidad,tvprecio;
    private Button btnagregar, btneliminar, btnactualizar;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvnombre = findViewById(R.id.tvNombre);
        tvcantidad = findViewById(R.id.tvCantidad);
        tvprecio =findViewById(R.id.tvPrecio);
        btnagregar = findViewById(R.id.btnAgregarCuentaFB);
        btneliminar = findViewById(R.id.btnEliminar);
        btnactualizar = findViewById(R.id.btnActualizar);

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = tvnombre.getText().toString();
                int cantidad = Integer.parseInt(tvcantidad.getText().toString());
                int precio = Integer.parseInt(tvprecio.getText().toString());


                Map<String, Object> producto = new HashMap<>();
                producto.put("nombre","pera");
                producto.put("cantidad",""+cantidad);
                producto.put("precio",""+precio);
                mDatabase.child("producto").setValue(producto).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Se a Agregado el nuevo producto exitosamente",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Np se ha agregado el producto", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("producto").removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Se a eliminado toda la lista de productos", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "No se a eliminado los productos", Toast.LENGTH_SHORT).show();
                    }
                });

                btnactualizar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nombre = tvnombre.getText().toString();
                        int cantidad = Integer.parseInt(tvcantidad.getText().toString());
                        int precio = Integer.parseInt(tvprecio.getText().toString());

                        Map<String, Object> producto = new HashMap<>();
                        producto.put("nombre",nombre);
                        producto.put("cantidad",""+cantidad);
                        producto.put("precio",""+precio);
                        mDatabase.child("producto").updateChildren(producto);

                    }
                });

            }
        });


    }

}
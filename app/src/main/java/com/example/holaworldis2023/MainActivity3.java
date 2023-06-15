package com.example.holaworldis2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        TextView txtMensaje = (TextView)findViewById(R.id.txtMensaje);

        Bundle bundle = this.getIntent().getExtras();

        txtMensaje.setText("Hola!, Bienvenido \n "
                + "Nombre: " + bundle.getString("NOMBRE") + "\n"
                + "Password: " + bundle.getString("PASSWORD") + "\n"
                + "Genero: " + bundle.getString("GENERO") + "\n"
                + "Notificaciones: " + bundle.getString("NOTIFICACIONES")
        );

    }
}
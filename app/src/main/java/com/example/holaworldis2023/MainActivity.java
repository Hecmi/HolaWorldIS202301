package com.example.holaworldis2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btEnviar(View view) {
        //EditText txtNombre = (EditText)findViewById(R.id.txtNombre);

        EditText txtNombre = (EditText)findViewById(R.id.txtNombre);
        EditText txtPassword = (EditText)findViewById(R.id.txtPassword);
        RadioButton rbMasc = (RadioButton)findViewById(R.id.rbMasc);
        EditText txtFecha = (EditText)findViewById(R.id.txtFechaNac);
        Switch swAlerta = (Switch)findViewById(R.id.swNotificaciones);


        String nombre = txtNombre.getText().toString();
        String password = txtPassword.getText().toString();
        String genero = rbMasc.isChecked() ? "Masculino" : "Femenino";
        String fechaNac = txtFecha.getText().toString();
        String alerta = swAlerta.isChecked() ? "Sí" : "No";

        //Toast.makeText(this.getApplicationContext(), "Hola " + txtNombre.getText().toString(), Toast.LENGTH_LONG).show();
        Toast.makeText(this.getApplicationContext(),
                "Nombre: " + nombre + ", " +
                "Password: " + password + ", " +
                "Genero: " + genero + ", " +
                "Alerta: " + alerta + ", ", Toast.LENGTH_LONG).show();

        //Abrir la actividad con los datos ingresados
        Intent intent = new Intent(MainActivity.this, MainActivity3.class);

        //Enviar datos a la siguiente actividad
        Bundle b = new Bundle();
        b.putString("NOMBRE", nombre);
        b.putString("PASSWORD", password);
        b.putString("GENERO", genero);
        b.putString("NOTIFICACIONES", alerta);

        intent.putExtras(b);

        startActivity(intent);

    }

    public void Guardar(View view){
        EditText txtNombre = (EditText)findViewById(R.id.txtNombre);
        EditText txtPassword = (EditText)findViewById(R.id.txtPassword);
        RadioButton rbMasc = (RadioButton)findViewById(R.id.rbMasc);
        EditText txtFecha = (EditText)findViewById(R.id.txtFechaNac);
        Switch swAlerta = (Switch)findViewById(R.id.swNotificaciones);


        String nombre = txtNombre.getText().toString();
        String password = txtPassword.getText().toString();
        String Genero = rbMasc.isChecked() ? "Masculino" : "Femenino";
        String fechaNac = txtFecha.getText().toString();
        String alerta = swAlerta.isChecked() ? "Sí" : "No";



    }
}
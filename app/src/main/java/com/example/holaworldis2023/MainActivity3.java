package com.example.holaworldis2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity3 extends AppCompatActivity implements Asynchtask {

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

        //Bundle bundle = this.getIntent().getExtras();
        /*Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(
                "https://revistas.uteq.edu.ec/ws/login.php?usr="
                        + bundle.getString("NOMBRE") + "&pass=" + bundle.getString("PASSWORD"),
                datos, MainActivity3.this, MainActivity3.this);
        ws.execute("GET");*/

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.google.com";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity3.this, "Resp: " + response, Toast.LENGTH_LONG).show();
                        //txtMensaje.setText("Response is: "+ response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //txtMensaje.setText("That didn't work!");
                        Toast.makeText(MainActivity3.this, "That didn't work!", Toast.LENGTH_LONG).show();
                    }
                });
        queue.add(stringRequest);
    }

    @Override
    public void processFinish(String result) throws JSONException {
        Toast.makeText(this, "Resp: " + result, Toast.LENGTH_LONG).show();


    }
}
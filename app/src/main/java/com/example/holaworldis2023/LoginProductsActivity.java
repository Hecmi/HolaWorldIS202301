package com.example.holaworldis2023;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


import WebService.Asynchtask;
import WebService.WebService;

public class LoginProductsActivity extends AppCompatActivity implements Asynchtask {

    EditText txtUsername;
    EditText txtPassword;
    String access_token;

    Integer error_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_products);

        error_type = 0;
        access_token = "";

        txtUsername = (EditText)findViewById(R.id.txtUsername);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
    }

    public void login(View view){

        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        String url = "https://api.uealecpeterson.net/public/login";

        /*Map<String, String> datos = new HashMap<String, String>();
        datos.put("correo", username);
        datos.put("clave", password);
        datos.put("fuente", "1");

        WebService ws= new WebService(
                url,
                datos, LoginProductsActivity.this, LoginProductsActivity.this);

        ws.execute("POST");*/


        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest getRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {

                        JSONObject jObject = null;
                        try {
                            jObject = new JSONObject(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        if(!jObject.has("error")){
                            try {
                                access_token = jObject.getString("access_token");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                            Intent intent = new Intent(LoginProductsActivity.this, ProductsActivity.class);

                            //Enviar datos a la siguiente actividad
                            Bundle b = new Bundle();
                            b.putString("EMAIL", txtUsername.getText().toString());
                            b.putString("PASSWORD", txtPassword.getText().toString());
                            b.putString("FUENTE", "1");
                            b.putString("TOKEN", access_token);

                            intent.putExtras(b);

                            startActivity(intent);
                        }
                        //Transformar/castear JSON
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error_type = error.networkResponse.statusCode;
                        Log.i("E", "Error");
                    }
                }
        ) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("correo", username);
                params.put("clave", password);
                params.put("fuente", "1");

                return params;
            }
        };
        queue.add(getRequest);
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONObject jObject = new JSONObject(result);

        Toast.makeText(this, result.toString(), Toast.LENGTH_LONG).show();
        if(!jObject.has("error")){
            access_token = jObject.getString("access_token");

            Intent intent = new Intent(LoginProductsActivity.this, ProductsActivity.class);

            //Enviar datos a la siguiente actividad
            Bundle b = new Bundle();
            b.putString("EMAIL", txtUsername.getText().toString());
            b.putString("PASSWORD", txtPassword.getText().toString());
            b.putString("FUENTE", "1");
            b.putString("TOKEN", access_token);

            intent.putExtras(b);

            startActivity(intent);
        }


    }
}
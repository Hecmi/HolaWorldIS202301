package com.example.holaworldis2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


import WebService.Asynchtask;
import WebService.WebService;

public class ProductsActivity extends AppCompatActivity implements Asynchtask {

    TextView txtProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        txtProducts = (TextView)findViewById(R.id.txtProductos);

        Bundle bundle = this.getIntent().getExtras();

        String email = bundle.getString("EMAIL");
        String password = bundle.getString("PASSWORD");
        String fuente = bundle.getString("FUENTE");
        String token = bundle.getString("TOKEN");


        Map<String, String> datos = new HashMap<String, String>();
        datos.put("correo", email);
        datos.put("clave", password);
        datos.put("fuente", fuente);

        String url = "https://api.uealecpeterson.net/public/productos/search";
        WebService ws= new WebService(
                url,
                datos, ProductsActivity.this, ProductsActivity.this);

        ws.execute("POST", "Authorization", "Bearer " + token);

    }


    @Override
    public void processFinish(String result) throws JSONException {
        String lstProducts ="";

        JSONObject product = new JSONObject(result);

        JSONArray JSONlista = product.getJSONArray("productos");
        for(int i = 0; i < JSONlista.length(); i++){

            JSONObject products = JSONlista.getJSONObject(i);
            lstProducts = lstProducts + "\n" +
                    products.getString("id").toString() + " - " +
                    products.getString("descripcion").toString();
        }


        txtProducts.setText(lstProducts);
        Toast.makeText(this, lstProducts, Toast.LENGTH_LONG);
    }
}
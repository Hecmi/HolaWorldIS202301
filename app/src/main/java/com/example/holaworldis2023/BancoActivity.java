package com.example.holaworldis2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

public class BancoActivity extends AppCompatActivity implements Asynchtask {

    TextView txtLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banco);

        txtLista = (TextView)findViewById(R.id.txtListaBancos);

        //LIBRERÌA WEBSERVICE
        /*
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(
                "https://api-uat.kushkipagos.com/transfer-subscriptions/v1/bankList",
                datos, BancoActivity.this, BancoActivity.this);

        ws.execute("GET","Public-Merchant-Id","84e1d0de1fbf437e9779fd6a52a9ca18");*/

        //GOOGLE VOLLEY
        loadVolley("https://api-uat.kushkipagos.com/transfer-subscriptions/v1/bankList");
    }





    private void loadVolley(String url){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {

                        //Transformar/castear JSON
                        String lstBancos="";
                        JSONArray JSONlista = null;
                        try {
                            JSONlista = new JSONArray(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        for(int i=0; i< JSONlista.length();i++){
                            JSONObject banco= null;
                            try {
                                banco = JSONlista.getJSONObject(i);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                lstBancos = lstBancos + "\n" +
                                        banco.getString("code").toString() + " - " +
                                        banco.getString("name").toString();
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        //Establecer el valor en el txt
                        txtLista.setText(lstBancos);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("E", "Error");
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Public-Merchant-Id", "84e1d0de1fbf437e9779fd6a52a9ca18");

                return params;
            }
        };
        queue.add(getRequest);
    }
    @Override
    public void processFinish(String result) throws JSONException {
        String lstBancos="";
        JSONArray JSONlista = new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco= JSONlista.getJSONObject(i);
            lstBancos = lstBancos + "\n" +
                    banco.getString("code").toString() + " - " +
                    banco.getString("name").toString();
        }
    }

    private String castJSON(String jsonString) throws JSONException {
        String lstBancos="";
        JSONArray JSONlista = new JSONArray(jsonString);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco= JSONlista.getJSONObject(i);
            lstBancos = lstBancos + "\n" +
                    banco.getString("code").toString() + " - " +
                    banco.getString("name").toString();
        }

        return lstBancos;
    }
}
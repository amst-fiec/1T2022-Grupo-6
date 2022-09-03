package com.example.theheroproyect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.VoiceInteractor;
import android.content.EntityIterator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.IDN;
import java.sql.Array;
import java.util.ArrayList;

public class VentanaBusqueda extends AppCompatActivity {

    private RequestQueue mQueue;
    ArrayList nombresheroes, idsheroes;
    ListView IdLista;
    JSONArray info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_busqueda);
        mQueue = Volley.newRequestQueue(this);

        Bundle bundle = getIntent().getExtras();
        String searchname = "https://superheroapi.com/api/4204085543050063/search/"+bundle.getString("nombredebusqueda");


        obtenerresults(searchname);


    }

    private void obtenerresults(String url){
        final TextView valorb = (TextView) findViewById(R.id.txt_valor);
        IdLista = (ListView) findViewById(R.id.IdLista);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                try {
                    info =response.getJSONArray("results");
                    nombresheroes = new ArrayList();
                    idsheroes = new ArrayList();
                    valorb.setText(Integer.toString(info.length()));

                    for (int i=0; i< info.length(); i++){
                        JSONObject nom = info.getJSONObject(i);
                        nombresheroes.add(nom.getString("name"));
                        idsheroes.add(nom.getString("id"));


                    }

                    IdLista.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,nombresheroes));


                    IdLista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long heroe){
                            Intent i = new Intent(VentanaBusqueda.this, PerfilHeroe.class);
                            String idheroe = (String) idsheroes.get(position);
                            String nomheroe = (String) nombresheroes.get(position);
                            i.putExtra("id", idheroe);
                            i.putExtra("nomheroe",nomheroe);
                            startActivity(i);

                        }
                    });



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.e("error",error.getMessage());
            }
        });
        mQueue.add(request);
    }


}

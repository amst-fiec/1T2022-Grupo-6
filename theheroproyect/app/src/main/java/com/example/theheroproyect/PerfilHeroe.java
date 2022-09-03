package com.example.theheroproyect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PerfilHeroe extends AppCompatActivity {

    private RequestQueue mqueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_heroe);


        mqueue = Volley.newRequestQueue(this);
        TextView nombreheroe = (TextView) findViewById(R.id.nheroe);
        Bundle bundle = getIntent().getExtras();
        String iddeheroe = bundle.getString("id");
        nombreheroe.setText(bundle.getString("nomheroe"));

        String searchimage = "https://superheroapi.com/api/4204085543050063/"+iddeheroe+"/image";
        String searchpowerstats = "https://superheroapi.com/api/4204085543050063/"+iddeheroe+"/powerstats";
        String searchfullname = "https://superheroapi.com/api/4204085543050063/"+iddeheroe+"/biography";


        fullname(searchfullname);
        imagenherore(searchimage);
        graficobarra(searchpowerstats);



    }

    private void fullname(String url){

        JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            final TextView nombrecompleto = (TextView) findViewById(R.id.ncompleto);
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                try {
                    String fname = response.getString("full-name");
                    nombrecompleto.setText(fname);

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
        mqueue.add(request2);
    }

    private void imagenherore(String url){

        JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            final ImageView imagen = (ImageView) findViewById(R.id.imgheroe);
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                try {
                    String urlimagen =response.getString("url");

                    Picasso.get().load(urlimagen).into(imagen);


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
        mqueue.add(request3);
    }

    private void graficobarra(String url){

        JsonObjectRequest request4 = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                try {
                    int inteligencia =  Integer.valueOf (response.getString("intelligence"));
                    int fuerza =  Integer.valueOf (response.getString("strength"));
                    int velocidad =  Integer.valueOf (response.getString("speed"));
                    int durabilidad =  Integer.valueOf (response.getString("durability"));
                    int poder =  Integer.valueOf (response.getString("power"));
                    int combate =  Integer.valueOf (response.getString("combat"));

                    BarChart barChart = findViewById(R.id.graficobarra);

                    ArrayList<BarEntry> poderes = new ArrayList<>();
                    poderes.add(new BarEntry(1,inteligencia));
                    poderes.add(new BarEntry(2,fuerza));
                    poderes.add(new BarEntry(3,velocidad));
                    poderes.add(new BarEntry(4,durabilidad));
                    poderes.add(new BarEntry(5,poder));
                    poderes.add(new BarEntry(6,combate));

                    BarDataSet barDataSet = new BarDataSet(poderes, " ");
                    barDataSet.setColors(Color.rgb(100,0,255));
                    barDataSet.setValueTextColor(Color.BLACK);
                    barDataSet.setValueTextSize(16f);

                    BarData barData = new BarData(barDataSet);
                    barChart.setFitBars(true);
                    barChart.setData(barData);
                    barChart.getDescription().setText(" ");
                    barChart.animateY(1500);




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
        mqueue.add(request4);
    }



}
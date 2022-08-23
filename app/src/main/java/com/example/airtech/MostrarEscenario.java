package com.example.airtech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;

import com.example.airtech.model.Data;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MostrarEscenario extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_escenario);
        TextView titulo =  (TextView) findViewById(R.id.textView6);
        TextView temp =  (TextView) findViewById(R.id.textView7);
        TextView hum =  (TextView) findViewById(R.id.textView8);
        TextView calidad =  (TextView) findViewById(R.id.textView9);
        TextView direccion =  (TextView) findViewById(R.id.textView10);
        TextView telefono =  (TextView) findViewById(R.id.textView12);
        TextView aforo =  (TextView) findViewById(R.id.textView13);
        Intent intent= getIntent();
        String escen = intent.getStringExtra("id");
        titulo.setText(escen);
        //tv=findViewById(R.id.textView);

        class LooperThread extends Thread {
            public Handler mHandler;

            public void run() {
                Looper.prepare();

                mHandler = new Handler(Looper.myLooper()) {

                };

                Looper.loop();
            }
        }
        FirebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        databaseReference.child(escen).addListenerForSingleValueEvent(new ValueEventListener() {
            //@SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Boolean internet= isOnline();
                databaseReference.child(escen).child("conexion").setValue(internet);
                Data d = snapshot.getValue(Data.class);

                temp.setText("Temperatura: "+ d.getTemp() + "C");
                hum.setText("Humedad: "+ d.getHumedad() + "%");
                calidad.setText("Concentracion de C02: "+ d.getCalidad() + "ppm");
                direccion.setText("Direccion: "+ d.getDireccion() );
                telefono.setText("Telefono de Emergencia: "+ d.getTelefono() );
                aforo.setText("Aforo: "+ d.getAforo() );
                if (d.getAlertaT().equals("peligro")){


                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MostrarEscenario.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
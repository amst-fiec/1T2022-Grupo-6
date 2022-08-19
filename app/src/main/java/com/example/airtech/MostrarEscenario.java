package com.example.airtech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.airtech.model.Data;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        Intent intent= getIntent();
        String escen = intent.getStringExtra("escenario");
        titulo.setText(escen);
        //tv=findViewById(R.id.textView);


        FirebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        databaseReference.child("Escenario1").addListenerForSingleValueEvent(new ValueEventListener() {
            //@SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Data d = snapshot.getValue(Data.class);
                //assert p != null;
                //titulo.setText("Bienvenido "+ d.getAlertaC());
                temp.setText("Temperatura: "+ d.getTemp() + "C");
                hum.setText("Humedad: "+ d.getHumedad() + "%");
                calidad.setText("Concentracion de C02: "+ d.getCalidad() + "ppm");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MostrarEscenario.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
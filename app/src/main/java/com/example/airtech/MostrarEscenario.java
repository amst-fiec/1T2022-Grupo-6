package com.example.airtech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;

import com.example.airtech.model.Data;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class MostrarEscenario extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_escenario);
        TextView titulo = findViewById(R.id.textView6);
        TextView temp = findViewById(R.id.textView7);
        TextView hum = findViewById(R.id.textView8);
        TextView calidad = findViewById(R.id.textView9);
        TextView direccion = findViewById(R.id.textView10);
        TextView telefono = findViewById(R.id.textView12);
        TextView aforo = findViewById(R.id.textView13);

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
        databaseReference.child(escen).addValueEventListener(new ValueEventListener() {
            //@SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                Data d = snapshot.getValue(Data.class);

                temp.setText("Temperatura: "+ d.getTemp() + "C");
                hum.setText("Humedad: "+ d.getHumedad() + "%");
                calidad.setText("Concentracion de C02: "+ d.getCalidad() + "ppm");
                direccion.setText("Direccion: "+ d.getDireccion() );
                telefono.setText("Telefono SOS: "+ d.getTelefono() );
                aforo.setText("Aforo: "+ d.getAforo() );

                if(d.getAlertaT().equals("peligro")){
                    Toast.makeText(MostrarEscenario.this, d.getAlertaT().toUpperCase(Locale.ROOT), Toast.LENGTH_SHORT).show();;
                    }
                if(d.getAlertaH().equals("peligro electricidad")){
                    Toast.makeText(MostrarEscenario.this, d.getAlertaH().toUpperCase(), Toast.LENGTH_SHORT).show();;
                    }
                if(d.getAlertaC().equals("peligro")){
                    Toast.makeText(MostrarEscenario.this, d.getAlertaC().toUpperCase(), Toast.LENGTH_SHORT).show(); ;
                    }




            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MostrarEscenario.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
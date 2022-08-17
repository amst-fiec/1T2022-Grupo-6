package com.example.airtech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrarEscenario extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    int i = 1;
    private LinearLayout layout;

    Button btnRegistrarEscenario;
    ImageView btnEliminar;
    String layouts[] = {"apple", "orange", "lemon", "pear", "grape"};
    String button[] = {"apple1", "orange1", "lemon1", "pear1", "grape1"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_escenario);
        btnRegistrarEscenario = findViewById(R.id.btnAddEsc);
        layout = (LinearLayout) findViewById(R.id.content);
        LayoutInflater inflater = LayoutInflater.from(this);
        FirebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot objSnapchot : snapshot.getChildren()){
                    String etiqueta= objSnapchot.getKey();
                    //String status= "Status: "+ objSnapchot.child("Activacion").getValue();
                    View laViewInflada = inflater.inflate(R.layout.escenario, layout, false);
                    TextView textView = (TextView) laViewInflada.findViewById(R.id.NumeroEscenario);

                    textView.setTextSize(22);
                    textView.setText((etiqueta+"     "));
                    layout.addView(laViewInflada);
                    btnEliminar  = laViewInflada.findViewById(R.id.ImageView02);

                    btnEliminar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(), "Eliminacion Exitosa", Toast.LENGTH_LONG).show();
                            layout.removeView(laViewInflada);
                            //layout.removeViewAt(2);
                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnRegistrarEscenario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View laViewInflada = inflater.inflate(R.layout.escenario, layout, false);
                TextView textView = (TextView) laViewInflada.findViewById(R.id.NumeroEscenario);
                textView.setTextSize(22);
                textView.setText(String.valueOf("  "+i+"     "));
                layout.addView(laViewInflada);
                btnEliminar = laViewInflada.findViewById(R.id.ImageView02);
                btnEliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "H0la", Toast.LENGTH_LONG).show();
                        //layout.removeView(laViewInflada);
                        layout.removeViewAt(2);
                    }
                });
                i++;
            }
        });
    }
}
package com.example.airtech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class RegistrarEscenario extends AppCompatActivity {

    int i = 1;
    private LinearLayout layout;
    Button btnRegistrarEscenario;
    ImageView btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_escenario);
        btnRegistrarEscenario = findViewById(R.id.btnAddEsc);
        layout = (LinearLayout) findViewById(R.id.content);
        LayoutInflater inflater = LayoutInflater.from(this);
        btnRegistrarEscenario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View laViewInflada = inflater.inflate(R.layout.escenario, layout, false);
                TextView textView = (TextView) laViewInflada.findViewById(R.id.NumeroEscenario);
                textView.setTextSize(22);
                textView.setText(String.valueOf("  "+i+"     "));
                layout.addView(laViewInflada);
                btnEliminar = findViewById(R.id.ImageView02);
                btnEliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "H0la", Toast.LENGTH_LONG).show();
                        //layout.removeView(laViewInflada);
                        layout.removeViewAt(3);
                    }
                });
                i++;
            }
        });
    }
}
package com.example.airtech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MostrarEscenario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_escenario);
        TextView titulo =  (TextView) findViewById(R.id.textView6);
        Intent intent= getIntent();
        String escen = intent.getStringExtra("escenario");
        titulo.setText(escen);

    }
}
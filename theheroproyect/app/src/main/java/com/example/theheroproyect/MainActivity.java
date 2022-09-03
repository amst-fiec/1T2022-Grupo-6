package com.example.theheroproyect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buscarHeroe(View view){
        EditText et1 = (EditText) findViewById(R.id.edtxt_busquedaxnombre);
        Intent i1 = new Intent(this, VentanaBusqueda.class );
        i1.putExtra("nombredebusqueda", et1.getText().toString());
        startActivity(i1);
    }
}
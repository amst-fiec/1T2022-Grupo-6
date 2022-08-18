package com.example.airtech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText popup_NameEscen, popup_adress, popup_aforo, popup_numero;
    private Button popup_save, popup_cancel;

    Button btnRegistrarEscenario;
    ImageView btnEliminar;
    ImageView btnEditar;
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
                    btnEditar  = laViewInflada.findViewById(R.id.ImageView01);

                    btnEliminar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(), "Eliminacion Exitosa", Toast.LENGTH_LONG).show();
                            layout.removeView(laViewInflada);
                        }
                    });

                    btnEditar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            createNewEscennaryDialog();
                            //Hacer que aparezcan los datos anteriores
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
                        layout.removeView(laViewInflada);
                    }
                });
                i++;
            }
        });
    }
    public void createNewEscennaryDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View escenarioPopUpView = getLayoutInflater().inflate(R.layout.popup,null);
        popup_NameEscen = (EditText) escenarioPopUpView.findViewById(R.id.newPopUp_Name);
        popup_adress = (EditText) escenarioPopUpView.findViewById(R.id.newPopUp_direccion);
        popup_numero = (EditText) escenarioPopUpView.findViewById(R.id.newPopUp_numero);
        popup_aforo = (EditText) escenarioPopUpView.findViewById(R.id.newPopUp_aforo);

        popup_save = (Button) escenarioPopUpView.findViewById(R.id.saveButton);
        popup_cancel = (Button) escenarioPopUpView.findViewById(R.id.cancelButton);

        dialogBuilder.setView(escenarioPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        popup_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //define save button here!
                //Que las variables se guarden
            }
        });
        popup_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
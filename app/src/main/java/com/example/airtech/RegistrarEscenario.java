package com.example.airtech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.airtech.model.Data;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrarEscenario extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    int i = 0;
    private LinearLayout layout;
    String escenario, numero, aforo, direccion;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText popup_NameEscen;
    private EditText popup_adress;
    private EditText popup_aforo;
    private EditText popup_numero;
    private Button popup_save, popup_cancel;

    Button btnRegistrarEscenario;
    ImageView btnEliminar;
    ImageView btnEditar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_escenario);
        btnRegistrarEscenario = findViewById(R.id.btnAddEsc);
        layout = findViewById(R.id.content);
        LayoutInflater inflater = LayoutInflater.from(this);
        FirebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (int iter = 0; iter<i;iter++ ){
                    layout.removeViewAt(0);


                }
                i=0;
                for (DataSnapshot objSnapchot : snapshot.getChildren()){
                    String etiqueta= objSnapchot.getKey();
                    //String status= "Status: "+ objSnapchot.child("Activacion").getValue();
                    View laViewInflada = inflater.inflate(R.layout.escenario, layout, false);
                    TextView textView = laViewInflada.findViewById(R.id.NumeroEscenario);

                    textView.setTextSize(18);
                    textView.setText((etiqueta));
                    layout.addView(laViewInflada);
                    btnEliminar  = laViewInflada.findViewById(R.id.ImageView02);
                    btnEditar  = laViewInflada.findViewById(R.id.ImageView01);
                    btnEliminar.setOnClickListener(v -> {

                        //layout.removeView(laViewInflada);
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegistrarEscenario.this);
                        builder.setTitle(R.string.app_name);
                        builder.setIcon(R.drawable.basura);
                        builder.setMessage("'¿Seguro quiére eliminar el escenario?");
                        builder.setPositiveButton("Si", (dialog, id) -> {


                            Toast.makeText(getApplicationContext(), "Eliminacion Exitosa", Toast.LENGTH_LONG).show();
                            databaseReference.child(String.valueOf(etiqueta)).setValue(null);
                            for (int iter = 0; iter < i; iter++) {
                                layout.removeViewAt(0);


                            }
                            i = 0;
                        });
                        builder.setNegativeButton("No", (dialog, id) -> dialog.dismiss());
                        AlertDialog alert = builder.create();
                        alert.show();
                    });
                    btnEliminar.setOnClickListener(v -> {

                        //layout.removeView(laViewInflada);
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegistrarEscenario.this);
                        builder.setTitle(R.string.app_name);
                        builder.setIcon(R.drawable.basura);
                        builder.setMessage("¿Seguro desea eliminar el escenario?");
                        builder.setPositiveButton("Si", (dialog, id) -> {


                            Toast.makeText(getApplicationContext(), "Eliminación Exitosa", Toast.LENGTH_LONG).show();
                            databaseReference.child(String.valueOf(etiqueta)).setValue(null);
                            for (int iter = 0; iter < i; iter++) {
                                layout.removeViewAt(0);


                            }
                            i = 0;
                        });
                        builder.setNegativeButton("No", (dialog, id) -> dialog.dismiss());
                        AlertDialog alert = builder.create();
                        alert.show();
                    });

                    btnEditar.setOnClickListener(v -> databaseReference.child("Escenario1").addListenerForSingleValueEvent(new ValueEventListener() {
                        //@SuppressLint("SetTextI18n")
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot1) {
                            Data d = snapshot1.getValue(Data.class);
                            String escenario = (String) textView.getText();
                            dialogBuilder = new AlertDialog.Builder(RegistrarEscenario.this);
                            final View escenarioPopUpView = getLayoutInflater().inflate(R.layout.popup1,null);

                            popup_adress = escenarioPopUpView.findViewById(R.id.newPopUp_direccion);
                            popup_numero = escenarioPopUpView.findViewById(R.id.newPopUp_numero);
                            popup_aforo = escenarioPopUpView.findViewById(R.id.newPopUp_aforo);

                            popup_save = escenarioPopUpView.findViewById(R.id.saveButton);
                            popup_cancel = escenarioPopUpView.findViewById(R.id.cancelButton);

                            dialogBuilder.setView(escenarioPopUpView);
                            dialog = dialogBuilder.create();
                            dialog.show();

                            popup_save.setOnClickListener(v12 -> {
                                aforo = popup_aforo.getText().toString().trim();
                                numero = popup_numero.getText().toString().trim();
                                direccion = popup_adress.getText().toString().trim();

                                if (numero.equals("") || aforo.equals("") || direccion.equals("")) {
                                    if (numero.equals("") && aforo.equals("")) {
                                        databaseReference.child(escenario).child("direccion").setValue(direccion);
                                    }
                                    if (direccion.equals("") && aforo.equals("")) {
                                        databaseReference.child(escenario).child("telefono").setValue(numero);
                                    }
                                    if (direccion.equals("") && numero.equals("")) {
                                        databaseReference.child(escenario).child("aforo").setValue(aforo);
                                    }
                                    if (direccion.equals("") && numero.equals("") && aforo.equals("")) {
                                        Toast.makeText(getApplicationContext(), "Por favor ingrese un parametro a editar", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    databaseReference.child(escenario).child("aforo").setValue(aforo);
                                    databaseReference.child(escenario).child("direccion").setValue(direccion);
                                    databaseReference.child(escenario).child("telefono").setValue(numero);

                                }


                                dialog.dismiss();
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            });
                            popup_cancel.setOnClickListener(v1 -> dialog.dismiss());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(RegistrarEscenario.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
                        }
                    }));
                    i=i+1;
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnRegistrarEscenario.setOnClickListener(view -> {
            final View escenarioPopUpView = getLayoutInflater().inflate(R.layout.popup,null);
            popup_NameEscen = escenarioPopUpView.findViewById(R.id.newPopUp_Name);
            dialogBuilder = new AlertDialog.Builder(RegistrarEscenario.this);
            dialogBuilder.setView(escenarioPopUpView);
            dialog = dialogBuilder.create();
            dialog.show();
            popup_adress = escenarioPopUpView.findViewById(R.id.newPopUp_direccion);
            popup_numero = escenarioPopUpView.findViewById(R.id.newPopUp_numero);
            popup_aforo = escenarioPopUpView.findViewById(R.id.newPopUp_aforo);

            popup_save = escenarioPopUpView.findViewById(R.id.saveButton);
            popup_cancel = escenarioPopUpView.findViewById(R.id.cancelButton);


            popup_save.setOnClickListener(v -> {
                //define save button here!

                if (validaciones()) {
                    Toast.makeText(getApplicationContext(), "Registro Exitoso", Toast.LENGTH_LONG).show();
                    databaseReference.child(popup_NameEscen.getText().toString()).setValue("");

                    databaseReference.child(String.valueOf(popup_NameEscen.getText())).child("direccion").setValue(popup_adress.getText().toString());

                    databaseReference.child(String.valueOf(popup_NameEscen.getText())).child("aforo").setValue(popup_aforo.getText().toString());


                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    i = 0;
                }
            });




            popup_cancel.setOnClickListener(v -> dialog.dismiss());




        });
    }
    public boolean validaciones(){
        final View escenarioPopUpView = getLayoutInflater().inflate(R.layout.popup,null);
        popup_adress = escenarioPopUpView.findViewById(R.id.newPopUp_direccion);
        popup_numero = escenarioPopUpView.findViewById(R.id.newPopUp_numero);
        popup_aforo = escenarioPopUpView.findViewById(R.id.newPopUp_aforo);
        popup_save = escenarioPopUpView.findViewById(R.id.saveButton);
        popup_cancel = escenarioPopUpView.findViewById(R.id.cancelButton);
        escenario= popup_NameEscen.getText().toString();
        direccion=popup_adress.getText().toString();
        aforo=popup_aforo.getText().toString();
        numero= popup_numero.getText().toString();

        if (false){
            Toast.makeText(getApplicationContext(), "Eliminación Exitosa", Toast.LENGTH_LONG).show();
            if (escenario.equals("")){
                popup_NameEscen.setError("Se requiere el nombre de escenario");
            }
            if (direccion.equals("")) {
                popup_adress.setError("Se requiere dirección");
            }
            if (aforo.equals("")){
                popup_aforo.setError("Se requiere aforo");
            }
            if (numero.equals("")){
                popup_aforo.setError("Se requiere numero de emergencia");
            }


            return false;
        }

        else
            return true;

    }

}
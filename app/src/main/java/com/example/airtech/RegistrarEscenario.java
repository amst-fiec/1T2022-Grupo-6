package com.example.airtech;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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
        ScrollView sv = (ScrollView) findViewById((R.id.scrollView)) ;
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
                for (int iter = 0; iter<i;iter++ ){
                    layout.removeViewAt(0);


                }
                i=0;
                for (DataSnapshot objSnapchot : snapshot.getChildren()){
                    String etiqueta= objSnapchot.getKey();
                    //String status= "Status: "+ objSnapchot.child("Activacion").getValue();
                    View laViewInflada = inflater.inflate(R.layout.escenario, layout, false);
                    TextView textView = (TextView) laViewInflada.findViewById(R.id.NumeroEscenario);

                    textView.setTextSize(18);
                    textView.setText((etiqueta));
                    layout.addView(laViewInflada);
                    btnEliminar  = laViewInflada.findViewById(R.id.ImageView02);
                    btnEditar  = laViewInflada.findViewById(R.id.ImageView01);
                    btnEliminar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //layout.removeView(laViewInflada);
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegistrarEscenario.this);
                            builder.setTitle(R.string.app_name);
                            builder.setIcon(R.drawable.basura);
                            builder.setMessage("'¿Seguro quiére eliminar el escenario?");
                            builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {


                                    Toast.makeText(getApplicationContext(), "Eliminacion Exitosa", Toast.LENGTH_LONG).show();
                                    databaseReference.child(String.valueOf(etiqueta)).setValue(null);
                                    for (int iter = 0; iter<i;iter++ ){
                                        layout.removeViewAt(0);


                                    }
                                    i=0;
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                    });
                    btnEliminar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //layout.removeView(laViewInflada);
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegistrarEscenario.this);
                            builder.setTitle(R.string.app_name);
                            builder.setIcon(R.drawable.basura);
                            builder.setMessage("¿Seguro desea eliminar el escenario?");
                            builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {


                                    Toast.makeText(getApplicationContext(), "Eliminación Exitosa", Toast.LENGTH_LONG).show();
                                    databaseReference.child(String.valueOf(etiqueta)).setValue(null);
                                    for (int iter = 0; iter<i;iter++ ){
                                        layout.removeViewAt(0);


                                    }
                                    i=0;
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                    });

                    btnEditar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            databaseReference.child("Escenario1").addListenerForSingleValueEvent(new ValueEventListener() {
                                //@SuppressLint("SetTextI18n")
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    Data d = snapshot.getValue(Data.class);
                                    String escenario = (String) textView.getText();
                                    dialogBuilder = new AlertDialog.Builder(RegistrarEscenario.this);
                                    final View escenarioPopUpView = getLayoutInflater().inflate(R.layout.popup1,null);

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
                                            d.setAforo(popup_aforo.getText().toString());
                                            d.setTelefono(popup_aforo.getText().toString());
                                            d.setDireccion(popup_aforo.getText().toString());

                                            databaseReference.child(escenario).child("aforo").setValue(popup_aforo.getText().toString());

                                            databaseReference.child(escenario).child("telefono").setValue(popup_numero.getText().toString());
                                            dialog.dismiss();
                                            Intent intent=getIntent();
                                            finish();
                                            startActivity(intent);
                                        }
                                    });
                                    popup_cancel.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                        }
                                    });
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(RegistrarEscenario.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    i=i+1;
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnRegistrarEscenario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder = new AlertDialog.Builder(RegistrarEscenario.this);
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

                        databaseReference.child(popup_NameEscen.getText().toString()).setValue("");

                        databaseReference.child(String.valueOf(popup_NameEscen.getText())).child("direccion").setValue(popup_adress.getText().toString());

                        databaseReference.child(String.valueOf(popup_NameEscen.getText())).child("aforo").setValue(popup_aforo.getText().toString());

                        databaseReference.child(String.valueOf(popup_NameEscen.getText())).child("telefono").setValue(popup_numero.getText().toString());
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);

                    }

                });

                i=0;
                popup_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });



            }
        });
    }

}
package com.amst.popupform;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btnOpenFormulary;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText popup_NameEscen, popup_adress, popup_aforo, popup_numero;
    private Button popup_save, popup_cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOpenFormulary = findViewById(R.id.btnRegis);
        btnOpenFormulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewEscennaryDialog();
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
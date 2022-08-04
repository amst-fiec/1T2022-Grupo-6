package com.example.airtech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MenuPrincipal extends AppCompatActivity {
    Button btnCerrarSesion;
    Button btnRegistrar;
    private FirebaseAuth mAuth;
    //Variables opcionales para desloguear de google tambien
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        btnCerrarSesion = findViewById(R.id.btnSignOff);
        btnRegistrar = findViewById(R.id.btnRegis);
        mAuth = FirebaseAuth.getInstance();
        TextView tv1 =  (TextView) findViewById(R.id.textView2);
        TextView tv2 =  (TextView) findViewById(R.id.textView3);
        TextView tv3 =  (TextView) findViewById(R.id.textView4);

        tv1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuPrincipal.this, MostrarEscenario.class);

                i.putExtra("escenario",tv1.getText().toString());
                startActivity(i);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuPrincipal.this, MostrarEscenario.class);
                i.putExtra("escenario",tv2.getText().toString());
                startActivity(i);
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuPrincipal.this, MostrarEscenario.class);
                i.putExtra("escenario",tv3.getText().toString());
                startActivity(i);
            }
        });
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //Configurar las gso para google signIn con el fin de desloguear de google
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cerrar sesión con firebase
                mAuth.signOut();

                //Cerrar sesión con google tambien: Google sign out
                mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //Abrir MainActivity con SigIn button
                        if(task.isSuccessful()){
                            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(mainActivity);
                            MenuPrincipal.this.finish();
                        }else{
                            Toast.makeText(getApplicationContext(), "No se pudo cerrar sesión con google",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
    public void RegistrarEscen(View v){

        Intent i = new Intent(this, RegistrarEscenario.class);
        startActivity(i);
    }
}
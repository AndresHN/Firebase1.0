package com.example.proyectoautenticacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginScreen extends AppCompatActivity {

    private EditText usuario, contraseña;
    private Button ingresar;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        firebaseAuth = FirebaseAuth.getInstance();

        usuario = (EditText) findViewById(R.id.Textuser);
        contraseña = (EditText) findViewById(R.id.Textpass);

        ingresar = (Button) findViewById(R.id.btning);
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ingresar = new Intent(LoginScreen.this, MainActivity.class);
                startActivity(ingresar);

            }
        });
    }

    private void registrarUsuario(){

        String user = usuario.getText().toString().trim();
        String password = contraseña.getText().toString().trim();

        if(TextUtils.isEmpty(user)){
            Toast.makeText(this,"Se debe guardar un usuario", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(user, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task){
                         if(task.isSuccessful()){

                             Toast.makeText(LoginScreen.this, "Se ha registrado el user", Toast.LENGTH_SHORT).show();
                         }else{
                             Toast.makeText(LoginScreen.this, "No se puedo registrar el usuario", Toast.LENGTH_LONG).show();
                         }
                }
            });
    }

    public void onClick(View view){
            registrarUsuario();
    }
}

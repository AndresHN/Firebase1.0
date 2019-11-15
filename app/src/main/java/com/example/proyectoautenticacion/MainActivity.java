package com.example.proyectoautenticacion;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
Spinner seccion;
Spinner asignaturas;
EditText txtTema;
Spinner spinAreas, spinSecciones;
Button btnRegistrar;
private DatabaseReference Clases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seccion = (Spinner) findViewById(R.id.spinseccion);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this, R.array.seccion, android.R.layout.simple_spinner_item);
        seccion.setAdapter(adapter);

        asignaturas = (Spinner) findViewById(R.id.spinarea);
        ArrayAdapter<CharSequence> adapterr = ArrayAdapter
                .createFromResource(this, R.array.asignaturas, android.R.layout.simple_spinner_item);
        asignaturas.setAdapter(adapterr);

        Clases = FirebaseDatabase.getInstance().getReference("Clases");

        txtTema = (EditText) findViewById(R.id.txttema);
        spinAreas = (Spinner) findViewById(R.id.spinarea);
        spinSecciones = (Spinner) findViewById(R.id.spinseccion);
        btnRegistrar = (Button) findViewById(R.id.btnregistrar);

    }

    public void registrarClase(){
        String seccion = spinSecciones.getSelectedItem().toString();
        String area = spinAreas.getSelectedItem().toString();
        String tema = txtTema.getText().toString();

        if (!TextUtils.isEmpty(tema)) {
            String id = Clases.push().getKey();
            Clases leccion = new Clases(id, seccion, area, tema);
            Clases.child("users").child(id).setValue(leccion);

            Toast.makeText(this, "Clase registrada", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,  "Debe introducir un tema", Toast.LENGTH_LONG).show();

        }
    }
}

package com.example.contactos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;

public class NuevoContacto extends AppCompatActivity {

    EditText txtNombre, txtApellido, txtTelefono, txtCorreo;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_contacto);

        //asignar los editText
        txtNombre=(EditText)findViewById(R.id.txtNombre);
        txtApellido=(EditText)findViewById(R.id.txtApellido);
        txtTelefono=(EditText)findViewById(R.id.txtTelefono);
        txtCorreo=(EditText)findViewById(R.id.txtCorreo);
        btnGuardar=(Button)findViewById(R.id.btnGuardar);

        //conexion a la bd
        bdManager bd = new bdManager(getApplicationContext());

        // GUardar datos ingresados, en la table contacto
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                //verificar si los campos estan vacios
                if (txtNombre.getText().toString().isEmpty() || txtApellido.getText().toString().isEmpty() || txtTelefono.getText().toString().isEmpty() || txtCorreo.getText().toString().isEmpty()) {
                    Toast.makeText(NuevoContacto.this, "ASEGURATE DE LLENAR TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
                }else{
                    // guardar datos
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    bd.agregarContacto(txtNombre.getText().toString()+" "+txtApellido.getText().toString(), txtTelefono.getText().toString(), txtCorreo.getText().toString());
                    Toast.makeText(NuevoContacto.this, "SE AGREGO CORRECTAMENTE", Toast.LENGTH_LONG).show();
                    // volver al main depeus de guardar los datos
                    startActivity(intent);
                }
                finish();
            }
        });

    }
}
package com.example.contactos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DetalleContacto extends AppCompatActivity {
    TextView tvNombre, tvTelefono, tvCorreo;
    Button button;
    String telefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);

        //recibo nombre desde el main
        Bundle bundle = getIntent().getExtras();
        String nombre = bundle.getString("NOMBRECONTACTO");

        //asignar los edit text
        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvTelefono = (TextView) findViewById(R.id.tvTelefono);
        tvCorreo = (TextView) findViewById(R.id.tvCorreo);
        button = (Button)findViewById(R.id.btnLlamar);
        tvNombre.setText(nombre);

        // configuracion y conexion a bd
        bdManager bd = new bdManager(getApplicationContext());
        String buscar = nombre;
        String[] datos;
        datos=bd.buscar_reg(buscar);
        //mostrar la info almacenada en datos resultado de la consulta
        tvTelefono.setText(datos[1]);
        tvCorreo.setText(datos[2]);

        // usamos esta variable para el telefono
        telefono = datos[1];
    }

    public void llamar(View v) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+telefono));
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(DetalleContacto.this, "ACTIVE EL PERMISO DE LLAMADAS", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(intent);
    }
}
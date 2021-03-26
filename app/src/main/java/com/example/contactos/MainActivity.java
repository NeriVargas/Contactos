package com.example.contactos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvContactos;
    ArrayList<String>lista;
    ArrayAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //configuracion lista y conexion a bd
        lvContactos = (ListView) findViewById(R.id.lvContactos);
        bdManager bd = new bdManager(getApplicationContext());
        lista = bd.llenar_lv();
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,lista);
        lvContactos.setAdapter(adaptador);

        //enviar nombre a detalle para hacer consulta
        lvContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int position, long l) {
                Intent intent = new Intent(MainActivity.this, DetalleContacto.class);
                intent.putExtra("NOMBRECONTACTO", lista.get(position));
                startActivity(intent);
            }
        });
    }

    // ir al apartado de nuevo contacto
    public void agregar(View view) {
        Intent intent = new Intent(this, NuevoContacto.class);
        startActivity(intent);
    }
}
package com.example.contactos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class bdManager extends SQLiteOpenHelper {
        private static final String NOMBRE_BD="example.bd";
        private static final int VERSION_BD=1;
        private static final String TABLA_CONTACTO="CREATE TABLE CONTACTO (NOMBRE TEXT PRIMARY KEY, TELEFONO TEXT, CORREO TEXT)";

        public bdManager(Context context){
            super(context, NOMBRE_BD, null, VERSION_BD);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(TABLA_CONTACTO);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLA_CONTACTO);
            sqLiteDatabase.execSQL(TABLA_CONTACTO);
        }

        public  void  agregarContacto(String nombre, String telefono, String correo){
            SQLiteDatabase bd=getWritableDatabase();
            if(bd!=null){
                bd.execSQL("INSERT INTO CONTACTO VALUES('"+nombre+"', '"+telefono+"', '"+correo+"')");
                bd.close();
            }
        }
        public ArrayList llenar_lv(){
            ArrayList<String> lista = new ArrayList<>();
            SQLiteDatabase bd = this.getReadableDatabase();
            String q = "SELECT * FROM CONTACTO";
            Cursor registros = bd.rawQuery(q,null);
            if (registros.moveToFirst()){
                do {
                    lista.add(registros.getString(0));
                }while(registros.moveToNext());
            }
            return lista;
        }
        public  String[] buscar_reg(String buscar) {
            String[] datos = new String[3];
            SQLiteDatabase bd = this.getWritableDatabase();
            String q = "SELECT * FROM CONTACTO WHERE NOMBRE ='"+buscar+"'";
            Cursor registros = bd.rawQuery(q,null);
            if (registros.moveToFirst()){
                for (int i = 0; i <= 2; i++) {
                    datos[i] = registros.getString(i);
                }
            }
            return datos;
        }
}
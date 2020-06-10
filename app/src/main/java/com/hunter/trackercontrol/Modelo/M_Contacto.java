package com.hunter.trackercontrol.Modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.hunter.trackercontrol.BD.LocalBD;
import com.hunter.trackercontrol.BD.T_Contacto;

import java.util.ArrayList;

public class M_Contacto {

    private LocalBD localBD;
    private SQLiteDatabase sqLiteDatabase;


    public int traeConfiguracionServidor(Context context, String tipoUsuario){
        int conteo = 0;
        Cursor registros = null;
        try{
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros = sqLiteDatabase.rawQuery(T_Contacto.SELECT_CONTACTO(tipoUsuario), null);
            if(registros.moveToFirst()){
                conteo = registros.getCount();
            }while (registros.moveToNext());
        }finally {
            if(registros != null){
                registros.close();
                sqLiteDatabase.close();
            }
        }
        return conteo;
    }

    public String muestraNumeroAdmin(Context context){
        String data = "";
        Cursor registros = null;
        try{
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros = sqLiteDatabase.rawQuery(T_Contacto.SELECT_CONTACTO("ADMINISTRADOR"), null);
            if(registros.moveToFirst()){
                data = registros.getString(0);
            }while (registros.moveToNext());
        }finally {
            if(registros != null){
                registros.close();
                sqLiteDatabase.close();
            }
        }
        return data;
    }

    public void insertarContacto(Context context, String con_TipoNumero, String con_Numero, String con_NombreCon){
        try {
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            sqLiteDatabase.execSQL(T_Contacto.INSERT_CONTACTO(con_TipoNumero, con_Numero, con_NombreCon));
            Toast.makeText(context, "Contacto guardado", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(context, "Error de inserción\n¡Verifique número repetido!", Toast.LENGTH_SHORT).show();

        }finally {
            sqLiteDatabase.close();
        }
    }

    public void mostrarContactos(Context context, GridView gridView){
        Cursor registros = null;

        try {
            ArrayList<String> lista = new ArrayList<>();
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros = sqLiteDatabase.rawQuery(T_Contacto.SELECT_CONTACTO("USUARIO"), null);

            if(registros.moveToFirst()){
                do{
                    lista.add(registros.getString(0));
                    lista.add(registros.getString(1));
                }while (registros.moveToNext());
            }
            ArrayAdapter adapter;
            adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, lista);
            gridView.setAdapter(adapter);

        }finally {
            if(registros != null){
                registros.close();
                sqLiteDatabase.close();
            }
        }
    }


}

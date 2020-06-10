package com.hunter.trackercontrol.Modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.hunter.trackercontrol.BD.LocalBD;
import com.hunter.trackercontrol.BD.T_GeoLocalizacion;

import java.util.ArrayList;

public class M_GeoLocalizacion {

    private LocalBD localBD;
    private SQLiteDatabase sqLiteDatabase;

    public void insertaHistorial(Context context, String geoLocFecha, String geoLocFechaHora, String geoLocMapa){
        try {
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            sqLiteDatabase.execSQL(T_GeoLocalizacion.INSERT_GEOLOCALIZACION(geoLocFecha, geoLocFechaHora, geoLocMapa));
        } finally {
            sqLiteDatabase.close();
        }
    }

    public void mostrarHistorial(Context context, String fecha, GridView gridView){
        Cursor registros = null;

        try {
            ArrayList<String> lista = new ArrayList<>();
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros = sqLiteDatabase.rawQuery(T_GeoLocalizacion.SELECT_GEOLOCALIZACION(fecha), null);

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

package com.hunter.trackercontrol.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalBD extends SQLiteOpenHelper {

    private static final String baseDatosLocal = "BdControlTracker";
    private static final int version = 1;

    public LocalBD(Context context) {
        super(context, baseDatosLocal, null, version);
        this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(T_Contacto.CREATE_TABLA);
        db.execSQL(T_GeoLocalizacion.CREATE_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(T_Contacto.DROP_TABLA);
        db.execSQL(T_GeoLocalizacion.DROP_TABLA);
        onCreate(db);
    }
}

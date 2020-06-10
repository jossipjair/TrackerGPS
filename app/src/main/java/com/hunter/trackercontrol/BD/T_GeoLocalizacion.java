package com.hunter.trackercontrol.BD;

public class T_GeoLocalizacion {

    private static final String TABLA = "GeoLocalizacion";

    private static final String GEOLOC_ID = "GeoLoc_Id";
    private static final String GEOLOC_FECHA = "GeoLoc_Fecha";
    private static final String GEOLOC_FECHAHORA = "GeoLoc_FechaHora";
    private static final String GEOLOC_MAPA = "GeoLoc_Mapa";

    public static final String CREATE_TABLA = "CREATE TABLE " + TABLA + "("
            + GEOLOC_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + GEOLOC_FECHA + " TEXT NOT NULL,"
            + GEOLOC_FECHAHORA + " TEXT NOT NULL,"
            + GEOLOC_MAPA + " TEXT NOT NULL);";

    public static final String DROP_TABLA = "DROP TABLE IF EXITS " + TABLA + ";";

    public static final String INSERT_GEOLOCALIZACION(String geoLocFecha, String geoLocFechaHora, String geoLocMapa){
        return "INSERT INTO " + TABLA + "(" + GEOLOC_FECHA + "," + GEOLOC_FECHAHORA + "," + GEOLOC_MAPA + ") VALUES('"
                + geoLocFecha + "','" + geoLocFechaHora + "','" + geoLocMapa + "');";
    }

    public static final String SELECT_GEOLOCALIZACION(String fecha){
        return "SELECT " + GEOLOC_FECHAHORA + "," + GEOLOC_MAPA + " FROM " + TABLA + " WHERE " + GEOLOC_FECHA + "='" + fecha + "';";
    }


}

package com.hunter.trackercontrol.BD;

public class T_Contacto {

    private static final String TABLA = "Contacto";

    private static final String CON_ID = "Con_Id";
    private static final String CON_TIPONUMERO = "Con_TipoNumero";
    private static final String CON_NUMERO = "Con_Numero";
    private static final String CON_NOMBRECON = "Con_NombreCon";

    public static final String CREATE_TABLA = "CREATE TABLE " + TABLA + "("
            + CON_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + CON_TIPONUMERO + " TEXT NOT NULL," //ADMINISTRADOR / USUARIO
            + CON_NUMERO + " TEXT NOT NULL,"
            + CON_NOMBRECON + " TEXT NOT NULL, CONSTRAINT unq_Numero UNIQUE(Con_Numero));";

    public static final String DROP_TABLA = "DROP TABLE IF EXISTS " + TABLA + ";";

    public static final String INSERT_CONTACTO(String con_TipoNumero, String con_Numero, String con_NombreCon){
        return "INSERT INTO " + TABLA + "(" + CON_TIPONUMERO + "," + CON_NUMERO + "," + CON_NOMBRECON + ") VALUES('"
                + con_TipoNumero + "','" + con_Numero + "','" + con_NombreCon +"');";
    }

    public static final String SELECT_CONTACTO(String con_TipoNumero){
        return "SELECT " + CON_NUMERO + "," + CON_NOMBRECON + " FROM " + TABLA + " WHERE " + CON_TIPONUMERO + "='" + con_TipoNumero  + "';";
    }


}
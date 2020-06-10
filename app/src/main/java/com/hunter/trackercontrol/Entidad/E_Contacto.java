package com.hunter.trackercontrol.Entidad;

public class E_Contacto {

    private int Con_Id;
    private String Con_TipoNumero;
    private String Con_Numero;
    private String Con_NombreCon;

    public E_Contacto(int con_Id, String con_TipoNumero, String con_Numero, String con_NombreCon) {
        Con_Id = con_Id;
        Con_TipoNumero = con_TipoNumero;
        Con_Numero = con_Numero;
        Con_NombreCon = con_NombreCon;
    }

    public E_Contacto() {
    }

    public int getCon_Id() {
        return Con_Id;
    }

    public void setCon_Id(int con_Id) {
        Con_Id = con_Id;
    }

    public String getCon_TipoNumero() {
        return Con_TipoNumero;
    }

    public void setCon_TipoNumero(String con_TipoNumero) {
        Con_TipoNumero = con_TipoNumero;
    }

    public String getCon_Numero() {
        return Con_Numero;
    }

    public void setCon_Numero(String con_Numero) {
        Con_Numero = con_Numero;
    }

    public String getCon_NombreCon() {
        return Con_NombreCon;
    }

    public void setCon_NombreCon(String con_NombreCon) {
        Con_NombreCon = con_NombreCon;
    }
}

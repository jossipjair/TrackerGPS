package com.hunter.trackercontrol.Controlador;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.DialogFragment;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.hunter.trackercontrol.Modelo.M_Contacto;
import com.hunter.trackercontrol.Modelo.M_GeoLocalizacion;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyReceiver extends BroadcastReceiver {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG = "SmsBroadcastReceiver";
    String msj, phoneNo = "";
    private static String nroAdministrador = "";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() == SMS_RECEIVED) {
            Bundle dataBundle = intent.getExtras();
            if (dataBundle != null) {

                Object[] mypdu = (Object[]) dataBundle.get("pdus");
                final SmsMessage[] message = new SmsMessage[mypdu.length];
                for (int i = 0; i < mypdu.length; i++) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        String format = dataBundle.getString("format");
                        message[i] = SmsMessage.createFromPdu((byte[]) mypdu[i], format);
                    } else {
                        message[i] = SmsMessage.createFromPdu((byte[]) mypdu[i]);
                    }

                    //Trae Numero Administrador
                    M_Contacto m_contacto = new M_Contacto();
                    nroAdministrador = m_contacto.muestraNumeroAdmin(context);

                    //Captura de mensaje
                    msj = message[i].getMessageBody();
                    phoneNo = message[i].getOriginatingAddress();
                    String[] dataSms = msj.split(":-");
                    String smsMapa = dataSms[0];

                    //Valida que solo lea los mensajes que viene desde el numero servidor
                    if (phoneNo.equals("+51" + nroAdministrador) && msj.equals("monitor ok!")) {
                        //Todo: Modo Microfono
                        try {
                            if (nroAdministrador.length() > 0) {
                                String numero = "tel:" + nroAdministrador;
                                Intent llamada = new Intent(Intent.ACTION_CALL, Uri.parse((numero)));
                                llamada.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(llamada);
                                Toast.makeText(context, "Modo Micrófono activo", Toast.LENGTH_SHORT).show();
                            }
                        } catch (ActivityNotFoundException activityException) {
                            Toast.makeText(context, "¡Error en llamada!", Toast.LENGTH_SHORT).show();
                        }

                    } else if (phoneNo.equals("+51" + nroAdministrador) && msj.equals("Stop engine Succeed")) {
                        //Todo: Modo apagar motor
                        Toast.makeText(context, "Energía Motor Inactiva", Toast.LENGTH_SHORT).show();
                        VariableGlobal.ESTATUS_MOTOR = true;


                    } else if (phoneNo.equals("+51" + nroAdministrador) && msj.equals("Resume engine Succeed")) {
                        //Todo: Modo enciende motor
                        Toast.makeText(context, "Energia Motor Activa", Toast.LENGTH_SHORT).show();
                        VariableGlobal.ESTATUS_MOTOR =  false;


                    } else if (phoneNo.equals("+51" + nroAdministrador) && msj.equals("tracker ok!")) {
                        //Todo: Modo Mapa
                        Toast.makeText(context, "¡Timbrar hasta llegar a buzón de voz! ", Toast.LENGTH_SHORT).show();
                        try {
                            if (nroAdministrador.length() > 0) {
                                String numero = "tel:" + nroAdministrador;
                                Intent llamada = new Intent(Intent.ACTION_CALL, Uri.parse((numero)));
                                llamada.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(llamada);
                            }
                        } catch (ActivityNotFoundException activityException) {
                            Toast.makeText(context, "¡Error en llamada!", Toast.LENGTH_SHORT).show();
                        }
                    } else if (phoneNo.equals("+51" + nroAdministrador) && smsMapa.equals("lat")) {

                        String lineaUno = dataSms[1];
                        String lineaDos = dataSms[2];
                        String latitud = "", longitud = "", linkMapa = "";
                        latitud = "-" + lineaUno.substring(0, lineaUno.length() - 3);
                        longitud = "-" + lineaDos.substring(0, 10);

                        linkMapa = "http://maps.google.com/maps?f=q&q=" + latitud.trim() + "," + longitud + "&z=16";

                        Intent mapa = new Intent(Intent.ACTION_VIEW, Uri.parse((linkMapa)));
                        mapa.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(mapa);

                        //Insercion de Historial
                        M_GeoLocalizacion m_geoLocalizacion = new M_GeoLocalizacion();
                        Calendar Cal = new GregorianCalendar();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat dfH = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        String fecha = (df.format(Cal.getInstance().getTime()).toString());
                        String fechaHora = (dfH.format(Cal.getInstance().getTime()).toString());

                        m_geoLocalizacion.insertaHistorial(context, fecha, fechaHora, linkMapa);

                        //FIN INSERCION
                    }
                }
            }

        }
    }


}
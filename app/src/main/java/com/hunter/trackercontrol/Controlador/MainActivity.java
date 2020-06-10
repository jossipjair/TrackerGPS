package com.hunter.trackercontrol.Controlador;

import android.Manifest;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.telephony.SmsManager;
import android.widget.TextView;
import android.widget.Toast;

import com.hunter.trackercontrol.Modelo.M_Contacto;
import com.hunter.trackercontrol.R;

public class MainActivity extends AppCompatActivity {

    private ImageButton imbMicrofono;
    private ImageButton imbMotorEncender;
    private ImageButton imbMotorApagar;
    private ImageButton imbVisualizaRutas;
    private TextView lblMotorApagar;
    private TextView lblMotorEncender;

    private static final String INBOX = "content://sms/inbox"; //Ingreso a la bandeja de entrada
    private static String nroAdministrador = "";
    private int estadoConf = 0;
    String mensaje = "";

    M_Contacto m_contacto = new M_Contacto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imbMicrofono = findViewById(R.id.imbMicrofono);
        imbMotorEncender = findViewById(R.id.imbMotorEncender);
        imbMotorApagar = findViewById(R.id.imbMotorApagar);
        imbVisualizaRutas = findViewById(R.id.imbVisualizaRutas);
        lblMotorApagar = findViewById(R.id.lblMotorApagar);
        lblMotorEncender = findViewById(R.id.lblMotorEncender);


        if(VariableGlobal.ESTATUS_MOTOR){
            imbMotorEncender.setVisibility(View.INVISIBLE);
            lblMotorEncender.setVisibility(View.INVISIBLE);
            imbMotorApagar.setVisibility(View.VISIBLE);
            lblMotorApagar.setVisibility(View.VISIBLE);
        }else {
            imbMotorEncender.setVisibility(View.VISIBLE);
            lblMotorEncender.setVisibility(View.VISIBLE);
            imbMotorApagar.setVisibility(View.INVISIBLE);
            lblMotorApagar.setVisibility(View.INVISIBLE);
        }


        //SI NO SE ENCUENTRA NUMERO ADMINISTRADOR EN LA BD
        if (m_contacto.traeConfiguracionServidor(MainActivity.this, "ADMINISTRADOR") == 0) {
            Intent intent = new Intent(MainActivity.this, Configuracion.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
        //


        //Solicitar permiso en tiempo de ejecucion SMS
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        int permissionCheckREAD = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
        if (permissionCheck == PackageManager.PERMISSION_DENIED || permissionCheckREAD == PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
            }

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 1);
            }
        }
        //

        //Trae Numero Administrador
        M_Contacto m_contacto = new M_Contacto();
        nroAdministrador = m_contacto.muestraNumeroAdmin(MainActivity.this);

        imbMicrofono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarComando("Monitor123456", "Modo Micrófono");
            }
        });

        imbMotorEncender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enviarComando("Resume123456", "Activa Motor");
                VariableGlobal.ESTATUS_MOTOR = true;
                /*if(VariableGlobal.ESTATUS_MOTOR){
                    imbMotorEncender.setVisibility(View.INVISIBLE);
                    lblMotorEncender.setVisibility(View.INVISIBLE);
                    imbMotorApagar.setVisibility(View.VISIBLE);
                    lblMotorApagar.setVisibility(View.VISIBLE);
                }*/

            }
        });

        imbMotorApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enviarComando("Stop123456", "Desactiva Motor");
                VariableGlobal.ESTATUS_MOTOR = false;
               /* if(!VariableGlobal.ESTATUS_MOTOR){
                    imbMotorEncender.setVisibility(View.VISIBLE);
                    lblMotorEncender.setVisibility(View.VISIBLE);
                    imbMotorApagar.setVisibility(View.INVISIBLE);
                    lblMotorApagar.setVisibility(View.INVISIBLE);
                }*/

            }
        });

        imbVisualizaRutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarComando("Tracker123456", "Modo Mapa");
            }
        });
    }


    private void enviarComando(final String comando, final String tipoOperacion) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Mensaje de Confirmación");
        builder.setMessage("¿Desea realizar la operación: " + tipoOperacion + " ?");
        builder.setCancelable(false);
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, new Intent(MainActivity.this, MainActivity.class), 0);
                //PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, new Intent(), 0);
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(nroAdministrador, null, comando, pendingIntent, null);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    //MENU CONTEXTUAL
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnuContactos) {
            Intent intent = new Intent(MainActivity.this, Contactos.class);
            startActivity(intent);
            MainActivity.this.finish();
        }else if(item.getItemId() == R.id.mnuHistorialGps){
            Intent intent = new Intent(MainActivity.this, HistorialGps.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
        return true;
    }

}

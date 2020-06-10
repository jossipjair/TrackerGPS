package com.hunter.trackercontrol.Controlador;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hunter.trackercontrol.Modelo.M_Contacto;
import com.hunter.trackercontrol.R;

public class Configuracion extends AppCompatActivity {

    private EditText txtNumeroTelefonoAdmin;
    private EditText txtNombreContactoAdmin;
    private Button btnGuardarContactoAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        txtNumeroTelefonoAdmin = findViewById(R.id.txtNumeroTelefonoAdmin);
        txtNombreContactoAdmin = findViewById(R.id.txtNombreContactoAdmin);
        btnGuardarContactoAdmin = findViewById(R.id.btnGuardarContactoAdmin);

        //Solicitar permiso en tiempo de ejecucion Llamada
        int permissionCheckCALL = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (permissionCheckCALL == PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            }
        }
        //


        btnGuardarContactoAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                M_Contacto m_contacto = new M_Contacto();
                m_contacto.insertarContacto(Configuracion.this,"ADMINISTRADOR", txtNumeroTelefonoAdmin.getText().toString(), txtNombreContactoAdmin.getText().toString());
                Intent intent = new Intent(Configuracion.this, MainActivity.class);
                startActivity(intent);
                Configuracion.this.finish();
            }
        });


    }
}

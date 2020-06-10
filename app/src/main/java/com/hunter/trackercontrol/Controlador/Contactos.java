package com.hunter.trackercontrol.Controlador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.hunter.trackercontrol.Modelo.M_Contacto;
import com.hunter.trackercontrol.R;

public class Contactos extends AppCompatActivity {

    private EditText txtNumeroTelefono;
    private EditText txtNombreContacto;
    private Button btnGuardarContacto;
    private GridView gvContactos;
    M_Contacto m_contacto = new M_Contacto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        txtNumeroTelefono = findViewById(R.id.txtNumeroTelefono);
        txtNombreContacto = findViewById(R.id.txtNombreContacto);
        btnGuardarContacto = findViewById(R.id.btnGuardarContacto);
        gvContactos = findViewById(R.id.gvContactos);


        btnGuardarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(m_contacto.traeConfiguracionServidor(Contactos.this, "USUARIO") <= 4){
                    m_contacto.insertarContacto(Contactos.this, "USUARIO", txtNumeroTelefono.getText().toString(), txtNombreContacto.getText().toString());
                    llenarGrid();
                    txtNombreContacto.setText("");
                    txtNumeroTelefono.setText("");
                }else{
                    Toast.makeText(Contactos.this, "¡Solo se permiten 5 contactos como máximo!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void llenarGrid() {
        m_contacto.mostrarContactos(Contactos.this, gvContactos);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Contactos.this, MainActivity.class);
        startActivity(intent);
        Contactos.this.finish();
    }
}

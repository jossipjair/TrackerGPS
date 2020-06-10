package com.hunter.trackercontrol.Controlador;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.hunter.trackercontrol.Modelo.M_GeoLocalizacion;
import com.hunter.trackercontrol.R;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HistorialGps extends AppCompatActivity {

    private TextView lblHistorialGps;
    private Button btnSeleccionFecha;
    private GridView gvHistorialGps;
    private int dia;
    private int mes;
    private int anio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_gps);

        lblHistorialGps = findViewById(R.id.lblHistorialGps);
        btnSeleccionFecha = findViewById(R.id.btnSeleccionFecha);
        gvHistorialGps = findViewById(R.id.gvHistorialGps);

        btnSeleccionFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Calendar calendar = Calendar.getInstance();
                dia = calendar.get(Calendar.DAY_OF_MONTH);
                mes = calendar.get(Calendar.MONTH);
                anio = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(HistorialGps.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String fechaSeleccion = "";
                        String fechaDate = year + "/" + (month + 1) + "/" + dayOfMonth;
                        Date date = new Date(fechaDate);
                        Format format = new SimpleDateFormat("dd/MM/yyyy");
                        fechaSeleccion = format.format(date);
                        lblHistorialGps.setText("Historial GPS del " + fechaSeleccion);

                        if (!fechaSeleccion.equals("")) {
                            mostrarHistorial(fechaSeleccion);
                        }
                    }
                }, anio, mes, dia);
                datePickerDialog.show();
            }
        });

        gvHistorialGps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position % 2 == 0) {
                   // Toast.makeText(HistorialGps.this,parent.getItemAtPosition(position + 1).toString() , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(parent.getItemAtPosition(position + 1).toString()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });


    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HistorialGps.this, MainActivity.class);
        startActivity(intent);
        HistorialGps.this.finish();
    }

    void mostrarHistorial(String fecha) {
        M_GeoLocalizacion m_geoLocalizacion = new M_GeoLocalizacion();
        m_geoLocalizacion.mostrarHistorial(HistorialGps.this, fecha, gvHistorialGps);
    }


}

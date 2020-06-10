package com.hunter.trackercontrol.Controlador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hunter.trackercontrol.R;

public class Login extends AppCompatActivity {

    private Button btn_one;
    private Button btn_two;
    private Button btn_three;
    private Button btn_zero;
    private Button btn_equal;
    private Button btn_four;
    private Button btn_five;
    private Button btn_six;
    private Button btn_seven;
    private Button btn_eight;
    private Button btn_nine;
    private TextView lblNumber;
    private String numero = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_one = findViewById(R.id.btn_one);
        btn_two = findViewById(R.id.btn_two);
        btn_three = findViewById(R.id.btn_three);
        btn_zero = findViewById(R.id.btn_zero);
        btn_equal = findViewById(R.id.btn_equal);
        btn_four = findViewById(R.id.btn_four);
        btn_five = findViewById(R.id.btn_five);
        btn_six = findViewById(R.id.btn_six);
        btn_seven = findViewById(R.id.btn_seven);
        btn_eight = findViewById(R.id.btn_eight);
        btn_nine = findViewById(R.id.btn_nine);
        lblNumber = findViewById(R.id.lblNumber);

        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numero.equals("5555")){
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(Login.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    Login.this.finish();
                }else if(!numero.equals("55555")){
                    numero = "";
                    lblNumber.setText("");
                }
            }
        });

        btn_zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = numero + "0";
                lblNumber.setText(numero);
            }
        });

        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = numero + "1";
                lblNumber.setText(numero);
            }
        });

        btn_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = numero + "2";
                lblNumber.setText(numero);
            }
        });

        btn_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = numero + "3";
                lblNumber.setText(numero);
            }
        });

        btn_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = numero + "4";
                lblNumber.setText(numero);
            }
        });

        btn_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = numero + "5";
                lblNumber.setText(numero);
            }
        });

        btn_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = numero + "6";
                lblNumber.setText(numero);
            }
        });

        btn_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = numero + "7";
                lblNumber.setText(numero);
            }
        });

        btn_eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = numero + "8";
                lblNumber.setText(numero);
            }
        });

        btn_nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = numero + "9";
                lblNumber.setText(numero);
            }
        });
    }
}

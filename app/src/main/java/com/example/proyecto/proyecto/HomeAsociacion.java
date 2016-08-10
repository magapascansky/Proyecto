package com.example.proyecto.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class HomeAsociacion extends AppCompatActivity {

    Button btnJugar;
    Button btnResultados;
    RadioButton rdbNivel1;
    RadioButton rdbNivel2;
    RadioGroup rdgNivel;
    String nivel;

    final public static String NIVEL = "com.proyecto.proyecto.NIVEL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_asociacion);
        Intent actividadHome = getIntent();
        referencias();
        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rdbNivel1.isChecked())
                {
                    nivel = "1";
                    iniciarActivity(nivel);
                }
                else if(rdbNivel2.isChecked())
                {
                    nivel = "2";
                    iniciarActivity(nivel);
                }
                else
                {
                    nivel = "1";
                    iniciarActivity(nivel);
                }
            }
        });
        btnResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarResultados();
            }
        });


    }

    public void referencias()
    {
        btnJugar = (Button) findViewById(R.id.btnJugar);
        rdbNivel1 = (RadioButton) findViewById(R.id.rdbNivel1);
        rdbNivel2 = (RadioButton) findViewById(R.id.rdbNivel2);
        rdgNivel = (RadioGroup) findViewById(R.id.rdgNivel);
        btnResultados = (Button) findViewById(R.id.btnResultados);
    }

    public void iniciarActivity (String nivel)
    {
        Bundle datos = new Bundle();
        Intent nuevaActivity = new Intent(this, Asociacion.class);
        datos.putString(NIVEL, nivel);
        nuevaActivity.putExtras(datos);


        startActivity(nuevaActivity);
    }

    public void mostrarResultados()
    {
        Intent activityResultados = new Intent(this, ResultadosAsociacion.class);
        startActivity(activityResultados);
    }
}

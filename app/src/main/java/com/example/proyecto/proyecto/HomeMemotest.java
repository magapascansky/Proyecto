package com.example.proyecto.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class HomeMemotest extends AppCompatActivity {

    ImageView imgLogo;
    Button btnResultados;
    TextView tvwExplicacion;
    TextView tvwNivel;
    RadioButton rdbNivel1;
    RadioButton rdbNivel2;
    RadioButton rdbNivel3;
    RadioButton rdbNivel4;
    RadioGroup rdgNivel;
    Button btnJugar;
    int intNivel;
    Bundle datos;
    String nivel;

    final public static String NIVEL = "com.proyecto.proyecto.NIVEL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_memotest);

        Intent actividadHome = getIntent();

        datos = actividadHome.getExtras();

        Referencias();
        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rdbNivel1.isChecked())
                {
                    nivel = "1";
                }
                else if(rdbNivel2.isChecked())
                {
                    nivel = "2";
                }
                else if(rdbNivel3.isChecked())
                {
                    nivel = "3";
                }
                else if(rdbNivel4.isChecked())
                {
                    nivel = "4";
                }
                else
                {
                    nivel = "1";
                }

                iniciarActivity(nivel);

            }
        });
        btnResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarResultados();
            }
        });

    }

    public void Referencias()
    {
        imgLogo = (ImageView) findViewById(R.id.imgLogo);
        btnResultados = (Button) findViewById(R.id.btnResultados);
        tvwExplicacion = (TextView) findViewById(R.id.tvwExplicacion);
        tvwNivel = (TextView) findViewById(R.id.tvwNivel);
        rdbNivel1 = (RadioButton) findViewById(R.id.rdbNivel1);
        rdbNivel2 = (RadioButton) findViewById(R.id.rdbNivel2);
        rdbNivel3 = (RadioButton) findViewById(R.id.rdbNivel3);
        rdbNivel4 = (RadioButton) findViewById(R.id.rdbNivel4);
        rdgNivel = (RadioGroup) findViewById(R.id.rdgNivel);
        btnJugar = (Button) findViewById(R.id.btnJugar);
    }

    public void iniciarActivity (String strNivel)
    {
        Bundle datos = new Bundle();
        Intent nuevaActivity = new Intent(this, Memotest.class);
        datos.putString(NIVEL, nivel);
        nuevaActivity.putExtras(datos);

        startActivity(nuevaActivity);
    }

    public void mostrarResultados()
    {
        Intent activityResultados = new Intent(this, ResultadosMemotest.class);
        startActivity(activityResultados);
    }
}

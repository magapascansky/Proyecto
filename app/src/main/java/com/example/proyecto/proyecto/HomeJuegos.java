package com.example.proyecto.proyecto;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeJuegos extends AppCompatActivity{



    Button btnMemotest;
    Button btnAsociacion;
    Button btnMemoria;
    Button btnBailar;
    Button btnFreestyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_juegos);

        Referencias();
        btnMemotest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iniciarActividad("memotest");
            }
        });
        btnAsociacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iniciarActividad("asociacion");
            }
        });
        btnMemoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iniciarActividad("memoria");
            }
        });
        btnFreestyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iniciarActividad("freestyle");
            }
        });


    }

    public void Referencias()
    {
        btnMemotest = (Button) findViewById(R.id.btnMemotest);
        btnAsociacion = (Button) findViewById(R.id.btnAsociacion);
        btnMemoria = (Button) findViewById(R.id.btnMemoria);
        btnBailar = (Button) findViewById(R.id.btnBailar);
        btnFreestyle = (Button) findViewById(R.id.btnFreestyle);
    }

    public void iniciarActividad(String activityAIniciar)
    {
        Intent nuevaActivity = new Intent();
        switch (activityAIniciar)
        {
           // Log.e("error:", "entro a iniciar");
            case "memotest":
                nuevaActivity.setClass(this, HomeMemotest.class);
                break;
            case "asociacion":
                nuevaActivity.setClass(this, HomeAsociacion.class);
                break;
            case "memoria":
                nuevaActivity.setClass(this, HomeSimon.class);
                break;
            case "freestyle":
                nuevaActivity.setClass(this, HomeFreestyle.class);
                break;
        }
        startActivity(nuevaActivity);
    }
}

package com.example.proyecto.proyecto;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

public class Simon extends AppCompatActivity implements TextToSpeech.OnInitListener{

    ImageButton btnPlay;
    ImageButton btn1;
    ImageButton btn2;
    ImageButton btn3;

    String nivel;
    String nombre;
    int contMovimientos;
    Bundle datos;
    int contApretados;
    int contVez;
    TextToSpeech tts;

    String secuencia;

    MediaPlayer guitar;
    MediaPlayer drum;
    MediaPlayer piano;
    MediaPlayer incorrecto;
    MediaPlayer aplausos;
    MediaPlayer correcto;

    String formattedDate;
    long tStart;
    long tEnd;
    long tDelta;
    double elapsedSeconds;

    MediaPlayer vecSonidos[] = new MediaPlayer[7];
    String vecNombresSonidos[] = new String[7];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon);

        referencias();

        Intent actividadHome = getIntent();
        datos = actividadHome.getExtras();
        nivel = datos.getString(HomeAsociacion.NIVEL);
        contMovimientos = 0;

        tts = new TextToSpeech(Simon.this, Simon.this);
        tts.setLanguage(Locale.US);


        consulta();
        Calendar c = Calendar.getInstance();
        System.out.println("Current time =&gt; "+c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
        formattedDate = df.format(c.getTime());

        guitar = MediaPlayer.create(getApplicationContext(), R.raw.guitar);
        piano = MediaPlayer.create(getApplicationContext(), R.raw.piano);
        drum = MediaPlayer.create(getApplicationContext(), R.raw.tambor);
        correcto = MediaPlayer.create(getApplicationContext(), R.raw.correcto);
        incorrecto = MediaPlayer.create(getApplicationContext(), R.raw.incorrecto);
        aplausos = MediaPlayer.create(getApplicationContext(), R.raw.aplausos);

        vecSonidos[0]=piano;
        vecSonidos[1]=guitar;
        vecSonidos[2]=drum;
        vecSonidos[3]=piano;
        vecSonidos[4]=guitar;
        vecSonidos[5]=drum;
        vecSonidos[6]=piano;

        vecNombresSonidos[0]="1";
        vecNombresSonidos[1]="2";
        vecNombresSonidos[2]="3";
        vecNombresSonidos[3]="1";
        vecNombresSonidos[4]="2";
        vecNombresSonidos[5]="3";
        vecNombresSonidos[6]="1";

        iniciar(nivel);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        for (int i = 0; i<contVez; i++){
                            if(i != 0){
                                while(vecSonidos[i-1].isPlaying())
                                {
                                    //raro
                                }
                                secuencia=secuencia+Integer.toString(i);
                                vecSonidos[i].start();
                            }
                            vecSonidos[i].start();
                        }

            }
        });
    }
    private View.OnClickListener btnPresionado_Click = new View.OnClickListener(){
        public void onClick(View v){

            ImageButton btnPresionado = (ImageButton) v;
            int intIdPresionado = btnPresionado.getId();
            Resources res = getResources();
            String viewName = res.getResourceEntryName(intIdPresionado);
            String elemento= viewName.substring(3);

            if (elemento.equals(vecNombresSonidos[contApretados]))
            {
                contApretados++;
                if (contApretados == 1 && contVez == 1)
                {
                    correcto.start();
                    Toast.makeText(getApplicationContext(), "¡Muy bien!", Toast.LENGTH_SHORT).show();
                    contVez++;
                    contApretados=0;
                }
                else if (contApretados == 2 && contVez == 2)
                {
                    correcto.start();
                    Toast.makeText(getApplicationContext(), "¡Muy bien!", Toast.LENGTH_SHORT).show();
                    contVez++;
                    contApretados=0;
                }
                else if (contApretados == 3 && contVez == 3)
                {
                    if (nivel.equals("1")){
                        aplausos.start();
                        tEnd = System.currentTimeMillis();
                        tDelta = tEnd - tStart;
                        elapsedSeconds = tDelta / 1000.0;
                        alta();
                        crearDialogoConfirmacion().show();
                    }
                    else {
                        correcto.start();
                        contVez++;
                        contApretados=0;
                        Toast.makeText(getApplicationContext(), "¡Muy bien!", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (contApretados == 4 && contVez == 4)
                {
                    correcto.start();
                    contVez++;
                    contApretados=0;
                    Toast.makeText(getApplicationContext(), "¡Muy bien!", Toast.LENGTH_SHORT).show();
                }
                else if (contApretados == 5 && contVez == 5)
                {
                    if (nivel.equals("2")){
                        aplausos.start();
                        tEnd = System.currentTimeMillis();
                        tDelta = tEnd - tStart;
                        elapsedSeconds = tDelta / 1000.0;
                        alta();
                        crearDialogoConfirmacion().show();
                    }
                    else {
                        correcto.start();
                        contVez++;
                        contApretados=0;
                        Toast.makeText(getApplicationContext(), "¡Muy bien!", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (contApretados == 6 && contVez == 6)
                {
                    correcto.start();
                    Toast.makeText(getApplicationContext(), "¡Muy bien!", Toast.LENGTH_SHORT).show();
                    contVez++;
                    contApretados=0;
                }
                else if (contApretados == 7 && contVez == 7)
                {
                    if (nivel.equals("3")){
                        aplausos.start();
                        tEnd = System.currentTimeMillis();
                        tDelta = tEnd - tStart;
                        elapsedSeconds = tDelta / 1000.0;
                        alta();
                        crearDialogoConfirmacionFinal().show();
                    }
                }

            }
            else{
                contApretados=0;
                incorrecto.start();
                Toast.makeText(getApplicationContext(), "Casi... Empeza de nuevo", Toast.LENGTH_SHORT).show();
            }

        }
    };

    public void referencias(){

        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btn1 = (ImageButton) findViewById(R.id.btn1);
        btn2 = (ImageButton) findViewById(R.id.btn2);
        btn3 = (ImageButton) findViewById(R.id.btn3);

        btn1.setOnClickListener(btnPresionado_Click);
        btn2.setOnClickListener(btnPresionado_Click);
        btn3.setOnClickListener(btnPresionado_Click);

    }

    public void iniciar(String nivel){
        tStart = System.currentTimeMillis();
        contApretados = 0;
        contVez = 1;

        Random generadorRandom = new Random();
        int numRandom1;
        int numRandom2;
        MediaPlayer tempMedia;
        String temp;
        for (int i=0; i<100; i++)
        {
            numRandom1 = generadorRandom.nextInt(7 - 0) + 0;
            numRandom2 = generadorRandom.nextInt(7 - 0) + 0;
            tempMedia = vecSonidos[numRandom1];
            vecSonidos[numRandom1] = vecSonidos[numRandom2];
            vecSonidos[numRandom2] = tempMedia;
            temp = vecNombresSonidos[numRandom1];
            vecNombresSonidos[numRandom1] = vecNombresSonidos[numRandom2];
            vecNombresSonidos[numRandom2] = temp;
        }//desordena el vector
    }
    public Dialog crearDialogoConfirmacionFinal() {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("¡Muy Bien " + nombre +"!");
        builder.setMessage("Ganaste todos los niveles del Memoria y Sonidos" + "\n" + "Ganaste un trofeo de Memoria");
        HashMap<String, String> hash = new HashMap<String,String>();
        hash.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_NOTIFICATION));
        tts.speak("Muy bien" + nombre + ". Ganaste todos los niveles del Memoria y Sonidos. Ganaste un trofeo de Memoria", TextToSpeech.QUEUE_FLUSH, hash);
        builder.setIcon(R.drawable.trofeo);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Log.i("Diálogos", "Confirmación Aceptada.");
                finish();
                dialog.cancel();
            }
        });

        return builder.create();

    }
    public Dialog crearDialogoConfirmacion() {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("¡Muy Bien " + nombre + "!");
        builder.setMessage("¿Queres pasar al siguiente nivel?");
        HashMap<String, String> hash = new HashMap<String,String>();
        hash.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_NOTIFICATION));
        tts.speak("Muy bien" + nombre + ". Queres pasar al siguiente nivel?", TextToSpeech.QUEUE_FLUSH, hash);
        builder.setPositiveButton("¡Si!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Log.i("Diálogos", "Confirmación Aceptada.");
                nivel = "2";
                contVez = 0;
                contApretados = 1;
                iniciar(nivel);
                dialog.cancel();
            }
        });

        builder.setNegativeButton("Ahora no...", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                Log.i("Diálogos", "Confirmación Cancelada.");
                finish();
                dialog.cancel();
            }

        });

        return builder.create();

    }

    public void consulta() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "database", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor curNombre = bd.rawQuery(  //devuelve 0 o 1 fila //es una consulta
                "select nombre from Jugadores where ID=" + MainActivity.idUsuario, null);
        if (curNombre.moveToFirst()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)

            nombre = curNombre.getString(0);
        } else{
            Toast.makeText(this, "Error" , Toast.LENGTH_SHORT).show();
        }

        bd.close();

    }
    public void alta() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"database", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();  //es una clase para guardar datos
        registro.put("idJugador", MainActivity.idUsuario);
        registro.put("IdJuego", 3);
        registro.put("IdNivel", nivel);
        registro.put("fecha", formattedDate);
        registro.put("movimientos", contMovimientos);
        registro.put("tiempo", elapsedSeconds);
        bd.insert("Resultados", null, registro);
        bd.close();
    }
    @Override
    public void onInit(int i) {

    }
}

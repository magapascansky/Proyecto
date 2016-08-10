package com.example.proyecto.proyecto;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;

public class Asociacion extends AppCompatActivity implements TextToSpeech.OnInitListener{

    ImageButton btnPlay;
    ImageButton btnPlay2;
    ImageButton btn1;
    ImageButton btn2;
    ImageButton btn3;
    ImageButton btn4;
    ImageButton btn5;
    ImageButton btn6;
    ImageButton btn7;
    ImageButton btn8;
    ImageButton btn9;
    ImageButton btn10;
    ImageButton btn11;
    ImageButton btn12;
    ImageButton btn13;
    ImageButton btn14;
    ImageButton btn15;

    TextToSpeech tts;

    MediaPlayer guitar;
    MediaPlayer drum;
    MediaPlayer trumpet;
    MediaPlayer piano;
    MediaPlayer bell;
    MediaPlayer perro;
    MediaPlayer gato;
    MediaPlayer oveja;
    MediaPlayer vaca;
    MediaPlayer raton;
    MediaPlayer gallina;
    MediaPlayer leon;
    MediaPlayer elefante;
    MediaPlayer mono;
    MediaPlayer caballo;
    MediaPlayer correcto;
    MediaPlayer incorrecto;
    MediaPlayer aplausos;
    Bundle datos;
    String nombre;
    String formattedDate;
    long tStart;
    long tEnd;
    long tDelta;
    double elapsedSeconds;

    int contMovimientos = 0;

    int intContador;

    boolean vecRespuestas[] = new boolean[]{false, false, false, false,false};
    boolean vecRespuestas2[] = new boolean[]{false, false, false, false,false, false, false, false, false,false};
    MediaPlayer vecSonidos[] = new MediaPlayer[5];
    String vecNombresSonidos[] = new String[5];
    MediaPlayer vecAnimales[] = new MediaPlayer[10];
    String vecNombresAnimales[] = new String[10];

    String nivel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent actividadHome = getIntent();
        datos = actividadHome.getExtras();
        nivel = datos.getString(HomeAsociacion.NIVEL);
        contMovimientos = 0;

        tts = new TextToSpeech(Asociacion.this, Asociacion.this);
        tts.setLanguage(Locale.US);


        consulta();
        Calendar c = Calendar.getInstance();
        System.out.println("Current time =&gt; "+c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
        formattedDate = df.format(c.getTime());

        guitar = MediaPlayer.create(getApplicationContext(), R.raw.guitar);
        piano = MediaPlayer.create(getApplicationContext(), R.raw.piano);
        bell = MediaPlayer.create(getApplicationContext(), R.raw.bell);
        drum = MediaPlayer.create(getApplicationContext(), R.raw.tambor);
        trumpet = MediaPlayer.create(getApplicationContext(), R.raw.trumpet);

        perro = MediaPlayer.create(getApplicationContext(), R.raw.perro);
        gato = MediaPlayer.create(getApplicationContext(), R.raw.gato);
        gallina = MediaPlayer.create(getApplicationContext(), R.raw.chicken);
        vaca = MediaPlayer.create(getApplicationContext(), R.raw.cow);
        mono = MediaPlayer.create(getApplicationContext(), R.raw.monkey);
        leon = MediaPlayer.create(getApplicationContext(), R.raw.lion);
        raton = MediaPlayer.create(getApplicationContext(), R.raw.rat);
        oveja = MediaPlayer.create(getApplicationContext(), R.raw.oveja);
        elefante= MediaPlayer.create(getApplicationContext(), R.raw.elephant);
        caballo = MediaPlayer.create(getApplicationContext(), R.raw.horse);
        correcto = MediaPlayer.create(getApplicationContext(), R.raw.correcto);
        incorrecto = MediaPlayer.create(getApplicationContext(), R.raw.incorrecto);
        aplausos = MediaPlayer.create(getApplicationContext(), R.raw.aplausos);

        vecSonidos[0]=guitar;
        vecSonidos[1]=piano;
        vecSonidos[2]=trumpet;
        vecSonidos[3]=bell;
        vecSonidos[4]=drum;


        vecNombresSonidos[0]="1";
        vecNombresSonidos[1]="2";
        vecNombresSonidos[2]="3";
        vecNombresSonidos[3]="4";
        vecNombresSonidos[4]="5";

        vecAnimales[0] = perro;
        vecAnimales[1] = gato;
        vecAnimales[2] = vaca;
        vecAnimales[3] = oveja;
        vecAnimales[4] = caballo;
        vecAnimales[5] = mono;
        vecAnimales[6] = elefante;
        vecAnimales[7] = raton;
        vecAnimales[8] = leon;
        vecAnimales[9] = gallina;

        vecNombresAnimales[0] = "6";
        vecNombresAnimales[1] = "7";
        vecNombresAnimales[2] = "8";
        vecNombresAnimales[3] = "9";
        vecNombresAnimales[4] = "10";
        vecNombresAnimales[5] = "11";
        vecNombresAnimales[6] = "12";
        vecNombresAnimales[7] = "13";
        vecNombresAnimales[8] = "14";
        vecNombresAnimales[9] = "15";

        iniciar(nivel);
    }



    private View.OnClickListener btnPresionado_Click = new View.OnClickListener(){
        public void onClick(View v){

            contMovimientos++;
            int intRespuestas = 0;
            ImageButton btnPresionado = (ImageButton) v;
            int intIdPresionado = btnPresionado.getId();
            Resources res = getResources();
            String viewName = res.getResourceEntryName(intIdPresionado);
            String elemento= viewName.substring(3);

            if (nivel.equals("2"))
            {
                if (elemento.toString().equals(vecNombresAnimales[intContador]))
                {
                    btnPresionado.setImageResource(R.drawable.tick);
                    vecRespuestas2[intContador]=true;
                    correcto.start();
                    Toast.makeText(getApplicationContext(),"¡Muy bien!",Toast.LENGTH_SHORT).show();
                    intContador++;
                }
                else
                {
                    incorrecto.start();
                    Toast.makeText(getApplicationContext(),"Casi...",Toast.LENGTH_SHORT).show();
                }
                intRespuestas= 0;
                for(int i = 0;i<10;i++)
                {
                    if (vecRespuestas2[i] == true)
                    {
                        intRespuestas++;
                    }
                }
                if (intRespuestas==10)
                {
                    aplausos.start();
                    aplausos.start();
                    tEnd = System.currentTimeMillis();
                    tDelta = tEnd - tStart;
                    elapsedSeconds = tDelta / 1000.0;
                    alta();
                    crearDialogoConfirmacionFinal().show();
                }
            }
            else
            {
                if (elemento.toString().equals(vecNombresSonidos[intContador]))
                {
                    btnPresionado.setImageResource(R.drawable.tick);
                    vecRespuestas[intContador]=true;
                    correcto.start();
                    Toast.makeText(getApplicationContext(),"¡Muy bien!",Toast.LENGTH_SHORT).show();
                    intContador++;
                }
                else
                {
                    incorrecto.start();
                    Toast.makeText(getApplicationContext(),"Casi...",Toast.LENGTH_SHORT).show();
                }

                intRespuestas= 0;
                for(int i = 0;i<5;i++)
                {
                    if (vecRespuestas[i] == true)
                    {
                        intRespuestas++;
                    }
                }
                if (intRespuestas==5)
                {
                    aplausos.start();
                    tEnd = System.currentTimeMillis();
                    tDelta = tEnd - tStart;
                    elapsedSeconds = tDelta / 1000.0;
                    alta();
                    crearDialogoConfirmacion().show();
                }
            }



        }
    };

    private View.OnClickListener btnPlay_Click = new View.OnClickListener() {
        public void onClick(View v) {

            if (nivel.equals("2")) {
                vecAnimales[intContador].start();
            } else {
                vecSonidos[intContador].start();
            }
        }

    };



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
    public Dialog crearDialogoConfirmacionFinal() {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("¡Muy Bien " + nombre +"!");
        builder.setMessage("Ganaste todos los niveles del Asociacion de Sonidos" + "\n" + "Ganaste un trofeo de Memoria");
        HashMap<String, String> hash = new HashMap<String,String>();
        hash.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_NOTIFICATION));
        tts.speak("Muy bien" + nombre + ". Ganaste todos los niveles del Asociacin de Sonidos. Ganaste un trofeo de Memoria", TextToSpeech.QUEUE_FLUSH, hash);
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



    public void iniciar(String nivel)
    {
        contMovimientos=0;
        tStart = System.currentTimeMillis();
        if (nivel.toString().equals("1"))
        {
            setContentView(R.layout.activity_asociacion);

            btn1 = (ImageButton) findViewById(R.id.btn1);
            btn2 = (ImageButton) findViewById(R.id.btn2);
            btn3 = (ImageButton) findViewById(R.id.btn3);
            btn4 = (ImageButton) findViewById(R.id.btn4);
            btn5 = (ImageButton) findViewById(R.id.btn5);
            btnPlay = (ImageButton) findViewById(R.id.btnPlay);

            btn1.setOnClickListener(btnPresionado_Click);
            btn2.setOnClickListener(btnPresionado_Click);
            btn3.setOnClickListener(btnPresionado_Click);
            btn4.setOnClickListener(btnPresionado_Click);
            btn5.setOnClickListener(btnPresionado_Click);
            btnPlay.setOnClickListener(btnPlay_Click);


            Random generadorRandom = new Random();
            int numRandom1;
            int numRandom2;
            MediaPlayer tempMedia;
            String temp;
            for (int i=0; i<100; i++)
            {
                numRandom1 = generadorRandom.nextInt(5 - 0) + 0;
                numRandom2 = generadorRandom.nextInt(5 - 0) + 0;
                tempMedia = vecSonidos[numRandom1];
                vecSonidos[numRandom1] = vecSonidos[numRandom2];
                vecSonidos[numRandom2] = tempMedia;
                temp = vecNombresSonidos[numRandom1];
                vecNombresSonidos[numRandom1] = vecNombresSonidos[numRandom2];
                vecNombresSonidos[numRandom2] = temp;
            }//desordena el vector

        }
        else
        {
            intContador =0;

            setContentView(R.layout.asociacion2);

            btn6 = (ImageButton) findViewById(R.id.btn6);
            btn7 = (ImageButton) findViewById(R.id.btn7);
            btn8 = (ImageButton) findViewById(R.id.btn8);
            btn9 = (ImageButton) findViewById(R.id.btn9);
            btn10 = (ImageButton) findViewById(R.id.btn10);
            btn11 = (ImageButton) findViewById(R.id.btn11);
            btn12 = (ImageButton) findViewById(R.id.btn12);
            btn13 = (ImageButton) findViewById(R.id.btn13);
            btn14 = (ImageButton) findViewById(R.id.btn14);
            btn15 = (ImageButton) findViewById(R.id.btn15);
            btnPlay2 = (ImageButton) findViewById(R.id.btnPlay2);

            btn6.setOnClickListener(btnPresionado_Click);
            btn7.setOnClickListener(btnPresionado_Click);
            btn8.setOnClickListener(btnPresionado_Click);
            btn9.setOnClickListener(btnPresionado_Click);
            btn10.setOnClickListener(btnPresionado_Click);
            btn11.setOnClickListener(btnPresionado_Click);
            btn12.setOnClickListener(btnPresionado_Click);
            btn13.setOnClickListener(btnPresionado_Click);
            btn14.setOnClickListener(btnPresionado_Click);
            btn15.setOnClickListener(btnPresionado_Click);
            btnPlay2.setOnClickListener(btnPlay_Click);

            Random generadorRandom = new Random();
            int numRandom1;
            int numRandom2;
            MediaPlayer tempMedia;
            String temp;
            for (int i=0; i<100; i++)
            {
                numRandom1 = generadorRandom.nextInt(10 - 0) + 0;
                numRandom2 = generadorRandom.nextInt(10 - 0) + 0;
                tempMedia = vecAnimales[numRandom1];
                vecAnimales[numRandom1] = vecAnimales[numRandom2];
                vecAnimales[numRandom2] = tempMedia;
                temp = vecNombresAnimales[numRandom1];
                vecNombresAnimales[numRandom1] = vecNombresAnimales[numRandom2];
                vecNombresAnimales[numRandom2] = temp;
            }//desordena el vector
        }
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
        registro.put("IdJuego", 2);
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

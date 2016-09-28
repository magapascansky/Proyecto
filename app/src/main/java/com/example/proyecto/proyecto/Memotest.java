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
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.Locale;
import java.util.Random;

public class Memotest extends AppCompatActivity implements TextToSpeech.OnInitListener{



    Button btn1;
    Button btn8;
    Button btn7;
    Button btn6;
    Button btn5;
    Button btn4;
    Button btn3;
    Button btn2;
    Button btn9;
    Button btn10;
    Button btn11;
    Button btn12;
    Button btn13;
    Button btn14;

    MediaPlayer perro;
    MediaPlayer fono;
    MediaPlayer oveja;
    MediaPlayer piano;
    MediaPlayer gato;
    MediaPlayer tambor;
    MediaPlayer risa;

    MediaPlayer[] vecSonidos = new MediaPlayer[7];
    MediaPlayer correcto;
    MediaPlayer aplausos;
    MediaPlayer incorrecto;
    String[] vecColores =  {"azul","azul","azul","azul","azul","azul","azul","azul","azul","azul", "azul", "azul", "azul", "azul"};
    int[] vectorValores={1,1,2,2,3,3,4,4,5,5,6,6,7,7};
    Bundle datos;
    String nombre;
    String nivel;
    int[] vecCants = new int[4];

    String formattedDate;
    long tStart;
    long tEnd;
    long tDelta;
    double elapsedSeconds;

    int contMovimientos = 0;
    TextToSpeech tts;
    int intCantNivel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memotest);

        perro = MediaPlayer.create(getApplicationContext(), R.raw.perro);
        piano = MediaPlayer.create(getApplicationContext(), R.raw.piano);
        fono = MediaPlayer.create(getApplicationContext(), R.raw.fono);
        oveja = MediaPlayer.create(getApplicationContext(), R.raw.oveja);
        gato = MediaPlayer.create(getApplicationContext(), R.raw.gato);
        tambor = MediaPlayer.create(getApplicationContext(), R.raw.tambor);
        risa = MediaPlayer.create(getApplicationContext(), R.raw.risa);

        vecSonidos[0] = perro;
        vecSonidos[1] = piano;
        vecSonidos[2] = fono;
        vecSonidos[3] = oveja;
        vecSonidos[4] = gato;
        vecSonidos[5] = tambor;
        vecSonidos[6] = risa;

        correcto = MediaPlayer.create(getApplicationContext(), R.raw.correcto);
        aplausos = MediaPlayer.create(getApplicationContext(), R.raw.aplausos);
        incorrecto = MediaPlayer.create(getApplicationContext(), R.raw.incorrecto);

        Intent actividadHome = getIntent();
        datos = actividadHome.getExtras();
        nivel = datos.getString(HomeMemotest.NIVEL);

        contMovimientos = 0;

        tts = new TextToSpeech(Memotest.this, Memotest.this);
        tts.setLanguage(Locale.US);


        consulta();
        consultaTrofeos();
        Calendar c = Calendar.getInstance();
        System.out.println("Current time =&gt; "+c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        formattedDate = df.format(c.getTime());

        iniciar(nivel);
    };
    private View.OnClickListener btnPresionado_Click = new View.OnClickListener(){
        public void onClick(View v){

            contMovimientos++;

            Button btnPresionado = (Button) v;
            int intIdPresionado = btnPresionado.getId();
            Resources res = getResources();
            String viewName = res.getResourceEntryName(intIdPresionado);
            String btnPosta= viewName.substring(3);
            int numButton = Integer.parseInt(btnPosta);//obtiene numero de boton

            vecSonidos[vectorValores[numButton-1]-1].start();


            boolean apretado = false;
            int intApretado = 0;
            if (vecColores[numButton-1] != "verde")
            {for (int i=0;i<intCantNivel;i++)
            {
                if (vecColores[i] == "rosa")
                {
                    apretado = true;
                    intApretado = i;
                }
            }
            if (apretado == true)
            {
                int intAnterior = intApretado+1;
                String viewName2 = "btn" + intAnterior;
                Resources res2 = getResources();
                int idD = res2.getIdentifier(viewName2, "id", getApplicationContext().getPackageName());
                Button btnAnterior = (Button) findViewById(idD);
                if (vectorValores[intApretado] == vectorValores[numButton-1])
                {
                    if (!viewName.toString().equals(viewName2.toString())) {
                        btnPresionado.setBackgroundColor(Color.GREEN);
                        btnPresionado.setText("¡BIEN!");
                        btnAnterior.setText("¡BIEN!");
                        btnAnterior.setBackgroundColor(Color.GREEN);
                        vecColores[numButton - 1] = "verde";
                        vecColores[intApretado] = "verde";
                        while(vecSonidos[vectorValores[numButton-1]-1].isPlaying())
                        {
                            //medio raro
                        }
                        correcto.start();
                        Toast.makeText(getApplicationContext(), "¡Muy bien!", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {

                    while(vecSonidos[vectorValores[numButton-1]-1].isPlaying())
                    {
                        //medio raro
                    }
                    incorrecto.start();
                    Toast.makeText(getApplicationContext(), "Casi...!", Toast.LENGTH_SHORT).show();
                    btnPresionado.setBackgroundColor(Color.CYAN);
                    btnPresionado.setText("¡APRETAME!");
                    btnAnterior.setText("¡APRETAME!");
                    btnAnterior.setBackgroundColor(Color.CYAN);
                    vecColores[numButton-1]="azul";
                    vecColores[intApretado]="azul";

                }
            }
            else
            {
                btnPresionado.setBackgroundColor(Color.MAGENTA);
                btnPresionado.setText("...");
                vecColores[numButton-1] = "rosa";
            }}
            int contVerdes =0;
            for (int i=0; i<intCantNivel;i++)
            {
                if (vecColores[i] == "verde")
                {
                    contVerdes++;
                }
            }
            if (contVerdes == intCantNivel)
            {
                while(vecSonidos[vectorValores[numButton-1]-1].isPlaying())
                {
                    //medio raro
                }
                aplausos.start();

                if (nivel.equals("4"))
                {
                    tEnd = System.currentTimeMillis();
                    tDelta = tEnd - tStart;
                    elapsedSeconds = tDelta / 1000.0;
                    alta();
                    crearDialogoConfirmacionFinal().show();
                }
                else {
                    tEnd = System.currentTimeMillis();
                    tDelta = tEnd - tStart;
                    elapsedSeconds = tDelta / 1000.0;
                    alta();
                    crearDialogoConfirmacion().show();
                }

            }

        }
    };

    public void iniciar(String strNivel)
    {
        Random generadorRandom = new Random();
        int numRandom1;
        int numRandom2;
        int temp;

        tStart = System.currentTimeMillis();
        contMovimientos=0;

        vecColores[0]="azul";
        vecColores[1]="azul";
        vecColores[2]="azul";
        vecColores[3]="azul";
        vecColores[4]="azul";
        vecColores[5]="azul";
        vecColores[6]="azul";
        vecColores[7]="azul";
        vecColores[8]="azul";
        vecColores[9]="azul";
        vecColores[10]="azul";
        vecColores[11]="azul";
        vecColores[12]="azul";
        vecColores[13]="azul";

        switch (nivel)
        {
            case "1":
                setContentView(R.layout.activity_memotest);
                intCantNivel = 8;
                btn1 = (Button) findViewById(R.id.btn1);
                btn2 = (Button) findViewById(R.id.btn2);
                btn3 = (Button) findViewById(R.id.btn3);
                btn4 = (Button) findViewById(R.id.btn4);
                btn5 = (Button) findViewById(R.id.btn5);
                btn6 = (Button) findViewById(R.id.btn6);
                btn7 = (Button) findViewById(R.id.btn7);
                btn8 = (Button) findViewById(R.id.btn8);
                btn1.setOnClickListener(btnPresionado_Click);
                btn2.setOnClickListener(btnPresionado_Click);
                btn3.setOnClickListener(btnPresionado_Click);
                btn4.setOnClickListener(btnPresionado_Click);
                btn5.setOnClickListener(btnPresionado_Click);
                btn6.setOnClickListener(btnPresionado_Click);
                btn7.setOnClickListener(btnPresionado_Click);
                btn8.setOnClickListener(btnPresionado_Click);

                btn1.setBackgroundColor(Color.CYAN);
                btn2.setBackgroundColor(Color.CYAN);
                btn3.setBackgroundColor(Color.CYAN);
                btn4.setBackgroundColor(Color.CYAN);
                btn5.setBackgroundColor(Color.CYAN);
                btn6.setBackgroundColor(Color.CYAN);
                btn7.setBackgroundColor(Color.CYAN);
                btn8.setBackgroundColor(Color.CYAN);
                btn1.setText("¡Apretame!");
                btn2.setText("¡Apretame!");
                btn3.setText("¡Apretame!");
                btn4.setText("¡Apretame!");
                btn5.setText("¡Apretame!");
                btn6.setText("¡Apretame!");
                btn7.setText("¡Apretame!");
                btn8.setText("¡Apretame!");

                for (int i=0; i<100; i++)
                {
                    numRandom1 = generadorRandom.nextInt(8 - 0) + 0;
                    numRandom2 = generadorRandom.nextInt(8 - 0) + 0;
                    temp = vectorValores[numRandom1];
                    vectorValores[numRandom1] = vectorValores[numRandom2];
                    vectorValores[numRandom2] = temp;
                }
                break;
            case "2":
                setContentView(R.layout.activity_memotest2);
                intCantNivel=10;
                btn1 = (Button) findViewById(R.id.btn1);
                btn2 = (Button) findViewById(R.id.btn2);
                btn3 = (Button) findViewById(R.id.btn3);
                btn4 = (Button) findViewById(R.id.btn4);
                btn5 = (Button) findViewById(R.id.btn5);
                btn6 = (Button) findViewById(R.id.btn6);
                btn7 = (Button) findViewById(R.id.btn7);
                btn8 = (Button) findViewById(R.id.btn8);
                btn9 = (Button) findViewById(R.id.btn9);
                btn10 = (Button) findViewById(R.id.btn10);
                btn1.setOnClickListener(btnPresionado_Click);
                btn2.setOnClickListener(btnPresionado_Click);
                btn3.setOnClickListener(btnPresionado_Click);
                btn4.setOnClickListener(btnPresionado_Click);
                btn5.setOnClickListener(btnPresionado_Click);
                btn6.setOnClickListener(btnPresionado_Click);
                btn7.setOnClickListener(btnPresionado_Click);
                btn8.setOnClickListener(btnPresionado_Click);
                btn9.setOnClickListener(btnPresionado_Click);
                btn10.setOnClickListener(btnPresionado_Click);

                btn1.setBackgroundColor(Color.CYAN);
                btn2.setBackgroundColor(Color.CYAN);
                btn3.setBackgroundColor(Color.CYAN);
                btn4.setBackgroundColor(Color.CYAN);
                btn5.setBackgroundColor(Color.CYAN);
                btn6.setBackgroundColor(Color.CYAN);
                btn7.setBackgroundColor(Color.CYAN);
                btn8.setBackgroundColor(Color.CYAN);
                btn9.setBackgroundColor(Color.CYAN);
                btn10.setBackgroundColor(Color.CYAN);
                btn1.setText("¡Apretame!");
                btn2.setText("¡Apretame!");
                btn3.setText("¡Apretame!");
                btn4.setText("¡Apretame!");
                btn5.setText("¡Apretame!");
                btn6.setText("¡Apretame!");
                btn7.setText("¡Apretame!");
                btn8.setText("¡Apretame!");
                btn9.setText("¡Apretame!");
                btn10.setText("¡Apretame!");


                for (int i=0; i<100; i++)
                {
                    numRandom1 = generadorRandom.nextInt(10 - 0);
                    numRandom2 = generadorRandom.nextInt(10 - 0);
                    temp = vectorValores[numRandom1];
                    vectorValores[numRandom1] = vectorValores[numRandom2];
                    vectorValores[numRandom2] = temp;
                }//desordena el vector

                break;
            case "3":
                setContentView(R.layout.activity_memotest3);
                intCantNivel=12;
                btn1 = (Button) findViewById(R.id.btn1);
                btn2 = (Button) findViewById(R.id.btn2);
                btn3 = (Button) findViewById(R.id.btn3);
                btn4 = (Button) findViewById(R.id.btn4);
                btn5 = (Button) findViewById(R.id.btn5);
                btn6 = (Button) findViewById(R.id.btn6);
                btn7 = (Button) findViewById(R.id.btn7);
                btn8 = (Button) findViewById(R.id.btn8);
                btn9 = (Button) findViewById(R.id.btn9);
                btn10 = (Button) findViewById(R.id.btn10);
                btn11 = (Button) findViewById(R.id.btn11);
                btn12 = (Button) findViewById(R.id.btn12);
                btn1.setOnClickListener(btnPresionado_Click);
                btn2.setOnClickListener(btnPresionado_Click);
                btn3.setOnClickListener(btnPresionado_Click);
                btn4.setOnClickListener(btnPresionado_Click);
                btn5.setOnClickListener(btnPresionado_Click);
                btn6.setOnClickListener(btnPresionado_Click);
                btn7.setOnClickListener(btnPresionado_Click);
                btn8.setOnClickListener(btnPresionado_Click);
                btn9.setOnClickListener(btnPresionado_Click);
                btn10.setOnClickListener(btnPresionado_Click);
                btn11.setOnClickListener(btnPresionado_Click);
                btn12.setOnClickListener(btnPresionado_Click);

                btn1.setBackgroundColor(Color.CYAN);
                btn2.setBackgroundColor(Color.CYAN);
                btn3.setBackgroundColor(Color.CYAN);
                btn4.setBackgroundColor(Color.CYAN);
                btn5.setBackgroundColor(Color.CYAN);
                btn6.setBackgroundColor(Color.CYAN);
                btn7.setBackgroundColor(Color.CYAN);
                btn8.setBackgroundColor(Color.CYAN);
                btn9.setBackgroundColor(Color.CYAN);
                btn10.setBackgroundColor(Color.CYAN);
                btn11.setBackgroundColor(Color.CYAN);
                btn12.setBackgroundColor(Color.CYAN);
                btn1.setText("¡Apretame!");
                btn2.setText("¡Apretame!");
                btn3.setText("¡Apretame!");
                btn4.setText("¡Apretame!");
                btn5.setText("¡Apretame!");
                btn6.setText("¡Apretame!");
                btn7.setText("¡Apretame!");
                btn8.setText("¡Apretame!");
                btn9.setText("¡Apretame!");
                btn10.setText("¡Apretame!");
                btn11.setText("¡Apretame!");
                btn12.setText("¡Apretame!");

                for (int i=0; i<100; i++)
                {
                    numRandom1 = generadorRandom.nextInt(12 - 0);
                    numRandom2 = generadorRandom.nextInt(12 - 0);
                    temp = vectorValores[numRandom1];
                    vectorValores[numRandom1] = vectorValores[numRandom2];
                    vectorValores[numRandom2] = temp;
                }//desordena el vector
                break;
            case "4":
                setContentView(R.layout.activity_memotest4);
                intCantNivel=14;
                btn1 = (Button) findViewById(R.id.btn1);
                btn2 = (Button) findViewById(R.id.btn2);
                btn3 = (Button) findViewById(R.id.btn3);
                btn4 = (Button) findViewById(R.id.btn4);
                btn5 = (Button) findViewById(R.id.btn5);
                btn6 = (Button) findViewById(R.id.btn6);
                btn7 = (Button) findViewById(R.id.btn7);
                btn8 = (Button) findViewById(R.id.btn8);
                btn9 = (Button) findViewById(R.id.btn9);
                btn10 = (Button) findViewById(R.id.btn10);
                btn11 = (Button) findViewById(R.id.btn11);
                btn12 = (Button) findViewById(R.id.btn12);
                btn13 = (Button) findViewById(R.id.btn13);
                btn14 = (Button) findViewById(R.id.btn14);
                btn1.setOnClickListener(btnPresionado_Click);
                btn2.setOnClickListener(btnPresionado_Click);
                btn3.setOnClickListener(btnPresionado_Click);
                btn4.setOnClickListener(btnPresionado_Click);
                btn5.setOnClickListener(btnPresionado_Click);
                btn6.setOnClickListener(btnPresionado_Click);
                btn7.setOnClickListener(btnPresionado_Click);
                btn8.setOnClickListener(btnPresionado_Click);
                btn9.setOnClickListener(btnPresionado_Click);
                btn10.setOnClickListener(btnPresionado_Click);
                btn11.setOnClickListener(btnPresionado_Click);
                btn12.setOnClickListener(btnPresionado_Click);
                btn13.setOnClickListener(btnPresionado_Click);
                btn14.setOnClickListener(btnPresionado_Click);

                btn1.setBackgroundColor(Color.CYAN);
                btn2.setBackgroundColor(Color.CYAN);
                btn3.setBackgroundColor(Color.CYAN);
                btn4.setBackgroundColor(Color.CYAN);
                btn5.setBackgroundColor(Color.CYAN);
                btn6.setBackgroundColor(Color.CYAN);
                btn7.setBackgroundColor(Color.CYAN);
                btn8.setBackgroundColor(Color.CYAN);
                btn9.setBackgroundColor(Color.CYAN);
                btn10.setBackgroundColor(Color.CYAN);
                btn11.setBackgroundColor(Color.CYAN);
                btn12.setBackgroundColor(Color.CYAN);
                btn13.setBackgroundColor(Color.CYAN);
                btn14.setBackgroundColor(Color.CYAN);
                btn1.setText("¡Apretame!");
                btn2.setText("¡Apretame!");
                btn3.setText("¡Apretame!");
                btn4.setText("¡Apretame!");
                btn5.setText("¡Apretame!");
                btn6.setText("¡Apretame!");
                btn7.setText("¡Apretame!");
                btn8.setText("¡Apretame!");
                btn9.setText("¡Apretame!");
                btn10.setText("¡Apretame!");
                btn11.setText("¡Apretame!");
                btn12.setText("¡Apretame!");
                btn13.setText("¡Apretame!");
                btn14.setText("¡Apretame!");

                for (int i=0; i<100; i++)
                {
                    numRandom1 = generadorRandom.nextInt(14 - 0);
                    numRandom2 = generadorRandom.nextInt(14 - 0);
                    temp = vectorValores[numRandom1];
                    vectorValores[numRandom1] = vectorValores[numRandom2];
                    vectorValores[numRandom2] = temp;
                }//desordena el vector
                break;
        }
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
                int numNivel = Integer.parseInt(nivel);
                numNivel++;
                nivel = Integer.toString(numNivel);
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
        builder.setMessage("Ganaste todos los niveles del Memotest de Sonidos" + "\n" + "Ganaste un trofeo de Memoria y otro de atención");
        HashMap<String, String> hash = new HashMap<String,String>();
        hash.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_NOTIFICATION));
        tts.speak("Muy bien" + nombre + ". Ganaste todos los niveles del Memotest de Sonidos. Ganaste un trofeo de Memoria y otro de atención", TextToSpeech.QUEUE_FLUSH, hash);
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
        registro.put("IdJuego", 1);
        registro.put("IdNivel", nivel);
        registro.put("fecha", formattedDate);
        registro.put("movimientos", contMovimientos);
        registro.put("tiempo", elapsedSeconds);
        bd.insert("Resultados", null, registro);

        ContentValues registro2 = new ContentValues();
        if (vecCants[0]==0 && vecCants[1]==0)
        {
            registro2.put("idJugador", MainActivity.idUsuario);
            registro2.put("memoria", 1);
            registro2.put("atencion", 1);
            registro2.put("baile", 1);
            registro2.put("musica", 1);
            bd.insert("Trofeos", null, registro);
        }
        else {
            registro2.put("memoria", vecCants[0]+1);
            registro2.put("atencion", vecCants[1]+1);
            bd.update("Trofeos", registro, "idJugador=?" + Integer.toString(MainActivity.idUsuario), null);

        }

        bd.close();
    }
    public void consultaTrofeos() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "database", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();
        /*Cursor curNombre = bd.rawQuery(  //devuelve 0 o 1 fila //es una consulta
                "select IdNivel, fecha, movimientos, tiempo from Resultados where IdJuego=2 and idJugador=0", null);*/
        String[] campos = new String[] {"memoria", "atencion", "baile", "musica"};
        String[] args = new String[] {Integer.toString(MainActivity.idUsuario)};
        Cursor curNombre = bd.query("Trofeos", campos, "idJugador=?", args, null, null, null);
        if (curNombre.moveToFirst()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)
            do
            {
                vecCants[0] = curNombre.getInt(0);
                vecCants[1] = curNombre.getInt(1);
                vecCants[2] = curNombre.getInt(2);
                vecCants[3] = curNombre.getInt(3);
            }while(curNombre.moveToNext());
        } else {
            vecCants[0] = 0;
            vecCants[1] = 0;
            vecCants[2] = 0;
            vecCants[3] = 0;
        }
        bd.close();

    }
    @Override
    public void onInit(int i) {

    }
}




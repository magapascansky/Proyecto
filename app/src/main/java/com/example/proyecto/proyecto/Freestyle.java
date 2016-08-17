package com.example.proyecto.proyecto;

import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;

public class Freestyle extends AppCompatActivity implements TextToSpeech.OnInitListener{

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
    Button btnSalir;

    String nombre;

    MediaPlayer dom;
    MediaPlayer re;
    MediaPlayer mi;
    MediaPlayer fa;
    MediaPlayer sol;
    MediaPlayer la;
    MediaPlayer si;
    MediaPlayer reb;
    MediaPlayer mib;
    MediaPlayer solb;
    MediaPlayer lab;
    MediaPlayer sib;

    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freestyle);

        tts = new TextToSpeech(Freestyle.this, Freestyle.this);
        tts.setLanguage(Locale.US);

        consulta();

        referencias();
        setListeners();

        dom = MediaPlayer.create(getApplicationContext(), R.raw.dom);
        re = MediaPlayer.create(getApplicationContext(), R.raw.re);
        mi = MediaPlayer.create(getApplicationContext(), R.raw.mi);
        fa = MediaPlayer.create(getApplicationContext(), R.raw.fa);
        sol = MediaPlayer.create(getApplicationContext(), R.raw.sol);
        la = MediaPlayer.create(getApplicationContext(), R.raw.la);
        si = MediaPlayer.create(getApplicationContext(), R.raw.si);
        reb = MediaPlayer.create(getApplicationContext(), R.raw.reb);
        mib = MediaPlayer.create(getApplicationContext(), R.raw.mib);
        solb = MediaPlayer.create(getApplicationContext(), R.raw.solb);
        lab = MediaPlayer.create(getApplicationContext(), R.raw.lab);
        sib = MediaPlayer.create(getApplicationContext(), R.raw.sib);


    }

    private View.OnClickListener btnPresionado1_Click = new View.OnClickListener(){
        public void onClick(View v){
            dom.start();
        }
    };
    private View.OnClickListener btnPresionado2_Click = new View.OnClickListener(){
        public void onClick(View v){
            re.start();
        }
    };
    private View.OnClickListener btnPresionado3_Click = new View.OnClickListener(){
        public void onClick(View v){
            mi.start();
        }
    };
    private View.OnClickListener btnPresionado4_Click = new View.OnClickListener(){
        public void onClick(View v){
            fa.start();
        }
    };
    private View.OnClickListener btnPresionado5_Click = new View.OnClickListener(){
        public void onClick(View v){
            sol.start();
        }
    };
    private View.OnClickListener btnPresionado6_Click = new View.OnClickListener(){
        public void onClick(View v){
            la.start();
        }
    };
    private View.OnClickListener btnPresionado7_Click = new View.OnClickListener(){
        public void onClick(View v){
            si.start();
        }
    };
    private View.OnClickListener btnPresionado8_Click = new View.OnClickListener(){
        public void onClick(View v){
            reb.start();
        }
    };
    private View.OnClickListener btnPresionado9_Click = new View.OnClickListener(){
        public void onClick(View v){
            mib.start();
        }
    };
    private View.OnClickListener btnPresionado10_Click = new View.OnClickListener(){
        public void onClick(View v){
            solb.start();
        }
    };
    private View.OnClickListener btnPresionado11_Click = new View.OnClickListener(){
        public void onClick(View v){
            lab.start();
        }
    };
    private View.OnClickListener btnPresionado12_Click = new View.OnClickListener(){
        public void onClick(View v){
            sib.start();
        }
    };
    private View.OnClickListener btnSalir_Click = new View.OnClickListener(){
        public void onClick(View v){
            crearDialogoConfirmacionFinal().show();
        }
    };

    public void setListeners(){
        btn1.setOnClickListener(btnPresionado1_Click);
        btn2.setOnClickListener(btnPresionado2_Click);
        btn3.setOnClickListener(btnPresionado3_Click);
        btn4.setOnClickListener(btnPresionado4_Click);
        btn5.setOnClickListener(btnPresionado5_Click);
        btn6.setOnClickListener(btnPresionado6_Click);
        btn7.setOnClickListener(btnPresionado7_Click);
        btn8.setOnClickListener(btnPresionado8_Click);
        btn9.setOnClickListener(btnPresionado9_Click);
        btn10.setOnClickListener(btnPresionado10_Click);
        btn11.setOnClickListener(btnPresionado11_Click);
        btn12.setOnClickListener(btnPresionado12_Click);
        btnSalir.setOnClickListener(btnSalir_Click);
    }

    public void referencias(){
        btn1  = (ImageButton) findViewById(R.id.btn1);
        btn2  = (ImageButton) findViewById(R.id.btn2);
        btn3  = (ImageButton) findViewById(R.id.btn3);
        btn4  = (ImageButton) findViewById(R.id.btn4);
        btn5  = (ImageButton) findViewById(R.id.btn5);
        btn6  = (ImageButton) findViewById(R.id.btn6);
        btn7  = (ImageButton) findViewById(R.id.btn7);
        btn8  = (ImageButton) findViewById(R.id.btn8);
        btn9  = (ImageButton) findViewById(R.id.btn9);
        btn10  = (ImageButton) findViewById(R.id.btn10);
        btn11  = (ImageButton) findViewById(R.id.btn11);
        btn12  = (ImageButton) findViewById(R.id.btn12);
        btnSalir = (Button) findViewById(R.id.btnSalir);
    }

   public Dialog crearDialogoConfirmacionFinal() {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("¡Muy Bien " + nombre +"!");
        builder.setMessage("Ya hiciste tu propia canción" + "\n" + "Ganaste un trofeo de Música");
        HashMap<String, String> hash = new HashMap<String,String>();
        hash.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_NOTIFICATION));
        tts.speak("Muy bien" + nombre + ". Ya hiciste tu propia canción. Ganaste un trofeo de Música", TextToSpeech.QUEUE_FLUSH, hash);
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
    @Override
    public void onInit(int i) {

    }
}

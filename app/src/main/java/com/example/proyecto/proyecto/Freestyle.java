package com.example.proyecto.proyecto;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Freestyle extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freestyle);

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
    }
}

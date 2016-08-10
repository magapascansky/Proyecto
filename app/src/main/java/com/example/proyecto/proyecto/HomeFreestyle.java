package com.example.proyecto.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeFreestyle extends AppCompatActivity {

    Button btnJugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_freestyle);

        btnJugar = (Button) findViewById(R.id.btnJugar);
        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarActivity();
            }
        });
    }

    public void iniciarActivity(){
        Intent nuevaActivity = new Intent(this, Freestyle.class);
        startActivity(nuevaActivity);
    }
}

package com.example.proyecto.proyecto;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SalaDeTrofeos extends AppCompatActivity {

    TextView tvwMemoria;
    TextView tvwAtencion;
    TextView tvwMusica;
    TextView tvwBaile;
    int[] vecCants = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sala_de_trofeos);
        referencias();
        consulta();
        tvwMemoria.setText(Integer.toString(vecCants[0]));
        tvwAtencion.setText(Integer.toString(vecCants[1]));
        tvwBaile.setText(Integer.toString(vecCants[2]));
        tvwMusica.setText(Integer.toString(vecCants[3]));

    }

    public void referencias(){
        tvwAtencion = (TextView) findViewById(R.id.tvwAtencion);
        tvwBaile = (TextView) findViewById(R.id.tvwBaile);
        tvwMemoria = (TextView) findViewById(R.id.tvwMemoria);
        tvwMusica = (TextView) findViewById(R.id.tvwMusica);
    }

    public void consulta() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "database", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
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
}

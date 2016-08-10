package com.example.proyecto.proyecto;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ResultadosMemotest extends AppCompatActivity {

    ArrayList<String> listaResultados = new ArrayList<String>();
    int idNivel;
    String fecha;
    int movimientos;
    double tiempo;

    ListView lstResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_memotest);
        referecias();
        consulta();

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaResultados);
        lstResultados.setAdapter(adaptador);

    }

    public void referecias(){
        lstResultados = (ListView) findViewById(R.id.lstResultados);
    }

    public void consulta() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "database", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        /*Cursor curNombre = bd.rawQuery(  //devuelve 0 o 1 fila //es una consulta
                "select IdNivel, fecha, movimientos, tiempo from Resultados where IdJuego=2 and idJugador=0", null);*/
        String[] campos = new String[] {"IdNivel", "fecha", "movimientos", "tiempo"};

        String[] args = new String[] {"1", Integer.toString(MainActivity.idUsuario)};

        Cursor curNombre = bd.query("Resultados", campos, "IdJuego=? and idJugador=?", args, null, null, null);
        if (curNombre.moveToFirst()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)
            do
            {
                idNivel = curNombre.getInt(0);
                fecha = curNombre.getString(1);
                movimientos = curNombre.getInt(2);
                tiempo = curNombre.getDouble(3);
                Resultado unResultado = new Resultado();
                unResultado.IdNivel = idNivel;
                unResultado.fecha = fecha;
                unResultado.movimientos = movimientos;
                unResultado.tiempo = tiempo;
                listaResultados.add("           " + unResultado.fecha.toString() + "                               " + Integer.toString(unResultado.IdNivel) + "                                                                        " + Integer.toString(unResultado.movimientos) + "                                                                            " + Double.toString(unResultado.tiempo));
            }while(curNombre.moveToNext());


        } else {
            idNivel = -1;
            fecha = "-";
            movimientos = -1;
            tiempo = -1;
        }

        bd.close();

    }
}

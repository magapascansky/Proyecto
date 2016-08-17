package com.example.proyecto.proyecto;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.ListViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    ArrayList<String> listaUsuarios = new ArrayList<String>();
    EditText edtName;
    Button btnStart;
    String nombre;
    ListView lstUsuarios;
    final public static String PARAMETRO = "com.proyecto.proyecto.PARAMETRO";
    public static int idUsuario = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setBackgroundDrawableResource(R.drawable.backgroundinicio);
        References();
        TraerUsuarios();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaUsuarios);
        lstUsuarios.setAdapter(adaptador);
        lstUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                idUsuario = i+1;
                edtName.setText(listaUsuarios.get(i));
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtName.getText().toString().trim().length() != 0) {
                    nombre = edtName.getText().toString();
                    if (listaUsuarios.contains(nombre)){
                        TraerUnUsuario(idUsuario);
                        iniciarActividad();
                    }
                    else{
                        crearDialogoConfirmacion().show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "¡No te olvides de poner tu nombre!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public Dialog crearDialogoConfirmacion() {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Nuevo Jugador");
        builder.setMessage("¿Estás seguro de que querés crear un nuevo jugador?");
        builder.setPositiveButton("¡Si!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Log.i("Diálogos", "Confirmación Aceptada.");
                alta();
                iniciarActividad();
                dialog.cancel();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                Log.i("Diálogos", "Confirmación Cancelada.");
                finish();
                dialog.cancel();
            }

        });

        return builder.create();

    }

    public void References() {
        edtName = (EditText) findViewById(R.id.edtName);
        btnStart = (Button) findViewById(R.id.btnStart);
        lstUsuarios = (ListView) findViewById(R.id.lstUsuarios);
    }

    public void iniciarActividad()
    {
        Intent actividadHome = new Intent(this, HomeJuegos.class);
        startActivity(actividadHome);
    }

    public void alta() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"database", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombre = edtName.getText().toString();
        ContentValues registro = new ContentValues();  //es una clase para guardar datos
        registro.put("nombre", nombre);
        bd.insert("Jugadores", null, registro);
        bd.close();
    }
    public void TraerUsuarios() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "database", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor curNombre = bd.rawQuery(  //devuelve 0 o 1 fila //es una consulta
                "select nombre from Jugadores", null);
        if (curNombre.moveToFirst()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)

            do {
                nombre = curNombre.getString(0);
                listaUsuarios.add(nombre);
            }while(curNombre.moveToNext());

        } else{
            nombre = "";
        }

        bd.close();

    }
    public void TraerUnUsuario(int idUsuario) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "database", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor curNombre = bd.rawQuery(  //devuelve 0 o 1 fila //es una consulta
                "select nombre from Jugadores where ID=" + idUsuario, null);
        if (curNombre.moveToFirst()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)
                nombre = curNombre.getString(0);

        } else{
            nombre = "";
        }

        bd.close();

    }


}

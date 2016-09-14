package com.example.proyecto.proyecto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 42103267 on 15/6/2016.
 */
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Jugadores(ID INTEGER PRIMARY KEY AUTOINCREMENT, nombre text)");
        db.execSQL("create table Resultados(ID INTEGER PRIMARY KEY AUTOINCREMENT, idJugador integer, IdJuego integer, IdNivel integer, fecha text, movimientos integer, tiempo double)");
        db.execSQL("create table Trofeos(ID INTEGER PRIMARY KEY AUTOINCREMENT, idJugador integer, memoria integer, atencion integer, baile integer, musica integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists Jugadores");
        db.execSQL("create table Jugadores(ID INTEGER PRIMARY KEY AUTOINCREMENT, nombre text)");

        db.execSQL("drop table if exists Resultados");
        db.execSQL("create table Resultados(ID INTEGER PRIMARY KEY AUTOINCREMENT, idJugador integer, IdJuego integer, IdNivel integer, fecha text, movimientos integer, tiempo double)");

        db.execSQL("drop table if exists Trofeos");
        db.execSQL("create table Trofeos(ID INTEGER PRIMARY KEY AUTOINCREMENT, idJugador integer, memoria integer, atencion integer, baile integer, musica integer)");
    }
}

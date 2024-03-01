package com.example.appsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbUsers extends SQLiteOpenHelper {
    // Definir la o las tablas  que intervienen en sqlite

    // primero el nombre del campo , luego el tipo de dato
    // y si quiero que tenga  llave
    // si hay un campo numerico integre o boolean en caso tal

    // se crea la tabla con su nombre , crear tabla nombre de esta
    String  tblUser = "Create Table user(text user primary key," +
            "fullname text, email text, password text, resservedword text)";

    String tblProfile ="Create Table profile (idProfile text, descrip text)";
    public dbUsers( Context context,  String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    //                 tipo de variable : db es la variable de sqlitedatabase
    //                                    se le puede cambiar el nombre, pero es mejor dejarlo asi
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tblUser);
        db.execSQL(tblProfile);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //sirve para mantener actualizada la base de datos el upgrade

        //borre la tabla  y nombre de la tabla
        db.execSQL("Drop Table user");
        db.execSQL(tblUser);
        db.execSQL("Drop Table profile");
        db.execSQL(tblProfile);
    }
}

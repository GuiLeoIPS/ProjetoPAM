package pt.ips.pam.projetopam;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "biblioteca";

    private static final String TABLE_UTILIZADOR = "contas";
    // User Table Columns names
    private static final String COLUMN_IDUSER = "id";
    private static final String COLUMN_NOME = "nome";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_NUMERO = "numero";
    private static final String COLUMN_ADMIN = "admin";

    private static final String TABLE_FILME = "livros";
    // User Table Columns names
    private static final String COLUMN_IDFILME= "idFilme";
    private static final String COLUMN_titulo = "titulo";
    private static final String COLUMN_descricao = "realizador";
    private static final String COLUMN_paginas = "tipoFilme";


    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

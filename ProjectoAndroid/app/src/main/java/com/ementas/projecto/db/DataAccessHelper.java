package com.ementas.projecto.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataAccessHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "mobilecanteen";
    private static final String USER_TABLE_NAME = "user";
    private static final String EMENTA_TABLE_NAME = "ementa";
    private static final String FATURA_TABLE_NAME = "fatura";

    public DataAccessHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + USER_TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY, " +
                "username TEXT," +
                "auth_key TEXT," +
                "password_hash TEXT," +
                "email TEXT," +
                "status TEXT, " +
                "divida FLOAT " +
                ")";

        String createEmentaTable = "CREATE TABLE " + EMENTA_TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "diadasemana TEXT, " +
                "data TEXT, " +
                "refeicao TEXT, " +
                "sopa TEXT, " +
                "carne TEXT, " +
                "peixe TEXT, " +
                "vegetariano TEXT, " +
                "sombremesa TEXT, " +
                "haementa TEXT, " +
                ")";

        String createFaturaTable = "CREATE TABLE " + FATURA_TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "data TEXT, " +
                "preco FLOAT, " +
                "cantina TEXT, " +
                "refeicao TEXT, " +
                "prato TEXT, " +
                "id_user TEXT " +
                ")";


        db.execSQL(createUserTable);
        db.execSQL(createEmentaTable);
        db.execSQL(createFaturaTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EMENTA_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FATURA_TABLE_NAME);
        onCreate(db);
    }
}

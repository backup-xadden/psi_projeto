package com.ementas.projecto.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xadden on 6/12/2016.
 */

public class DataAccessHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "mobilecanteen";
    private static final String ESTUDANTE_TABLE_NAME = "estudante";
    private static final String EMENTA_TABLE_NAME = "ementa";
    private static final String FATURA_TABLE_NAME = "fatura";

    public DataAccessHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createEstudanteTable = "CREATE TABLE " + ESTUDANTE_TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY, " +
                "nome TEXT," +
                "password TEXT," +
                "email TEXT," +
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
                "sombremesa TEXT " +
                ")";

        String createFaturaTable = "CREATE TABLE " + FATURA_TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "data TEXT, " +
                "preco FLOAT, " +
                "cantina TEXT, " +
                "refeicao TEXT, " +
                "prato TEXT, " +
                "idestudante TEXT " +
                ")";


        db.execSQL(createEstudanteTable);
        db.execSQL(createEmentaTable);
        db.execSQL(createFaturaTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + ESTUDANTE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EMENTA_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FATURA_TABLE_NAME);
        onCreate(db);
    }
}

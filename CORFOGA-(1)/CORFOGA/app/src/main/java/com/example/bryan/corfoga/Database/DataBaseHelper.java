package com.example.bryan.corfoga.Database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    // debemos incrementar la version de la base de datos
    public static final int DATABASE_VERSION = 2;
    // Nombre de la base de datos
    public static final String DATABASE_NAME = "AndroidStorage.db";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public Boolean deleteDB(Context context) {
        boolean bool = context.deleteDatabase(DATABASE_NAME);
        return bool; // true if deleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la base de datos de la app
        db.execSQL(DataBaseContract.SQL_CREATE_ANIMAL_VISIT);
        db.execSQL(DataBaseContract.SQL_CREATE_USER);
        db.execSQL(DataBaseContract.SQL_CREATE_FARM);
        db.execSQL(DataBaseContract.SQL_CREATE_ANIMAL);
        db.execSQL(DataBaseContract.SQL_CREATE_INSPECTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DataBaseContract.SQL_DELETE_INSPECTION);
        db.execSQL(DataBaseContract.SQL_DELETE_ANIMAL);
        db.execSQL(DataBaseContract.SQL_DELETE_FARM);
        db.execSQL(DataBaseContract.SQL_DELETE_USER);
        db.execSQL(DataBaseContract.SQL_DELETE_ANIMAL_VISIT);
    }
}

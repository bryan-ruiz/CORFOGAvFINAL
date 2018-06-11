package com.example.bryan.corfoga.Class;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bryan.corfoga.Database.DataBaseHelper;
import com.example.bryan.corfoga.Database.DataBaseContract;
import java.util.ArrayList;

public class Region {
    private int id;
    private String name;
    private ArrayList<Farm> farmsList;

    public Region(int id, String name) {
        this.id = id;
        this.name = name;
        this.farmsList = new ArrayList<Farm>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Farm> getFarmsList() {
        return farmsList;
    }

    public void setFarmsList(ArrayList<Farm> farmsList) {
        this.farmsList = farmsList;
    }

    public long addFarmDB(Context context, Farm farm) {
        // usar la clase DataBaseHelper para realizar la operacion de insertar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        // Crear un mapa de valores donde las columnas son las llaves
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_ASOCEBU_ID, farm.getAsocebuID());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_USER_ID, farm.getUserID());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_NAME, farm.getName());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_STATE, farm.getState());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_REGION, this.getId());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_CREATED_AT, farm.getCreated_at());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_UPDATED_AT, farm.getUpdated_at());
        // Insertar la nueva fila
        return db.insert(DataBaseContract.DataBaseEntry.TABLE_NAME_FARM, null, values);
    }

    public ArrayList<Farm> getFarmListDB (Context context){
        this.farmsList = new ArrayList<Farm>();
        Farm farm;
        String farmName, state, created, updated;
        int asocebuId, userId, region;
        // usar la clase DataBaseHelper para realizar la operacion de leer
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo lectura
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        // Define cuales columnas quiere solicitar // en este caso todas las de la clase
        String[] projection = {
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_ASOCEBU_ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_USER_ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_NAME,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_STATE,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_REGION,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_CREATED_AT,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_UPDATED_AT
        };
        // Resultados en el cursor
        Cursor cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_FARM, // tabla
                projection, // columnas
                null, // where
                null, // valores del where
                null, // agrupamiento
                null, // filtros por grupo
                null // orden
        );
        cursor.moveToFirst();
        do {

            region = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_REGION));
            Log.d("region-------------", String.valueOf(region));
            Log.d("comparacion---------", String.valueOf(this.getId()));
            if (region == this.getId()) {
                asocebuId = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_ASOCEBU_ID));
                userId = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_USER_ID));
                farmName = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_NAME));
                state = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_STATE));
                created = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_CREATED_AT));
                updated = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_UPDATED_AT));
                farm = new Farm(asocebuId, userId, farmName, state, created, updated);
                this.farmsList.add(farm);
            }
        }
        while (cursor.moveToNext());
        return this.farmsList;

    }


    public ArrayList<Farm> getWholeFarmListDB (Context context){
        this.farmsList = new ArrayList<Farm>();
        Farm farm;
        String farmName, region, state, created, updated;
        int asocebuId, userId;
        // usar la clase DataBaseHelper para realizar la operacion de leer
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo lectura
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        // Define cuales columnas quiere solicitar // en este caso todas las de la clase
        String[] projection = {
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_ASOCEBU_ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_USER_ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_NAME,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_STATE,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_REGION,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_CREATED_AT,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_UPDATED_AT
        };
        // Resultados en el cursor
        Cursor cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_FARM, // tabla
                projection, // columnas
                null, // where
                null, // valores del where
                null, // agrupamiento
                null, // filtros por grupo
                null // orden
        );
        cursor.moveToFirst();
        do
        {
            asocebuId = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_ASOCEBU_ID));
            userId = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_USER_ID));
            farmName = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_NAME));
            state = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_STATE));
            created = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_CREATED_AT));
            updated = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_FARM_UPDATED_AT));
            farm = new Farm(asocebuId, userId, farmName, state, created, updated);
            this.farmsList.add(farm);
        }
        while (cursor.moveToNext());
        return this.farmsList;
    }


}
